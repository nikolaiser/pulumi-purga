// *** WARNING: this file was generated by pulumi. ***
// *** Do not edit by hand unless you're certain you know what you are doing! ***

using System;
using System.Collections.Generic;
using System.Collections.Immutable;
using System.Threading.Tasks;
using Pulumi.Serialization;

namespace Pulumi.Purga
{
    [PurgaResourceType("purga:index:PurgaDeployment")]
    public partial class PurgaDeployment : global::Pulumi.CustomResource
    {
        [Output("configArrayString")]
        public Output<ImmutableDictionary<string, ImmutableArray<string>>> ConfigArrayString { get; private set; } = null!;

        [Output("configString")]
        public Output<ImmutableDictionary<string, string>> ConfigString { get; private set; } = null!;

        [Output("flake")]
        public Output<string> Flake { get; private set; } = null!;

        [Output("flakeInput")]
        public Output<string> FlakeInput { get; private set; } = null!;

        [Output("flakeRevision")]
        public Output<string> FlakeRevision { get; private set; } = null!;

        [Output("host")]
        public Output<string> Host { get; private set; } = null!;


        /// <summary>
        /// Create a PurgaDeployment resource with the given unique name, arguments, and options.
        /// </summary>
        ///
        /// <param name="name">The unique name of the resource</param>
        /// <param name="args">The arguments used to populate this resource's properties</param>
        /// <param name="options">A bag of options that control this resource's behavior</param>
        public PurgaDeployment(string name, PurgaDeploymentArgs args, CustomResourceOptions? options = null)
            : base("purga:index:PurgaDeployment", name, args ?? new PurgaDeploymentArgs(), MakeResourceOptions(options, ""))
        {
        }

        private PurgaDeployment(string name, Input<string> id, CustomResourceOptions? options = null)
            : base("purga:index:PurgaDeployment", name, null, MakeResourceOptions(options, id))
        {
        }

        private static CustomResourceOptions MakeResourceOptions(CustomResourceOptions? options, Input<string>? id)
        {
            var defaultOptions = new CustomResourceOptions
            {
                Version = Utilities.Version,
            };
            var merged = CustomResourceOptions.Merge(defaultOptions, options);
            // Override the ID if one was specified for consistency with other language SDKs.
            merged.Id = id ?? merged.Id;
            return merged;
        }
        /// <summary>
        /// Get an existing PurgaDeployment resource's state with the given name, ID, and optional extra
        /// properties used to qualify the lookup.
        /// </summary>
        ///
        /// <param name="name">The unique name of the resulting resource.</param>
        /// <param name="id">The unique provider ID of the resource to lookup.</param>
        /// <param name="options">A bag of options that control this resource's behavior</param>
        public static PurgaDeployment Get(string name, Input<string> id, CustomResourceOptions? options = null)
        {
            return new PurgaDeployment(name, id, options);
        }
    }

    public sealed class PurgaDeploymentArgs : global::Pulumi.ResourceArgs
    {
        [Input("configArrayString", required: true)]
        private InputMap<ImmutableArray<string>>? _configArrayString;
        public InputMap<ImmutableArray<string>> ConfigArrayString
        {
            get => _configArrayString ?? (_configArrayString = new InputMap<ImmutableArray<string>>());
            set => _configArrayString = value;
        }

        [Input("configString", required: true)]
        private InputMap<string>? _configString;
        public InputMap<string> ConfigString
        {
            get => _configString ?? (_configString = new InputMap<string>());
            set => _configString = value;
        }

        [Input("flake", required: true)]
        public Input<string> Flake { get; set; } = null!;

        [Input("flakeInput", required: true)]
        public Input<string> FlakeInput { get; set; } = null!;

        [Input("host", required: true)]
        public Input<string> Host { get; set; } = null!;

        public PurgaDeploymentArgs()
        {
        }
        public static new PurgaDeploymentArgs Empty => new PurgaDeploymentArgs();
    }
}
