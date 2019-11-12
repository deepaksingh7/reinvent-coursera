import uuid from "uuid";
import * as dynamoDbLib from "../../libs/dynamodb-lib";
import { success, failure, failureWithCustomCode } from "../../libs/response-lib";

export async function main(event, context) {
  const data = JSON.parse(event.body);
  if(data && data.createdBy && data.firstName && data.lastName && data.email && data.occupation && data.description){
    let firstName = data.firstName;
    let lastName = data.lastName;
    let email = data.email;
    let firstNameUpper = firstName.toUpperCase();
    let lastNameUpper = lastName.toUpperCase();
    let emailLower = email.toLowerCase();
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
        console.log("Instructor with same name or email already exists.");
        return failureWithCustomCode(400, {status: false, error: "Instructor with same name or email already exists."});
      }
    }catch (e) {
      console.log(e);
      console.log("Error occurred querying for instructor with same nameor email.");
      return failure({status: false, error: "Error occurred querying for instructor with same name or email."});
    }
    const timeStamp = new Date().toLocaleString();
    //const userCognitoIdentityID = event.requestContext.identity.cognitoIdentityId;
    const userCognitoIdentityID = data.createdBy;
    const params = {
      TableName: process.env.TABLE_NAME,
      Item: {
        instructorId: uuid.v1(),
        firstName: firstNameUpper,
        lastName: lastNameUpper,
        email: emailLower,
        occupation: data.occupation,
        description: data.description,
        createdBy: userCognitoIdentityID,
        lastUpdatedBy: userCognitoIdentityID,
        createdAt: timeStamp,
        updatedAt: timeStamp
      }
    };
    try {
      console.log(params);
      await dynamoDbLib.call("put", params);
      return success(params.Item);
    } catch (e) {
      console.log(e);
      return failure({ status: false,error: "Error occurred while creating instructor." });
    }
  }else{
    return failureWithCustomCode(400,{ status: false, error: "Unable to create due to insufficient data."});
  }
}
