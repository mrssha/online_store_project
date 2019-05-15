<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Maria
  Date: 03.04.2019
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order manager</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <c:set var="contextPath" value="${pageContext.request.getContextPath()}"/>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="${contextPath}/resources/style/general.css">
</head>
<body>
<div class="wrapper">
    <jsp:include page="header.jsp"/>

    <div class="container-fluid content">
        <div class="my-3 text-center">
            <h2>Order manager</h2>
        </div>
        <table class="table my-3">
            <thead class="thead-dark">
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Customer</th>

                <th scope="col" width="130">Date order</th>
                <th scope="col" width="20">Quantity products</th>

                <th scope="col">Total</th>
                <th scope="col" width="100">Payment method</th>

                <th scope="col">Delivery method</th>
                <th scope="col">Address delivery</th>

                <th scope="col" width="50">Payment status</th>
                <th scope="col">Order status</th>
                <th scope="col">Save changes</th>
            </tr>
            </thead>

            <tbody>
            <c:set var="count" value="0" scope="page" />

            <c:forEach items="${requestScope.orders}" var="order">
                <c:set var="count" value="${count + 1}" scope="page"/>

                <tr>
                    <td>${order.id}</td>
                    <td>${order.customer.firstName} ${order.customer.secondName} </td>
                    <td><fmt:formatDate value="${order.dateOrder}" pattern="yyyy-MM-dd"/></td>
                    <td width="10">${order.quantityProducts}</td>
                    <td>${order.payment_amount} rub</td>
                    <td>${order.paymentMethod}</td>
                    <td>${order.deliveryMethod}</td>
                    <td>
                        ${order.customerAddress.houseNumber} ${order.customerAddress.street},
                        ${order.customerAddress.flatNumber}, ${order.customerAddress.city},
                        ${order.customerAddress.postcode}, ${order.customerAddress.country}
                    </td>
                    <td>${order.paymentStatus}</td>

                    <td>
                        <div class="dropdown show">
                            <button id ="dropId${order.id}" class="btn bg-white dropdown-toggle" href="#" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    ${order.orderStatus}
                            </button>
                            <div id = "menuId${order.id}" class="dropdown-menu" aria-labelledby="dropdownMenu2">
                                <a class="dropdown-item" onclick="changeValue('dropId${order.id}', 'AWAIT_PAYMENT')">
                                    AWAIT_PAYMENT</a>
                                <a class="dropdown-item" onclick="changeValue('dropId${order.id}', 'WAIT_SHIPMENT')">
                                    WAIT_SHIPMENT</a>
                                <a class="dropdown-item" onclick="changeValue('dropId${order.id}', 'SHIPPED')">
                                    SHIPPED</a>
                                <a class="dropdown-item" onclick="changeValue('dropId${order.id}','DELIVERED')">
                                    DELIVERED</a>
                            </div>
                        </div>
                    </td>
                    <td>
                        <button id="${order.id}" type="button" class="btn btn-sm"
                                onclick="update('${contextPath}/admin/orders/save',
                                    ${order.id}, id)">
                            Save
                        </button>

                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>
    <jsp:include page="footer.jsp"/>
</div>




<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>


<script src="${contextPath}/resources/script/orderManager.js"></script>
</body>
</html>
