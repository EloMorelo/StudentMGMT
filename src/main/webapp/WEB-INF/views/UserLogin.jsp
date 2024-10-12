<!-- /WEB-INF/views/UserLogin.jsp -->
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
 <div align="center">
    <h2>Login</h2>
    <form action="login" method="post">
        <label for="login">Login:</label>
        <input type="text" id="login" name="login" required><br><br>
        
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>
        
        <button type="submit">Login</button>
    </form>

    <c:if test="${not empty error}">
        <div style="color: red;">${error}</div>
    </c:if>
</div>
</body>
</html>
