package com.coursera.admin.web.service;

/**
 * The information that is obtained with the user logs into the system. This
 * class extends the UserInfo class by adding an session token which is needed
 * by operations like changePassword(). Note that the session token is used for
 * the session, while the access token is used to grant access to resources
 * (which is not used in this code).
 */
public class LoginInfo extends UserInfo {
	private Boolean newPasswordRequired = false;

	public LoginInfo(final String email) {
		super(email);
	}

	public LoginInfo(final UserInfo info) {
		this(info.getEmailAddr());
	}

	public Boolean getNewPasswordRequired() {
		return newPasswordRequired;
	}

	public void setNewPasswordRequired(Boolean newPasswordRequired) {
		this.newPasswordRequired = newPasswordRequired;
	}

}
