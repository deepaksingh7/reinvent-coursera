var AWS = require('aws-sdk');
AWS.config.update({region: process.env.APP_REGION}); 
exports.handler = function(event, context, callback) {
    console.log(process.env.APP_REGION);
    console.log(process.env.SNS_REF);
    console.log(JSON.stringify(event, null, 2));
    var snsMessage = null;
    event.Records.forEach(function(record) {
        snsMessage = null;
        if(record.eventName === 'MODIFY'){
            let courseName = record.dynamodb.NewImage.courseName.S;
            let oldCourseName = record.dynamodb.OldImage.courseName.S;
            if(courseName && oldCourseName){
                if(courseName === oldCourseName){
                    snsMessage = "Please note that the following course is updated - " +courseName;
                }else {
                    snsMessage = "Please note that the following course is updated - " + oldCourseName + ".\nThe course is now renamed as - " + courseName;
                }
            }else {
                snsMessage = "Please note that some course is updated";
            }
        }else if(record.eventName === 'INSERT'){
            let courseName = record.dynamodb.NewImage.courseName.S;
            if(courseName){
                snsMessage = "Please note that the following new course is published - " + courseName;
            }else{
                snsMessage = "Please note that the some new course is published";
            }
        }else if(record.eventName === 'REMOVE'){
            let oldCourseName = record.dynamodb.OldImage.courseName.S;
            if(oldCourseName){
                snsMessage = "Please note that the following course is no longer available - " + oldCourseName;
            }else{
                snsMessage = "Please note that some course is no longer available";
            }
            
        }else {
            console.log("Do Nothing");
        }
        if(snsMessage !== null){
            console.log(snsMessage);
            var params = {
                          Subject: "Notification from Re-invent Coursera App",
                          Message: snsMessage,
                          TopicArn: process.env.SNS_REF
                        };
            // Create promise and SNS service object
            var publishTextPromise = new AWS.SNS({apiVersion: '2010-03-31'}).publish(params).promise();
            // Handle promise's fulfilled/rejected states
            publishTextPromise.then(function(data) {console.log("MessageID is " + data.MessageId);}).catch(function(err) {console.error(err, err.stack);});
        }
    });
    callback(null,"done");
};