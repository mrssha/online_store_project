<%--
  Created by IntelliJ IDEA.
  User: Maria
  Date: 29.03.2019
  Time: 23:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Order</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <c:set var="contextPath" value="${pageContext.request.getContextPath()}"/>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <link rel="stylesheet" href="${contextPath}/resources/style/general.css">
</head>
<body>

<div class="wrapper">
<jsp:include page="header.jsp"/>

    <div class="container-fluid content">

            <h4 class="mb-3">Checkout</h4>

            <form id="orderForm" action="${contextPath}/order/confirmOrder" method="post" modelAttribute="newOrder">
                <div class="row mb-2">
                    <div class="col-md-8 md-1">
                        <div class="my-3">
                            <h4>Customer data</h4>
                            <label for="customerInfo">First and second names</label>
                            <input type="text" class="form-control" id="customerInfo"
                                   placeholder="${sessionScope.principalUser.firstName} ${sessionScope.principalUser.secondName}"
                                   value="" readonly>
                            <label for="customerEmail">Email</label>
                            <input type="text" class="form-control" id="customerEmail"
                                   placeholder="${sessionScope.principalUser.email}"
                                   value="" readonly>
                        </div>

                        <h4 class="mb-3">Delivery method</h4>
                        <div class="d-block my-3">
                            <div class="custom-control custom-radio">
                                <input id="deliveryMethod1" name="deliveryMethod" type="radio" class="custom-control-input"
                                       value="COURIER"  onclick="checkCourier()" checked required>
                                <label class="custom-control-label" for="deliveryMethod1">Курьером и почтой</label>
                            </div>
                            <div class="custom-control custom-radio">
                                <input id="deliveryMethod2" name="deliveryMethod" type="radio" class="custom-control-input"
                                       value="POSTAMPT" onclick="checkPostampt()" required>
                                <label class="custom-control-label" for="deliveryMethod2">Пункт выдачи</label>
                            </div>
                        </div>

                        <div id = "customerAddressId">
                            <c:if test="${requestScope.addresses.size()!=0}">
                                <h5 class="my-3">Choose address for delivery </h5>
                                <c:forEach items="${requestScope.addresses}" var="address">
                                    <div class="custom-control custom-radio">
                                        <input id="address${address.id}" name="deliveryAddress" type="radio" class="custom-control-input"
                                               value="${address.id}" required>
                                        <label class="custom-control-label" for="address${address.id}">
                                                ${address.houseNumber} ${address.street}, Apt ${address.flatNumber}, ${address.city},
                                                ${address.postcode}, ${address.country}
                                        </label>
                                    </div>
                                </c:forEach>
                            </c:if>
                        </div>

                        <div id = "postamptAddressId" hidden>
                            <h5 class="my-3">Choose pickup point</h5>
                            <c:forEach items="${requestScope.pickupPoints}" var="point">
                                <div class="custom-control custom-radio">
                                    <input id="pointId${point.id}" name="deliveryAddress" type="radio" class="custom-control-input"
                                           value="${point.id}" required>
                                    <label class="custom-control-label" for="pointId${point.id}">
                                            Point: ${point.houseNumber} ${point.street}, Apt ${point.flatNumber}, ${point.city},
                                            ${point.postcode}, ${point.country}
                                    </label>
                                </div>
                            </c:forEach>
                        </div>

                        <hr class="mb-4">
                        <h4 class="mb-3">Payment</h4>
                        <div class="d-block my-3">
                            <div class="custom-control custom-radio">
                                <input id="paymentMethod1" name="paymentMethod" type="radio" class="custom-control-input"
                                       value="CARD"  checked required>
                                <label class="custom-control-label" for="paymentMethod1">By card</label>
                            </div>
                            <div class="custom-control custom-radio">
                                <input id="paymentMethod2" name="paymentMethod" type="radio" class="custom-control-input"
                                       value="CASH"  required>
                                <label class="custom-control-label" for="paymentMethod2">By cash</label>
                            </div>
                        </div>
                    </div>


                    <%--<button type="submit" class="btn btn-primary mt-2">Submit</button>--%>
                    <div class="col-md-4">
                        <div class="d-md-flex flex-md-equal w-100 mt-md-5 text-center overflow-hidden">
                            <div class="bg-light shadow-sm mx-auto" style="width: 70%; height: 250px; border-radius: 21px 21px 21px 21px;">
                                <div class="my-3 p-3">
                                    <p class="lead">Total: ${requestScope.total} rub</p>
                                    <p class="lead">Amount of products: ${requestScope.amount}</p>

                                    <button type="submit" class="btn btn-block btn-success btn-lg">
                                        Confirm order
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
    </div>
    <jsp:include page="footer.jsp"/>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<script src="${contextPath}/resources/script/order.js"></script>
</body>
</html>
