package com.StudentMGMT.entities;

import java.io.Serializable;
import java.util.UUID;

public class Grade implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id;
    private UUID userId;
    private UUID courseId;
    private String grade;
    private String description;
    private Integer weight;
    private String courseName;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getCourseId() {
        return courseId;
    }

    public void setCourseId(UUID courseId) {
        this.courseId = courseId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    public Integer getWeight() {return weight;}

    public void setWeight(Integer weight) {this.weight = weight;}

    public String getCourseName() {return courseName;}

    public void setCourseName(String courseName) {this.courseName = courseName;}
}
