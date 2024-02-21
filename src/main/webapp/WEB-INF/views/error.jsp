<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ERROR</title>
</head>
<body>
	<h1>FAILED TO LOG IN</h1>
	<h2>Error</h2>
    <p><%= request.getParameter("message") %></p>
    <p>Please <a href="login.jsp">try again</a>.</p>
</body>
</html>