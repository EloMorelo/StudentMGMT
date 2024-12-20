package com.StudentMGMT.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.StudentMGMT.entities.Grade;
import com.StudentMGMT.entities.User;
import com.StudentMGMT.services.ClassService;
import com.StudentMGMT.services.CourseService;
import com.StudentMGMT.services.EventService;
import com.StudentMGMT.services.GradeService;
import com.StudentMGMT.entities.Class;


@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ClassService classService = new ClassService();
    private EventService eventService = new EventService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        if (user == null || user.getId() == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        LocalDate currentDate = LocalDate.now();
        String dateParam = request.getParameter("date");
        if (dateParam != null && !dateParam.isEmpty()) {
            currentDate = LocalDate.parse(dateParam);
        }

        List<Class> classesToday = classService.getClassesByUserAndDate(user.getId(), currentDate, user.getRole());

        request.setAttribute("classes", classesToday);
        request.setAttribute("currentDate", currentDate.toString());
        request.setAttribute("events", eventService.getUpcomingEventsByUser(user.getId()));

        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            request.getRequestDispatcher("/WEB-INF/views/ClassListFragment.jsp").forward(request, response);
        } else if("Teacher".equals(user.getRole())) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/TeacherDashboard.jsp");
            dispatcher.forward(request, response);
        } else {
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/StudentDashboard.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

