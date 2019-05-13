<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <c:set var="contextPath" value="${pageContext.request.getContextPath()}"/>
    <c:set var="user" value="${sessionScope.principalUser.id}"/>

    <script type="text/javascript" src="${contextPath}/resources/script/tocart.js"></script>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <link rel="stylesheet" href="${contextPath}/resources/style/home.css">
    <link rel="stylesheet" href="${contextPath}/resources/style/general.css">

    <title>Letsnow</title>
</head>
<body>
<div class="wrapper">
    <jsp:include page="header.jsp"/>
    <div class="container-fluid content">
        <div class="row">
            <nav class="col-md-2 d-none d-md-block my-3 sidebar" style="border-right: 1px solid #DCDCDC; height: fit-content">

                <div class="sidebar-sticky">
                    <form id="filterId" action="${contextPath}/filter" method="get">
                        <div class="form-group">
                            <label for="selectCategory">Category</label>
                            <select class="form-control" id="selectCategory"
                                    name="category">
                                <%--<option selected>Choose category</option>--%>
                                <c:forEach items="${sessionScope.categories}" var="category">
                                    <option value=${category.id}>${category.categoryName}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="selectBrandId">Brand</label>
                            <select multiple class="form-control" id="selectBrandId"
                                    name="brand">
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

                        <div class="form-group">
                            <label for="minPriceId">Minimum price</label>
                            <input type="number" min=0 class="form-control" name="minPrice"
                                   value="${minPrice}" id="minPriceId">
                        </div>

                        <div class="form-group">
                            <label for="maxPriceId">Maximum price</label>
                            <input type="number" min=0 class="form-control" name="maxPrice"
                                   value="${maxPrice}" id="maxPriceId" >
                        </div>

                        <button type="submit" class="btn btn-primary mb-2">Submit</button>
                    </form>
                </div>
            </nav>

            <div class="col-md-10">
                <div class="container-fluid mt-3">
                    <div id="imgMain" class="photoMain">
                        <%--<img id="imgId" src="${contextPath}/resources/image/main2.jpg" class="photo-main" alt="product_photo">--%>
                    </div>
                </div>
                <div class="container-fluid mt-3">
                <div class="row product-list">
                    <c:forEach items="${requestScope.selectedProducts}" var="product">
                        <div class="col">
                            <div class="card text-center" >
                                <a href="${contextPath}/product/${product.id}">
                                    <img  src="${contextPath}/resources/image/bg/${product.imageBg}" class="card-img-top" alt="product_photo">
                                    <div class="card-body" >
                                        <p class="card-title">${product.name}</p>
                                        <p class="card-text">${product.price}</p>
                                    </div>
                                </a>
                                <button id="buttonId${product.id}" type="button" class="btn"
                                        onclick="addRemoveProduct('productCart', ${product.id}, id,
                                            ${user})"
                                    ${product.quantity eq 0  ? 'disabled="disabled"' : ''}>
                                    Add to cart
                                </button>

                                <script async>
                                    correctButtonName('buttonId${product.id}', '${product.id}');
                                    <%--hideImg('imgMain', '${requestScope.hiddenImg}')--%>
                                </script>

                            </div>
                        </div>
                    </c:forEach>
                </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="footer.jsp"/>
</div>


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>


<script>var contextPath = '${contextPath}'</script>
<script src="${contextPath}/resources/script/cart.js"></script>

</body>
</html>


