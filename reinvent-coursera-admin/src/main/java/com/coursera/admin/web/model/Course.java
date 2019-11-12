package com.coursera.admin.web.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "lastUpdatedBy", "content", "instructorId", "categoryId", "updatedAt", "courseId", "createdAt",
		"description", "createdBy", "courseName", "title" })
public class Course {

	@JsonProperty("lastUpdatedBy")
	private Object lastUpdatedBy;
	@JsonProperty("content")
	private List<Content> content = null;
	@JsonProperty("instructorId")
	private String instructorId;
	@JsonProperty("categoryId")
	private String categoryId;
	@JsonProperty("updatedAt")
	private String updatedAt;
	@JsonProperty("courseId")
	private String courseId;
	@JsonProperty("createdAt")
	private String createdAt;
	@JsonProperty("description")
	private String description;
	@JsonProperty("createdBy")
	private Object createdBy;
	@JsonProperty("courseName")
	private String courseName;
	@JsonProperty("title")
	private String title;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("lastUpdatedBy")
	public Object getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	@JsonProperty("lastUpdatedBy")
	public void setLastUpdatedBy(Object lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	@JsonProperty("content")
	public List<Content> getContent() {
		return content;
	}

	@JsonProperty("content")
	public void setContent(List<Content> content) {
		this.content = content;
	}

	@JsonProperty("instructorId")
	public String getInstructorId() {
		return instructorId;
	}

	@JsonProperty("instructorId")
	public void setInstructorId(String instructorId) {
		this.instructorId = instructorId;
	}

	@JsonProperty("categoryId")
	public String getCategoryId() {
		return categoryId;
	}

	@JsonProperty("categoryId")
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	@JsonProperty("updatedAt")
	public String getUpdatedAt() {
		return updatedAt;
	}

	@JsonProperty("updatedAt")
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	@JsonProperty("courseId")
	public String getCourseId() {
		return courseId;
	}

	@JsonProperty("courseId")
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	@JsonProperty("createdAt")
	public String getCreatedAt() {
		return createdAt;
	}

	@JsonProperty("createdAt")
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
	}

	@JsonProperty("createdBy")
	public Object getCreatedBy() {
		return createdBy;
	}

	@JsonProperty("createdBy")
	public void setCreatedBy(Object createdBy) {
		this.createdBy = createdBy;
	}

	@JsonProperty("courseName")
	public String getCourseName() {
		return courseName;
	}

	@JsonProperty("courseName")
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	@JsonProperty("title")
	public String getTitle() {
		return title;
	}

	@JsonProperty("title")
	public void setTitle(String title) {
		this.title = title;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@Override
	public String toString() {
		return "Course [lastUpdatedBy=" + lastUpdatedBy + ", content=" + content + ", instructorId=" + instructorId
				+ ", categoryId=" + categoryId + ", updatedAt=" + updatedAt + ", courseId=" + courseId + ", createdAt="
				+ createdAt + ", description=" + description + ", createdBy=" + createdBy + ", courseName=" + courseName
				+ ", title=" + title + ", additionalProperties=" + additionalProperties + "]";
	}

}