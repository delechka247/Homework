<#ftl encoding="UTF-8"/>
<!doctype html>
<html lang="ru">
<#import "spring.ftl" as spring/>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Регистрация</title>
    <style>
        .error {
            color: red;
        }
    </style>

</head>
<body>
<h1><@spring.message 'sign_up.registration.title'/></h1>
<@spring.bind "userForm"/>


<form method="post" action="/signUp">

<#--    <input type="text" name="firstName" placeholder="Имя">-->
<#--    <input type="text" name="lastName" placeholder="Фамилия">-->
<#--    <input type="email" name="email" placeholder="Email">-->
<#--    <input type="password" name="password" placeholder="Пароль">-->
<#--    <input type="submit" value="Регистрация">-->


    <#if namesErrorMessage??>
        <p style=".error">${namesErrorMessage}</p>
    </#if>

    <label><@spring.message 'sign_up.first_name'/></label>
    <@spring.formInput "userForm.firstName"/>
    <@spring.showErrors "<br>" "error"/>
    <br>
    <label><@spring.message 'sign_up.last_name'/></label>
    <@spring.formInput "userForm.lastName"/>
    <@spring.showErrors "<br>" "error"/>
    <br>
    <label><@spring.message 'sign_up.email'/></label>
    <@spring.formInput "userForm.email"/>
    <@spring.showErrors "<br>" "error"/>
    <br>
    <label><@spring.message 'sign_up.password'/></label>
    <@spring.formPasswordInput "userForm.password"/>
    <@spring.showErrors "<br>" "error"/>
    <br>
    <input type="submit" value="<@spring.message 'sign_up.registration_button'/>">

</form>

</body>
</html>
