'use strict';

function addRemoveProduct(cname, product_id, button_id, user) {
    var cook_value = decodeURIComponent(getCookie(cname));
    console.log(cook_value);
    if(cook_value==""){
        var cart = new Object();
        cart[product_id] = 1;
        var json_cart = encodeURIComponent(JSON.stringify(cart));
        console.log(json_cart);
        setCookie(cname, json_cart, 1);
        if (user != null){
            fetchCartDb(contextPath +'/addToCart', product_id)
        }
    }
    else{
        var cart = JSON.parse(cook_value);
        if (product_id in cart){
            delete cart[product_id];
            var json_cart = encodeURIComponent(JSON.stringify(cart));
            setCookie(cname, json_cart, 1);
            document.getElementById(button_id).textContent = "Add to cart";
            console.log('remove');
            if (user != null){
                fetchCartDb(contextPath +'/deleteCartItem', product_id)
            }
        }
        else {
            cart[product_id] = 1;
            var json_cart = encodeURIComponent(JSON.stringify(cart));
            console.log(json_cart);
            setCookie(cname, json_cart, 1);
            document.getElementById(button_id).textContent = "Remove from cart";
            console.log('put');
            if (user != null){
                fetchCartDb(contextPath +'/addToCart', product_id)
            }
        }
    }
}


function plusOneProductCookie(cname, product_id, product_quantity,
                              product_price, element_id, user) {
    var cook_value = decodeURIComponent(getCookie(cname));
    if(cook_value!=""){
        var cart = JSON.parse(cook_value);
        if (product_id in cart){
            var quantity_in_cart = cart[product_id];
            if (quantity_in_cart < product_quantity){
                cart[product_id] = quantity_in_cart + 1;
                document.getElementById(element_id).textContent = cart[product_id];
                var json_cart = encodeURIComponent(JSON.stringify(cart));
                setCookie(cname, json_cart, 1);
                if (user != null) {
                    fetchCartDb(contextPath + '/addToCart', product_id);
                }
                renderTotalPlus(product_price,1);
            }
        }
    }
}

function minusOneProductCookie(cname, product_id, product_price, element_id, user) {
    var cook_value = decodeURIComponent(getCookie(cname));
    if(cook_value!=""){
        var cart = JSON.parse(cook_value);
        if (product_id in cart){
            var quantity_in_cart = cart[product_id];
            if (quantity_in_cart > 1){
                cart[product_id] = quantity_in_cart - 1;
                document.getElementById(element_id).textContent = cart[product_id];
                var json_cart = encodeURIComponent(JSON.stringify(cart));
                setCookie(cname, json_cart, 1);
                if (user != null) {
                    fetchCartDb(contextPath +'/removeFromCart', product_id);
                }
                renderTotalMinus(product_price,1)
            }
        }
    }
}

function deleteCookieItem(cname, product_id, product_price, element_id, user){
    var cook_value = decodeURIComponent(getCookie(cname));
    if(cook_value!=""){
        var cart = JSON.parse(cook_value);
        if (product_id in cart){
            var product_quantity = cart[product_id];
            delete cart[product_id];
            var json_cart = encodeURIComponent(JSON.stringify(cart));
            setCookie(cname, json_cart, 1);
            document.getElementById(element_id).remove();
            if (user != null) {
                fetchCartDb(contextPath + '/deleteCartItem', product_id);
            }
            renderTotalMinus(product_price, product_quantity);
        }
    }
}

function fetchCartDb(url, product_id) {
    fetch(url, {
        method: 'post',
        headers: {
            "Content-type": "application/json"
        },
        body: product_id
    })
        .then(function (response) {
            return response.json();
        })
        .then(function (data) {
        })
        .catch(function (error) {
            console.log('Request failed', error);
        });
}


function renderTotalMinus(product_price, quantity) {
    var old_total = document.getElementById('totalId').textContent;
    document.getElementById('totalId').textContent
        = (parseFloat(old_total) - parseFloat(product_price) * quantity).toFixed(1);
    var old_amount = document.getElementById('amountId').textContent;
    document.getElementById('amountId').textContent
        = parseInt(old_amount) - quantity;

}

function renderTotalPlus(product_price, quantity) {
    var old_total = document.getElementById('totalId').textContent;
    document.getElementById('totalId').textContent
        = (parseFloat(old_total) + parseFloat(product_price) * quantity).toFixed(1);
    var old_amount = document.getElementById('amountId').textContent;
    document.getElementById('amountId').textContent
        = parseInt(old_amount) + quantity;

}

function correctButtonName(button_id, product_id){
    var cook_value = decodeURIComponent(getCookie('productCart'));
    var cart = JSON.parse(cook_value);
    if (product_id in cart){
        document.getElementById(button_id).textContent = "Remove from cart";
        console.log('remove');
    }
}


function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    var expires = "expires="+d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}


function getCookie( cookie_name )
{
    var results = document.cookie.match ( '(^|;) ?' + cookie_name + '=([^;]*)(;|$)' );

    if ( results ) {
        console.log(unescape(results[2]));
        return (unescape(results[2]));
    }
    else
        return "";
}


