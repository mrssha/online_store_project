function changeValue(id, value){

    document.getElementById(id).innerText = value;
}

function update(url, id_order, id_button) {

    var order_status = document.getElementById(id_button).closest("tr").cells[8].innerText;
    console.log(order_status);

    saveRow(url, id_order, order_status, id_button)

}

function saveRow(url, id_order, order_status, id_button) {
    fetch(url, {
        method: 'post',
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify({id: id_order, orderStatus: order_status})
    })
        .then(function (response) {
            return response;
        })
        .then(function (data) {

            //document.getElementById(id_button).closest("tr").remove();

        })
        .catch(function (error) {
            console.log('Request failed', error);
        });
}