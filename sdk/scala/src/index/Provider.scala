package besom.api.purga

final case class Provider private(
  urn: besom.types.Output[besom.types.URN],
  id: besom.types.Output[besom.types.ResourceId]
) extends besom.ProviderResource

object Provider extends besom.ResourceCompanion[Provider]:
  /** Resource constructor for Provider. 
    * 
    * @param name [[besom.util.NonEmptyString]] The unique (stack-wise) name of the resource in Pulumi state (not on provider's side).
    *        NonEmptyString is inferred automatically from non-empty string literals, even when interpolated. If you encounter any
    *        issues with this, please try using `: NonEmptyString` type annotation. If you need to convert a dynamically generated
    *        string to NonEmptyString, use `NonEmptyString.apply` method - `NonEmptyString(str): Option[NonEmptyString]`.
    *
    * @param args [[ProviderArgs]] The configuration to use to create this resource. This resource has a default configuration.
    *
    * @param opts [[besom.CustomResourceOptions]] Resource options to use for this resource. 
    *        Defaults to empty options. If you need to set some options, use [[besom.opts]] function to create them, for example:
    *  
    *        {{{
    *        val res = Provider(
    *          "my-resource",
    *          ProviderArgs(...), // your args
    *          opts(provider = myProvider)
    *        )
    *        }}}
    */
  def apply(using ctx: besom.types.Context)(
    name: besom.util.NonEmptyString,
    args: ProviderArgs = ProviderArgs(),
    opts: besom.ResourceOptsVariant.Custom ?=> besom.CustomResourceOptions = besom.CustomResourceOptions()
  ): besom.types.Output[Provider] =
    ctx.readOrRegisterResource[Provider, ProviderArgs]("pulumi:providers:purga", name, args, opts(using besom.ResourceOptsVariant.Custom))

  private[besom] def typeToken: besom.types.ResourceType = "pulumi:providers:purga"

  given resourceDecoder(using besom.types.Context): besom.types.ResourceDecoder[Provider] =
    besom.internal.ResourceDecoder.derived[Provider]

  given decoder(using besom.types.Context): besom.types.Decoder[Provider] =
    besom.internal.Decoder.customResourceDecoder[Provider]


  given outputOps: {} with
    extension(output: besom.types.Output[Provider])
      def urn : besom.types.Output[besom.types.URN] = output.flatMap(_.urn)
      def id : besom.types.Output[besom.types.ResourceId] = output.flatMap(_.id)

