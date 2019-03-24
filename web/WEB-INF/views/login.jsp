<%--
  Created by IntelliJ IDEA.
  User: Maria
  Date: 20.03.2019
  Time: 22:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Login</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <c:set var="contextPath" value="${pageContext.request.getContextPath()}"/>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <link href="${contextPath}/resources/style/login.css" rel="stylesheet">

</head>
<body>
<jsp:include page="header.jsp"/>

<div class="container">

    <c:url value="/login_processing" var="loginUrl" />
    <form class="form-login" action="${loginUrl}" method="post">
        <c:if test="${param.error != null}">
            <p>
                Invalid username and password.
            </p>
        </c:if>
        <c:if test="${param.logout != null}">
            <p>
                You have been logged out.
            </p>
        </c:if>

        <h2 class="h3 mb-3 font-weight-normal">Введите данные для входа</h2>
        <label for="inputEmail" class="sr-only">Email address</label>

        <input type="email" id="inputEmail" class="form-control" name="username"
               placeholder="Email address" required autofocus>

        <label for="password" class="sr-only">Password</label>
        <input type="password" id="password" class="form-control" name="password"
               placeholder="Password" required>

        <div class="checkbox mb-3">
            <label>
                <input type="checkbox" value="remember-me"> Remember me
            </label>
        </div>
        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>

        <p align="center"><a href="signup">Регистрация</a></p>
    </form>
</div>


</body>
</html>
