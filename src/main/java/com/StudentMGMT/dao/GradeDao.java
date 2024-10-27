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
        String SELECT_GRADES_BY_USER_SQL = "SELECT id, user_id, course_id, grade FROM Grades WHERE user_id = ?";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GRADES_BY_USER_SQL)) {

            preparedStatement.setObject(1, userId); 
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Grade grade = new Grade();
                    grade.setId(UUID.fromString(resultSet.getString("id"))); // Convert to UUID
                    grade.setUserId(UUID.fromString(resultSet.getString("user_id"))); // Convert to UUID
                    grade.setCourseId(UUID.fromString(resultSet.getString("course_id"))); // Convert to UUID
                    grade.setGrade(resultSet.getString("grade")); 
                    grades.add(grade);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return grades;
    }
}	
