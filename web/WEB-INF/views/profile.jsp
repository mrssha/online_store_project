<%--
  Created by IntelliJ IDEA.
  User: Maria
  Date: 24.03.2019
  Time: 21:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Customer profile</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <c:set var="contextPath" value="${pageContext.request.getContextPath()}"/>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <%--<link rel="stylesheet" href="${contextPath}/resources/style/profile.css">--%>
</head>
<body>

<jsp:include page="header.jsp"/>

<div class="container-fluid">
    <div class="row my-4" >
        <div class="col-md-1">
        </div>
        <div class="col-md-3">
            <div class="my-2 py-2">
                <h2 class="display-5">${sessionScope.principalUser.firstName} ${sessionScope.principalUser.secondName}</h2>
                <p class="text-muted mb-0">Email</p>
                <p class="mb-0">${sessionScope.principalUser.email}</p>
            </div>
            <div class="my-2 py-2">
                <p class="text-muted mb-0">Date of birth</p>
                <p class="mb-0">Date: ${sessionScope.principalUser.birthDate}</p>
            </div>

            <div class="btn-group" role="group" aria-label="Basic example">
                <button id="editInfo" type="button" class="btn btn-outline-info btn-sm m-2" onclick="showFormEdit()">
                    Edit profile info
                </button>
                <button id="addAddres" type="button" class="btn btn-outline-info btn-sm m-2" onclick="showFormAddress()">
                    Add address
                </button>
                <button id="editPassword" type="button" class="btn btn-outline-info btn-sm m-2"
                        onclick="showFormEditPassword()">
                    Change password
                </button>
            </div>
            <c:if test="${requestScope.addresses.size()!=0}">
                <p class="my-3 text-muted">Addresses:</p>
                <c:forEach items="${requestScope.addresses}" var="address">
                    <ul class="list-group list-group-flush ">
                        <li class="list-group-item">
                            <div class="col-flex mb-0">
                                <p class="mb-0">${address.houseNumber} ${address.street}, Apt ${address.flatNumber}</p>
                                <p class="mb-0">${address.city}</p>
                                <p class="mb-0">${address.postcode}</p>
                                <p class="mb-0">${address.country}</p>
                            </div>
                            <div class="col-flex mb-0 text-center text-muted">

                            </div>
                        </li>
                    </ul>
                </c:forEach>
            </c:if>
        </div>
        <div class="col-md-3">
            <div class="my-3">
                <form id="editProfile" action="${contextPath}/profile/changeInfo" hidden method="post">
                    <div class="form-group">
                        <label for="inputFirstName">Enter first name</label>
                        <input type="text" class="form-control my-0" id="inputFirstName"
                               value="${sessionScope.principalUser.firstName}${changeInfo.firstName}"
                               name="firstName" required>
                    </div>
                    <div class="form-group">
                        <label for="inputSecondName">Enter second name</label>
                        <input type="text" class="form-control my-0" id="inputSecondName"
                               value="${sessionScope.principalUser.secondName}${changeInfo.secondName}"
                               name="secondName" placeholder="Second name" required>
                    </div>

                    <div class="form-group">
                        <label for="inputEmail">Enter email</label>
                        <input type="email" id="inputEmail" class="form-control my-0" name="email"
                               value="${sessionScope.principalUser.email}${changeInfo.email}"
                               placeholder="Email address" required>
                    </div>
                    <%--<label for="inputDate">Enter new date of birth</label>--%>
                    <%--<input type="date" class="form-control" id="inputDate" placeholder="Date of birth"--%>
                           <%--value="${changeInfo.birthDate}" name="birthDate">--%>
                    <button type="submit" class="btn btn-primary mt-2">Submit</button>
                </form>
            </div>
            <div class="my-3">
                <form id="editPasswordForm" action="${contextPath}/profile/changePassword" hidden method="post">
                    <div class="form-group">
                        <label for="inputPassword1">Enter old password</label>
                        <input type="password" class="form-control" id="inputPassword1"
                               name="oldPassword" placeholder="Old password" required>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword2">Enter new password</label>
                        <input type="password" class="form-control" id="inputPassword2"
                               name="newPassword" placeholder="New password" required>
                    </div>
                    <button type="submit" class="btn btn-primary mt-2">Submit</button>
                </form>
            </div>

            <c:if test ="${not empty requestScope.passEditMessage}">
                <c:out value="${requestScope.passEditMessage}" />
            </c:if>
            <%--<div id = "passwordMessageId" class="my-3" hidden>--%>
                <%--<p>${requestScope.passEditMessage}</p>--%>
            <%--</div>--%>

        </div>
        <div class="col-md-3">
            <div class="my-3">

                <form id="addAddressForm" class="needs-validation" hidden
                      action="${contextPath}/profile/addAddress" method="post">
                    <div class="form-group">
                        <label for="inputCountryId">Enter country</label>
                        <input type="text" class="form-control" name = "country" id="inputCountryId"
                               value="${newAddress.country}" placeholder="Country" required>
                    </div>
                    <div class="form-group">
                        <label for="inputCityId">Enter city</label>
                        <input type="text" class="form-control" name = "city" id="inputCityId"
                               value="${newAddress.city}" placeholder="City" required>
                    </div>
                    <div class="form-group">
                        <label for="inputPostcodeId">Enter new postcode</label>
                        <input type="text" class="form-control" name="postcode"  id="inputPostcodeId"
                               value="${newAddress.postcode}" placeholder="Postcode" required>
                    </div>
                    <div class="form-group">
                        <label for="inputStreetId">Enter new street</label>
                        <input type="text" class="form-control" name="street"  id="inputStreetId"
                               value="${newAddress.street}" placeholder="Street" required>
                    </div>
                    <div class="form-group">
                        <label for="houseNumberId">Enter house number</label>
                        <input type="number" class="form-control" id="houseNumberId"
                               value="${newAddress.houseNumber}" name="houseNumber"  required>
                    </div>
                    <div class="form-group">
                    <label for="flatNumberId">Enter flat number</label>
                    <input type="number" class="form-control" id="flatNumberId"
                           value="${newAddress.flatNumber}" name="flatNumber"  required>
                    </div>
                    <button type="submit" class="btn btn-primary mt-2">Submit</button>
                </form>
            </div>
        </div>
    </div>
</div>


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>



<script src="${contextPath}/resources/script/show.js"></script>
</body>
</html>
