<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
    <script src="https://www.google.com/recaptcha/enterprise.js?render=6LedP58qAAAAAMyhotIuE90PcdonjD6X3PZ7bvy6"></script>
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

    <c:if test="${not empty errorMessage}">
        <div class="error-message">${errorMessage}</div>
    </c:if>

    <div class="admin-tools">
        <h2>Admin Tools</h2>
        <form id="demo-form" action="user-creation" method="post">
            <input type="hidden" name="role" value="Student">
            <button class="g-recaptcha"
                    data-sitekey="6LedP58qAAAAAMyhotIuE90PcdonjD6X3PZ7bvy6"
                    data-callback="onSubmit"
                    data-action="submit">
                Create Student
            </button>
        </form>

        <form id="demo-form" action="user-creation" method="post">
            <input type="hidden" name="role" value="Teacher">
            <button class="g-recaptcha"
                    data-sitekey="6LedP58qAAAAAMyhotIuE90PcdonjD6X3PZ7bvy6"
                    data-callback="onSubmit"
                    data-action="submit">
                Create Teacher
            </button>
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
<script>
    function onSubmit(token) {
        var form = document.getElementById("demo-form");
        var input = document.createElement("input");
        input.type = "hidden";
        input.name = "g-recaptcha-response";
        input.value = token;
        form.appendChild(input);

        form.submit();
    }
</script>
</body>
</html>

