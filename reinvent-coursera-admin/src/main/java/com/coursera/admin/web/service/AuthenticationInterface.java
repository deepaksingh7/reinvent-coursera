package com.coursera.admin.web.service;

import com.amazonaws.services.cognitoidp.model.AWSCognitoIdentityProviderException;

/**
 * An interface for authentication. This interface is designed to abstract the
 * actual implementation of authentication.
 **/

public interface AuthenticationInterface {

	/**
	 * 
	 * Create a new user.
	 * 
	 * 
	 */
	void createNewUser(UserInfo userInfo) throws Exception;

	/**
	 * 
	 * Delete a user. Since deleting a user can be a "big deal" a password is
	 * required.
	 * 
	 * 
	 * @param userName
	 * @param password
	 */
	void deleteUser(String userName, String password) throws Exception;

	/**
	 * Find a user by email address.
	 * 
	 * @param email
	 * @return
	 * @throws Exception
	 */
	UserInfo findUserByEmailAddr(final String email) throws Exception;

	/**
	 * 
	 * Log a user into the system
	 * 
	 * 
	 * @param userName
	 * @param password
	 * @return a LoginInfo object may include special context information.
	 */
	LoginInfo userLogin(String userName, String password) throws Exception;

	/**
	 * 
	 * Log the user out.
	 * 
	 * 
	 * @param userName
	 */
	void userLogout(String userName) throws Exception;

	/**
	 * 
	 * Change the user's password from a temporary password to a new (permanent)
	 * password.
	 * 
	 */
	public void changeFromTemporaryPassword(final PasswordRequest passwordRequest) throws Exception;

	/**
	 * Support for resetting the user's password in the event that it was forgotten.
	 * 
	 * @param userName
	 * @param resetCode
	 * @throws Exception
	 */
	void forgotPassword(final String userName) throws Exception;

	/**
	 * 
	 * Get the information associated with the user.
	 * 
	 * 
	 * @param userName the name of the user
	 * @return a UserInfo object if the information could be retrieved, null
	 *         otherwise.
	 */
	UserInfo getUserInfo(String userName) throws Exception; // getUserInfo

	/**
	 * 
	 * Determine whether a user with userName exists in the login database.
	 * 
	 * 
	 * @param userName
	 * @return true if the user exists, false otherwise.
	 */
	boolean hasUser(String userName);

	/**
	 * 
	 * Reset a user's password using an authentication code.
	 * 
	 * 
	 * @param resetRequest
	 * @throws Exception
	 */
	void resetPassword(ResetPasswordRequest resetRequest) throws Exception;

	/**
	 * 
	 * Change the password for a logged in user. Unlike the forgotten password
	 * logic, this does not require an authentication code.
	 * 
	 * 
	 * @param passwordRequest
	 * @throws Exception
	 */
	void changePassword(PasswordRequest passwordRequest) throws Exception;

	/**
	 * 
	 * Change the email address of a logged in user.
	 * 
	 * 
	 * @param userName
	 * @param newEmailAddr
	 * @throws Exception
	 */
	void changeEmail(String userName, String newEmailAddr) throws Exception;


}
