<<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

<div class="container-fluid">
    <div class="row">
        <div class="col-md-1" style = "background-color: #ff9999">
        </div>

        <div class="col-md-10" style = "background-color: #99CCFF">
        </div>

        <div class="col-md-1" style = "background-color: #ff9999">
        </div>
    </div>

</div>
</body>
</html>