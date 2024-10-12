package com.StudentMGMT.services;

import com.StudentMGMT.dao.UserDao;
import com.StudentMGMT.entities.User;

public class UserService {
    private UserDao userDao = new UserDao();

    public User getUserByLogin(String login) throws ClassNotFoundException {
    	User user = userDao.findUserByLogin(login);
        return user;
    }
    
    
    public boolean register(User user) {
        try {
            if (userDao.findUserByLogin(user.getLogin()) != null) {
                System.out.println("User already exists!");
                return false;
            }
            userDao.registerUser(user);
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean login(String login, String password) {
        try {
            User user = userDao.findUserByLogin(login);
            if (user != null && user.getPassword().equals(password)) {
                return true;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    

}
