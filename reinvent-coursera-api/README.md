This project is used to create API gateway and associated LAMBDA function handlers.
This contains the cloudformation script for database creation, lambda function, api gateway , cognito user-pool and identity pool creation.  

PREREQUISITE:
1. nodejs latest version to be installed on your machine(v.8 or higher)
Download nodejs from https://nodejs.org/en/download/
2. AWS account. Create a new user with required previleges.(OR ADMIN ACCESS)

STEPS to follow:

1. Clone this
2. $ cd reinvent-coursera-api
3. $ npm install
4. $ npm install -g serverless@1.51.0
5. You need to create a user with admin previleges in your aws account. Add that user [profile] in your credentials file.
6. $ serverless deploy --aws-profile [your_user_profile]
7. To undeploy this execute $ serverless remove

TODO:
1. You can locally test lambda fucntion. Will soon add the steps for that
    (serverless invoke local --function listCategory --path mocks/category/list-category-event.json --aws-profile [your_user_profile])
2. Will add script and steps for CI/CD
