<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" />
</head>
<body>
    <div class="container">
        <h1>Welcome to Your Dashboard</h1>
        <div class="grid-container">
            <!-- My Grades Section -->
            <div class="card">
                <a href="grades.jsp">My Grades</a>
            </div>
            
            <!-- My Courses Section -->
            <div class="card">
                <a href="courses.jsp">My Courses</a>
            </div>
            
            <!-- Events Section -->
            <div class="card">
                <a href="events.jsp">Events</a>
            </div>
        </div>
    </div>
</body>
</html>
