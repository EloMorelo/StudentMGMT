<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Account Settings</title>
    <link rel="stylesheet" href="css/account-settings.css">
</head>
<body>
    <div class="settings-container">
        <h2>Account Settings</h2>
        
        <div class="account-info">
            <h3>Your Account Information</h3>
            <p><strong>Email:</strong> ${user.email}</p>
            <p><strong>Login:</strong> ${user.login}</p>
        </div>
        
        <div class="form-section">
            <h3>Change Email</h3>
            <form action="${pageContext.request.contextPath}/account-settings" method="post">
			    <input type="hidden" name="action" value="changeEmail">
			    <label for="newEmail">New Email:</label>
			    <input type="email" id="newEmail" name="newEmail" required>
			    <button type="submit">Change Email</button>
			</form>
        </div>
        
        <div class="form-section">
            <h3>Change Password</h3>
            <form action="${pageContext.request.contextPath}/account-settings" method="post">
            <input type="hidden" name="action" value="changePassword">
                <label for="currentPassword">Current Password:</label>
                <input type="password" id="currentPassword" name="currentPassword" required>
                
                <label for="newPassword">New Password:</label>
                <input type="password" id="newPassword" name="newPassword" required>
                
                <label for="confirmPassword">Confirm New Password:</label>
                <input type="password" id="confirmPassword" name="confirmPassword" required>
                
                <button type="submit">Update Password</button>
            </form>
        </div>
        
        <div class="form-section danger-zone">
            <h3>Admin Tools:</h3>
            <form action="${pageContext.request.contextPath}/account-settings" method="post">
                <input type="hidden" name="action" value="deleteAccount">
                <button type="submit" class="delete-btn">Delete My Account</button>
            </form>
        </div>
    </div>
</body>
</html>
