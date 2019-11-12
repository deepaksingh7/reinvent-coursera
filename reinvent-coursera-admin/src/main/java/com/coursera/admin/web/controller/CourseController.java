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
import com.coursera.admin.web.service.CategoryService;
import com.coursera.admin.web.service.CoursesService;
import com.coursera.admin.web.service.InstructorService;

import io.micrometer.core.instrument.util.StringUtils;

@Controller
public class CourseController {

	@Autowired
	CoursesService coursesService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	InstructorService instructorService;

	@RequestMapping(value = "/courses", method = RequestMethod.GET)
	public String showCoursesPage(ModelMap model) {
		model.put("courses", coursesService.getAllCourses());
		model.put("instructor", instructorService.getAllInstructor());
		return "courseList";
	}

	@RequestMapping(value = "/course/{id}", method = RequestMethod.GET)
	public String showCoursesPage(ModelMap model, @PathVariable String id) {
		model.put("courses", coursesService.getCourse(id));
		model.put("category", categoryService.getAllCategory());
		model.put("instructor", instructorService.getAllInstructor());
		return "update_course";
	}

	@RequestMapping(value = "/delete_course_form", method = RequestMethod.POST)
	public String deleteCoursePage(ModelMap model, @RequestParam("courseId") final String courseId) {
		ResponseEntity<String> rs  = coursesService.deleteCourse(courseId);
		if(rs.getStatusCodeValue() != 200) {
			model.put("errorMessage", rs.getBody());
		}
		model.put("courses", coursesService.getAllCourses());
		model.put("instructor", instructorService.getAllInstructor());
		return "courseList";
	}

	@RequestMapping(value = "/addcourse", method = RequestMethod.GET)
	public String showAddCoursePage(ModelMap model) {
		model.put("category", categoryService.getAllCategory());
		model.put("instructor", instructorService.getAllInstructor());
		return "addCourse";
	}

