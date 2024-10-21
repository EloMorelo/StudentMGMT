package com.StudentMGMT.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.StudentMGMT.entities.Event;
import com.StudentMGMT.util.DatabaseUtil;

public class EventDao {

    public List<Event> getAllEvents() throws ClassNotFoundException {
        List<Event> events = new ArrayList<>();
        String SELECT_ALL_EVENTS_SQL = "SELECT * FROM Events";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EVENTS_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Event event = new Event();
	            event.setId(UUID.fromString(resultSet.getString("id"))); // Convert to UUID
                event.setTitle(resultSet.getString("title"));
                event.setDescription(resultSet.getString("description"));
                event.setDate(resultSet.getDate("date"));
                events.add(event);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return events;
    }
}
