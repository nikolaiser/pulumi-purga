import pulumi
import pulumi_purga as purga

my_random_resource = purga.Random("myRandomResource", length=24)
pulumi.export("output", {
    "value": my_random_resource.result,
})
