<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="cu" uri="WEB-INF/TableTag.tld" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User List</title>
</head>
<body>
	 <h1>User List</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Username</th>
        </tr>
        <c:forEach items="${userList}" var="user">
            <tr>
                <td><c:out value="${user.id}" /></td>
                <td><c:out value="${user.username}" /></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>