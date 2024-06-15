// *** WARNING: this file was generated by pulumi-language-nodejs. ***
// *** Do not edit by hand unless you're certain you know what you are doing! ***

import * as pulumi from "@pulumi/pulumi";
import * as utilities from "./utilities";

export class PurgaDeployment extends pulumi.CustomResource {
    /**
     * Get an existing PurgaDeployment resource's state with the given name, ID, and optional extra
     * properties used to qualify the lookup.
     *
     * @param name The _unique_ name of the resulting resource.
     * @param id The _unique_ provider ID of the resource to lookup.
     * @param opts Optional settings to control the behavior of the CustomResource.
     */
    public static get(name: string, id: pulumi.Input<pulumi.ID>, opts?: pulumi.CustomResourceOptions): PurgaDeployment {
        return new PurgaDeployment(name, undefined as any, { ...opts, id: id });
    }

    /** @internal */
    public static readonly __pulumiType = 'purga:index:PurgaDeployment';

    /**
     * Returns true if the given object is an instance of PurgaDeployment.  This is designed to work even
     * when multiple copies of the Pulumi SDK have been loaded into the same process.
     */
    public static isInstance(obj: any): obj is PurgaDeployment {
        if (obj === undefined || obj === null) {
            return false;
        }
        return obj['__pulumiType'] === PurgaDeployment.__pulumiType;
    }

    public readonly configArrayString!: pulumi.Output<{[key: string]: string[]}>;
    public readonly configString!: pulumi.Output<{[key: string]: string}>;
    public readonly flake!: pulumi.Output<string>;
    public readonly flakeInput!: pulumi.Output<string>;
    public /*out*/ readonly flakeRevision!: pulumi.Output<string>;
    public readonly host!: pulumi.Output<string>;

    /**
     * Create a PurgaDeployment resource with the given unique name, arguments, and options.
     *
     * @param name The _unique_ name of the resource.
     * @param args The arguments to use to populate this resource's properties.
     * @param opts A bag of options that control this resource's behavior.
     */
    constructor(name: string, args: PurgaDeploymentArgs, opts?: pulumi.CustomResourceOptions) {
        let resourceInputs: pulumi.Inputs = {};
        opts = opts || {};
        if (!opts.id) {
            if ((!args || args.configArrayString === undefined) && !opts.urn) {
                throw new Error("Missing required property 'configArrayString'");
            }
            if ((!args || args.configString === undefined) && !opts.urn) {
                throw new Error("Missing required property 'configString'");
            }
            if ((!args || args.flake === undefined) && !opts.urn) {
                throw new Error("Missing required property 'flake'");
            }
            if ((!args || args.flakeInput === undefined) && !opts.urn) {
                throw new Error("Missing required property 'flakeInput'");
            }
            if ((!args || args.host === undefined) && !opts.urn) {
                throw new Error("Missing required property 'host'");
            }
            resourceInputs["configArrayString"] = args ? args.configArrayString : undefined;
            resourceInputs["configString"] = args ? args.configString : undefined;
            resourceInputs["flake"] = args ? args.flake : undefined;
            resourceInputs["flakeInput"] = args ? args.flakeInput : undefined;
            resourceInputs["host"] = args ? args.host : undefined;
            resourceInputs["flakeRevision"] = undefined /*out*/;
        } else {
            resourceInputs["configArrayString"] = undefined /*out*/;
            resourceInputs["configString"] = undefined /*out*/;
            resourceInputs["flake"] = undefined /*out*/;
            resourceInputs["flakeInput"] = undefined /*out*/;
            resourceInputs["flakeRevision"] = undefined /*out*/;
            resourceInputs["host"] = undefined /*out*/;
        }
        opts = pulumi.mergeOptions(utilities.resourceOptsDefaults(), opts);
        super(PurgaDeployment.__pulumiType, name, resourceInputs, opts);
    }
}

/**
 * The set of arguments for constructing a PurgaDeployment resource.
 */
export interface PurgaDeploymentArgs {
    configArrayString: pulumi.Input<{[key: string]: pulumi.Input<pulumi.Input<string>[]>}>;
    configString: pulumi.Input<{[key: string]: pulumi.Input<string>}>;
    flake: pulumi.Input<string>;
    flakeInput: pulumi.Input<string>;
    host: pulumi.Input<string>;
}
