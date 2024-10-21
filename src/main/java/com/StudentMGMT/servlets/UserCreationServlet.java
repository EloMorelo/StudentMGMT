package com.StudentMGMT.servlets;

import com.StudentMGMT.dao.UserDao;
import com.StudentMGMT.entities.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user-creation")
public class UserCreationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao userDao = new UserDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("UserCreationServlet invoked."); 

        String role = request.getParameter("role");
        System.out.println("Role received: " + role);
        if (role == null || (!role.equals("Student") && !role.equals("Teacher"))) {
            System.err.println("Invalid role specified.");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid role specified.");
            return;
        }

        User user = new User();
        user.setRole(role);
        user.setEmail(role.toLowerCase() + "@example.com");

        System.out.println("Creating user with role: " + role + ", email: " + user.getEmail());

        int result = userDao.registerUser(user, 8); 

        if (result > 0) {
            System.out.println("User created successfully: " + user.getLogin());
            request.setAttribute("login", user.getLogin());
            request.setAttribute("password", user.getPassword());
            request.getRequestDispatcher("/UserLogin.jsp").forward(request, response);
        } else {
            System.err.println("Failed to create user. Result: " + result);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error occurred while creating user.");
        }
    }
}

