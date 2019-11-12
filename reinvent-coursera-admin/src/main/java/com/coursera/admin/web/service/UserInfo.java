package com.coursera.admin.web.service;

/**
 * The UserInfo class packages the information associated with a user. This
 * allows the user information to be abstracted from the underlying user
 * implementation for user authentication.
 */
public class UserInfo {
	private final String emailAddr;

	public UserInfo(final String emailAddr) {
		this.emailAddr = emailAddr;
	}

	public String getEmailAddr() {
		return emailAddr;
	}

}
