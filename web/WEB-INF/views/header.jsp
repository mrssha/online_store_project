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

<nav class="navbar navbar-default bg-light">
    <c:set var="contextPath" value="${pageContext.request.getContextPath()}"/>

    <div class="container-fluid">
        <ul class="nav">
            <li class="nav-item">
                <a class="navbar-brand" href="${pageContext.request.getContextPath()}">
                    <img src="${pageContext.request.getContextPath()}/resources/image/logo.png"
                         width="30" height="30" class="d-inline-block align-top" alt="">
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

            <sec:authorize access="hasRole('ADMIN')">
                <li class="nav-item">
                    <div class="dropdown">
                        <a class="btn btn-secondary dropdown-toggle btn-sm" href="" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Admin
                        </a>

                        <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                            <a class="dropdown-item" href="${contextPath}/admin/products">Products</a>
                            <a class="dropdown-item" href="${contextPath}/admin/categories">Categories</a>
                            <a class="dropdown-item" href="${contextPath}/admin/orders">Orders</a>
                        </div>
                    </div>
                </li>
            </sec:authorize>

            <li class="nav-item">
                <c:if test="${sessionScope.principalUser != null}">
                    <a class="nav-link active" href="${contextPath}/orders">Orders</a>
                </c:if>
            </li>

            <li class="nav-item">
                <c:if test="${sessionScope.principalUser != null}">
                    <a class="nav-link active" href="${contextPath}/profile">User profile</a>
                </c:if>
                <c:if test="${sessionScope.principalUser == null}">
                    <a class="nav-link active" href="${contextPath}/signup">Signup</a>
                </c:if>
            </li>

            <li class="nav-item">
                <c:if test="${sessionScope.principalUser == null}">
                    <a class="nav-link active" href="${contextPath}/login">Login</a>
                </c:if>
                <c:if test="${sessionScope.principalUser != null}">
                    <a class="nav-link active" href="${contextPath}/logout">Logout</a>
                </c:if>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="${contextPath}/cart">Product cart</a>
            </li>
        </ul>

    </div>
</nav>



