package com.coursera.admin.web.service;

import com.amazonaws.regions.Regions;

/**
 * Constants that are used by for AWS Cognito authentication.
 */

public interface CognitoResources {
	public final static String EMAIL = "email";
	// Cognito IAM ID for full Cognito access
	public final static String cognitoID = "cognitoID";
	// Cognito IAM "secret key" for full Cognito access
	public final static String cognitoKey = "cognitoKey";
	public final static String poolID = "poolID";
	public final static String clientID = "clientID";
	// Replace this with the AWS region for your application
	public final static Regions region = Regions.AP_SOUTH_1;
}
