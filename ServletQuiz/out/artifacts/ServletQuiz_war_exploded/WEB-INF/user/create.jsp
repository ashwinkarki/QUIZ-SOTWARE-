<%--
  Created by IntelliJ IDEA.
  User: AshwinPC
  Date: 2/21/2018
  Time: 9:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Add user</h1>
<form method="post" action="addUser">
    <input type="hidden" name="url" value="create-users"/>
    <input type="text" name="userName" placeholder="Enter name of user"/><br>
    <input type="text" name="email" placeholder="Enter email"/><br>
    <input type="password" name="password" placeholder="Enter password"/><br>
    <input type="submit" value="save"/>
</form>
</body>
</html>
