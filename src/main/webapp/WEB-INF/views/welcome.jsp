<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome Page</title>
</head>
<body>
	 <h2>Welcome, <%= session.getAttribute("username") %>!</h2>
    <p>This is your welcome page. You can proceed to:</p>
    <ul>
        <li><a href="userList">View User List</a></li>
        <li><a href="logout">Logout</a></li>
    </ul>
</body>
</html>