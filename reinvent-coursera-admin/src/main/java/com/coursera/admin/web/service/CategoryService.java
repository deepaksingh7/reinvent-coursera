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

import com.coursera.admin.web.model.Category;
import com.coursera.admin.web.model.Token;
import com.google.gson.Gson;

@Service
public class CategoryService {
	
	private static final int TIMEOUT = 3000;

	@Autowired
	RestTemplate restTemplate;

	@Value("${category.getURL}")
	String serviceURL;
	

	public List<Category> getAllCategory() {
		HttpHeaders headers = getToken();
		Gson gson = new Gson();
		HttpEntity<String> httpReq = new HttpEntity<String>(headers);
		ResponseEntity<String> rs = restTemplate.exchange(serviceURL, HttpMethod.GET, httpReq, String.class);
		Category[] response = gson.fromJson(rs.getBody(), Category[].class);
		return Arrays.asList(response);
	}

	public Object getCategory(String id) {
		HttpHeaders headers = getToken();
		Gson gson = new Gson();
		HttpEntity<String> httpReq = new HttpEntity<String>(headers);
		ResponseEntity<String> rs  = restTemplate.exchange(serviceURL+id,HttpMethod.GET,httpReq, String.class);
		Category response = gson.fromJson(rs.getBody(), Category.class); 
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

	public ResponseEntity<String> deleteCategory(String categoryId) {
		HttpHeaders headers = getToken();
		HttpEntity<String> httpReq = new HttpEntity<String>(headers);
		try {
			ResponseEntity<String> rs  = restTemplate.exchange(serviceURL+categoryId,HttpMethod.DELETE,httpReq, String.class);
			return rs;
		} catch(HttpStatusCodeException e) {
	        return ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
	                .body(e.getResponseBodyAsString());
	    }
		
	}

	public ResponseEntity<String> addCategory(String jsonRequest) {
		HttpHeaders headers = getToken();
		Gson gson = new Gson();
		Category category = gson.fromJson(jsonRequest, Category.class);
		HttpEntity<Category> entity = new HttpEntity<Category>(category, headers);
		try {
			ResponseEntity<String> response = restTemplate.exchange(serviceURL, HttpMethod.POST, entity, String.class);
			return response;
		} catch(HttpStatusCodeException e) {
	        return ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
	                .body(e.getResponseBodyAsString());
	    }
		
	}

	public ResponseEntity<String> updateCategory(String jsonRequest, String categoryId) {
		HttpHeaders headers = getToken();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setConnectTimeout(TIMEOUT);
		requestFactory.setReadTimeout(TIMEOUT);

		restTemplate.setRequestFactory(requestFactory);
		Gson gson = new Gson();
		Category category = gson.fromJson(jsonRequest, Category.class);
		HttpEntity<Category> entity = new HttpEntity<Category>(category, headers);
		try {
			ResponseEntity<String> response = restTemplate.exchange(serviceURL+categoryId, HttpMethod.PATCH, entity, String.class);
			return response;
		} catch(HttpStatusCodeException e) {
	        return ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
	                .body(e.getResponseBodyAsString());
	    }
		
	}
	
	private HttpHeaders getToken() {
		Map<String, String> map = new HashMap<String, String>();
		HttpHeaders headers = createHttpHeaders(map);
		headers.add("Authorization", Token.getTokenID());
		return headers;
	}

	

}
