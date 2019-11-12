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
@JsonPropertyOrder({ "lastUpdatedBy", "createdAt", "categoryName", "createdBy", "categoryId", "updatedAt" })
public class Category {

	@JsonProperty("lastUpdatedBy")
	private String lastUpdatedBy;
	@JsonProperty("createdAt")
	private String createdAt;
	@JsonProperty("categoryName")
	private String categoryName;
	@JsonProperty("createdBy")
	private String createdBy;
	@JsonProperty("categoryId")
	private String categoryId;
	@JsonProperty("updatedAt")
	private String updatedAt;
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

	@JsonProperty("createdAt")
	public String getCreatedAt() {
		return createdAt;
	}

	@JsonProperty("createdAt")
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	@JsonProperty("categoryName")
	public String getCategoryName() {
		return categoryName;
	}

	@JsonProperty("categoryName")
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@JsonProperty("createdBy")
	public String getCreatedBy() {
		return createdBy;
	}

	@JsonProperty("createdBy")
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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
		return "Category [lastUpdatedBy=" + lastUpdatedBy + ", createdAt=" + createdAt + ", categoryName="
				+ categoryName + ", createdBy=" + createdBy + ", categoryId=" + categoryId + ", updatedAt=" + updatedAt
				+ ", additionalProperties=" + additionalProperties + "]";
	}

}