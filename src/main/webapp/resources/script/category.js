'use strict';

function deleteRow(url, id_category, id_button) {
    fetch(url, {
        method: 'post',
        headers: {
            "Content-type": "application/json"
        },
        body: id_category
    })
        .then(function (response) {
            return response.json();
        })
        .then(function (data) {
            switch (data.resultType) {
                case "CATEGORY_SUCCESS_DELETE": case "PRODUCT_SUCCESS_DELETE":
                    document.getElementById(id_button).closest("tr").remove();
                    document.getElementById('messageId').hidden = true;
                    break;
                case "CATEGORY_FAIL_DELETE": case "PRODUCT_FAIL_DELETE":
                    document.getElementById('messageId').hidden = false;
                    document.getElementById('messageId').textContent = data.message;
            }
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
            return response.json();
        })
        .then(function (data) {
            console.log(data);
            if (data.resultType == "CATEGORY_ALREADY_EXIST") {
                document.getElementById('messageId').hidden = false;
                document.getElementById('messageId').textContent = data.message;
                document.getElementById(id_button).closest("tr").cells[1]
                    .innerText = data.dataMap.oldName;
            }
            else {
                document.getElementById('messageId').hidden = true;
            }

        })
        .catch(function (error) {
            console.log('Request failed', error);
        });
}