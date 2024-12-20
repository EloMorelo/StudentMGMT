package com.StudentMGMT.servlets;

import com.StudentMGMT.dao.UserDao;
import com.StudentMGMT.entities.User;
import com.StudentMGMT.util.CreateAssessment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


@WebServlet("/user-creation")
public class UserCreationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao userDao = new UserDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("UserCreationServlet invoked.");
//        String recaptchaSecretKey = (String) getServletContext().getAttribute("RECAPTCHA_SECRET_KEY");
//        if (recaptchaSecretKey == null) {
//            throw new IllegalStateException("RECAPTCHA_SECRET_KEY not loaded.");
//        }
        String captchaResponse = request.getParameter("g-recaptcha-response");
        if (captchaResponse == null || captchaResponse.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "CAPTCHA token is missing.");
            return;
        }
        String projectID = "";
        String recaptchaKey = "";
        String recaptchaAction = "submit";

        try {
            CreateAssessment.createAssessment(projectID, recaptchaKey, captchaResponse, recaptchaAction);
        } catch (IOException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error validating CAPTCHA.");
            return;
        }
        response.getWriter().write("CAPTCHA validation successful!");


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
            request.setAttribute("login", user.getLogin());
            request.setAttribute("password", user.getPassword());
            System.out.println("User created successfully: " + user.getLogin());
            String encodedLogin = URLEncoder.encode(user.getLogin(), StandardCharsets.UTF_8.toString());
            String encodedPassword = URLEncoder.encode(user.getPassword(), StandardCharsets.UTF_8.toString());
            response.sendRedirect("login?newUserLogin=" + encodedLogin + "&newUserPassword=" + encodedPassword);
        } else {
            System.err.println("Failed to create user. Result: " + result);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error occurred while creating user.");
        }
    }

}

