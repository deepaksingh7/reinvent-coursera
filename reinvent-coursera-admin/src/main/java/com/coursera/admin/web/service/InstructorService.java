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

import com.coursera.admin.web.model.Instructor;
import com.coursera.admin.web.model.Token;
import com.google.gson.Gson;

@Service
public class InstructorService {
	
	private static final int TIMEOUT = 3000;

	@Autowired
	RestTemplate restTemplate;

	@Value("${instructor.getURL}")
	String serviceURL;

	SessionInfo sessioninfo;
	
	public List<Instructor> getAllInstructor() {
		Map<String, String> map = new HashMap<String, String>();
		HttpHeaders headers = createHttpHeaders(map);
		//String token = sessioninfo.getIdnetityToken();
		headers.add("Authorization", Token.getTokenID());
		
		Gson gson = new Gson();
		HttpEntity<String> httpReq = new HttpEntity<String>(headers);
		ResponseEntity<String> rs  = restTemplate.exchange(serviceURL,HttpMethod.GET,httpReq, String.class);
		Instructor[] response = gson.fromJson(rs.getBody(), Instructor[].class); 
		return Arrays.asList(response);
	}
	
	public List<Instructor> getInstructor(String id) {
		Map<String, String> map = new HashMap<String, String>();
		HttpHeaders headers = createHttpHeaders(map);
		headers.add("Authorization", Token.getTokenID());
		Gson gson = new Gson();
		HttpEntity<String> httpReq = new HttpEntity<String>(headers);
		ResponseEntity<String> rs  = restTemplate.exchange(serviceURL+id,HttpMethod.GET,httpReq, String.class);
		Instructor response = gson.fromJson(rs.getBody(), Instructor.class); 
		return Arrays.asList(response);
	}

	public ResponseEntity<String> deleteInstructor(String instructorId) {
		Map<String, String> map = new HashMap<String, String>();
		HttpHeaders headers = createHttpHeaders(map);
		headers.add("Authorization", Token.getTokenID());
		HttpEntity<String> httpReq = new HttpEntity<String>(headers);
		try {
			ResponseEntity<String> rs  = restTemplate.exchange(serviceURL+instructorId,HttpMethod.DELETE,httpReq, String.class);
			return rs;
		} catch(HttpStatusCodeException e) {
	        return ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
	                .body(e.getResponseBodyAsString());
	    }
	}

	public ResponseEntity<String> addInstructor(String jsonRequest) {
		Map<String, String> map = new HashMap<String, String>();
		HttpHeaders headers = createHttpHeaders(map);
		headers.add("Authorization", Token.getTokenID());
		Gson gson = new Gson();
		Instructor instructor = gson.fromJson(jsonRequest, Instructor.class);
		HttpEntity<Instructor> entity = new HttpEntity<Instructor>(instructor, headers);
		try {
			ResponseEntity<String> response = restTemplate.exchange(serviceURL, HttpMethod.POST, entity, String.class);
			return response;
		} catch(HttpStatusCodeException e) {
	        return ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
	                .body(e.getResponseBodyAsString());
	    }
		
	}
	
	
	
	private HttpHeaders createHttpHeaders(Map<String, String> map) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		for (Entry<String, String> entry : map.entrySet()) {
			headers.add(entry.getKey(), entry.getValue());
		}
		return headers;
	}

	public ResponseEntity<String> updateInstructor(String jsonRequest, String instructor_id) {
		Map<String, String> map = new HashMap<String, String>();
		HttpHeaders headers = createHttpHeaders(map);
		headers.add("Authorization", Token.getTokenID());
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setConnectTimeout(TIMEOUT);
		requestFactory.setReadTimeout(TIMEOUT);

		restTemplate.setRequestFactory(requestFactory);
		Gson gson = new Gson();
		Instructor instructor = gson.fromJson(jsonRequest, Instructor.class);
		HttpEntity<Instructor> entity = new HttpEntity<Instructor>(instructor, headers);
		try {
			ResponseEntity<String> response = restTemplate.exchange(serviceURL+instructor_id, HttpMethod.PATCH, entity, String.class);
			return response;
		} catch(HttpStatusCodeException e) {
	        return ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
	                .body(e.getResponseBodyAsString());
	    }
		
	}

}
