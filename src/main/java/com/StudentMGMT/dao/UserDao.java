package com.StudentMGMT.dao;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.StudentMGMT.entities.User;
import com.StudentMGMT.util.DatabaseUtil;

public class UserDao {

	public int registerUser(User user, int passwordLength) {
	    String INSERT_USERS_SQL = "INSERT INTO Users (id, login, password, email, role) VALUES (?, ?, ?, ?, ?);";
	    int result = 0;
	    

	    
	    try (Connection connection = DatabaseUtil.getConnection()) {
	        connection.setAutoCommit(false);

	        UUID userId = UUID.randomUUID();
	        String userLogin = generateUserLogin(user);
	        String generatedPassword = generateRandomPassword(passwordLength);
	        
	        
	        user.setLogin(userLogin);
	        user.setPassword(generatedPassword);
	        
	        System.out.println("Generated userId: " + userId);
	        System.out.println("Generated userLogin: " + userLogin);
	        System.out.println("Generated password: " + generatedPassword);
	        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
	            preparedStatement.setObject(1, userId);
	            preparedStatement.setString(2, userLogin);
	            preparedStatement.setString(3, generatedPassword);
	            preparedStatement.setString(4, user.getEmail());
	            preparedStatement.setString(5, user.getRole());

	            result = preparedStatement.executeUpdate();
	            System.out.println("User insert executed, result: " + result);
	        }

	        connection.commit();

	    } catch (SQLException e) {
	        System.err.println("SQL exception occurred while registering user: " + e.getMessage());
	        e.printStackTrace();
	    }

	    return result;
	}

    
    private String generateUserLogin(User user) throws SQLException {
        String counterQuery = "SELECT studentCounter, teacherCounter FROM UserCounter WHERE id = 1";
        int studentCounter = 0;
        int teacherCounter = 0;

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement counterStmt = connection.prepareStatement(counterQuery);
             ResultSet resultSet = counterStmt.executeQuery()) {

            if (resultSet.next()) {
                studentCounter = resultSet.getInt("studentCounter");
                teacherCounter = resultSet.getInt("teacherCounter");
            }
        }

        String newLogin;

        if ("Student".equals(user.getRole())) {
            studentCounter++;
            newLogin = "sms" + String.format("%05d", studentCounter); // sms00001...
            String updateCounterQuery = "UPDATE UserCounter SET studentCounter = ? WHERE id = 1";
            try (Connection connection = DatabaseUtil.getConnection();
                 PreparedStatement updateCounterStmt = connection.prepareStatement(updateCounterQuery)) {
                updateCounterStmt.setInt(1, studentCounter);
                updateCounterStmt.executeUpdate();
            }
        } else if ("Teacher".equals(user.getRole())) {
            teacherCounter++;
            newLogin = "smst" + String.format("%05d", teacherCounter); // smst00001...
            String updateCounterQuery = "UPDATE UserCounter SET teacherCounter = ? WHERE id = 1";
            try (Connection connection = DatabaseUtil.getConnection();
                 PreparedStatement updateCounterStmt = connection.prepareStatement(updateCounterQuery)) {
                updateCounterStmt.setInt(1, teacherCounter);
                updateCounterStmt.executeUpdate();
            }
        } else {
            throw new IllegalArgumentException("Invalid user role");
        }

        return newLogin;
    }
    
    private String generateRandomPassword(int length) {
        String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_+=";
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(allowedChars.length());
            password.append(allowedChars.charAt(index));
        }

        return password.toString();
    }

    public User findUserByLogin(String login) {
        String SELECT_USER_SQL = "SELECT * FROM Users WHERE login = ?;";
        User user = null;

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_SQL)) {

            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setId(UUID.fromString(resultSet.getString("id")));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setRole(resultSet.getString("role"));
            }

        } catch (SQLException e) {
            System.err.println("SQL exception occurred while retrieving user: " + e.getMessage());
            e.printStackTrace();
        }

        return user;
    }
    
    public void UpdatePassword(UUID userId, String newPassword) {
        String sql = "UPDATE users SET password = ? WHERE id = ?";
        
        try (Connection connection = DatabaseUtil.getConnection(); 
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
             
            preparedStatement.setString(1, newPassword);
            preparedStatement.setObject(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void UpdateEmail(UUID userId, String newEmail) {
        String sql = "UPDATE users SET email = ? WHERE id = ?";

        try (Connection connection = DatabaseUtil.getConnection(); 
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, newEmail);
            preparedStatement.setObject(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void DeleteUser (UUID userId) {
    	String sql = "DELETE FROM users WHERE id = ?";
        
        try (Connection connection = DatabaseUtil.getConnection(); 
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
}

