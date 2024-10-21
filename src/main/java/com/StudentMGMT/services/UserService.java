package com.StudentMGMT.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
    
    
}

