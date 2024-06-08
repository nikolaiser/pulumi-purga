import * as pulumi from "@pulumi/pulumi";
import * as purga from "@pulumi/purga";

const myRandomResource = new purga.Random("myRandomResource", {length: 24});
export const output = {
    value: myRandomResource.result,
};
