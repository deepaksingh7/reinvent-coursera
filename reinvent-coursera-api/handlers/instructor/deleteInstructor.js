import * as dynamoDbLib from "../../libs/dynamodb-lib";
import { success, failure, failureWithCustomCode } from "../../libs/response-lib";

export async function main(event, context) {
  //Check if any course is associated with this instructor
  let instructorID = event.pathParameters.id;
  const courseInstructorParam = {
    TableName: process.env.COURSE_TABLE,
    FilterExpression: 'instructorId= :instructorID',
    ExpressionAttributeValues: {
      ":instructorID": instructorID
    }
  };
  var courseInstructorResult;
  try {
    // Do scan
    courseInstructorResult = await dynamoDbLib.call("scan", courseInstructorParam);
    if(courseInstructorResult && courseInstructorResult.Count !== 0){
      console.log("Cannot delete category as it is associated with course.");
      return failureWithCustomCode(400,{ status: false, error: "Cannot delete instructor as it is associated with course." });
    }
  }catch (e) {
    console.log(e);
    console.log("Error occurred querying for courses associated with the provided instructor.");
    return failure({ status: false, error: "Error occurred querying for courses associated with the provided instructor." });
  }
  const params = {
    TableName: process.env.TABLE_NAME,
    // 'Key' defines the partition key and sort key of the item to be removed
    // - 'instructorId': path parameter
    Key: {
      instructorId: event.pathParameters.id
    }
  };

  try {
    await dynamoDbLib.call("delete", params);
    return success({ status: true });
  } catch (e) {
    console.log(e);
    return failure({ status: false, error: "Error occurred while deleting instructor" });
  }
}
