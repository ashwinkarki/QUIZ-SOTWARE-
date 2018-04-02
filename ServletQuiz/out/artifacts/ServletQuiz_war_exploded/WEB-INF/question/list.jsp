<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List</title>
</head>
<body>
<a href="create?url=create">Add Question</a>
<table border="1">
    <tr>
        <th>Question</th>
        <th>Option 1</th>
        <th>Option 2</th>
        <th>Option 3</th>
        <th>Option 4</th>
        <th>Correct Answer</th>
        <th>Category</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <c:forEach items="${qlist}" var="q">
        <tr>
            <td>${q.question}</td>
            <td>${q.option1}</td>
            <td>${q.option2}</td>
            <td>${q.option3}</td>
            <td>${q.option4}</td>
            <td>${q.correctAnswer}</td>
            <td>${q.category}</td>
            <td><a href="edit?url=edit-question&id=${q.id}">Edit</a> </td>
            <td><a href="delete?url=delete-question&id=${q.id}">Delete</a> </td>
        </tr>

    </c:forEach>
</table>
</body>
</html>
