package com.StudentMGMT.entities;

import java.io.Serializable;
import java.util.UUID;

public class Grade implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id;
    private UUID userId;
    private UUID courseId;
    private String grade;

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
}
