<%--
  Created by IntelliJ IDEA.
  User: AshwinPC
  Date: 2/21/2018
  Time: 8:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="save">
    <input type="text" name="question" value="${q.question}"/><br>
    <input type="text" name="option1" value="${q.option1}"/><br>
    <input type="text" name="option2" value="${q.option2}"/><br>
    <input type="text" name="option3" value="${q.option3}"/><br>
    <input type="text" name="option4" value="${q.option4}"/><br>
    <input type="text" name="correctAnswer" value="${q.correctAnswer}"/><br>
    <input type="text" name="category" value="${q.category}"/><br>

    <input type="submit" value="Update"/>
</form>
</body>
</html>
