<%--
  Created by IntelliJ IDEA.
  User: Maria
  Date: 29.03.2019
  Time: 3:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <c:set var="contextPath" value="${pageContext.request.getContextPath()}"/>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <link rel="stylesheet" href="${contextPath}/resources/style/cart.css">
    <title>Product cart</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="container-fluid">

    <div class="row mb-2">
        <div class="col-md-6">
            <div class="my-4 text-center">
                <h3>Product cart</h3>
                <c:if test="${requestScope.cartItems.size() == 0}">
                    <p>The cart is empty</p>
                </c:if>
            </div>

            <ul class="list-group list-group-flush"
                <c:forEach items="${requestScope.cartItems}" var="cartItem">
                    <li class="list-group-item">
                        <div class="row align-items no-gutters  flex-md-row mb-4  h-md-250 position-relative">
                        <div class="col-auto d-none d-lg-block" >
                            <img  src="${contextPath}/resources/image/sm/${cartItem.product.imageSm}"
                                  class="item-photo" alt="product_photo">
                        </div>
                        <div class="col p-4 d-flex flex-column position-static">
                            <p class="mb-0">${cartItem.product.name}</p>
                            <div class="mb-1 text-muted">Brand: ${cartItem.product.brand}</div>
                            <div class="mb-1 text-muted">In Stock: ${cartItem.product.quantity}</div>

                            <div class="cart-amount my-3 ">
                                <%--<span data-action="remove" onclick="" class="">–</span>--%>
                                <button id="add" type="button" class="btn btn-sm"
                                        onclick="removeFromCart('${contextPath}/removeFromCart',
                                                '${cartItem.product.id}', 'quantityId${cartItem.product.id}')">
                                    _
                                </button>
                                <i id ="quantityId${cartItem.product.id}">${cartItem.quantity}</i>
                                <button id="add" type="button" class="btn btn-sm"
                                        onclick="addToCart('${contextPath}/addToCart',
                                                '${cartItem.product.id}', 'quantityId${cartItem.product.id}')">
                                    +
                                </button>
                            </div>
                        </div>
                            <%--style="border: 1px solid #dddddd;--%>

                        <div class="col p-4 d-flex flex-column text-center position-static">
                            <p class="price">${cartItem.product.price} rub</p>
                        </div>

                        </div>
                    </li>
                </c:forEach>
            </ul>

        </div>

        <div class="col-md-6">

            <%--<c:if test="${requestScope.cartItems.size() != 0}">--%>

            <div class="d-md-flex flex-md-equal w-100 mt-md-5 text-center overflow-hidden">
                <div class="bg-light shadow-sm mx-auto" style="width: 70%; height: 250px; border-radius: 21px 21px 21px 21px;">
                    <div class="my-3 p-3">
                        <p class="lead">Total: ${requestScope.total} rub</p>
                        <p class="lead">Amount of products: ${requestScope.amount}</p>

                        <button type="button" class="btn btn-block btn-success btn-lg"
                                onclick='location.href="${contextPath}/order"'>
                            Made an order
                        </button>
                    </div>
                </div>
            </div>

            <div class="my-3 p-3">

                <sec:authorize access="!isAuthenticated()">
                    <div class="row site-padding auth justify-content-center">
                        <div class="col-md-6">
                            Please Log in to continue
                            <button  type="button" class="btn btn-success btn"
                                     onClick='location.href="${contextPath}/login"'>
                                Go to cart</button>
                        </div>
                    </div>
                </sec:authorize>

                <%--<c:if test="${requestScope.missingProducts.size() == 0}">--%>
                    <%--<p>Все продукты в наличии</p>--%>
                <%--</c:if>--%>
                <c:if test="${requestScope.missingProducts.size() != 0}">
                    <p>Некоторые товары из вашего заказа отсутствуют на складе:</p>
                    <c:forEach items="${requestScope.missingProducts}" var="missingProduct">
                        <p>${missingProduct}</p>
                    </c:forEach>
                </c:if>
            </div>
        </div>
    </div>
</div>

<script src="${contextPath}/resources/script/cart.js"></script>
</body>
</html>
