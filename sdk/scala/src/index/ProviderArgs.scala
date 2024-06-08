package besom.api.purga

final case class ProviderArgs private(

)

object ProviderArgs:
  def apply(

  )(using besom.types.Context): ProviderArgs =
    new ProviderArgs(

    )

  given encoder(using besom.types.Context): besom.types.Encoder[ProviderArgs] =
    besom.internal.Encoder.derived[ProviderArgs]
  given providerArgsEncoder(using besom.types.Context): besom.types.ProviderArgsEncoder[ProviderArgs] =
    besom.internal.ProviderArgsEncoder.derived[ProviderArgs]


