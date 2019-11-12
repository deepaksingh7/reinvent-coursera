package com.coursera.admin.web.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * Validate the syntax of an email address (of course this code cannot assure
 * that the email address actually connects to a working email account).
 *
 */
public class EmailValidator {

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";
	private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

	public static boolean isValid(String email) {
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

}
