package com.StudentMGMT.servlets;

import com.StudentMGMT.entities.User;
import com.StudentMGMT.services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/account-settings")
public class AccountSettingsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService = new UserService(); 

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            request.setAttribute("user", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/Settings.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        User user = (User) session.getAttribute("user");
        String action = request.getParameter("action");
        if ("changeEmail".equals(action)) {
            String newEmail = request.getParameter("newEmail");
            handleEmailChange(user, newEmail, request, response);
        } else if ("changePassword".equals(action)) {
            String currentPassword = request.getParameter("currentPassword");
            String newPassword = request.getParameter("newPassword");
            String confirmPassword = request.getParameter("confirmPassword");
            handlePasswordChange(user, currentPassword, newPassword, confirmPassword, request, response);
        } else if ("deleteAccount".equals(action)) {
            handleAccountDeletion(user, request, response);
        }
    }

    private void handleEmailChange(User user, String newEmail, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (newEmail != null && !newEmail.isEmpty()) {
            userService.UpdateEmail(user.getId(), newEmail); 
            user.setEmail(newEmail);
            request.setAttribute("successMessage", "Email updated successfully!");
        } else {
            request.setAttribute("errorMessage", "Email cannot be empty.");
        }
        doGet(request, response);
    }

    private void handlePasswordChange(User user, String currentPassword, String newPassword, String confirmPassword, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!user.getPassword().equals(currentPassword)) {
            request.setAttribute("errorMessage", "Current password is incorrect.");
        } else if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("errorMessage", "New passwords do not match.");
        } else if (newPassword.isEmpty()) {
            request.setAttribute("errorMessage", "New password cannot be empty.");
        } else {
            userService.updatePassword(user.getId(), newPassword);
            user.setPassword(newPassword);
            request.setAttribute("successMessage", "Password updated successfully!");
        }
        doGet(request, response);
    }

    private void handleAccountDeletion(User user, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userService.DeleteUser(user.getId());
        HttpSession session = request.getSession();
        session.invalidate(); 
        response.sendRedirect(request.getContextPath() + "/login?message=Account deleted successfully");
    }
}
