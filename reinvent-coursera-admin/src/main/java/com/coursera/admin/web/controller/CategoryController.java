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

@Controller
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;

	@RequestMapping(value = "/category", method = RequestMethod.GET)
	public String showCategoryPage(ModelMap model) {
		model.put("category", categoryService.getAllCategory());
        return "categoryList";
	}
	
	@RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
	public String showCategoryPage(ModelMap model, @PathVariable String id) {
		model.put("category", categoryService.getCategory(id));
		return "update_category";
	}
	
	@RequestMapping(value = "/delete_category_form", method = RequestMethod.POST)
	public String deleteCategoryPage(ModelMap model, @RequestParam("categoryId") final String categoryId) {
		ResponseEntity<String> rs  = categoryService.deleteCategory(categoryId);
		if(rs.getStatusCodeValue() != 200) {
			model.put("errorMessage", rs.getBody());
		}
		model.put("category", categoryService.getAllCategory());
		return "categoryList";
	}

	@RequestMapping(value = "/addcategory", method = RequestMethod.GET)
	public String showAddCategoryPage(ModelMap model) {
		return "addCategory";
	}

	@RequestMapping(value = "/add_category_form", method = RequestMethod.POST)
	public String showInstructorPage(ModelMap model, @RequestParam("categoryName") final String categoryName) {
		String jsonRequest = "{ \"categoryName\": \""
				+ categoryName + "\", \"createdBy\": "+Token.cognitoUserId +" }";
		ResponseEntity<String> rs  = categoryService.addCategory(jsonRequest);
		if(rs.getStatusCodeValue() != 200) {
			model.put("errorMessage", rs.getBody());
		}
		model.put("category", categoryService.getAllCategory());
		return "categoryList";
	}
	
	@RequestMapping(value = "/update_category_form", method = RequestMethod.POST)
	public String updateCategoryPage(ModelMap model, @RequestParam("categoryName") final String categoryName,
			@RequestParam("categoryId") final String categoryId) {
		String jsonRequest = "{ \"categoryName\": \""
				+ categoryName +  "\" , \"lastUpdatedBy\": "+Token.cognitoUserId +" }";
		ResponseEntity<String> rs  =  categoryService.updateCategory(jsonRequest, categoryId);
		if(rs.getStatusCodeValue() != 200) {
			model.put("errorMessage", rs.getBody());
		}
		model.put("category", categoryService.getAllCategory());
		return "categoryList";
	}

}
