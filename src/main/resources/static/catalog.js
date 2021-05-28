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

function requestCatalogStats() {
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

function displayCatalog() {
    let list = document.getElementById("displayCatalog");
    let httpRequest = new XMLHttpRequest();
    if (!httpRequest)
        return false;
    httpRequest.onreadystatechange = () => {
        if (httpRequest.readyState === XMLHttpRequest.DONE) {
            if (httpRequest.status === 200) {
                let json = JSON.parse("[" + httpRequest.responseText + "]");
                json.forEach(childLevel0 => {
                    let node = document.createElement("LI");
                    let textNode = document.createTextNode(childLevel0["name"]);
                    node.appendChild(textNode);

                    let nodeChildULLevel1 = document.createElement("UL");
                    childLevel0["children"].forEach(childLevel1 => {
                        let nodeChildLILevel1 = document.createElement("LI");
                        let nodeChildLITextLevel1 = document.createTextNode(childLevel1["name"]);
                        nodeChildLILevel1.appendChild(nodeChildLITextLevel1);

                        let nodeChildULLevel2 = document.createElement("UL");
                        childLevel1["children"].forEach(childLevel2 => {
                            let nodeChildLILevel2 = document.createElement("LI");
                            let nodeChildLITextLevel2 = document.createTextNode(childLevel2["name"]);
                            nodeChildLILevel2.appendChild(nodeChildLITextLevel2);

                            let nodeChildULLevel3 = document.createElement("UL");
                            childLevel2["children"].forEach(childLevel3 => {
                                let nodeChildLILevel3 = document.createElement("LI");
                                let nodeChildLITextLevel3 = document.createTextNode(childLevel3["name"]);
                                nodeChildLILevel3.appendChild(nodeChildLITextLevel3);
                                nodeChildULLevel3.appendChild(nodeChildLILevel3);
                            });
                            nodeChildLILevel2.appendChild(nodeChildULLevel3);

                            nodeChildULLevel2.appendChild(nodeChildLILevel2);
                        });
                        nodeChildLILevel1.appendChild(nodeChildULLevel2);

                        nodeChildULLevel1.appendChild(nodeChildLILevel1);
                    });
                    node.appendChild(nodeChildULLevel1);
                    list.appendChild(node);
                });
            } else {
                list.innerHTML = "Erreur " + httpRequest.status;
            }
        }
    };
    httpRequest.open("GET", "/catalog");
    httpRequest.send();
}

requestCatalog();
requestCatalogStats();
displayCatalog();