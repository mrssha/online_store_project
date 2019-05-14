'use strict';

function checkCourier() {

    document.getElementById("customerAddressId").hidden = false;
    document.getElementById("postamptAddressId").hidden = true;

}

function checkPostampt() {

    document.getElementById("customerAddressId").hidden = true;
    document.getElementById("postamptAddressId").hidden = false;

}