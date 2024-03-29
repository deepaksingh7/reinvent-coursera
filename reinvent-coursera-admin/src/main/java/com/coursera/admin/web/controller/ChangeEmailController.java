package com.coursera.admin.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coursera.admin.web.service.UserInfo;
import com.coursera.admin.web.util.EmailValidator;
import com.google.common.base.Strings;

/**
 * ChangeEmailController Change the user's email address.
 */
@Controller
public class ChangeEmailController extends AuthenticationBase {

	@GetMapping("/change_email")
	public ModelAndView changeEmailRequest(ModelMap model, HttpServletRequest request) {
		String nextPage = "change_email";
		UserInfo info = (UserInfo) request.getSession().getAttribute(USER_SESSION_ATTR);
		if (info != null) {
			String currentEmailAddr = info.getEmailAddr();
			model.addAttribute("current_email", currentEmailAddr);
		} else {
			nextPage = "index";
		}
		return new ModelAndView(nextPage, model);
	}

	@PostMapping("/change_email_form")
	public String changeEmailRequestForm(@RequestParam("email_addr") final String newEmailAddr,
			RedirectAttributes redirect, HttpServletRequest request) {
		String nextPage = "redirect:change_email";
		UserInfo info = (UserInfo) request.getSession().getAttribute(USER_SESSION_ATTR);
		if (info != null) {
			if (Strings.isNullOrEmpty(newEmailAddr)) {
				redirect.addFlashAttribute("email_addr_error", "Please provide an email address");
			} else if (EmailValidator.isValid(newEmailAddr)) {
				// Get the current email address from the Cognito database
				UserInfo cognitoInfo = authService.getUserInfo(info.getEmailAddr());
				if (!cognitoInfo.getEmailAddr().equals(newEmailAddr)) {
					try {
						authService.changeEmail(info.getEmailAddr(), newEmailAddr);
						UserInfo newInfo = authService.getUserInfo(info.getEmailAddr());
						request.getSession().setAttribute(USER_SESSION_ATTR, newInfo);
						nextPage = "redirect:application";
					} catch (Exception e) {
						redirect.addFlashAttribute("email_addr_error", e.getLocalizedMessage());
					}
				} else {
					redirect.addFlashAttribute("email_addr_error",
							"The new email address is the same as the address in the database");
				}
			} else {
				redirect.addFlashAttribute("email_addr_error", "Badly formatted email address");
			}
		} else {
			// Someone submitted a POST without being logged in, so redirect to the index
			// page
			nextPage = "redirect:/";
		}
		return nextPage;
	}

}
