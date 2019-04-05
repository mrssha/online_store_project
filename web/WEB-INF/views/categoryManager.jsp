<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Maria
  Date: 02.04.2019
  Time: 19:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Category manager</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <c:set var="contextPath" value="${pageContext.request.getContextPath()}"/>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body>

<jsp:include page="header.jsp"/>

<div class="container-fluid">

    <div class="row my-4" >
        <div class="col-md-3">
        </div>

        <div class="col-md-6">
            <form id="categoryForm" action="${contextPath}/admin/categories/addCategory"
                  method="post" modelAttribute="newCategory">

                <div class="my-3 text-center">
                    <h2>Category manager</h2>
                </div>

                <div class="col">
                    <label for="categoryName">Enter category name</label>
                    <input type="text" name="categoryName" class="form-control" id="categoryName" required>
                </div>

                <div class="my-3" style="text-align: center">
                    <button type="submit" class="btn btn-success btn-lg" style="width: 30%">
                        Add category
                    </button>
                </div>
            </form>

            <table id ="categoryTableId" class="table">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Category name</th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                </tr>
                </thead>

                <tbody>

                <c:set var="count" value="0" scope="page" />
                <c:forEach items="${requestScope.categories}" var="category">
                    <c:set var="count" value="${count + 1}" scope="page"/>

                    <tr id = ${count}>
                        <th scope="row" width="100">${category.id}</th>
                        <td contenteditable="true">${category.categoryName}</td>

                        <td>
                            <button id="${category.id}" type="button" class="btn btn-sm"
                                    onclick="update('${contextPath}/admin/categories/save',
                                        ${category.id}, 'categoryTableId', id)">
                                Save
                            </button>

                        </td>

                        <td width="100">
                            <button id="${category.id}" type="button" class="btn btn-sm"
                                    onclick="deleteRow('${contextPath}/admin/categories/delete',
                                        ${category.id}, id)">
                                Delete
                            </button>
                        </td>

                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="col-md-3">
        </div>
    </div>


</div>



<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<script src="${contextPath}/resources/script/category.js"></script>
</body>
</html>
