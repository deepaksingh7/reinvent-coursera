import * as dynamoDbLib from "../../libs/dynamodb-lib";
import { success, failure, failureWithCustomCode } from "../../libs/response-lib";

export async function main(event, context) {
  const data = JSON.parse(event.body);
  if(data && data.categoryName && data.lastUpdatedBy){
    //const userCognitoIdentityID = event.requestContext.identity.cognitoIdentityId;
    const userCognitoIdentityID = data.lastUpdatedBy;
    let categoryName = data.categoryName;
    let categoryNameUpper = categoryName.toUpperCase();
    const timeStamp = new Date().toLocaleString();
    const categoryNameParam = {
      TableName: process.env.TABLE_NAME,
      FilterExpression: 'categoryName= :categoryName ',
      ExpressionAttributeValues: {
        ":categoryName": categoryNameUpper
      }
    };
    var categoryNameResult;
    try {
      // Do scan
      categoryNameResult = await dynamoDbLib.call("scan", categoryNameParam);
       if(categoryNameResult && categoryNameResult.Count !== 0){
         console.log("Res" + JSON.stringify(categoryNameResult));
         if( categoryNameResult.Count == 1 && (JSON.stringify(event.pathParameters.id) == JSON.stringify(categoryNameResult.Items[0].categoryId))){
           console.log(JSON.stringify(event.pathParameters.id));
           console.log(JSON.stringify(categoryNameResult.Items[0].categoryId));
           console.log("Updating the same category");
          }else{
            console.log("Category with same name already exists.");
            return failureWithCustomCode(400, {status: false, error: "Category with same name already exists."});
          }
        }
    }catch (e) {
      console.log(e);
      console.log("Error occurred querying for category with same name.");
      return failure({status: false, error: "Error occurred querying for category with same name."});
    }
    const params = {
      TableName: process.env.TABLE_NAME,
      // 'Key' defines the partition key and sort key of the item to be updated
      // - 'categoryId': path parameter
      Key: {
          categoryId: event.pathParameters.id
      },
      // 'UpdateExpression' defines the attributes to be updated
      // 'ExpressionAttributeValues' defines the value in the update expression
      UpdateExpression: "SET categoryName = :categoryName, updatedAt= :updatedAt, lastUpdatedBy = :lastUpdatedBy ",
      ConditionExpression: "categoryId= :categoryId",
      ExpressionAttributeValues: {
        ":categoryId":event.pathParameters.id,
        ":categoryName": categoryNameUpper || null,
        ":updatedAt": timeStamp ||null,
        ":lastUpdatedBy": userCognitoIdentityID ||null
      },
      // 'ReturnValues' specifies if and how to return the item's attributes,
      // where ALL_NEW returns all attributes of the item after the update; you
      // can inspect 'result' below to see how it works with different settings
      ReturnValues: "ALL_NEW"
    };
    try {
      await dynamoDbLib.call("update", params);
      return success({ status: true });
    } catch (e) {
      console.log(e);
      return failure({ status: false,error: "Error occurred while updating category." });
    }
  }else{
    return failureWithCustomCode(400,{ status: false, error: "Unable to update due to insufficient data."});
  }
}
