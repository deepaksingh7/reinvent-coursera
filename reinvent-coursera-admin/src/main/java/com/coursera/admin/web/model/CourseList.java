
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
@JsonPropertyOrder({
    "id",
    "courseName",
    "courseDesc",
    "courseIntructor"
})
public class CourseList {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("courseName")
    private String courseName;
    @JsonProperty("courseDesc")
    private String courseDesc;
    @JsonProperty("courseIntructor")
    private String courseIntructor;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("courseName")
    public String getCourseName() {
        return courseName;
    }

    @JsonProperty("courseName")
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @JsonProperty("courseDesc")
    public String getCourseDesc() {
        return courseDesc;
    }

    @JsonProperty("courseDesc")
    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }

    @JsonProperty("courseIntructor")
    public String getCourseIntructor() {
        return courseIntructor;
    }

    @JsonProperty("courseIntructor")
    public void setCourseIntructor(String courseIntructor) {
        this.courseIntructor = courseIntructor;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }


}
