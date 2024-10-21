package com.StudentMGMT.services;

import java.util.List;
import java.util.UUID;

import com.StudentMGMT.dao.CourseDao;
import com.StudentMGMT.entities.Course;

public class CourseService {
    private CourseDao courseDao = new CourseDao();

    public List<Course> getCoursesByUser(UUID userId) {
        try {
            return courseDao.getCoursesByUser(userId);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
