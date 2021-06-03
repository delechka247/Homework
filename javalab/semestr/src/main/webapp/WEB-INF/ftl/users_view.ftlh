<#ftl encoding="UTF-8"/>
<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<h1>Users</h1>
<table>
    <tr>
        <th>Имя</th>
        <th>Фамилия</th>
    </tr>
    <#list users as user>
        <tr>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
        </tr>
    </#list>
</table>

<form method="post" action="/banAll">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <input type="submit" value="BAN">
</form>

</body>
</html>
