<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link
            rel="stylesheet"
            type="text/css"
            href="${pageContext.request.contextPath}/css/admin.css"
    />
</head>
<body>
    <h1>Admin Dashboard</h1>

    <h2>Add Student to Group</h2>
    <form action="${pageContext.request.contextPath}/admin-dashboard" method="POST">
        <input type="hidden" name="action" value="addStudentToGroup">
        <label for="studentId">Select Student:</label>
        <select name="studentId" id="studentId" required>
            <c:forEach var="student" items="${students}">
                <option value="${student.id}">${student.login} (${student.email})</option>
            </c:forEach>
        </select>
        <label for="groupId">Select Group:</label>
        <select name="groupId" id="groupId" required>
            <c:forEach var="group" items="${groups}">
                <option value="${group.id}">${group.name}</option>
            </c:forEach>
        </select>
        <button type="submit">Add to Group</button>
    </form>
        <h2>Add Teacher to Group</h2>
    <form action="${pageContext.request.contextPath}/admin-dashboard" method="POST">
        <input type="hidden" name="action" value="addTeacherToGroup">
        <label for="teacherId">Select Student:</label>
        <select name="teacherId" id="teacherId" required>
            <c:forEach var="teacher" items="${teachers}">
                <option value="${teacher.id}">${teacher.login} (${teacher.email})</option>
            </c:forEach>
        </select>
        <label for="groupId">Select Group:</label>
        <select name="groupId" id="groupId" required>
            <c:forEach var="group" items="${groups}">
                <option value="${group.id}">${group.name}</option>
            </c:forEach>
        </select>
        <button type="submit">Add to Group</button>
    </form>

    <h2>Schedule a Class</h2>
    <form action="${pageContext.request.contextPath}/admin-dashboard" method="POST">
        <input type="hidden" name="action" value="addClassToSchedule">
        <label for="className">Class Name:</label>
        <input type="text" name="className" id="className" required>
        <label for="building">Building:</label>
        <input type="text" name="building" id="building" required>
        <label for="room">Room:</label>
        <input type="text" name="room" id="room" required>
        <select name="teacherId" id="teacherId" required>
            <c:forEach var="teacher" items="${teachers}">
                <option value="${teacher.id}">${teacher.login} (${teacher.email})</option>
            </c:forEach>
        </select>
        <label for="date">Date:</label>
        <input type="date" name="date" id="date" required>
        <label for="startTime">Start Time:</label>
        <input type="time" name="startTime" id="startTime" required>
        <label for="endTime">End Time:</label>
        <input type="time" name="endTime" id="endTime" required>
        <label for="groupIdClass">Select Group:</label>
        <select name="groupId" id="groupIdClass" required>
            <c:forEach var="group" items="${groups}">
                <option value="${group.id}">${group.name}</option>
            </c:forEach>
        </select>
        <button type="submit">Add to Schedule</button>
    </form>
    <h2>Create a Group</h2>
    <form action="${pageContext.request.contextPath}/admin-dashboard" method="POST">
    <input type="hidden" name="action" value="CreateAGroup">
    <label for="GroupName">Group Name:</label>
    <input type="text" name="GroupName" id="GroupName" required>
    <label for="GroupYear">Group Year:</label>
    <select name="GroupYear" id="GroupYear" required>
        <% 
            int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
            for (int year = 2010; year <= currentYear; year++) { 
        %>
            <option value="<%= year %>"><%= year %></option>
        <% 
            } 
        %>
    </select>
    <button type="submit">Add to Group</button>
</form>

    <c:if test="${param.success == 'true'}">
        <p style="color: green;">Operation completed successfully!</p>
    </c:if>
    <c:if test="${param.success == 'false'}">
        <p style="color: red;">Operation failed. Please try again.</p>
    </c:if>
</body>
</html>

