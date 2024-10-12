package com.StudentMGMT.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.StudentMGMT.entities.User;

public class UserDao {

    public int registerUser(User user) throws ClassNotFoundException {
        String INSERT_USERS_SQL = "INSERT INTO Users (login, password, email) VALUES (?, ?, ?);";
        int result = 0;

        Class.forName("org.postgresql.Driver");

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SMS", "postgres", "admin1122");
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {

            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());

            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("SQL exception occurred while registering user: " + e.getMessage());
            e.printStackTrace();
        }

        return result;
    }

    public User findUserByLogin(String login) throws ClassNotFoundException {
        String SELECT_USER_SQL = "SELECT * FROM Users WHERE login = ?;";
        User user = null;

        Class.forName("org.postgresql.Driver");

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SMS", "postgres", "admin1122");
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_SQL)) {

            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
            }

        } catch (SQLException e) {
            System.err.println("SQL exception occurred while retrieving user: " + e.getMessage());
            e.printStackTrace();
        }

        return user;
    }
}

