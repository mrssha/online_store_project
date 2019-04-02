'use strict';

function addToCart(url, id, button_id) {
    fetch(url, {
        method: 'post',
        headers: {
            "Content-type": "application/json"
        },
        body: id
    })
        .then(function (response) {
            return response.json();
        })
        .then(function (data) {
            console.log('Request succeeded with response', data);
            console.log('Request succeeded with response', data.message);
            //console.log('Request succeeded with response', data.quantity);
            if (data.message == "ADDED_TO_CART"){
                document.getElementById(button_id).textContent = data.quantity;
            }
        })
        .catch(function (error) {
            console.log('Request failed', error);
        });
}


function removeFromCart(url, id, button_id) {
    fetch(url, {
        method: 'post',
        headers: {
            "Content-type": "application/json"
        },
        body: id
    })
        .then(function (response) {
            return response.json();
        })
        .then(function (data) {
            console.log('Request succeeded with response', data);
            console.log('Request succeeded with response', data.message);

            if (data.message == "REMOVED_FROM_CART"){
                document.getElementById(button_id).textContent = data.quantity;
            }

        })
        .catch(function (error) {
            console.log('Request failed', error);
        });
}