<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>

<div class="container">
    <h2>Login</h2>
    <form action="login" method="post">
        <div class="input-group">
            <label for="login">Login:</label>
            <input type="text" id="login" name="login" required>
        </div>
        <div class="input-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <button type="submit">Login</button>
    </form>

    <div class="admin-tools">
        <h2>Admin Tools</h2>
        <form action="user-creation" method="post">
            <input type="hidden" name="role" value="Student">
            <button type="submit">Create Student</button>
        </form>

        <form action="user-creation" method="post">
            <input type="hidden" name="role" value="Teacher">
            <button type="submit">Create Teacher</button>
        </form>
    </div>

    <c:if test="${not empty param.newUserLogin and not empty param.newUserPassword}">
        <div class="new-user-info">
            <h3>New User Created:</h3>
            <p>Login: <strong>${param.newUserLogin}</strong></p>
            <p>Password: <strong>${param.newUserPassword}</strong></p>
        </div>
    </c:if>
</div>

</body>
</html>

