package com.coursera.admin.web.service;

import com.amazonaws.services.cognitoidp.model.AWSCognitoIdentityProviderException;

/**
 * An exception designed for the case where there is more than one user in the
 * Cognito database with a given email address.
 */
public class DuplicateEmailException extends AWSCognitoIdentityProviderException {

	/**
	 * long
	 */
	private static final long serialVersionUID = 6561571810771139916L;

	public DuplicateEmailException(final String errorMessage) {
		super(errorMessage);
	}
}
