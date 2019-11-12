import * as dynamoDbLib from "../../libs/dynamodb-lib";
import { success, failure, failureWithCustomCode } from "../../libs/response-lib";

export async function main(event, context) {
  const data = JSON.parse(event.body);
  if(data && data.lastUpdatedBy && data.firstName && data.lastName && data.email && data.occupation && data.description){
    //const userCognitoIdentityID = event.requestContext.identity.cognitoIdentityId;
    const userCognitoIdentityID = data.lastUpdatedBy;
    let firstName = data.firstName;
    let lastName = data.lastName;
    let email = data.email;
    let firstNameUpper = firstName.toUpperCase();
    let lastNameUpper = lastName.toUpperCase();
    let emailLower = email.toLowerCase();
    const timeStamp = new Date().toLocaleString();
    const instructorNameParam = {
      TableName: process.env.TABLE_NAME,
      FilterExpression: '(firstName= :firstName and lastName= :lastName) or email= :email',
      ExpressionAttributeValues: {
        ":firstName": firstNameUpper,
        ":lastName": lastNameUpper,
        ":email" : emailLower
      }
    };
    var instructorNameResult;
    try {
      // Do scan
      instructorNameResult = await dynamoDbLib.call("scan", instructorNameParam);
      if(instructorNameResult && instructorNameResult.Count !== 0){
        console.log("Res" + JSON.stringify(instructorNameResult));
        if( instructorNameResult.Count == 1 && (JSON.stringify(event.pathParameters.id) == JSON.stringify(instructorNameResult.Items[0].instructorId))){
          console.log(JSON.stringify(event.pathParameters.id));
          console.log(JSON.stringify(instructorNameResult.Items[0].instructorId));
          console.log("Updating the same instructor");
        }else{
          console.log("Instructor with same name or email already exists.");
          return failureWithCustomCode(400, {status: false, error: "Instructor with same name or email already exists."});
        }
      }
    }catch (e) {
      console.log(e);
      console.log("Error occurred querying for instructor with same nameor email.");
      return failure({status: false, error: "Error occurred querying for instructor with same name or email."});
    }
    const params = {
      TableName: process.env.TABLE_NAME,
      // 'Key' defines the partition key and sort key of the item to be updated
      // - 'instructorId': path parameter
      Key: {
        instructorId: event.pathParameters.id
      },
      // 'UpdateExpression' defines the attributes to be updated
      // 'ExpressionAttributeValues' defines the value in the update expression
      UpdateExpression: "SET firstName = :firstName,lastName = :lastName, email = :email, occupation = :occupation, description= :description, updatedAt= :updatedAt, lastUpdatedBy = :lastUpdatedBy ",
      ConditionExpression: "instructorId= :instructorId",
      ExpressionAttributeValues: {
        ":instructorId": event.pathParameters.id,
        ":firstName": firstNameUpper || null,
        ":lastName": lastNameUpper || null,
        ":email": emailLower || null,
        ":occupation": data.occupation || null,
        ":description": data.description || null,
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
      return failure({ status: false, error: "Error occurred while updating instructor." });
    }
  }else{
    return failureWithCustomCode(400,{ status: false, error: "Unable to update due to insufficient data."});
  }
}
