package com.coursera.admin.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.coursera.admin.web.service.UserInfo;

@Controller
public class IndexController extends AuthenticationBase {

	/**
	 * Main index.html page. This page has the login dialog, which is processed (via
	 * a post) by the LoginController
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/")
	public ModelAndView index(ModelMap model, HttpServletRequest request) {
		String nextPage = "index";
		UserInfo info = (UserInfo) request.getSession().getAttribute(USER_SESSION_ATTR);
		if (info != null) {
			// the user is already logged in, so redirect to the application page.
			nextPage = "application";
			model.addAttribute(USER_SESSION_ATTR, info);
		}
		return new ModelAndView(nextPage, model);
	}
}
