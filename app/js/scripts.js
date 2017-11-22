var problema = {};
var xmlhttp;

function httpPost(dados) {

    xmlhttp = new XMLHttpRequest();

    var link = "https://tpsimplexapi.azurewebsites.net/api/otimizacao/simplex";

    xmlhttp.open("POST", link, true);
    xmlhttp.setRequestHeader("Content-Type", "application/json");
    xmlhttp.onreadystatechange = resultadoProblema;
    xmlhttp.send(dados);
}

function getValueInputId(idElemento) {
    return document.getElementById(idElemento).value;
}

function resultadoProblema() {
    if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
        adicionarHTML("resposta", xmlhttp.responseText);
    }
}

function getValueInputClass() {
    var e = document.getElementById("objetivo-selecionado");
    var strUser = e.options[e.selectedIndex].value;

    return strUser;
}

function resolver() {
    remover("problema");
    adicionar("solucao");
    problema = {
        "restricoes": [{
            "tipoRestricao": "maior",
            "x1": getValueInputId("r1x1"),
            "x2": getValueInputId("r1x2"),
            "result": getValueInputId("r1Result")
        }, {
            "tipoRestricao": "maior",
            "x1": getValueInputId("r2x1"),
            "x2": getValueInputId("r2x2"),
            "result": getValueInputId("r2Result")
        }, {
            "tipoRestricao": "maior",
            "x1": getValueInputId("r3x1"),
            "x2": getValueInputId("r3x2"),
            "result": getValueInputId("r3Result")
        }],
        "funcaoObjetiva": {
            "objetivo": getValueInputClass(),
            "x1": getValueInputId("fox1"),
            "x2": getValueInputId("fox2")
        }
    }
    httpPost(JSON.stringify(problema));
}

function remover(idElemento) {
    document.getElementById(idElemento).style.display = "none";
}

function adicionar(idElemento) {
    document.getElementById(idElemento).style.display = "block";
}

function adicionarHTML(idElemento, conteudo) {
    document.getElementById(idElemento).innerHTML = conteudo;
}