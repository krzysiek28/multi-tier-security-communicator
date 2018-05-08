
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Login page</title>
    <link href="<c:url value='/static/css/bootstrap.css' />"  rel="stylesheet"></link>
    <link href="<c:url value='/static/css/logStyle.css' />" rel="stylesheet"></link>
    <link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
    <title>Dodaj konwersację</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/">Home</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
</nav>

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

    <button class="btn btn-secondary" type="submit"> Stwórz </button>

</form>
    </div>
</div>

</body>
</html>










