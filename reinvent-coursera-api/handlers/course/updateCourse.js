import * as dynamoDbLib from "../../libs/dynamodb-lib";
import { success, failure, failureWithCustomCode } from "../../libs/response-lib";

export async function main(event, context) {
  const data = JSON.parse(event.body);
  if(data && data.instructorId && data.lastUpdatedBy && data.courseName && data.title && data.categoryId && data.description && data.content){
    //const userCognitoIdentityID = event.requestContext.identity.cognitoIdentityId;
    const userCognitoIdentityID = data.lastUpdatedBy;
    let courseName = data.courseName;
    let courseNameUpper = courseName.toUpperCase();
    const timeStamp = new Date().toLocaleString();
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
        console.log("Res" + JSON.stringify(courseNameResult));
        if( courseNameResult.Count == 1 && (JSON.stringify(event.pathParameters.id) == JSON.stringify(courseNameResult.Items[0].courseId))){
          console.log(JSON.stringify(event.pathParameters.id));
          console.log(JSON.stringify(courseNameResult.Items[0].courseId));
          console.log("Updating the same course");
        }else{
          console.log("Course with same name already exists.");
          return failureWithCustomCode(400, {status: false, error: "Course with same name already exists."});
        }
      }
    }catch (e) {
      console.log(e);
      console.log("Error occurred querying for course with same name.");
      return failure({status: false, error: "Error occurred querying for course with same name."});
    }
    const params = {
      TableName: process.env.TABLE_NAME,
      // 'Key' defines the partition key and sort key of the item to be updated
      // - 'courseId': path parameter
      Key: {
          courseId: event.pathParameters.id
      },
      // 'UpdateExpression' defines the attributes to be updated
      // 'ExpressionAttributeValues' defines the value in the update expression
      UpdateExpression: "SET instructorId = :instructorId,courseName = :courseName, title = :title, categoryId = :categoryId, description= :description, content= :content, updatedAt= :updatedAt, lastUpdatedBy = :lastUpdatedBy ",
      ConditionExpression: "courseId= :courseId",
      ExpressionAttributeValues: {
        ":courseId":event.pathParameters.id,
        ":instructorId": data.instructorId || null,
        ":courseName": courseNameUpper || null,
        ":title": data.title || null,
        ":categoryId": data.categoryId || null,
        ":description": data.description || null,
        ":content": data.content || null,
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
      return failure({ status: false, error: "Error occurred while updating course." });
    }
  }else{
    return failureWithCustomCode(400,{ status: false, error: "Unable to update due to insufficient data."});
  }
}
