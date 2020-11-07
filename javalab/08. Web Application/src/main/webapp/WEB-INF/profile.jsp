<%--
  Created by IntelliJ IDEA.
  User: Adelya
  Date: 07.11.2020
  Time: 18:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Профиль</title>
</head>
<body>
<p><b>Имя: </b> ${user.getFirstName()} </p>
<p><b>Куки: </b> ${cookie.get("authCookie").value} </p>
</body>
</html>
