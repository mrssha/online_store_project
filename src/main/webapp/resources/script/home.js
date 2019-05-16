
'use strict';

window.onload = function() {
    var url = document.URL;
    if (url == 'http://localhost:8080/store/'){
        var parentEl = document.getElementById("imgMain"),
            img = document.createElement("IMG");
        img.className = "photo-main";
        img.src = contextPath +"/resources/image/main2.jpg";
        parentEl.appendChild(img);
    }
};
