'use strict';

function showFormEdit() {
    document.getElementById("editProfile").hidden =
        !document.getElementById("editProfile").hidden;

    document.getElementById("editPasswordForm").hidden = true;
    document.getElementById("addAddressForm").hidden = true;

    document.getElementById("mes").hidden = true;
}

function showFormAddress() {
    document.getElementById("addAddressForm").hidden =
        !document.getElementById("addAddressForm").hidden;

    document.getElementById("editProfile").hidden = true;
    document.getElementById("editPasswordForm").hidden = true;

    document.getElementById("mes").hidden = true;
}


function showFormEditPassword(){
    document.getElementById("editPasswordForm").hidden =
        !document.getElementById("editPasswordForm").hidden;

    document.getElementById("editProfile").hidden = true;
    document.getElementById("addAddressForm").hidden =true;

    document.getElementById("mes").hidden = true;

}