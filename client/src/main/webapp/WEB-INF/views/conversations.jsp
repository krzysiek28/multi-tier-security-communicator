<%--
  Created by IntelliJ IDEA.
  User: krzys
  Date: 29.03.2018
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${authservice.isLoggedIn() == false}">
    <c:redirect url="/"/>
</c:if>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <title>Konwersajce</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">

</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/homeLogged">Home</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">

        </ul>
    </div>
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <h4>Zalogowano jako: ${pageContext.request.userPrincipal.name}</h4>
    </c:if>
    <div class="nick" style="padding-right: 10px; color: white">
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <h4>Zalogowano jako: ${pageContext.request.userPrincipal.name}</h4>
        </c:if>
    </div>

    <!-- naval with buttons -->
    <div class="btn-group" role="group" aria-label="Basic example">
        <button type="button" class="btn btn-secondary" onclick="window.location.href='/logout'">Wyloguj się</button>
    </div>
</nav>

<h3>Prywatny komunikator</h3>
xxaaxadx
<br>
<div class="btn-group" role="group">
    <button type="button" class="btn btn-secondary" name="conversationList" onclick="window.location.href='/conversations'">Zobacz listę konwersacji</button>
    <button type="button" class="btn btn-secondary" name="addConversation" onclick="window.location.href='/addConversation'">Dodaj konwersację</button>
</div>
<c:if test="${pageContext.request.userPrincipal.name != null}">
    <h4>Zalogowano jako: ${pageContext.request.userPrincipal.name}</h4>
</c:if>
<%--container--%>
<div class="conversations-list">
    <c:if test="${param.error != null}">
        <div  style="width:900px; margin:0 auto; margin-top: 10px; margin-bottom: 10px;"class="alert alert-danger">
            <p><c:out value="${param.error}"/></p>
        </div>
    </c:if>
    <table class="table" border="1">
        <thead class="thead-dark" align="center">
        <div>
            <th>Id</th>
            <th>Nazwa</th>
        </div>

        </thead>
        <tbody>
        <c:forEach var="conversation" items="${conversations}">
            <tr>
                <td align="center" style="width: 20%">${conversation.id}</td>
                <td align="center">${conversation.name}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


</div>


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
</body>
</html>
