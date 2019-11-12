package com.coursera.admin.web.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "lastUpdatedBy", "occupation", "instructorId", "lastName", "updatedAt", "createdAt", "description",
		"email", "createdBy", "firstName" })
public class Instructor {

	@JsonProperty("lastUpdatedBy")
	private String lastUpdatedBy;
	@JsonProperty("occupation")
	private String occupation;
	@JsonProperty("instructorId")
	private String instructorId;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("updatedAt")
	private String updatedAt;
	@JsonProperty("createdAt")
	private String createdAt;
	@JsonProperty("description")
	private String description;
	@JsonProperty("email")
	private String email;
	@JsonProperty("createdBy")
	private String createdBy;
	@JsonProperty("firstName")
	private String firstName;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("lastUpdatedBy")
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	@JsonProperty("lastUpdatedBy")
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	@JsonProperty("occupation")
	public String getOccupation() {
		return occupation;
	}

	@JsonProperty("occupation")
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	@JsonProperty("instructorId")
	public String getInstructorId() {
		return instructorId;
	}

	@JsonProperty("instructorId")
	public void setInstructorId(String instructorId) {
		this.instructorId = instructorId;
	}

	@JsonProperty("lastName")
	public String getLastName() {
		return lastName;
	}

	@JsonProperty("lastName")
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@JsonProperty("updatedAt")
	public String getUpdatedAt() {
		return updatedAt;
	}

	@JsonProperty("updatedAt")
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
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

	@JsonProperty("email")
	public String getEmail() {
		return email;
	}

	@JsonProperty("email")
	public void setEmail(String email) {
		this.email = email;
	}

	@JsonProperty("createdBy")
	public String getCreatedBy() {
		return createdBy;
	}

	@JsonProperty("createdBy")
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@JsonProperty("firstName")
	public String getFirstName() {
		return firstName;
	}

	@JsonProperty("firstName")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
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
		return "Instructor [lastUpdatedBy=" + lastUpdatedBy + ", occupation=" + occupation + ", instructorId="
				+ instructorId + ", lastName=" + lastName + ", updatedAt=" + updatedAt + ", createdAt=" + createdAt
				+ ", description=" + description + ", email=" + email + ", createdBy=" + createdBy + ", firstName="
				+ firstName + ", additionalProperties=" + additionalProperties + "]";
	}

}