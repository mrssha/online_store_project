<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Maria
  Date: 18.04.2019
  Time: 17:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Statistics</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <c:set var="contextPath" value="${pageContext.request.getContextPath()}"/>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

<jsp:include page="header.jsp"/>

<div class="container-fluid">

    <div class="my-3 text-center">
        <h2>Statistics</h2>
    </div>
    <div class="row">
        <div class="col-auto my-1">
            <h4>Revenue for period:</h4>
        </div>
        <div class="col-auto my-1">
            <h4 id="resultRevenue"></h4>
        </div>
    </div>
    <div class="form-row align-items-center">
        <div class="col-auto my-1">
            <label class="mr-sm-2 sr-only" for="yearSearch">Preference</label>
            <select class="custom-select mr-sm-2" id="yearSearch">
                <%--<option selected>Year...</option>--%>
                <c:forEach items="${years}" var="year">
                    <option value="${year}">${year}</option>
                </c:forEach>
            </select>
        </div>
        <div class="col-auto my-1">
            <label class="mr-sm-2 sr-only" for="monthSearch">Preference</label>
            <select class="custom-select mr-sm-2" id="monthSearch">
                <%--<option selected>Month...</option>--%>
                <c:forEach items="${months}" var="month">
                    <option value="${month}">${month}</option>
                </c:forEach>
            </select>
        </div>
        <button id="butCalculate" type="button" class="btn btn-primary mx-2"
                onclick="calculate('${contextPath}/admin/statistics/calculate')">
            calculate
        </button>
    </div>


    <div class="mt-5 mb-2 text-center">
        <h2>TOP 10 products</h2>
    </div>

    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">#</th>
            <th scope="col">Id</th>
            <th scope="col">Name</th>
            <th scope="col">Category</th>
            <th scope="col">Brand</th>

            <th scope="col">Weight</th>
            <th scope="col">Price</th>
            <th scope="col">Quantity</th>
            <th scope="col">Small image</th>
            <th scope="col">Big image</th>
            <th scope="col">Sales</th>
        </tr>
        </thead>

        <tbody>

        <c:set var="count" value="0" scope="page" />

        <c:forEach items="${requestScope.products}" var="product">
            <c:set var="count" value="${count + 1}" scope="page"/>

            <tr>
                <td>${count}</td>
                <th>${product.id}</th>
                <td>${product.name}</td>
                <td>${product.category.categoryName}</td>
                <td>${product.brand}</td>
                <td>${product.weight}</td>
                <td>${product.price}</td>
                <td>${product.quantity}</td>
                <td>${product.imageSm}</td>
                <td>${product.imageBg}</td>
                <td>${product.sales}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="mt-5 mb-2 text-center">
        <h2>TOP 10 customers</h2>
    </div>

    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">#</th>
            <th scope="col">Id</th>
            <th scope="col">First name</th>
            <th scope="col">Second name</th>
            <th scope="col">Email</th>
            <th scope="col">Birth date</th>
            <th scope="col">Sum of purchases</th>
        </tr>
        </thead>

        <tbody>
        <c:set var="count" value="0" scope="page" />
        <c:forEach items="${requestScope.customersTop}" var="customer">
            <c:set var="count" value="${count + 1}" scope="page"/>

            <tr>
                <td>${count}</td>
                <th>${customer.id}</th>
                <td>${customer.firstName}</td>
                <td>${customer.secondName}</td>
                <td>${customer.email}</td>
                <td><fmt:formatDate value="${customer.birthDate}" pattern="yyyy-MM-dd"/></td>
                <%--<td>${customer.birthDate}</td>--%>
                <td>${customer.sumPurchases}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<script src="${contextPath}/resources/script/stat.js"></script>

</body>
</html>