	@RequestMapping(value = "/add_course_form", method = RequestMethod.POST)
	public String showCoursePage(ModelMap model, @RequestParam("courseName") final String courseName,
			@RequestParam("description") final String description,
			@RequestParam("instructorId") final String instructorId, @RequestParam("title") final String title,
			@RequestParam("categoryId") final String categoryId,
			@RequestParam(name = "content_0", required = false) final String content_0,
			@RequestParam(name = "contenttitle_0", required = false) final String contenttitle_0,
			@RequestParam(name = "content_1", required = false) final String content_1,
			@RequestParam(name = "contenttitle_1", required = false) final String contenttitle_1,
			@RequestParam(name = "content_2", required = false) final String content_2,
			@RequestParam(name = "contenttitle_2", required = false) final String contenttitle_2,
			@RequestParam(name = "content_3", required = false) final String content_3,
			@RequestParam(name = "contenttitle_3", required = false) final String contenttitle_3) {
		String jsonRequest = "";
		if (StringUtils.isBlank(content_1) && StringUtils.isBlank(content_2) && StringUtils.isBlank(content_3)) {
			jsonRequest = "{ \"courseName\": \"" + courseName + "\", \"description\": \""
					+ description.replaceAll("[^a-zA-Z0-9]", " ") + "\", \"instructorId\": \"" + instructorId
					+ "\", \"title\": \"" + title + "\", \"categoryId\": \"" + categoryId + "\", \"createdBy\": "
					+ Token.cognitoUserId + " , \"content\": [ { \"content\": \""
					+ content_0.replaceAll("[^a-zA-Z0-9]", " ") + "\", \"title\": \"" + contenttitle_0
					+ "\" , \"contentId\": \"1\" }]  } ";
		} else

		if (StringUtils.isBlank(content_2) && StringUtils.isBlank(content_3)) {
			jsonRequest = "{ \"courseName\": \"" + courseName + "\", \"description\": \""
					+ description.replaceAll("[^a-zA-Z0-9]", " ") + "\", \"instructorId\": \"" + instructorId
					+ "\", \"title\": \"" + title + "\", \"categoryId\": \"" + categoryId + "\", \"createdBy\": "
					+ Token.cognitoUserId + " , \"content\": [ { \"content\": \""
					+ content_0.replaceAll("[^a-zA-Z0-9]", " ") + "\", \"title\": \"" + contenttitle_0
					+ "\" , \"contentId\": \"1\" }, { \"content\": \"" + content_1.replaceAll("[^a-zA-Z0-9]", " ")
					+ "\", \"title\": \"" + contenttitle_1 + "\" , \"contentId\": \"2\" }]  } ";
		} else if (StringUtils.isBlank(content_3)) {
			jsonRequest = "{ \"courseName\": \"" + courseName + "\", \"description\": \""
					+ description.replaceAll("[^a-zA-Z0-9]", " ") + "\", \"instructorId\": \"" + instructorId
					+ "\", \"title\": \"" + title + "\", \"categoryId\": \"" + categoryId + "\", \"createdBy\": "
					+ Token.cognitoUserId + " , \"content\": [ { \"content\": \""
					+ content_0.replaceAll("[^a-zA-Z0-9]", " ") + "\", \"title\": \"" + contenttitle_0
					+ "\" , \"contentId\": \"1\" }, { \"content\": \"" + content_1.replaceAll("[^a-zA-Z0-9]", " ")
					+ "\", \"title\": \"" + contenttitle_1 + "\" , \"contentId\": \"2\" }, { \"content\": \""
					+ content_2.replaceAll("[^a-zA-Z0-9]", " ") + "\", \"title\": \"" + contenttitle_2
					+ "\" , \"contentId\": \"3\" }]  } ";
		} else {
			jsonRequest = "{ \"courseName\": \"" + courseName + "\", \"description\": \""
					+ description.replaceAll("[^a-zA-Z0-9]", " ") + "\", \"instructorId\": \"" + instructorId
					+ "\", \"title\": \"" + title + "\", \"categoryId\": \"" + categoryId + "\", \"createdBy\": "
					+ Token.cognitoUserId + " , \"content\": [ { \"content\": \""
					+ content_0.replaceAll("[^a-zA-Z0-9]", " ") + "\", \"title\": \"" + contenttitle_0
					+ "\" , \"contentId\": \"1\" }, { \"content\": \"" + content_1.replaceAll("[^a-zA-Z0-9]", " ")
					+ "\", \"title\": \"" + contenttitle_1 + "\" , \"contentId\": \"2\" }, { \"content\": \""
					+ content_2.replaceAll("[^a-zA-Z0-9]", " ") + "\", \"title\": \"" + contenttitle_2
					+ "\" , \"contentId\": \"3\" }, { \"content\": \"" + content_3.replaceAll("[^a-zA-Z0-9]", " ")
					+ "\", \"title\": \"" + contenttitle_3 + "\" , \"contentId\": \"4\" }]  } ";
		}
		ResponseEntity<String> rs  =  coursesService.addCourse(jsonRequest);
		if(rs.getStatusCodeValue() != 200) {
			model.put("errorMessage", rs.getBody());
		}
		model.put("courses", coursesService.getAllCourses());
		model.put("instructor", instructorService.getAllInstructor());
		return "courseList";
	}

