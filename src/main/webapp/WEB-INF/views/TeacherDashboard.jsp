<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Teacher Dashboard</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <header>
        <h1>Welcome, Teacher ${sessionScope.user.login}</h1>
        <nav>
            <ul>
                <li><a href="${pageContext.request.contextPath}/teacherDashboard">Dashboard</a></li>
                <li><a href="${pageContext.request.contextPath}/manageCourses">Manage Courses</a></li>
                <li><a href="${pageContext.request.contextPath}/viewEvents">View Events</a></li>
                <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
            </ul>
        </nav>
    </header>
    <main>
        <h2>My Courses</h2>
        <div>
            <input type="text" id="courseSearch" placeholder="Search courses..." />
        </div>
        <div id="courseList">
            <c:if test="${empty courses}">
                <p>You are not currently assigned to any courses.</p>
            </c:if>
            <c:forEach var="course" items="${courses}">
                <div class="course-item" data-course-name="${course.name.toLowerCase()}">
                    <h3>${course.name}</h3>
                    <p>${course.description}</p>
                    <a href="${pageContext.request.contextPath}/manageCourseDetails?courseId=${course.id}">Manage Course</a>
                </div>
            </c:forEach>
        </div>

        <h2>Upcoming Events</h2>
        <div id="eventList">
            <c:if test="${empty events}">
                <p>No upcoming events scheduled.</p>
            </c:if>
            <c:forEach var="event" items="${events}">
                <div class="event-item">
                    <h3>${event.title}</h3>
                    <p>${event.description}</p>
                    <p>Date: ${event.date}</p>
                    <a href="${pageContext.request.contextPath}/manageEventDetails?eventId=${event.id}">View Details</a>
                </div>
            </c:forEach>
        </div>

        <h2>Student Performance Reports</h2>
        <button id="loadReports">Load Reports</button>
        <div id="reportList">
        </div>
    </main>
    <footer>
        <p> test</p>
    </footer>

    <script>
        $(document).ready(function() {
            $('#courseSearch').on('keyup', function() {
                var value = $(this).val().toLowerCase();
                $('.course-item').filter(function() {
                    $(this).toggle($(this).data('course-name').includes(value));
                });
            });
            $('#loadReports').on('click', function() {
                $.ajax({
                    url: '${pageContext.request.contextPath}/getStudentReports',
                    method: 'GET',
                    success: function(data) {
                        $('#reportList').html('');
                        if (data.length === 0) {
                            $('#reportList').append('<p>No student performance reports available.</p>');
                        } else {
                            $.each(data, function(index, report) {
                                var reportHtml = '<div class="report-item">' +
                                    '<h3>Student: ' + report.studentName + '</h3>' +
                                    '<p>Course: ' + report.courseName + '</p>' +
                                    '<p>Grade: ' + report.grade + '</p>' +
                                    '<a href="' + '${pageContext.request.contextPath}/viewStudentDetails?studentId=' + report.studentId + '">View Details</a>' +
                                    '</div>';
                                $('#reportList').append(reportHtml);
                            });
                        }
                    },
                    error: function() {
                        alert('Error loading reports. Please try again later.');
                    }
                });
            });
        });
    </script>
</body>
</html>

