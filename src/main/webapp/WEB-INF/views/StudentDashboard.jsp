<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%@ page
session="true" %> <%@ page language="java" contentType="text/html;
charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Student Dashboard</title>
    <link
      rel="stylesheet"
      type="text/css"
      href="${pageContext.request.contextPath}/css/student.css"
    />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  </head>
  <body>
    <div class="container">
      <div class="sidebar">
        <div class="button-group">
          <button onclick="showSection('courses')">My Courses</button>
          <button onclick="showSection('grades')">My Grades</button>
          <button onclick="showSection('events')">Events</button>
          <button onclick="showSection('messages')">Messages</button>
        </div>
      </div>

      <div class="content">
        <div id="courses-section" class="section" style="display: block">
          <div class="day-chooser">
            <span class="arrow" onclick="changeDay(-1)">&#8592;</span>
            <span id="current-date">${currentDate}</span>
            <span class="arrow" onclick="changeDay(1)">&#8594;</span>
          </div>

          <div id="courses-content">
            <jsp:include page="ClassListFragment.jsp" />
          </div>
        </div>

        <div id="grades-section" class="section" style="display: none">
          <div id="grades-content">
          <jsp:include page="GradeListFragment.jsp" />
<%--            <p>Loading grades...</p> <--%>
<%--          </div>--%>
          </div>
        </div>

        <div id="events-section" class="section" style="display: none">
          <h2>Events</h2>
          <p>Your upcoming events will be displayed here.</p>
        </div>

        <div id="messages-section" class="section" style="display: none">
          <h2>Messages</h2>
          <p>Your recent messages will be displayed here.</p>
        </div>
      </div>

      <div class="rightbar">
        <h3>Account Options</h3>
        <form
                action="${pageContext.request.contextPath}/account-settings"
                method="get"
                style="display: inline"
        >
          <button type="submit" id="account-settings-btn">
            Account Settings
          </button>
        </form>
        <form
                action="${pageContext.request.contextPath}/logout"
                method="get"
                style="display: inline"
        >
          <button type="submit" id="logout-btn">Logout</button>
        </form>
        <h4>Admin</h4>
        <p>Click here to add classes to the schedule</p>
        <form
                action="${pageContext.request.contextPath}/admin-dashboard"
                method="get"
                style="display: inline"
        >
          <button type="submit" id="admin-dashboard-btn">Admin Dashboard</button>
        </form>
      </div>

    </div>
    <script>
      console.log("Current date from server:", "${currentDate}");

      let currentDate = new Date("${currentDate}");

      console.log("Initialized current date:", currentDate);
    </script>

    <script src="${pageContext.request.contextPath}/js/dashboard.js"></script>
  </body>
</html>

