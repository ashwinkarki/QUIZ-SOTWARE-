<%--
  Created by IntelliJ IDEA.
  User: AshwinPC
  Date: 2/26/2018
  Time: 8:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Title</title>
    <style>
        div {
            background-color:lightgrey;
            width: 300px;
            border: 20px solid green;
            padding:40px;
            margin: 50px;
        }
    </style>
</head>
<body>
<p>Please Answer In<span id="countdowntimer">10 </span> Seconds</p>


<div>
<form method="post" action="play-quiz-next">
    <input type="hidden" name="categoryName" value="${catName}"/>
    <input type="hidden" name="correctAnswer" value="${q.correctAnswer}"/>
    <input type="hidden" name="id" value="${q.id}"/>
    <input type="hidden" name="url" value="play-n"/>

    <h1>${q.question}</h1>
    <input type="radio" name="option" value="${q.option1}">${q.option1}<br>
    <input type="radio" name="option" value="${q.option2}">${q.option2}<br>
    <input type="radio" name="option" value="${q.option3}">${q.option3}<br>
    <input type="radio" name="option" value="${q.option4}">${q.option4}<br>

    <input type="submit" value="Next" />

</div>
</form>
<script type="text/javascript">
    var timeleft = 10;
    var downloadTimer = setInterval(function(){
        timeleft--;
        document.getElementById("countdowntimer").innerText = timeleft;
        if(timeleft <= 0){
            clearInterval(downloadTimer);
            alert(window.location);
            window.location='http://localhost:8092/';
            alert("YOU ARE OUTTTTT");
        }


    },1000);
</script>
</body>
</html>
