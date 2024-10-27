package com.StudentMGMT.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.StudentMGMT.entities.Course;
import com.StudentMGMT.util.DatabaseUtil;

public class CourseDao {

    public List<Course> getCoursesByUser(UUID userId) throws ClassNotFoundException {
        List<Course> courses = new ArrayList<>();
        String SELECT_COURSES_BY_USER_SQL = "SELECT c.id, c.name, c.description " +
                                            "FROM Courses c " +
                                            "INNER JOIN UserCourses uc ON c.id = uc.course_id " +
                                            "WHERE uc.user_id = ?";

        try (Connection connection = DatabaseUtil.getConnection();
        	    PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COURSES_BY_USER_SQL)) {

        		preparedStatement.setObject(1, userId);

        	    ResultSet resultSet = preparedStatement.executeQuery();

        	    while (resultSet.next()) {
        	        Course course = new Course();
                    course.setId(resultSet.getObject("id", UUID.class));
        	        course.setName(resultSet.getString("name"));
        	        course.setDescription(resultSet.getString("description"));
        	        courses.add(course);
        	    }

        	} catch (SQLException e) {
        	    e.printStackTrace();
        	}

        return courses;
    }
}

