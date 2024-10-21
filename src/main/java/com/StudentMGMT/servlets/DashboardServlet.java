package com.StudentMGMT.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.StudentMGMT.entities.User;
import com.StudentMGMT.services.CourseService;
import com.StudentMGMT.services.EventService;
import com.StudentMGMT.services.GradeService;


@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private CourseService courseService = new CourseService();
    private GradeService gradeService = new GradeService();
    private EventService eventService = new EventService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        if (user == null || user.getId() == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return; 
        }

        request.setAttribute("courses", courseService.getCoursesByUser(user.getId()));
        request.setAttribute("grades", gradeService.getGradesByUser(user.getId()));
        request.setAttribute("events", eventService.getUpcomingEventsByUser(user.getId()));
        
        if ("Teacher".equals(user.getRole())) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/TeacherDashboard.jsp");
            dispatcher.forward(request, response);
        } else if ("Student".equals(user.getRole())) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/StudentDashboard.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

