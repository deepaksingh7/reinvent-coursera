import * as dynamoDbLib from "../../libs/dynamodb-lib";
import { success, failure, failureWithCustomCode } from "../../libs/response-lib";

export async function main(event, context) {
    //Check if any course is associated with this category
    let categoryID = event.pathParameters.id;
    const courseInstructorParam = {
      TableName: process.env.COURSE_TABLE,
      FilterExpression: 'categoryId= :categoryID',
      ExpressionAttributeValues: {
        ":categoryID": categoryID
      }
    };
    var courseCategoryResult;
    try {
      // Do scan
      courseCategoryResult = await dynamoDbLib.call("scan", courseInstructorParam);
      if(courseCategoryResult && courseCategoryResult.Count !== 0){
        console.log("Cannot delete category as it is associated with course.");
        return failureWithCustomCode(400, {status: false, error: "Cannot delete category as it is associated with course." });
      }
    }catch (e) {
      console.log(e);
      console.log("Error occurred querying for courses associated with the provided category.");
      return failure({ status: false, error: "Error occurred querying for courses associated with the provided category." });
    }
    const params = {
    TableName: process.env.TABLE_NAME,
    // 'Key' defines the partition key and sort key of the item to be removed
    // - 'categoryId': path parameter
    Key: {
         categoryId: event.pathParameters.id
    }
  };

  try {
    await dynamoDbLib.call("delete", params);
    return success({ status: true });
  } catch (e) {
    console.log(e);
    return failure({ status: false, error: "Error occurred while deleting category" });
  }
}
