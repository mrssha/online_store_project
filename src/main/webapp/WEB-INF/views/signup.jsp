<%--
  Created by IntelliJ IDEA.
  User: Maria
  Date: 21.03.2019
  Time: 0:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html>
<head>
    <title>Signup</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <c:set var="contextPath" value="${pageContext.request.getContextPath()}"/>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <link href="${contextPath}/resources/style/signup.css" rel="stylesheet">
    <link rel="stylesheet" href="${contextPath}/resources/style/general.css">

</head>

<body>

<div class="wrapper">
<jsp:include page="header.jsp"/>

    <div class="container-fluid content">
        <div class="container">

            <form:form class="form-signup" action="signup" method="post" modelAttribute="newUser">
                <h2 class="h3 mb-3 font-weight-normal">Enter data for sign up</h2>

                <div class="itemForm">
                <label for="inputEmail" class="sr-only">Email*</label>
                <form:input type="email" id="inputEmail" class="form-control"
                       placeholder="Email address" path="email" required = "true"/>
                <form:errors path="email" cssClass="error"/>
                </div>

                <div class="itemForm">
                <label for="firstPassword" class="sr-only">Введите пароль*</label>
                <form:input type="password" id="firstPassword" class="form-control"
                       placeholder="Password" path="password" required = "true"/>
                <form:errors path="password" cssClass="error"/>
                </div>


                <div class="itemForm">
                <label for="secondPassword" class="sr-only">Повторите пароль*</label>
                <form:input type="password" id="secondPassword" class="form-control"
                            placeholder="Repeat password" path="confirmedPassword" required = "true"/>
                <form:errors path="confirmedPassword" cssClass="error"/>
                <form:errors path="valid" cssClass="error"/>
                </div>

                <div class="itemForm">
                <label for="firstName" class="sr-only">Имя*</label>
                <form:input type="text" id="firstName" class="form-control"
                       placeholder="First name" path="firstName" required = "true"/>
                <form:errors path="firstName" cssClass="error"/>
                </div>

                <div class="itemForm">
                <label for="secondName" class="sr-only">Фамилия*</label>
                <form:input type="text" id="secondName" class="form-control"
                       placeholder="Second name" path="secondName" required = "true"/>
                <form:errors path="secondName" cssClass="error"/>
                </div>

                <div class="itemForm">
                <label for="dateBirthId" class="sr-only">Дата рождения</label>
                <form:input type="date" id="dateBirthId" class="form-control"
                       placeholder="Date of birth" path="birthDate" required = "true"/>
                <form:errors path="birthDate" cssClass="error"/>
                </div>

                <button class="btn btn-lg btn-primary btn-block my-3" type="submit">Sign up</button>

                <c:if test ="${not empty requestScope.message}">
                    <p class="error-email">${requestScope.message}</p>
                </c:if>

                <p align="center" ><a href="login">Log in</a><p>
            </form:form>
        </div>
    </div>
    <jsp:include page="footer.jsp"/>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

</body>
</html>
