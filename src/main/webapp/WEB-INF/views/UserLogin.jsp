<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Login</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <h2>Login</h2>
    <form action="login" method="post">
        <label for="login">Login:</label>
        <input type="text" id="login" name="login" required>
        <br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
        <br>
        <button type="submit">Login</button>
    </form>

    <h2>Admin Tools</h2>
    <form action="user-creation" method="post">
        <input type="hidden" name="role" value="Student">
        <button type="submit">Create Student</button>
    </form>

    <form action="user-creation" method="post">
        <input type="hidden" name="role" value="Teacher">
        <button type="submit">Create Teacher</button>
    </form>

    <c:if test="${not empty login && not empty password}">
    <h3>New User Created:</h3>
    <p>Login: <strong>${login}</strong></p>
    <p>Password: <strong>${password}</strong></p>
</c:if>
</body>
</html>
