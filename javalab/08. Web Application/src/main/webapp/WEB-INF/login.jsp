<%--
  Created by IntelliJ IDEA.
  User: Adelya
  Date: 04.11.2020
  Time: 19:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

    <title>Login</title>

    <style>
        .col-md-5 {
            margin: 20% 30%;

        }

        .overlay {
            background: rgba(178, 34, 34, 0.3);
            padding: 1rem;
            border-radius: 10px;
            margin-top: 10px;
        }

    </style>

</head>
<body>
<div class="container">
    <div class="col-md-5">
        <h1 style="text-align: center">Вход</h1>
        <div class="overlay">
            <form action="login" method="post">
                <div class="form-group">
                    <label for="email">Введите email:</label>
                    <input type="email" class="form-control" id="email" name="email">
                </div>
                <div class="form-group">
                    <label for="password">Введите пароль:</label>
                    <input type="password" class="form-control" id="password" name="password">
                </div>
                <div class="form-row text-center">
                    <div class="col-12">
                        <button type="submit" class="btn btn-outline-danger center-block">Войти</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
