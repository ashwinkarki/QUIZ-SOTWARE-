<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Online Quiz</a>
    </div>
    <ul class="nav navbar-nav">

      <c:if test="${r=='ROLE_ADMIN'}">
      <li> <a href="create?url=create">Add Question</a></li>
       <li> <a href="createuser?url=create-user">Add User</a></li>
        <li> <a href="list-question?url=list-question">Listquestion</a></li>
      </c:if>
      <li><a href="logout?url=logout">Logout</a></li>
    </ul>
  </div>
</nav>

<div class="container">
  <h3>1.Please select the Quiz Category:</h3>
  <select name="category" id="category" value="Categories Available">
<c:forEach items="${c}" var="cc">

    <option value="${cc}">${cc}</option>

</c:forEach>

  </select>
  <br/>
  <h3>Step 2:Press the Play Button</h3>
  <a href="playquiz?url=play-quiz" id="search" class="btn btn-success">Play!!</a>


      <script>
      $("#search").click(function(e){
          $(this).prop("href",$(this).prop("href") + "&&cat=" + $("#category").val());
      })
  </script>


</div>

  </body>
</html>
