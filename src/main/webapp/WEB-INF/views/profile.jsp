<%--
  Created by IntelliJ IDEA.
  User: Maria
  Date: 24.03.2019
  Time: 21:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Customer profile</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <c:set var="contextPath" value="${pageContext.request.getContextPath()}"/>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <link rel="stylesheet" href="${contextPath}/resources/style/profile.css">
    <link rel="stylesheet" href="${contextPath}/resources/style/general.css">
</head>
<body>

<div class="wrapper">
<jsp:include page="header.jsp"/>

    <div class="container-fluid content">
        <div class="row my-4" >
            <div class="col-md-1">
            </div>
            <div class="col-md-4">
                <div class="my-2 py-2">
                    <h2 class="display-5">${sessionScope.principalUser.firstName} ${sessionScope.principalUser.secondName}</h2>
                    <p class="text-muted mb-0">Email:</p>
                    <p class="mb-0">${sessionScope.principalUser.email}</p>
                </div>
                <div class="my-2 py-2">
                    <p class="text-muted mb-0">Date of birth:</p>
                    <p class="mb-0"><fmt:formatDate value="${sessionScope.principalUser.birthDate}" pattern="yyyy-MM-dd"/></p>
                </div>

                <div class="btn-group my-3" role="group" aria-label="Basic example">
                    <button id="editInfo" type="button" class="btn btn-outline-info btn-sm m-2" onclick="showFormEdit()">
                        Edit profile info
                    </button>
                    <button id="editPassword" type="button" class="btn btn-outline-info btn-sm m-2"
                            onclick="showFormEditPassword()">
                        Change password
                    </button>
                    <button id="addAddres" type="button" class="btn btn-outline-info btn-sm m-2" onclick="showFormAddress()">
                        Add address
                    </button>
                </div>

                <c:if test="${requestScope.addresses.size()!=0}">
                    <p class="my-3 text-muted">Addresses:</p>
                        <ul class="list-group">
                        <c:forEach items="${requestScope.addresses}" var="address">
                            <li id ="itemId${address.id}" class="list-group-item">
                                <div class="row">
                                <div class="col-flex " style="width: 80%">
                                    <p class="mb-0" style="margin-left: 10px">${address.houseNumber} ${address.street}, Apt ${address.flatNumber},
                                            ${address.city}, ${address.postcode}, ${address.country} </p>
                                </div>
                                <div class="col-flex " style="width: 10%">
                                    <button id="${address.id}" type="button" class="btn btn-sm"
                                            onclick="deleteAddress('${contextPath}/profile/deleteAddress',
                                                ${address.id}, 'itemId${address.id}')">
                                        Delete
                                    </button>
                                </div>
                                </div>
                            </li>
                        </c:forEach>
                        </ul>
                </c:if>
            </div>
            <div class="col-md-3">
                <div class="my-3">
                    <form:form id="editProfile" action="${contextPath}/profile/changeInfo"
                               hidden = "${not empty hiddenInfoForm ? null : 'true'}"
                               method="post" modelAttribute="changeInfo">
                        <div class="form-group">
                            <label for="inputFirstName">Edit first name</label>
                            <form:input type="text" class="form-control my-0" id="inputFirstName"
                                   value="${sessionScope.principalUser.firstName}"
                                   path="firstName" required ="true"/>
                            <form:errors path="firstName" cssClass="error"/>

                        </div>
                        <div class="form-group">
                            <label for="inputSecondName">Edit second name</label>
                            <form:input type="text" class="form-control my-0" id="inputSecondName"
                                   value="${sessionScope.principalUser.secondName}"
                                   path="secondName" placeholder="Second name" required ="true"/>
                            <form:errors path="secondName" cssClass="error"/>
                        </div>

                        <div class="form-group">
                            <label for="inputDate">Enter new date of birth</label>
                            <form:input type="date" class="form-control" id="inputDate"
                                        placeholder="Date of birth" path="birthDate" required ="true"/>
                            <form:errors path="birthDate" cssClass="error"/>
                        </div>

                        <button type="submit" class="btn btn-primary mt-2">Submit</button>
                    </form:form>
                </div>

                <div class="my-3">
                    <form:form id="editPasswordForm" action="${contextPath}/profile/changePassword"
                               hidden = "${not empty hiddenPassForm ? null : 'true'}"
                               method="post" modelAttribute="changePass" >
                        <div class="form-group">
                            <label for="inputPassword1">Enter old password</label>
                            <form:input type="password" class="form-control" id="inputPassword1"
                                   path ="oldPassword" placeholder="Old password" required ="true"/>
                            <form:errors path="oldPassword" cssClass="error"/>
                        </div>
                        <div class="form-group">
                            <label for="inputPassword2">Enter new password</label>
                            <form:input type="password" class="form-control" id="inputPassword2"
                                   path="newPassword" placeholder="New password" required="true"/>
                            <form:errors path="newPassword" cssClass="error"/>
                        </div>
                        <button type="submit" class="btn btn-primary mt-2">Submit</button>
                    </form:form>
                </div>

                <c:if test ="${not empty requestScope.passEditMessage}">
                    <p id ="mes" class="messageEditPass">${requestScope.passEditMessage}</p>
                </c:if>

                <div class="my-3">
                    <form:form id="addAddressForm" class="needs-validation"
                               hidden="${not empty hiddenAddressForm ? null : 'true'}"
                               action="${contextPath}/profile/addAddress" method="post" modelAttribute="newAddress">
                        <div class="form-group">
                            <label for="inputCountryId">Enter country</label>
                            <form:input type="text" class="form-control" id="inputCountryId"
                                        placeholder="Country" path="country" required ="true"/>
                            <form:errors path="country" cssClass="error"/>
                        </div>
                        <div class="form-group">
                            <label for="inputCityId">Enter city</label>
                            <form:input type="text" class="form-control" id="inputCityId"
                                        path="city" placeholder="City" required ="true"/>
                            <form:errors path="city" cssClass="error"/>
                        </div>
                        <div class="form-group">
                            <label for="inputPostcodeId">Enter new postcode</label>
                            <form:input type="number" class="form-control"  id="inputPostcodeId"
                                        path="postcode" placeholder="Postcode" required="true" max="2147483648"/>
                            <form:errors path="postcode" cssClass="error"/>
                        </div>
                        <div class="form-group">
                            <label for="inputStreetId">Enter new street</label>
                            <form:input type="text" class="form-control" id="inputStreetId"
                                        path="street" placeholder="Street" required="true"/>
                            <form:errors path="street" cssClass="error"/>
                        </div>
                        <div class="form-group form-row">
                            <div class="col">
                                <label for="houseNumberId">Enter house number</label>
                                <form:input type="number" class="form-control" id="houseNumberId" max="2147483648"
                                            path="houseNumber" placeholder="House number" required ="true"/>
                                <form:errors path="houseNumber" cssClass="error"/>
                            </div>

                            <div class="col">
                                <label for="flatNumberId">Enter flat number</label>
                                <form:input type="number" class="form-control" id="flatNumberId" max="2147483648"
                                            path="flatNumber" placeholder="Flat number" required ="true"/>
                                <form:errors path="flatNumber" cssClass="error1"/>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary mt-2">Submit</button>
                    </form:form>
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



<script src="${contextPath}/resources/script/profile.js"></script>
</body>
</html>
