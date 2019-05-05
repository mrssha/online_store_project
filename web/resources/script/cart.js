'use strict';

// function plusOneProductDb(url, id, button_id) {
//     fetch(url, {
//         method: 'post',
//         headers: {
//             "Content-type": "application/json"
//         },
//         body: id
//     })
//         .then(function (response) {
//             return response.json();
//         })
//         .then(function (data) {
//             console.log('Request succeeded with response', data);
//             console.log('Request succeeded with response', data.message);
//             console.log('Request succeeded with response', data.quantity);
            // if (data.message == "ADDED_TO_CART"){
            //     document.getElementById(button_id).textContent = data.quantity;
            // }
        // })
        // .catch(function (error) {
        //     console.log('Request failed', error);
        // });
// }
//
//
// function minusOneProductDb(url, id, button_id) {
//     fetch(url, {
//         method: 'post',
//         headers: {
//             "Content-type": "application/json"
//         },
//         body: id
//     })
//         .then(function (response) {
//             return response.json();
//         })
//         .then(function (data) {
//             console.log('Request succeeded with response', data);
//             console.log('Request succeeded with response', data.message);
//
//             if (data.message == "REMOVED_FROM_CART"){
//                 document.getElementById(button_id).textContent = data.quantity;
//             }
//
//         })
//         .catch(function (error) {
//             console.log('Request failed', error);
//         });
// }


/*
function deleteCartDbItem(url, cartItem_id, product_price, product_quantity, element_id) {
    fetch(url, {
        method: 'post',
        headers: {
            "Content-type": "application/json"
        },
        body: cartItem_id
    })
        .then(function (response) {
            return response;
        })
        .then(function (data) {
            var elem = document.getElementById(element_id);
            elem.parentNode.removeChild(elem);
            renderTotalMinus(product_price, product_quantity)
        })
        .catch(function (error) {
            console.log('Request failed', error);
        });
}
*/

/*
function addRemoveProductDB(url, id, button_id) {
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
*/