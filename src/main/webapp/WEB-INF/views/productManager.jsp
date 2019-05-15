<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en" xml:lang="en" xmlns="http://www.w3.org/1999/xhtml">
<%--
  Created by IntelliJ IDEA.
  User: Maria
  Date: 02.04.2019
  Time: 18:05
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Product manager</title>
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
        <form:form id="productForm" action="${contextPath}/admin/products"
              method="post" modelAttribute="newProduct" enctype="multipart/form-data">

            <form>
                <div class="my-3 text-center">
                    <h2>Product manager</h2>
                </div>
                <div class="form-row my-1">
                    <div class="col">
                        <label for="selectNameId">Product name</label>
                        <form:input type="text" name="name" maxlength="45" class="form-control"
                                    path="name" id="selectNameId" required="true"/>
                        <form:errors path="name" cssClass="error"/>
                    </div>
                    <div class="col">
                        <label for="cat">Category</label>
                        <select class="form-control" id="cat" name="categoryId">
                            <c:forEach items="${requestScope.categories}" var="category">
                            <option value=${category.id}>${category.categoryName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col">
                        <label for="selectBrandId">Brand</label>
                        <select class="form-control" id="selectBrandId"
                                name="brand" required>
                                <option>Jones</option>
                                <option>BF</option>
                                <option>NIDECKER</option>
                                <option>BURTON</option>
                                <option>ROXY</option>
                                <option>K2</option>
                                <option>666</option>
                                <option>Salomon</option>
                        </select>
                    </div>
                    <div class="col">
                        <label for="weightId">Weight</label>
                        <form:input id = "weightId" type="number" name="weight"
                               path="weight" class="form-control" min="0" max="999999999" required="true"/>
                        <form:errors path="weight" cssClass="error"/>
                    </div>
                </div>
                <div class="form-row my-1">
                    <div class="col">
                        <label for="priceId">Price</label>
                        <form:input id = "priceId" type="number" name="price"
                                    path="price" class="form-control" min="0" max="999999999" required="true" />
                        <form:errors path="price" cssClass="error"/>
                    </div>
                    <div class="col">
                        <label for="quantityId">Quantity</label>
                        <form:input id ="quantityId" type="number" name="quantity"
                                    path="quantity" class="form-control" min="0" max="999999999" required="true"/>
                        <form:errors path="quantity" cssClass="error"/>
                    </div>
                    <div class="col">
                        <label for="smImageId">Load small image</label>
                        <input id ="smImageId" type="file" name="fileSm" required>
                    </div>
                    <div class="col">
                        <label for="bgImageId">Load big image</label>
                        <input id ="bgImageId" type="file" name="fileBg"  required>
                    </div>
                </div>

                <div class="my-3" style="text-align: center">
                    <button type="submit" class="btn btn-success btn-lg" style="width: 30%">
                        Load product
                    </button>
                </div>
            </form>
        </form:form>

        <p id = "messageId" style="color: #DC143C">${requestScope.message}</p>

        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Name</th>
                <th scope="col">Category</th>
                <th scope="col">Brand</th>

                <th scope="col">Weight</th>
                <th scope="col">Price</th>
                <th scope="col">Quantity</th>
                <th scope="col">Small image</th>
                <th scope="col">Big image</th>
                <th scope="col"></th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${requestScope.products}" var="product">
                <tr>
                    <th scope="row">${product.id}</th>
                    <td>${product.name}</td>
                    <td>${product.category.categoryName}</td>
                    <td>${product.brand}</td>
                    <td>${product.weight}</td>
                    <td>${product.price}</td>
                    <td>${product.quantity}</td>
                    <td>${product.imageSm}</td>
                    <td>${product.imageBg}</td>
                    <td width="100">
                        <button id="${product.id}" type="button" class="btn btn-sm"
                                onclick="deleteRow('${contextPath}/admin/products/delete',
                                    ${product.id}, id)">
                            delete
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

<script src="${contextPath}/resources/script/category.js"></script>
</body>
</html>
