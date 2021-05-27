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

requestCatalog();