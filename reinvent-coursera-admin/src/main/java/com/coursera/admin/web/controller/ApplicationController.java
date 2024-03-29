package com.coursera.admin.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coursera.admin.web.service.UserInfo;

/**
 * ApplicationController This controller doesn't do much except support a
 * placeholder application page. This page should only be accessible to users
 * who are logged in.
 */
@Controller
public class ApplicationController extends AuthenticationBase {

	@GetMapping("/application")
	public ModelAndView application(ModelMap model, RedirectAttributes redirect, HttpServletRequest request) {
		String nextPage = "application";
		UserInfo info = (UserInfo) request.getSession().getAttribute(USER_SESSION_ATTR);
		if (info != null) {
			model.addAttribute("user_info", info);
		} else {
			// The user is not logged in, so go to the login page
			nextPage = "index";
		}
		return new ModelAndView(nextPage, model);
	}

	/**
	 * <p>
	 * Handle logout.
	 * </p>
	 * <p>
	 * The logout form is in this class since the class handles the application
	 * page. The processing for logout is simple, so there is no need for a separate
	 * controller.
	 * </p>
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping("/logout_form")
	public String logoutPage(HttpServletRequest request) {
		UserInfo info = (UserInfo) request.getSession().getAttribute(USER_SESSION_ATTR);
		if (info != null) {
			authService.userLogout(info.getEmailAddr());
			request.getSession().setAttribute(USER_SESSION_ATTR, null);
		}
		return "index";
	}
}
