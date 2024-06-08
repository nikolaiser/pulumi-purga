{
  description = "Purga devshell";

  inputs = {
    nixpkgs.url = "github:nixos/nixpkgs?ref=nixos-unstable";
    flake-utils.url = "github:numtide/flake-utils";
  };

  outputs = inputs:
    inputs.flake-utils.lib.eachDefaultSystem
      (system:
        let
          pkgs = import inputs.nixpkgs {
            inherit system;
          };

          attrsetValues = pkgs.lib.attrsets.mapAttrsToList (_: value: value);
        in
        {
          devShells.default = pkgs.mkShell {
            buildInputs = with pkgs; [
              go
              nodejs_20
              yarn
              typescript
              python3
              dotnet-sdk
              gnumake
              pulumictl
              (pulumi.withPackages (pulumiPackages: with pulumiPackages; [
                pulumi-language-python
                pulumi-language-nodejs
                pulumi-language-go
                pulumi-command
              ]))
            ];
          };
        }

      );
}
