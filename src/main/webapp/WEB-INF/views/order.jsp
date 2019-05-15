<%--
  Created by IntelliJ IDEA.
  User: Maria
  Date: 29.03.2019
  Time: 23:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Order</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <c:set var="contextPath" value="${pageContext.request.getContextPath()}"/>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <link rel="stylesheet" href="${contextPath}/resources/style/general.css">
    <link rel="stylesheet" href="${contextPath}/resources/style/profile.css">
</head>
<body>

<div class="wrapper">
<jsp:include page="header.jsp"/>

    <div class="container-fluid content">

            <form id="orderForm" action="${contextPath}/order/confirmOrder" method="post" modelAttribute="newOrder">
                <div class="mt-3 mb-2 text-center">
                    <h3>Checkout</h3>
                </div>
                <div class="row mb-2">
                    <div class="col-md-4 md-1" style="margin-left: 100px">
                        <div class="my-3">
                            <h4>Customer data</h4>
                            <label for="customerInfo">First and second names:</label>
                            <input type="text" class="form-control" id="customerInfo" style="width: 70%"
                                   placeholder="${sessionScope.principalUser.firstName} ${sessionScope.principalUser.secondName}"
                                   value="" readonly>
                            <label for="customerEmail">Email:</label>
                            <input type="text" class="form-control" id="customerEmail" style="width: 70%"
                                   placeholder="${sessionScope.principalUser.email}"
                                   value="" readonly>
                        </div>

                        <h4 class="mb-3">Delivery method</h4>
                        <div class="d-block my-3">
                            <div class="custom-control custom-radio">
                                <input id="deliveryMethod1" name="deliveryMethod" type="radio" class="custom-control-input"
                                       value="COURIER"  onclick="checkCourier()" checked required>
                                <label class="custom-control-label" for="deliveryMethod1">By courier</label>
                            </div>
                            <div class="custom-control custom-radio">
                                <input id="deliveryMethod2" name="deliveryMethod" type="radio" class="custom-control-input"
                                       value="POSTAMPT" onclick="checkPostampt()" required>
                                <label class="custom-control-label" for="deliveryMethod2">Postampt</label>
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
                    </div>

                    <div class="col-md-2">
                        <h4 class="my-3">Payment</h4>
                        <div class=" my-3">
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

                    <div class="col-md-5">
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
            <div class="row mb-2">
                <div class="first-coll col-md-3 md-1" style="margin-left: 90px">
                    <c:if test="${requestScope.addresses.size()==0}">

                        <h5 class="my-3">Add an address for delivery </h5>
                        <div class="my-3">
                            <form:form id="addAddressForm" class="needs-validation"
                                       action="${contextPath}/order" method="post" modelAttribute="newAddress">

                                <div class="form-group">
                                    <label for="inputCountryId">Enter country</label>
                                    <form:input type="text" class="form-control" id="inputCountryId"
                                                placeholder="Country" path="country" required ="true"/>
                                    <form:errors path="country" cssClass="error"/>
                                </div>
                                <div class="form-group">
                                    <label for="inputCityId">Enter city</label>
                                    <form:input type="text" class="form-control" id="inputCityId"
                                                path="city" placeholder="City" required ="true"/>
                                    <form:errors path="city" cssClass="error"/>
                                </div>
                                <div class="form-group">
                                    <label for="inputPostcodeId">Enter postcode</label>
                                    <form:input type="number" class="form-control" min="0"  id="inputPostcodeId"
                                                path="postcode" placeholder="Postcode" required="true" max="999999999"/>
                                    <form:errors path="postcode" cssClass="error"/>
                                </div>
                                <div class="form-group">
                                    <label for="inputStreetId">Enter street</label>
                                    <form:input type="text" class="form-control" id="inputStreetId"
                                                path="street" placeholder="Street" required="true"/>
                                    <form:errors path="street" cssClass="error"/>
                                </div>
                                <div class="form-group form-row">
                                    <div class="col">
                                        <label for="houseNumberId">Enter house number</label>
                                        <form:input type="number" class="form-control" id="houseNumberId" min="0" max="999999999"
                                                    path="houseNumber" placeholder="House number" required ="true"/>
                                        <form:errors path="houseNumber" cssClass="error"/>
                                    </div>

                                    <div class="col">
                                        <label for="flatNumberId">Enter flat number</label>
                                        <form:input type="number" class="form-control" id="flatNumberId" min="0" max="999999999"
                                                    path="flatNumber" placeholder="Flat number" required ="true"/>
                                        <form:errors path="flatNumber" cssClass="error1"/>
                                    </div>
                                </div>
                                <button type="submit" class="btn btn-primary mt-2">Submit</button>
                            </form:form>
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

<script src="${contextPath}/resources/script/order.js"></script>
</body>
</html>
