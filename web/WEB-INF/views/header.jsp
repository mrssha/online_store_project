<%--
  Created by IntelliJ IDEA.
  User: Maria
  Date: 19.03.2019
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.getContextPath()}"/>
<link rel="stylesheet" href="${contextPath}/resources/style/header.css">

<nav class="navbar navbar-dark navbar-expand" style=" background:  #4682B4">

    <div class="navbar-collapse collapse w-100 order-3 dual-collapse2">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="navbar-brand" href="${pageContext.request.getContextPath()}">
                    <img src="${pageContext.request.getContextPath()}/resources/image/logo3.jpg"
                         width="30" height="30" class="mr-2 d-inline-block align-top" alt="">
                    Letsnow
                </a>
            </li>
            <li class="nav-item" >
                <form class="form-inline my-0" action="${contextPath}/search" method="get">
                    <input class="form-control mr-sm-2" type="search" name="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-light my-2 my-sm-0" type="submit">Search</button>
                </form>
            </li>
        </ul>
    </div>

    <div class="navbar-collapse collapse w-100 order-3 dual-collapse2">
        <ul class="navbar-nav ml-auto">
            <sec:authorize access="hasRole('ADMIN')">
                <li class="nav-item">
                    <div class="dropdown">
                        <a class="btn btn-secondary dropdown-toggle shadow-none " href="" role="button" id="dropdownMenuLink"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
                           style=" background: #4682B4; border: none">
                            Admin
                        </a>

                        <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                            <a class="dropdown-item" href="${contextPath}/admin/products">Products</a>
                            <a class="dropdown-item" href="${contextPath}/admin/categories">Categories</a>
                            <a class="dropdown-item" href="${contextPath}/admin/orders">Orders</a>
                            <a class="dropdown-item" href="${contextPath}/admin/statistics">Statistics</a>
                        </div>
                    </div>
                </li>
            </sec:authorize>

            <li class="nav-item">
                <sec:authorize access="isAuthenticated()">
                    <a class="nav-link active" href="${contextPath}/orders">Orders</a>
                </sec:authorize>
            </li>

            <li class="nav-item">
                <sec:authorize access="isAuthenticated()">
                    <a class="nav-link active" href="${contextPath}/profile">User profile</a>
                </sec:authorize>
                <sec:authorize access="!isAuthenticated()">
                    <a class="nav-link active" href="${contextPath}/signup">Signup</a>
                </sec:authorize>
            </li>

            <li class="nav-item">
                <sec:authorize access="!isAuthenticated()">
                    <a class="nav-link active" href="${contextPath}/login">Login</a>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <a class="nav-link active" href="${contextPath}/logout">Logout</a>
                </sec:authorize>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="${contextPath}/cart">Product cart</a>
            </li>
        </ul>
    </div>
</nav>




