package com.StudentMGMT.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.StudentMGMT.util.DatabaseUtil;
import com.StudentMGMT.entities.Class;

public class ClassDao {

    private static final String GET_ALL_CLASSES_SQL = "SELECT class_id, name, building, room, date, start_time, group_id, teacher_id FROM class_schedule";

    public List<Class> getAllClasses() throws ClassNotFoundException {
        List<Class> classes = new ArrayList<>();

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_CLASSES_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Class classInstance = new Class();
                
                classInstance.setClassId(resultSet.getObject("class_id", UUID.class));
                classInstance.setName(resultSet.getString("name"));
                classInstance.setBuilding(resultSet.getString("building"));
                classInstance.setRoom(resultSet.getString("room"));
                classInstance.setDate(resultSet.getObject("date", LocalDate.class));
                classInstance.setStartTime(resultSet.getObject("start_time", LocalTime.class));
                classInstance.setEndTime(resultSet.getObject("end_time", LocalTime.class));
                classInstance.setGroupId(resultSet.getObject("group_id", UUID.class));
                classInstance.setTeacherId(resultSet.getObject("teacher_id", UUID.class));
                
                classes.add(classInstance);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return classes;
    }

    
    private static final String INSERT_CLASS_SCHEDULE_SQL = "INSERT INTO Class_Schedule " +
            "(class_id, group_id, class_name, building, room, teacher_id, date, start_time, end_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public void addClassToSchedule(UUID groupId, String className, String building, String room, UUID teacherId, LocalDate date, LocalTime startTime, LocalTime endTime) throws ClassNotFoundException {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLASS_SCHEDULE_SQL)) {

            UUID classId = UUID.randomUUID();

            preparedStatement.setObject(1, classId);
            preparedStatement.setObject(2, groupId);
            preparedStatement.setString(3, className);
            preparedStatement.setString(4, building);
            preparedStatement.setString(5, room);
            preparedStatement.setObject(6, teacherId);
            preparedStatement.setObject(7, date);
            preparedStatement.setObject(8, startTime);
            preparedStatement.setObject(9, endTime);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Class> getClassesByUserAndDate(UUID userId, LocalDate date, String role) {
        List<Class> classes = new ArrayList<>();
        String query;

        if ("Student".equalsIgnoreCase(role)) {
            query = """
                SELECT c.schedule_id, c.class_name, c.building, c.room, c.date, c.start_time, c.end_time, c.group_id, c.teacher_id 
                FROM class_schedule c 
                JOIN student_group sg ON c.group_id = sg.group_id
                WHERE sg.student_id = ? AND c.date = ?;
            """;
        } else if ("Teacher".equalsIgnoreCase(role)) {
            query = """
                SELECT c.schedule_id, c.class_name, c.building, c.room, c.date, c.start_time, c.end_time, c.group_id, c.teacher_id 
                FROM class_schedule c 
                JOIN teacher_group tg ON c.group_id = tg.group_id
                WHERE tg.teacher_id = ? AND c.date = ?;
            """;
        } else {
            throw new IllegalArgumentException("Invalid role specified: " + role);
        }
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setObject(1, userId);
            statement.setDate(2, Date.valueOf(date));
            System.out.println("Fetching classes for " + role + " ID: " + userId + " on date: " + date);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Class cls = new Class();
                cls.setClassId(rs.getObject("schedule_id", UUID.class));
                cls.setName(rs.getString("class_name"));
                cls.setBuilding(rs.getString("building"));
                cls.setRoom(rs.getString("room"));
                cls.setDate(rs.getDate("date").toLocalDate());
                cls.setStartTime(rs.getTime("start_time").toLocalTime());
                cls.setEndTime(rs.getTime("end_time").toLocalTime());
                cls.setGroupId(rs.getObject("group_id", UUID.class));
                cls.setTeacherId(rs.getObject("teacher_id", UUID.class));
                
                classes.add(cls);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Fetched classes: " + classes);
        return classes;
    }

}
