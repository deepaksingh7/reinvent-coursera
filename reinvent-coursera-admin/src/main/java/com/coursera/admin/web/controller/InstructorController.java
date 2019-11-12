package com.coursera.admin.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.coursera.admin.web.model.Token;
import com.coursera.admin.web.service.InstructorService;

@Controller
public class InstructorController {

	@Autowired
	InstructorService instructorService;

	@RequestMapping(value = "/instructors", method = RequestMethod.GET)
	public String showInstructorsPage(ModelMap model) {
		model.put("instructor", instructorService.getAllInstructor());
		return "instructorList";
	}

	@RequestMapping(value = "/instructor/{id}", method = RequestMethod.GET)
	public String showInstructorPage(ModelMap model, @PathVariable String id) {
		model.put("instructor", instructorService.getInstructor(id));
		return "update_instructor";
	}

	@RequestMapping(value = "/delete_instructor_form", method = RequestMethod.POST)
	public String deleteInstructorPage(ModelMap model, @RequestParam("instructor_id") final String instructorId) {
		ResponseEntity<String> rs  = instructorService.deleteInstructor(instructorId);
		if(rs.getStatusCodeValue() != 200) {
			model.put("errorMessage", rs.getBody());
		}
		model.put("instructor", instructorService.getAllInstructor());
		return "instructorList";
	}

	@RequestMapping(value = "/addinstructors", method = RequestMethod.GET)
	public String showAddInstructorsPage(ModelMap model) {
		return "addInstructor";
	}

	@RequestMapping(value = "/add_instructor_form", method = RequestMethod.POST)
	public String showInstructorPage(ModelMap model, @RequestParam("first_name") final String firstName,
			@RequestParam("last_name") final String lastName, @RequestParam("email") final String email,
			@RequestParam("occupation") final String occupation,
			@RequestParam("description") final String description) {
		String jsonRequest = "{ \"occupation\": \""
				+ occupation + "\", \"lastName\": \"" + lastName
				+ "\",  \"description\": \"" + description + "\", \"email\": \"" + email
				+ "\", \"createdBy\": "+Token.cognitoUserId +", \"firstName\": \"" + firstName
				+ "\" }";

		ResponseEntity<String> rs  = instructorService.addInstructor(jsonRequest);
		if(rs.getStatusCodeValue() != 200) {
			model.put("errorMessage", rs.getBody());
		}
		model.put("instructor", instructorService.getAllInstructor());
		return "instructorList";
	}
	
	@RequestMapping(value = "/update_instructor_form", method = RequestMethod.POST)
	public String updateInstructorPage(ModelMap model, @RequestParam("first_name") final String firstName,
			@RequestParam("last_name") final String lastName, @RequestParam("email") final String email,
			@RequestParam("occupation") final String occupation,
			@RequestParam("description") final String description,@RequestParam("instructor_id") final String instructor_id) {
		String jsonRequest = "{ \"occupation\": \""
				+ occupation + "\", \"lastName\": \"" + lastName
				+ "\",  \"description\": \"" + description + "\", \"email\": \"" + email
				+ "\", \"lastUpdatedBy\": "+Token.cognitoUserId +", \"firstName\": \"" + firstName
				+ "\" }";
		
		ResponseEntity<String> rs  = instructorService.updateInstructor(jsonRequest, instructor_id);
		if(rs.getStatusCodeValue() != 200) {
			model.put("errorMessage", rs.getBody());
		}
		model.put("instructor", instructorService.getAllInstructor());
		return "instructorList";
	}

}
