<%--
  Created by IntelliJ IDEA.
  User: Maria
  Date: 21.03.2019
  Time: 0:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
    <title>Signup</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <c:set var="contextPath" value="${pageContext.request.getContextPath()}"/>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <link href="${contextPath}/resources/style/signup.css" rel="stylesheet">

</head>

<body >

<jsp:include page="header.jsp"/>

<div class="container">
    <form class="form-signup" action="signup" method="post">
        <h2 class="h3 mb-3 font-weight-normal">Введите данные для регистрации</h2>
        <label for="inputEmail" class="sr-only">Email*</label>
        <input type="email" id="inputEmail" class="form-control" name="email"
               value="${newUser.email}" placeholder="Email address" required autofocus>

        <label for="firstPassword" class="sr-only">Введите пароль*</label>
        <input type="password" id="firstPassword" class="form-control" name="password"
               value="${newUser.password}" placeholder="Password" required>

        <label for="secondPassword" class="sr-only">Повторите пароль*</label>
        <input type="password" id="secondPassword" class="form-control" placeholder="Repeat password" required>

        <label for="firstName" class="sr-only">Имя*</label>
        <input type="text" id="firstName" class="form-control" name="firstName"
               value="${newUser.firstName}" placeholder="First name" required>

        <label for="secondName" class="sr-only">Фамилия*</label>
        <input type="text" id="secondName" class="form-control" name="secondName"
               value="${newUser.secondName}" placeholder="Second name" required>

        <label for="dateBirth" class="sr-only">Дата рождения</label>
        <input type="date" id="dateBirth" class="form-control" name="dateBirth"
               value="${newUser.birthDate}" placeholder="Date of birth" required>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign up</button>

        <c:if test ="${not empty requestScope.message}">
            <c:out value="${requestScope.message}" />
        </c:if>

        <p align="center" ><a href="login">Войти в систему</a><p>
    </form>
</div>


</body>
</html>
