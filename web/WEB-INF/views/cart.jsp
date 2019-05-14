<%--
  Created by IntelliJ IDEA.
  User: Maria
  Date: 29.03.2019
  Time: 3:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <c:set var="contextPath" value="${pageContext.request.getContextPath()}"/>
    <c:set var="user" value="${sessionScope.principalUser.id}"/>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <link rel="stylesheet" href="${contextPath}/resources/style/cart.css">
    <link rel="stylesheet" href="${contextPath}/resources/style/general.css">
    <title>Product cart</title>
</head>
<body>
<div class="wrapper">
    <jsp:include page="header.jsp"/>
    <div class="container-fluid content">


        <div class="row mb-2">
            <div class="col-md-6">
                    <div class="my-4 text-center">
                        <h3>Product cart</h3>
                        <c:if test="${requestScope.cartCookie.size() == 0}">
                            <p>The cart is empty</p>
                        </c:if>
                    </div>
                    <ul class="list-group list-group-flush">
                        <c:forEach items="${requestScope.cartCookie}" var="cartItem">
                            <li id ="itemCookieId${cartItem.key.id}" class="list-group-item">
                                <div class="row align-items no-gutters  flex-md-row mb-4  h-md-250 position-relative">
                                    <a href="${contextPath}/product/${cartItem.key.id}">
                                        <div class="col-auto d-none d-lg-block" >
                                            <img  src="${contextPath}/resources/image/sm/${cartItem.key.imageSm}"
                                                  class="item-photo" alt="product_photo">
                                        </div>
                                    </a>
                                    <div class="col p-4 d-flex flex-column position-static">
                                        <p class="mb-0">${cartItem.key.name}</p>
                                        <div class="mb-1 text-muted">Brand: ${cartItem.key.brand}</div>
                                        <div class="mb-1 text-muted">In Stock: ${cartItem.key.quantity}</div>

                                        <div class="cart-amount my-3 ">

                                            <button id="removeFromCookie" type="button" class="btn btn-sm"
                                                    onclick="minusOneProductCookie('productCart',
                                                            ${cartItem.key.id}, ${cartItem.key.price},
                                                            'quantityId${cartItem.key.id}', ${user})">
                                                _
                                            </button>

                                            <i id ="quantityId${cartItem.key.id}">${cartItem.value}</i>

                                            <button id="addToCookie" type="button" class="btn btn-sm"
                                                    onclick="plusOneProductCookie('productCart',
                                                        ${cartItem.key.id}, ${cartItem.key.quantity},
                                                        ${cartItem.key.price}, 'quantityId${cartItem.key.id}', ${user})">
                                                +
                                            </button>
                                        </div>
                                    </div>

                                    <div class="col p-4 d-flex flex-column text-center">
                                        <p class="price">${cartItem.key.price} rub</p>
                                    </div>
                                    <div class="col-flex " style="width: 10%">
                                        <button type="button" class="btn btn-sm"
                                                onclick="deleteCookieItem('productCart', ${cartItem.key.id},
                                                    ${cartItem.key.price}, 'itemCookieId${cartItem.key.id}', ${user})">
                                            Delete
                                        </button>
                                    </div>

                                </div>
                            </li>
                        </c:forEach>
                    </ul>
            </div>

            <div class="col-md-6">
                    <c:if test="${requestScope.cartCookie.size() != 0}">
                        <div class="d-md-flex flex-md-equal w-100 mt-md-5 text-center overflow-hidden">
                            <div class="bg-light shadow-sm mx-auto" style="width: 70%; height: 250px; border-radius: 21px 21px 21px 21px;">
                                <div class="total my-3 p-3">
                                    <nobr>Total: </nobr>
                                    <nobr id=totalId>${requestScope.total}</nobr>
                                    <p></p>
                                    <nobr>Products: </nobr>
                                    <nobr id=amountId>${requestScope.amount}</nobr>
                                        <sec:authorize access="isAuthenticated()">
                                            <button type="button" class="btn btn-block btn-success btn-lg my-4"
                                                    onclick='location.href="${contextPath}/order"'>
                                                Made an order
                                            </button>
                                        </sec:authorize>
                                            <div class="message">
                                                <c:if test="${requestScope.missingProducts.size() != 0}">
                                                    <p class="mb-0 mt-1">Some products from your order are missing:</p>
                                                    <c:forEach items="${requestScope.missingProducts}" var="missingProduct">
                                                        <p>${missingProduct.name}</p>
                                                    </c:forEach>
                                                </c:if>
                                            </div>
                                        <sec:authorize access="!isAuthenticated()">
                                        <div class="my-3 p-3">
                                            <p class="lead">Please Log in to continue</p>
                                            <button  type="button" class="btn btn-success btn-lg"
                                                     onClick='location.href="${contextPath}/login"'>
                                                Login</button>
                                        </div>
                                    </sec:authorize>
                                </div>
                            </div>
                        </div>
                    </c:if>
            </div>
        </div>

    </div>
    <jsp:include page="footer.jsp"/>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>


<script src="${contextPath}/resources/script/tocart.js"></script>
<script>var contextPath = '${contextPath}'</script>

</body>
</html>
