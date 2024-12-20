package com.StudentMGMT.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.StudentMGMT.entities.Grade;
import com.StudentMGMT.util.DatabaseUtil;

public class GradeDao {

	public List<Grade> getGradesByUser(UUID userId) throws ClassNotFoundException {
        List<Grade> grades = new ArrayList<>();
        String SELECT_GRADES_BY_USER_SQL = "SELECT g.id, g.user_id, g.course_id, g.grade, g.grade_description, g.grade_weight, c.name AS course_name\n" +
                "FROM grades g\n" +
                "JOIN courses c ON g.course_id = c.course_id\n" +
                "WHERE g.user_id = ?";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GRADES_BY_USER_SQL)) {

            preparedStatement.setObject(1, userId); 
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Grade grade = new Grade();
                    grade.setId(UUID.fromString(resultSet.getString("id"))); 
                    grade.setUserId(UUID.fromString(resultSet.getString("user_id"))); 
                    grade.setCourseId(UUID.fromString(resultSet.getString("course_id"))); 
                    grade.setGrade(resultSet.getString("grade"));
                    grade.setDescription(resultSet.getString("grade_description"));
                    grade.setWeight(resultSet.getInt("grade_weight"));
                    grade.setCourseName(resultSet.getString("course_name"));
                    grades.add(grade);
                }
                System.out.println("Grades for user " + userId + ": " + grades);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return grades;
    }
}	
