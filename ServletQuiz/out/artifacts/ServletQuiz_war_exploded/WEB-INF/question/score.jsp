<%--
  Created by IntelliJ IDEA.
  User: AshwinPC
  Date: 3/6/2018
  Time: 8:57 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Title</title>
</head>
<body>
<div align="right"><a href="logout?url=logout" class="btn btn-danger" >Logout</a></div>
<table class="table table-condensed">
        <tr>
            <th>Question</th>
            <th>Score</th>
            <th>Right/Wrong</th>
        </tr>
    <c:set var="total" value="${0}"/>
    <c:forEach var="ss" items="${s}">
        <tr>
            <td>${ss.questionId}</td>
            <td>${ss.score}</td>
           <c:choose>
                <c:when test = "${ss.score== 5}">
                    <td><span class="glyphicon glyphicon-ok"></span></td>
                </c:when>
                <c:otherwise>
                    <td><span class="glyphicon glyphicon-remove"></span></td>
                </c:otherwise>
            </c:choose>
            <c:set var="total" value="${total + ss.score}" />
        </tr>

    </c:forEach>

</table>
<h3>Your Total Score is:</h3><h1><c:out value="${total}" /></h1>
</body>
</html>
