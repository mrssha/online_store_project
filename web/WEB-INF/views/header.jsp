<%--
  Created by IntelliJ IDEA.
  User: Maria
  Date: 19.03.2019
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav class="navbar navbar-default bg-light">
    <div class="container-fluid">

        <ul class="nav">
            <li class="nav-item">
                <a class="navbar-brand" href="#">
                    <img src="${pageContext.request.getContextPath()}/resources/image/logo.png" width="30" height="30" class="d-inline-block align-top" alt="">
                    Letsnow
                </a>
            </li>
            <li class="nav-item" >
                <form class="form-inline">
                    <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                </form>
            </li>
        </ul>

        <ul class="nav  justify-content-end" >

            <li class="nav-item">
                <c:if test="${sessionScope.principalUser != null}">
                    <a class="nav-link active" href="profile">Профиль ${sessionScope.principalUser.getFirstName()}</a>
                </c:if>
                <c:if test="${sessionScope.principalUser == null}">
                    <a class="nav-link active" href="signup">Регистрация</a>
                </c:if>
            </li>

            <li class="nav-item">
                <c:if test="${sessionScope.principalUser == null}">
                    <a class="nav-link active" href="login">Войти</a>
                </c:if>
                <c:if test="${sessionScope.principalUser != null}">
                    <a class="nav-link active" href="logout">Выйти</a>
                </c:if>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="#">Корзина</a>
            </li>
        </ul>

    </div>
</nav>



