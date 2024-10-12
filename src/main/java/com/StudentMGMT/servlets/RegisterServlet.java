package com.StudentMGMT.servlets;

import java.io.IOException;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.StudentMGMT.entities.User;
import com.StudentMGMT.services.UserService;


@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService = new UserService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/UserRegister.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User();
        user.setLogin(login);
        user.setEmail(email);
        user.setPassword(password);

        boolean isRegistered = userService.register(user);
        if (isRegistered) {
            response.sendRedirect("login");
        } else {
            request.setAttribute("error", "User already exists or registration failed.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/UserRegister.jsp");
            dispatcher.forward(request, response);
        }
    }
}

