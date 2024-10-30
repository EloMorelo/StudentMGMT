package com.StudentMGMT.servlets;

import com.StudentMGMT.services.GroupService;
import com.StudentMGMT.services.UserService;
import com.StudentMGMT.services.ClassService;
import com.StudentMGMT.entities.Group;
import com.StudentMGMT.entities.User;
import com.StudentMGMT.entities.Class;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@WebServlet("/admin-dashboard")
public class AdminDashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserService userService = new UserService();
    private GroupService groupService = new GroupService();
    private ClassService classService = new ClassService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<User> students = userService.getAllStudents();
            request.setAttribute("students", students);

            List<Group> groups = groupService.getAllGroups();
            request.setAttribute("groups", groups);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/AdminDashboard.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("addStudentToGroup".equals(action)) {
                addStudentToGroup(request, response);
            } else if ("addClassToSchedule".equals(action)) {
                addClassToSchedule(request, response);
            } else {
                response.sendRedirect("AdminDashboard.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    private void addStudentToGroup(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UUID studentId = UUID.fromString(request.getParameter("studentId"));
        UUID groupId = UUID.fromString(request.getParameter("groupId"));
        groupService.addStudentToGroup(studentId, groupId);
        response.sendRedirect("admin-dashboard?action=getGroupsAndStudents&success=true");
    }

    private void addClassToSchedule(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UUID groupId = UUID.fromString(request.getParameter("groupId"));
        String className = request.getParameter("className");
        String building = request.getParameter("building");
        String room = request.getParameter("room");
        LocalDate date = LocalDate.parse(request.getParameter("date"));
        LocalTime startTime = LocalTime.parse(request.getParameter("startTime"));
        LocalTime endTime = LocalTime.parse(request.getParameter("endTime"));

        classService.addClassToSchedule(groupId, className, building, room, date, startTime,endTime);
        response.sendRedirect("admin-dashboard?action=getClassSchedule&success=true");
    }
}
