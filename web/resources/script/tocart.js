'use strict';

function addRemoveProduct(url, id, button_id) {
    fetch(url, {
        method: 'post',
        headers: {
            //"Content-type": "text/plain"
            //"Accept": "application/json"
            "Content-type": "application/json"
        },
        body: id
        // body: JSON.stringify({id: id})
    })
        .then(function (response) {
            return response.json();
        })
        .then(function (data) {
            console.log('Request succeeded with response', data);
            console.log('Request succeeded with response', data.message);
            if (data.message == "REMOVED_FROM_CART"){
                console.log('Add to cart!');
                document.getElementById(button_id).textContent = "Add to cart"
            }
            if (data.message == "ADDED_TO_CART"){
                console.log('Remove from cart!');
                document.getElementById(button_id).textContent = "Remove from cart"
            }
        })
        .catch(function (error) {
            console.log('Request failed', error);
        });
}

