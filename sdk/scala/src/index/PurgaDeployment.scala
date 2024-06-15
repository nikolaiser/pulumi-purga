package besom.api.purga

final case class PurgaDeployment private(
  urn: besom.types.Output[besom.types.URN],
  id: besom.types.Output[besom.types.ResourceId],
  configArrayString: besom.types.Output[scala.Predef.Map[String, scala.collection.immutable.List[String]]],
  configString: besom.types.Output[scala.Predef.Map[String, String]],
  flake: besom.types.Output[String],
  flakeInput: besom.types.Output[String],
  flakeRevision: besom.types.Output[String],
  host: besom.types.Output[String]
) extends besom.CustomResource

object PurgaDeployment extends besom.ResourceCompanion[PurgaDeployment]:
  /** Resource constructor for PurgaDeployment. 
    * 
    * @param name [[besom.util.NonEmptyString]] The unique (stack-wise) name of the resource in Pulumi state (not on provider's side).
    *        NonEmptyString is inferred automatically from non-empty string literals, even when interpolated. If you encounter any
    *        issues with this, please try using `: NonEmptyString` type annotation. If you need to convert a dynamically generated
    *        string to NonEmptyString, use `NonEmptyString.apply` method - `NonEmptyString(str): Option[NonEmptyString]`.
    *
    * @param args [[PurgaDeploymentArgs]] The configuration to use to create this resource. 
    *
    * @param opts [[besom.CustomResourceOptions]] Resource options to use for this resource. 
    *        Defaults to empty options. If you need to set some options, use [[besom.opts]] function to create them, for example:
    *  
    *        {{{
    *        val res = PurgaDeployment(
    *          "my-resource",
    *          PurgaDeploymentArgs(...), // your args
    *          opts(provider = myProvider)
    *        )
    *        }}}
    */
  def apply(using ctx: besom.types.Context)(
    name: besom.util.NonEmptyString,
    args: PurgaDeploymentArgs,
    opts: besom.ResourceOptsVariant.Custom ?=> besom.CustomResourceOptions = besom.CustomResourceOptions()
  ): besom.types.Output[PurgaDeployment] =
    ctx.readOrRegisterResource[PurgaDeployment, PurgaDeploymentArgs]("purga:index:PurgaDeployment", name, args, opts(using besom.ResourceOptsVariant.Custom))

  private[besom] def typeToken: besom.types.ResourceType = "purga:index:PurgaDeployment"

  given resourceDecoder(using besom.types.Context): besom.types.ResourceDecoder[PurgaDeployment] =
    besom.internal.ResourceDecoder.derived[PurgaDeployment]

  given decoder(using besom.types.Context): besom.types.Decoder[PurgaDeployment] =
    besom.internal.Decoder.customResourceDecoder[PurgaDeployment]


  given outputOps: {} with
    extension(output: besom.types.Output[PurgaDeployment])
      def urn : besom.types.Output[besom.types.URN] = output.flatMap(_.urn)
      def id : besom.types.Output[besom.types.ResourceId] = output.flatMap(_.id)
      def configArrayString : besom.types.Output[scala.Predef.Map[String, scala.collection.immutable.List[String]]] = output.flatMap(_.configArrayString)
      def configString : besom.types.Output[scala.Predef.Map[String, String]] = output.flatMap(_.configString)
      def flake : besom.types.Output[String] = output.flatMap(_.flake)
      def flakeInput : besom.types.Output[String] = output.flatMap(_.flakeInput)
      def flakeRevision : besom.types.Output[String] = output.flatMap(_.flakeRevision)
      def host : besom.types.Output[String] = output.flatMap(_.host)

