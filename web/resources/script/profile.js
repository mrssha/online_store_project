'use strict';

function deleteAddress(url, id_address, id_item) {
    fetch(url, {
        method: 'post',
        headers: {
            "Content-type": "application/json"
        },
        body: id_address
    })
        .then(function (response) {
            return response;
        })
        .then(function (data) {
            document.getElementById(id_item).remove();
        })
        .catch(function (error) {
            console.log('Request failed', error);
        });
}

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