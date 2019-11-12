package com.coursera.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coursera.admin.web.service.UserInfo;
import com.coursera.admin.web.util.EmailValidator;
import com.google.common.base.Strings;

@Controller
public class CreateUser extends AuthenticationBase {
	/**
	 * This is the web page display method (GET only)
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/create_user")
	public String create_user(ModelMap model) {
		return "/create_user";
	}

	/**
	 * <p>
	 * This method handles the data from the create user form.
	 * </p>
	 * 
	 * @param emailAddr
	 * @param redirect
	 * @return
	 * 
	 *         <p>
	 *         Flash attributes:
	 *         </p>
	 *         <ul>
	 *         <li>emailError</li>
	 *         <li>createUserError</li>
	 *         </ul>
	 */
	@PostMapping("/create_user_form")
	public String new_user(@RequestParam("email") final String emailAddr,
			RedirectAttributes redirect) {
		String newPage = "redirect:create_user";
		String emailArg = null;
		boolean badArgument = false;
		if (!badArgument) { // user name is OK
			if (Strings.isNullOrEmpty(emailAddr)) {
				redirect.addFlashAttribute("emailError", "Please provide an email address");
				badArgument = true;
			} else {
				emailArg = emailAddr.trim();
				if (EmailValidator.isValid(emailArg)) {
					// Check to see if the email address is already in use
					UserInfo info = authService.findUserByEmailAddr(emailArg);
					if (info != null) { // there is a user with that email address
						redirect.addFlashAttribute("emailError", "That email address is already in use");
						badArgument = true;
					}
				} else {
					redirect.addFlashAttribute("emailError", "Email address is not properly formed");
					badArgument = true;
				}
			}
		}
		/*
		 * If the arguments are OK, create a new user in the database.
		 */
		if (!badArgument) {
			try {
				UserInfo userInfo = new UserInfo(emailArg);
				authService.createNewUser(userInfo);
				redirect.addFlashAttribute("login_message",
						"Your user name is " + emailArg + ". Please check your email for your temporary password");
				redirect.addFlashAttribute("user_name_val", emailArg);
				newPage = "redirect:/";
			} catch (Exception e) {
				redirect.addFlashAttribute("createUserError", "Error creating new user: " + e.getLocalizedMessage());
			}
		}
		return newPage;
	}
}
