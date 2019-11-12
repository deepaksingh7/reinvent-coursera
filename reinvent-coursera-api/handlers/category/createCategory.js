import uuid from "uuid";
import * as dynamoDbLib from "../../libs/dynamodb-lib";
import { success, failure, failureWithCustomCode } from "../../libs/response-lib";

export async function main(event, context) {
  const data = JSON.parse(event.body);
  if(data && data.categoryName && data.createdBy ){
    let categoryName = data.categoryName;
    let categoryNameUpper = categoryName.toUpperCase();
    const categoryNameParam = {
      TableName: process.env.TABLE_NAME,
      FilterExpression: 'categoryName= :categoryName',
      ExpressionAttributeValues: {
        ":categoryName": categoryNameUpper
      }
    };
    var categoryNameResult;
    try {
      // Do scan
      categoryNameResult = await dynamoDbLib.call("scan", categoryNameParam);
      if(categoryNameResult && categoryNameResult.Count !== 0){
        console.log("Category with same name already exists.");
        return failureWithCustomCode(400, {status: false, error: "Category with same name already exists."});
      }
    }catch (e) {
      console.log(e);
      console.log("Error occurred querying for category with same name.");
      return failure({status: false, error: "Error occurred querying for category with same name."});
    }
    const timeStamp = new Date().toLocaleString();
    //const userCognitoIdentityID = event.requestContext.identity.cognitoIdentityId;
    const userCognitoIdentityID = data.createdBy;
    const params = {
      TableName: process.env.TABLE_NAME,
      Item: {
        categoryId: uuid.v1(),
        categoryName: categoryNameUpper,
        createdBy: userCognitoIdentityID,
        lastUpdatedBy: userCognitoIdentityID,
        createdAt: timeStamp,
        updatedAt: timeStamp
      }
    };
    try {
      await dynamoDbLib.call("put", params);
      return success(params.Item);
    } catch (e) {
      console.log(e);
      return failure({ status: false , error: "Error occurred while creating category."});
    }
  }else {
    return failureWithCustomCode(400,{ status: false, error: "Unable to create due to insufficient data."});
  }
}
