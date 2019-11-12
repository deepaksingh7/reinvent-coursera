package com.coursera.admin.web.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.coursera.admin.web.model.Course;
import com.coursera.admin.web.model.Token;
import com.google.gson.Gson;

@Service
public class CoursesService {
	
	private static final int TIMEOUT = 3000;
	
	@Autowired
	RestTemplate restTemplate;

	@Value("${course.getURL}")
	String serviceURL;

	public List<Course> getAllCourses() {
		Map<String, String> map = new HashMap<String, String>();
		HttpHeaders headers = createHttpHeaders(map);
		headers.add("Authorization", Token.getTokenID());
		Gson gson = new Gson();
		HttpEntity<String> httpReq = new HttpEntity<String>(headers);
		ResponseEntity<String> rs = restTemplate.exchange(serviceURL, HttpMethod.GET, httpReq, String.class);
		Course[] response = gson.fromJson(rs.getBody(), Course[].class);
		return Arrays.asList(response);
	}
	
	private HttpHeaders createHttpHeaders(Map<String, String> map) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		for (Entry<String, String> entry : map.entrySet()) {
			headers.add(entry.getKey(), entry.getValue());
		}
		return headers;
	}

	public Object getCourse(String id) {
		Map<String, String> map = new HashMap<String, String>();
		HttpHeaders headers = createHttpHeaders(map);
		headers.add("Authorization", Token.getTokenID());
		Gson gson = new Gson();
		HttpEntity<String> httpReq = new HttpEntity<String>(headers);
		ResponseEntity<String> rs  = restTemplate.exchange(serviceURL+id,HttpMethod.GET,httpReq, String.class);
		Course response = gson.fromJson(rs.getBody(), Course.class); 
		return Arrays.asList(response);
	}

	public ResponseEntity<String> deleteCourse(String courseId) {
		Map<String, String> map = new HashMap<String, String>();
		HttpHeaders headers = createHttpHeaders(map);
		headers.add("Authorization", Token.getTokenID());
		HttpEntity<String> httpReq = new HttpEntity<String>(headers);
		try {
			ResponseEntity<String> rs  = restTemplate.exchange(serviceURL+courseId,HttpMethod.DELETE,httpReq, String.class);
			return rs;
		} catch(HttpStatusCodeException e) {
	        return ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
	                .body(e.getResponseBodyAsString());
	    }
		
	}

	public ResponseEntity<String> addCourse(String jsonRequest) {
		Map<String, String> map = new HashMap<String, String>();
		HttpHeaders headers = createHttpHeaders(map);
		headers.add("Authorization", Token.getTokenID());
		Gson gson = new Gson();
		Course course = gson.fromJson(jsonRequest, Course.class);
		HttpEntity<Course> entity = new HttpEntity<Course>(course, headers);
		try {
			ResponseEntity<String> response = restTemplate.exchange(serviceURL, HttpMethod.POST, entity, String.class);
			return response;
		} catch(HttpStatusCodeException e) {
	        return ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
	                .body(e.getResponseBodyAsString());
	    }
		
	}

	public ResponseEntity<String> updateCourse(String jsonRequest, String courseId) {
		Map<String, String> map = new HashMap<String, String>();
		HttpHeaders headers = createHttpHeaders(map);
		headers.add("Authorization", Token.getTokenID());
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setConnectTimeout(TIMEOUT);
		requestFactory.setReadTimeout(TIMEOUT);

		restTemplate.setRequestFactory(requestFactory);
		Gson gson = new Gson();
		Course course = gson.fromJson(jsonRequest, Course.class);
		HttpEntity<Course> entity = new HttpEntity<Course>(course, headers);
		try {
			ResponseEntity<String> response = restTemplate.exchange(serviceURL+courseId, HttpMethod.PATCH, entity, String.class);
			return response;
		} catch(HttpStatusCodeException e) {
	        return ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
	                .body(e.getResponseBodyAsString());
	    }
		
	}

}
