function calculate(url) {

    var year = document.getElementById('yearSearch').value;
    var month = document.getElementById('monthSearch').value;
    console.log(year);
    console.log(month);
    fetchCalculate(url, year, month)

}

function fetchCalculate(url, year, month) {
    fetch(url, {
        method: 'post',
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify({year: year, month: month})
    })
        .then(function (response) {
            return response.json();
        })
        .then(function (data) {
            console.log(data);
            // console.log(data.sum);
            document.getElementById('resultRevenue').textContent = data;
        })
        .catch(function (error) {
            console.log('Request failed', error);
        });
}
