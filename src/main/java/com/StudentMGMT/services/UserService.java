package com.StudentMGMT.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.StudentMGMT.dao.UserDao;
import com.StudentMGMT.entities.User;
import com.StudentMGMT.util.DatabaseUtil;

public class UserService {
    private UserDao userDao = new UserDao();

    public User getUserByLogin(String login) {
        return userDao.findUserByLogin(login);
    }
    

    public boolean login(String login, String password) {
        try {
            User user = userDao.findUserByLogin(login);
            if (user != null && user.getPassword().equals(password)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public void updatePassword(UUID userId, String newPassword) {
        //String hashedPassword = hashPassword(newPassword); 
        
        userDao.UpdatePassword(userId, newPassword);
    }
    
    public void UpdateEmail(UUID userId, String newEmail) {
        userDao.UpdateEmail(userId, newEmail);
    }
    
//    private String hashPassword(String password) {
//        return BCrypt.hashpw(password, BCrypt.gensalt());
//    	}
//	}

    
    public List<User> getAllStudents() throws Exception {
        List<User> students = new ArrayList<>();
        String query = "SELECT id, login, email FROM users WHERE role = 'Student'";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                User student = new User();
                student.setId(rs.getObject("id", UUID.class));
                student.setLogin(rs.getString("login"));
                student.setEmail(rs.getString("email"));
                students.add(student);
            }
        }
        return students;
    }
    
	public void DeleteUser(UUID userId) {
        userDao.DeleteUser(userId);
    }
}

