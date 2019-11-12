package com.coursera.admin.web.model;

public class Token {
	
	public static String tokenID;
	
	public static String cognitoUserId;

	public static String getTokenID() {
		return tokenID;
	}

	public static void setTokenID(String tokenID) {
		Token.tokenID = tokenID;
	}

	public static String getCognitoUserId() {
		return cognitoUserId;
	}

	public static void setCognitoUserId(String cognitoUserId) {
		Token.cognitoUserId = cognitoUserId;
	}
	
	

}
