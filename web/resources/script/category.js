
function deleteRow(url, id_category, id_button) {
    fetch(url, {
        method: 'post',
        headers: {
            "Content-type": "application/json"
        },
        body: id_category
    })
        .then(function (response) {
            return response;
        })
        .then(function (data) {

            document.getElementById(id_button).closest("tr").remove();
        })
        .catch(function (error) {
            console.log('Request failed', error);
        });
}



function update(url, id_category, id_table, id_button) {

    var name_category = document.getElementById(id_button).closest("tr").cells[1].innerText;
    console.log(name_category);
    saveRow(url, id_category, name_category, id_button)

}

function saveRow(url, id_category, name_category, id_button) {
    fetch(url, {
        method: 'post',
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify({id: id_category, name: name_category})
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