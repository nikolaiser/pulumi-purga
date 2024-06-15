// Copyright 2016-2023, Pulumi Corporation.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package provider

import (
	"encoding/json"
	"fmt"
	"os"
	"os/exec"

	p "github.com/pulumi/pulumi-go-provider"
	"github.com/pulumi/pulumi-go-provider/infer"
	"github.com/pulumi/pulumi/sdk/v3/go/common/resource"
	"github.com/pulumi/pulumi/sdk/v3/go/common/tokens"
)

// Version is initialized by the Go linker to contain the semver of this build.
var Version string

const Name string = "purga"

func Provider() p.Provider {
	// We tell the provider what resources it needs to support.
	// In this case, a single custom resource.
	return infer.Provider(infer.Options{
		Resources: []infer.InferredResource{
			infer.Resource[PurgaDeployment, PurgaDeploymentArgs, PurgaDeploymentState](),
		},
		ModuleMap: map[tokens.ModuleName]tokens.ModuleName{
			"provider": "index",
		},
	})
}

// Each resource has a controlling struct.
// Resource behavior is determined by implementing methods on the controlling struct.
// The `Create` method is mandatory, but other methods are optional.
// - Check: Remap inputs before they are typed.
// - Diff: Change how instances of a resource are compared.
// - Update: Mutate a resource in place.
// - Read: Get the state of a resource from the backing provider.
// - Delete: Custom logic when the resource is deleted.
// - Annotate: Describe fields and set defaults for a resource.
// - WireDependencies: Control how outputs and secrets flows through values.

type PurgaDeployment struct{}

type PurgaDeploymentArgs struct {
	Flake             string              `pulumi:"flake"`
	FlakeInput        string              `pulumi:"flakeInput"`
	ConfigString      map[string]string   `pulumi:"configString"`
	ConfigArrayString map[string][]string `pulumi:"configArrayString"`
	Host              string              `pulumi:"host"`
}

type PurgaDeploymentState struct {
	PurgaDeploymentArgs
	FlakeRevision string `pulumi:"flakeRevision"`
}

func getRevision(ctx p.Context, args PurgaDeploymentArgs) (string, error) {
	flakeRevisionCmd := exec.CommandContext(ctx, "/bin/sh", "-c", "nix flake metadata %s --no-write-lock-file --json | jq '.revision'", args.Flake)
	revisionBytes, err := flakeRevisionCmd.Output()
	return string(revisionBytes), err
}

func deploy(ctx p.Context, args PurgaDeploymentArgs) error {
	configMap := make(map[string]interface{})

	for key, value := range args.ConfigArrayString {
		configMap[key] = value
	}

	for key, value := range args.ConfigString {
		configMap[key] = value
	}

	configJsonBytes, err := json.Marshal(configMap)
	if err != nil {
		return err
	}

	configJson := string(configJsonBytes)

	deployCommand := fmt.Sprint("f=$(mktemp); echo '", configJson, "' > $f ; nixos-rebuild switch < /dev/null --use-remote-sudo --target-host ", args.Host, " --show-trace --flake \"", args.Flake, "\";rm-rf $f")

	deployCmd := exec.CommandContext(ctx, "/bin/sh", "-c", deployCommand)
	deployCmd.Env = os.Environ()
	deployCmd.Env = append(deployCmd.Env, "NIX_SSHOPTS=\"-t -o StrictHostKeyChecking=no -o UserKnownHostsFile=/dev/null\"")

	_, err = deployCmd.Output()
	return err
}

func (PurgaDeployment) Create(ctx p.Context, name string, args PurgaDeploymentArgs, preview bool) (string, PurgaDeploymentState, error) {
	state := PurgaDeploymentState{PurgaDeploymentArgs: args}

	id, err := resource.NewUniqueHex(name, 8, 0)
	if err != nil {
		return id, state, err
	}

	revision, err := getRevision(ctx, args)
	if err != nil {
		return id, state, err
	}

	state.FlakeRevision = revision

	if preview {
		return id, state, nil
	}

	err = deploy(ctx, args)

	return id, state, err
}

func compareStringMaps(map1, map2 map[string]string) bool {
	if len(map1) != len(map2) {
		return false
	}

	for key, value := range map1 {
		if map2Value, ok := map2[key]; !ok || map2Value != value {
			return false
		}
	}

	return true
}

func compareSlices(a, b []string) bool {
	if len(a) != len(b) {
		return false
	}
	for i := range a {
		if a[i] != b[i] {
			return false
		}
	}
	return true
}

func compareStringArrayMaps(map1, map2 map[string][]string) bool {
	if len(map1) != len(map2) {
		return false
	}

	for k, v1 := range map1 {
		v2, ok := map2[k]
		if !ok {
			return false
		}
		if !compareSlices(v1, v2) {
			return false
		}
	}

	return true
}

func (PurgaDeployment) Diff(ctx p.Context, id string, oldState PurgaDeploymentState, newArgs PurgaDeploymentArgs) (p.DiffResponse, error) {
	response := p.DiffResponse{DeleteBeforeReplace: false}

	flakeChanged := newArgs.Flake != oldState.Flake
	flakeInputChanged := newArgs.FlakeInput != oldState.FlakeInput
	hostChanged := newArgs.Host != oldState.Host
	configStringChanged := compareStringMaps(newArgs.ConfigString, oldState.ConfigString)
	configArrayStringChanged := compareStringArrayMaps(newArgs.ConfigArrayString, oldState.ConfigArrayString)

	if flakeChanged || flakeInputChanged || hostChanged || configStringChanged || configArrayStringChanged {
		response.HasChanges = true
		return response, nil
	}

	revision, err := getRevision(ctx, newArgs)
	if err != nil {
		return response, err
	}

	revisionChanged := revision != oldState.FlakeRevision

	if revisionChanged {
		response.HasChanges = true
	} else {
		response.HasChanges = false
	}

	return response, nil
}

func (PurgaDeployment) Update(ctx p.Context, id string, oldState PurgaDeploymentState, newArgs PurgaDeploymentArgs, preview bool) (PurgaDeploymentState, error) {
	state := PurgaDeploymentState{PurgaDeploymentArgs: newArgs}

	revision, err := getRevision(ctx, newArgs)
	if err != nil {
		return state, err
	}

	state.FlakeRevision = revision

	if preview {
		return state, nil
	}

	err = deploy(ctx, newArgs)

	return state, err
}
