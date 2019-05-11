<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Maria
  Date: 03.04.2019
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer orders</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <c:set var="contextPath" value="${pageContext.request.getContextPath()}"/>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body>

<jsp:include page="header.jsp"/>

<div class="container-fluid">
    <div class="my-3 text-center">
        <h2>Order history</h2>
        <c:if test="${requestScope.orders.size() == 0}">
            <p>You have no orders yet.</p>
        </c:if>
    </div>
    <c:if test="${requestScope.orders.size() != 0}">
    <table class="table my-3">
        <thead class="thead-dark">
        <tr>
            <th scope="col">#</th>

            <th scope="col">Date order</th>
            <th scope="col" width="50">Quantity products</th>

            <th scope="col">Total</th>
            <th scope="col">Payment method</th>

            <th scope="col">Delivery method</th>
            <th scope="col">Address delivery</th>

            <th scope="col">Payment status</th>
            <th scope="col">Order status</th>

        </tr>
        </thead>

        <tbody>
        <c:set var="count" value="0" scope="page" />

        <c:forEach items="${requestScope.orders}" var="order">

            <c:set var="count" value="${count + 1}" scope="page"/>

            <tr>
                <td>${count}</td>
                <td><fmt:formatDate value="${order.dateOrder}" pattern="yyyy-MM-dd"/></td>
                <td>${order.quantityProducts}</td>
                <td>${order.payment_amount} rub</td>
                <td>${order.paymentMethod}</td>
                <td>${order.deliveryMethod}</td>
                <td>
                    ${order.customerAddress.houseNumber} ${order.customerAddress.street},
                        Apt ${order.customerAddress.flatNumber}, ${order.customerAddress.city},
                    ${order.customerAddress.postcode}, ${order.customerAddress.country}
                </td>
                <td>${order.paymentStatus}</td>
                <td>${order.orderStatus}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    </c:if>

</div>



<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

</body>
</html>
