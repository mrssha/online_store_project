<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Product details</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <c:set var="contextPath" value="${pageContext.request.getContextPath()}"/>
    <c:set var="user" value="${sessionScope.principalUser.id}"/>
    <script type="text/javascript" src="${contextPath}/resources/script/tocart.js"></script>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <link href="${contextPath}/resources/style/detail.css" rel="stylesheet">
    <link rel="stylesheet" href="${contextPath}/resources/style/general.css">
</head>
<body>
<div class="wrapper">
<jsp:include page="header.jsp"/>
    <div class="container-fluid content">
        <div class="row">
            <div class="col-md-1">
            </div>
            <div class="col-md-3">
                <div class="d-md-flex flex-md-equal w-100 my-md-3 pl-md-3">
                    <div class="mr-md-3 text-center overflow-hidden">
                        <div class="shadow-sm mx-auto">
                            <img  src="${contextPath}/resources/image/bg/${requestScope.productDetail.imageBg}"
                                  class="detail-photo" alt="product_photo">
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-md-4">
                <div class="my-3 py-3">
                    <h2 class="display-5">${requestScope.productDetail.name}</h2>
                    <p class="lead">Category: ${requestScope.productDetail.category.categoryName}</p>
                    <p class="lead">Brand: ${requestScope.productDetail.brand}</p>
                </div>
                <div class="my-3 py-3">
                    <p class="lead">Specifications:</p>
                    <h>Weight: ${requestScope.productDetail.weight}</h>
                </div>
            </div>

            <div class="col-md-3 text-center">
                <div class="my-3 py-3">
                    <h2 class="price">${requestScope.productDetail.price} rub</h2>
                </div>
                <div class="my-3 py-3">
                    <p class="lead">In stock: ${requestScope.productDetail.quantity}</p>
                    <c:set var="productId" value="${requestScope.productDetail.id}"/>
                    <button id = "addRemoveId${productId}" type="button" class="btn btn-outline-danger btn-lg"
                            onclick="addRemoveProduct('productCart', '${productId}', id,  ${user})"
                            ${requestScope.productDetail.quantity eq 0  ? 'disabled="disabled"' : ''}>
                        Add to cart
                    </button>
                    <script async>
                        correctButtonName('addRemoveId${productId}', '${productId}');
                    </script>
                </div>
                <div class="my-3 py-3">
                    <button  type="button" class="btn btn-success btn-lg"
                             onClick='location.href="${contextPath}/cart"'>
                        Go to cart</button>
                </div>
            </div>
        </div>

    </div>
    <jsp:include page="footer.jsp"/>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>


<script>var contextPath = '${contextPath}'</script>
</body>
</html>