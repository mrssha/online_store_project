<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <c:set var="contextPath" value="${pageContext.request.getContextPath()}"/>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <link href="${contextPath}/resources/style/home.css" rel="stylesheet">

    <title>Letsnow</title>
</head>
<body>

<jsp:include page="header.jsp"/>

<div class="container-fluid">
    <div class="row">
        <nav class="col-md-2 d-none d-md-block bg-light sidebar">

            <div class="sidebar-sticky">
                <form id="filterId" action="filter" method="get">
                    <div class="form-group">
                        <label for="selectCategoryId">Выберете категорию поиска</label>
                        <select class="form-control" id="selectCategoryId"
                                name="category">
                            <option>snowboards</option>
                            <option>accessories</option>
                            <option>clothes</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="selectBrandId">Брэнд</label>
                        <select multiple class="form-control" id="selectBrandId"
                                name="brand">
                            <option>K2</option>
                            <option>BF</option>
                            <option>666</option>
                            <option>BURTON</option>
                            <option>Roxy</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="minPriceId">Минимальная цена</label>
                        <input type="number" min=0 class="form-control" name="minPrice"
                               value="${minPrice}" id="minPriceId" placeholder= 0>
                    </div>

                    <div class="form-group">
                        <label for="maxPriceId">Максимальная цена</label>
                        <input type="number" min=0 class="form-control" name="maxPrice"
                               value="${maxPrice}" id="maxPriceId" >
                    </div>

                    <button type="submit" class="btn btn-primary mb-2">Применить фильтр</button>

                </form>
            </div>
        </nav>

        <div class="col-md-10">
            <div class="row justify-content-start product-list">
                <c:forEach items="${requestScope.selectedProducts}" var="product">
                    <div class="col">
                        <div class="card" >
                            <a href="${contextPath}/product/${product.id}">
                                <img  src="${contextPath}/resources/image/bg/${product.imageBg}" class="card-img-top" alt="product_photo">
                                <div class="card-body" >
                                    <h5 class="card-title">${product.name}</h5>
                                    <p class="card-text">${product.price}</p>
                                </div>
                            </a>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>


<script>var contextPath = '${contextPath}'</script>

</body>
</html>


