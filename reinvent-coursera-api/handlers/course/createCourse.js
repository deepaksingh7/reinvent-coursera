import uuid from "uuid";
import * as dynamoDbLib from "../../libs/dynamodb-lib";
import { success, failure, failureWithCustomCode } from "../../libs/response-lib";

export async function main(event, context) {
  const data = JSON.parse(event.body);
  if(data && data.instructorId && data.createdBy && data.courseName && data.title && data.categoryId && data.description && data.content ){
    let courseName = data.courseName;
    let courseNameUpper = courseName.toUpperCase();
    const courseNameParam = {
      TableName: process.env.TABLE_NAME,
      FilterExpression: 'courseName= :courseName',
      ExpressionAttributeValues: {
        ":courseName": courseNameUpper
      }
    };
    var courseNameResult;
    try {
      // Do scan
      courseNameResult = await dynamoDbLib.call("scan", courseNameParam);
      if(courseNameResult && courseNameResult.Count !== 0){
        console.log("Course with same name already exists.");
        return failureWithCustomCode(400, {status: false, error: "Course with same name already exists."});
      }
    }catch (e) {
      console.log(e);
      console.log("Error occurred querying for course with same name.");
      return failure({status: false, error: "Error occurred querying for course with same name."});
    }
    const timeStamp = new Date().toLocaleString();
    //const userCognitoIdentityID = event.requestContext.identity.cognitoIdentityId;
    const userCognitoIdentityID = data.createdBy;
    const params = {
      TableName: process.env.TABLE_NAME,
      Item: {
        courseId: uuid.v1(),
        instructorId: data.instructorId,
        courseName: courseNameUpper,
        title: data.title,
        categoryId: data.categoryId,
        description: data.description,
        content: data.content,
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
      return failure({ status: false ,error: "Error occurred while creating course."});
    }
  }else{
    return failureWithCustomCode(400,{ status: false, error: "Unable to create due to insufficient data."});
  }
}