	@RequestMapping(value = "/update_course_form", method = RequestMethod.POST)
	public String updateCoursePage(ModelMap model, @RequestParam("courseName") final String courseName,
			@RequestParam("description") final String description,
			@RequestParam("instructorId") final String instructorId, @RequestParam("title") final String title,
			@RequestParam("categoryId") final String categoryId, @RequestParam("courseId") final String courseId,
			@RequestParam(name = "content_0", required = false) final String content_0,
			@RequestParam(name = "contenttitle_0", required = false) final String contenttitle_0,
			@RequestParam(name = "content_1", required = false) final String content_1,
			@RequestParam(name = "contenttitle_1", required = false) final String contenttitle_1,
			@RequestParam(name = "content_2", required = false) final String content_2,
			@RequestParam(name = "contenttitle_2", required = false) final String contenttitle_2,
			@RequestParam(name = "content_3", required = false) final String content_3,
			@RequestParam(name = "contenttitle_3", required = false) final String contenttitle_3) {
		String jsonRequest = "";
		if (StringUtils.isBlank(content_1) && StringUtils.isBlank(content_2) && StringUtils.isBlank(content_3)) {
			jsonRequest = "{ \"courseName\": \"" + courseName + "\", \"description\": \""
					+ description.replaceAll("[^a-zA-Z0-9]", " ") + "\", \"instructorId\": \"" + instructorId
					+ "\", \"title\": \"" + title + "\", \"categoryId\": \"" + categoryId + "\", \"lastUpdatedBy\": "
					+ Token.cognitoUserId + " , \"content\": [ { \"content\": \""
					+ content_0.replaceAll("[^a-zA-Z0-9]", " ") + "\", \"title\": \"" + contenttitle_0
					+ "\" , \"contentId\": \"1\" }]  } ";
		} else

		if (StringUtils.isBlank(content_2) && StringUtils.isBlank(content_3)) {
			jsonRequest = "{ \"courseName\": \"" + courseName + "\", \"description\": \""
					+ description.replaceAll("[^a-zA-Z0-9]", " ") + "\", \"instructorId\": \"" + instructorId
					+ "\", \"title\": \"" + title + "\", \"categoryId\": \"" + categoryId + "\", \"lastUpdatedBy\": "
					+ Token.cognitoUserId + " , \"content\": [ { \"content\": \""
					+ content_0.replaceAll("[^a-zA-Z0-9]", " ") + "\", \"title\": \"" + contenttitle_0
					+ "\" , \"contentId\": \"1\" }, { \"content\": \"" + content_1.replaceAll("[^a-zA-Z0-9]", " ")
					+ "\", \"title\": \"" + contenttitle_1 + "\" , \"contentId\": \"2\" }]  } ";
		} else if (StringUtils.isBlank(content_3)) {
			jsonRequest = "{ \"courseName\": \"" + courseName + "\", \"description\": \""
					+ description.replaceAll("[^a-zA-Z0-9]", " ") + "\", \"instructorId\": \"" + instructorId
					+ "\", \"title\": \"" + title + "\", \"categoryId\": \"" + categoryId + "\", \"lastUpdatedBy\": "
					+ Token.cognitoUserId + " , \"content\": [ { \"content\": \""
					+ content_0.replaceAll("[^a-zA-Z0-9]", " ") + "\", \"title\": \"" + contenttitle_0
					+ "\" , \"contentId\": \"1\" }, { \"content\": \"" + content_1.replaceAll("[^a-zA-Z0-9]", " ")
					+ "\", \"title\": \"" + contenttitle_1 + "\" , \"contentId\": \"2\" }, { \"content\": \""
					+ content_2.replaceAll("[^a-zA-Z0-9]", " ") + "\", \"title\": \"" + contenttitle_2
					+ "\" , \"contentId\": \"3\" }]  } ";
		} else {
			jsonRequest = "{ \"courseName\": \"" + courseName + "\", \"description\": \""
					+ description.replaceAll("[^a-zA-Z0-9]", " ") + "\", \"instructorId\": \"" + instructorId
					+ "\", \"title\": \"" + title + "\", \"categoryId\": \"" + categoryId + "\", \"lastUpdatedBy\": "
					+ Token.cognitoUserId + " , \"content\": [ { \"content\": \""
					+ content_0.replaceAll("[^a-zA-Z0-9]", " ") + "\", \"title\": \"" + contenttitle_0
					+ "\" , \"contentId\": \"1\" }, { \"content\": \"" + content_1.replaceAll("[^a-zA-Z0-9]", " ")
					+ "\", \"title\": \"" + contenttitle_1 + "\" , \"contentId\": \"2\" }, { \"content\": \""
					+ content_2.replaceAll("[^a-zA-Z0-9]", " ") + "\", \"title\": \"" + contenttitle_2
					+ "\" , \"contentId\": \"3\" }, { \"content\": \"" + content_3.replaceAll("[^a-zA-Z0-9]", " ")
					+ "\", \"title\": \"" + contenttitle_3 + "\" , \"contentId\": \"4\" }]  } ";
		}

		ResponseEntity<String> rs  =  coursesService.updateCourse(jsonRequest, courseId);
		if(rs.getStatusCodeValue() != 200) {
			model.put("errorMessage", rs.getBody());
		}
		model.put("courses", coursesService.getAllCourses());
		model.put("instructor", instructorService.getAllInstructor());
		return "courseList";
	}

}
