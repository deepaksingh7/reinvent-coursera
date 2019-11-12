import * as dynamoDbLib from "../../libs/dynamodb-lib";
import { success, failure } from "../../libs/response-lib";

export async function main(event, context) {
const params = {
    TableName: process.env.TABLE_NAME,
};

let scanResults = [];
let items;
try{
    do{
        items = await dynamoDbLib.call("scan", params);
        items.Items.forEach((item) => scanResults.push(item));
        params.ExclusiveStartKey  = items.LastEvaluatedKey;
    }while(typeof items.LastEvaluatedKey != "undefined");

return  success(scanResults);
} catch(e) {
    console.log(e);
    return failure({status:  false, error: "Error occurred while getting all categories."});
}

}
