<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body>

<jsp:include page="header.jsp"/>


<div class="container-fluid">
    <div class="my-3 text-center">
        <h2>Order manager</h2>
    </div>
    <table class="table my-3">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Id</th>

            <th scope="col">Date order</th>
            <th scope="col">Quantity products</th>

            <th scope="col">Total</th>
            <th scope="col">Payment method</th>

            <th scope="col">Delivery method</th>
            <th scope="col">Address delivery</th>

            <th scope="col">Payment status</th>
            <th scope="col">Order status</th>
            <th scope="col"></th>
        </tr>
        </thead>

        <tbody>
        <c:set var="count" value="0" scope="page" />

        <c:forEach items="${requestScope.orders}" var="order">
            <c:set var="count" value="${count + 1}" scope="page"/>

            <tr>
                <td>${order.id}</td>
                    <%--<c:set var="date" value="${order.dateOrder}"/>--%>
                    <%--<fmt:formatDate pattern="MM/dd/yyyy" value="${date}" var="parsedDate" />--%>
                    <%--<td>${parsedDate}</td>--%>
                <td>${order.dateOrder}</td>
                <td>${order.quantityProducts}</td>
                <td>${order.payment_amount} rub</td>
                <td>${order.paymentMethod}</td>
                <td>${order.deliveryMethod}</td>
                <td>${order.customerAddress}</td>
                <td>${order.paymentStatus}</td>
                <td>${order.orderStatus}</td>
                <td>
                    <!-- Button trigger modal -->
                    <button id="${order.id}" type="button" class="btn btn-sm" data-toggle="modal" data-target="#exampleModal">
                        Edit
                    </button>

                    <!-- Modal -->
                    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    ...
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                    <button type="button" class="btn btn-primary">Save changes</button>
                                </div>
                            </div>
                        </div>
                    </div>

                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>




<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

</body>
</html>
