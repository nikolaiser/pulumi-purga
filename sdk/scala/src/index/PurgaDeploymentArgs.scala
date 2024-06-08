package besom.api.purga

final case class PurgaDeploymentArgs private(
  config: besom.types.Output[scala.Predef.Map[String, String]],
  flake: besom.types.Output[String],
  flakeInput: besom.types.Output[String],
  host: besom.types.Output[String]
)

object PurgaDeploymentArgs:
  def apply(
    config: besom.types.Input[scala.Predef.Map[String, besom.types.Input[String]]],
    flake: besom.types.Input[String],
    flakeInput: besom.types.Input[String],
    host: besom.types.Input[String]
  )(using besom.types.Context): PurgaDeploymentArgs =
    new PurgaDeploymentArgs(
      config = config.asOutput(isSecret = false),
      flake = flake.asOutput(isSecret = false),
      flakeInput = flakeInput.asOutput(isSecret = false),
      host = host.asOutput(isSecret = false)
    )

  given encoder(using besom.types.Context): besom.types.Encoder[PurgaDeploymentArgs] =
    besom.internal.Encoder.derived[PurgaDeploymentArgs]
  given argsEncoder(using besom.types.Context): besom.types.ArgsEncoder[PurgaDeploymentArgs] =
    besom.internal.ArgsEncoder.derived[PurgaDeploymentArgs]


