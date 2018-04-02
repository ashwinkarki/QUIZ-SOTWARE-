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
    <input type="hidden" name="url" value="create-question"/><br>
    <input type="text" name="question" placeholder="Enter a question"/><br>
    <input type="text" name="option1" placeholder="Enter option1"/><br>
    <input type="text" name="option2" placeholder="Enter option2"/><br>
    <input type="text" name="option3" placeholder="Enter option3"/><br>
    <input type="text" name="option4" placeholder="Enter option4"/><br>
    <input type="text" name="correctAnswer" placeholder="Enter correct ans"/><br>
    <input type="text" name="category" placeholder="Enter category"/><br>

    <input type="submit" value="save"/>
</form>
</body>
</html>
