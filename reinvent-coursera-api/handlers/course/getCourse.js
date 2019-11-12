import * as dynamoDbLib from "../../libs/dynamodb-lib";
import { success, failure, failureWithCustomCode } from "../../libs/response-lib";

export async function main(event, context) {
  const params = {
    TableName: process.env.TABLE_NAME,
    // 'Key' defines the partition key and sort key of the item to be retrieved
    // - 'courseId': path parameter
    Key: {
        courseId: event.pathParameters.id
    }
  };

  try {
    const result = await dynamoDbLib.call("get", params);
    if (result.Item) {
      // Return the retrieved item
      return success(result.Item);
    } else {
      return failureWithCustomCode(400,{ status: false, error: "Course not found." });
    }
  } catch (e) {
    console.log(e);
    return failure({ status: false,error: "Error occurred while getting the course." });
  }
}
