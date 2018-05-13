<%--
  Created by IntelliJ IDEA.
  User: krzys
  Date: 29.03.2018
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${authservice.isLoggedIn() == false}">
    <c:redirect url="/"/>
</c:if>

<style>
    label {
        display: block;
        float: left;
        width: 240px;
    }
</style>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="Sat, 01 Dec 2001 00:00:00 GMT">
    <title>MTSC</title>

    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>

<html>
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
<br>

<%--action buttons--%>
<div class="btn-group" role="group">
    <button type="button" class="btn btn-secondary" name="conversationList" onclick="window.location.href='/conversations'">Zobacz listę konwersacji</button>

    <!-- Button trigger modal -->
    <button type="button" class="btn btn-secondary" name="addConversation" data-toggle="modal" data-target="#addModal">Dodaj konwersację</button>

    <!-- Modal -->
    <div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="addModalLabel">Dodawanie konwersacji</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div id="mainWrapper">
                        <div class="form">
                            <form action="/conversation" method="post">
                                <div class="input-group input-sm">
                                    <label class="input-group-addon">Podaj nazwę konwersacji:</label>
                                    <input type="text" class="form-control" id="name" name="name" placeholder="name" required>
                                </div>
                                <div class="input-group input-sm">
                                    <label class="input-group-addon">Podaj hasło do konwersacji:</label>
                                    <input type="password" class="form-control" id="password" name="password" placeholder="Hasło" />
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Anuluj</button>
                                    <button class="btn btn-secondary" type="submit"> Stwórz </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Button trigger modal -->
    <button type="button" class="btn btn-secondary" data-toggle="modal" data-target="#JoinModal">Dołącz do konwersacji</button>

    <!-- Modal -->
    <div class="modal fade" id="JoinModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="joinModalLabel">Dołączanie do konwersacji</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    ADD FORM
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Anuluj</button>
                    <button type="button" class="btn btn-primary" name="joinToConversation" onclick="window.location.href='/'">Dołącz</button>
                </div>
            </div>
        </div>
    </div>
</div>

<%--end of action buttons--%>

<%--container--%>
<div class="conversations-list">
    <c:if test="${param.error != null}">
        <div  style="width:900px; margin:0 auto; margin-top: 10px; margin-bottom: 10px;"class="alert alert-danger">
            <p><c:out value="${param.error}"/></p>
        </div>
    </c:if>
</div>


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
</body>
</html>
