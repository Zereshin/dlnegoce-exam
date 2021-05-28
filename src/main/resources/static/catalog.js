function requestCatalog() {
    let textarea = document.getElementById("catalog");
    let httpRequest = new XMLHttpRequest();
    if (!httpRequest)
        return false;
    httpRequest.onreadystatechange = () => {
        if (httpRequest.readyState === XMLHttpRequest.DONE) {
            if (httpRequest.status === 200) {
                textarea.value = httpRequest.responseText;
            } else {
                textarea.value = "Erreur " + httpRequest.status;
            }
        }
    };
    httpRequest.open("GET", "/catalog");
    httpRequest.send();
}

function requestCatalogStat() {
    let text = document.getElementById("catalogStats");
    let httpRequest = new XMLHttpRequest();
    if (!httpRequest)
        return false;
    httpRequest.onreadystatechange = () => {
        if (httpRequest.readyState === XMLHttpRequest.DONE) {
            if (httpRequest.status === 200) {
                let json = JSON.parse(httpRequest.responseText);
                text.innerHTML = "Number of nodes: " + json["numberOfNodes"] + "</br>" +
                "Number of nodes level 1: " + json["numberOfLevel1"] + "</br>" +
                "Number of nodes level 2: " + json["numberOfLevel2"] + "</br>" +
                "Number of nodes level 3: " + json["numberOfLevel3"];
            } else {
                text.innerHTML = "Erreur " + httpRequest.status;
            }
        }
    };
    httpRequest.open("GET", "/catalog-stats");
    httpRequest.send();
}

requestCatalog();
requestCatalogStat();