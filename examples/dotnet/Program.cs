using System.Collections.Generic;
using System.Linq;
using Pulumi;
using purga = Pulumi.purga;

return await Deployment.RunAsync(() => 
{
    var myRandomResource = new purga.Random("myRandomResource", new()
    {
        Length = 24,
    });

    return new Dictionary<string, object?>
    {
        ["output"] = 
        {
            { "value", myRandomResource.Result },
        },
    };
});

