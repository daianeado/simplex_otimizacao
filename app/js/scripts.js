var problema = {};
var xmlhttp;

/**
 * Função responsável por enviar a requisição contendo o problema para a API
 */
function post(dadosProblema) {

    xmlhttp = new XMLHttpRequest();

    var url = "https://tpsimplexapi.azurewebsites.net/api/otimizacao/simplex";
    xmlhttp.open("POST", url, true);
    xmlhttp.setRequestHeader("Content-type", "application/json");
    xmlhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var response = [];
            response = this.responseText.split('x');

            document.getElementById("resposta").innerHTML =
                "<div> <span> A solução ótima é " + response[0] + "</span>" +
                "<br> <span> X" + response[1] + "</span>" +
                "<br> <span> X" + response[2] + "</span> </div>";
        }
    };
    xmlhttp.send(dadosProblema);
}

//Função responsável por recuperar o valor do dropdown
function getValueInputClass() {
    var e = document.getElementById("objetivo-selecionado");
    var strUser = e.options[e.selectedIndex].value;

    return strUser;
}

//Função responsável por montar o problema para enviar para que a api resolva
function resolver() {
    remover("problema");
    adicionar("solucao");

    problema = {
            "restricoes": [{
                    "tipoRestricao": "maior",
                    "x1": document.getElementById("r1x1").value,
                    "x2": document.getElementById("r1x2").value,
                    "result": document.getElementById("r1Result").value
                },
                {
                    "tipoRestricao": "maior",
                    "x1": document.getElementById("r2x1").value,
                    "x2": document.getElementById("r2x2").value,
                    "result": document.getElementById("r2Result").value
                },
                {
                    "tipoRestricao": "maior",
                    "x1": document.getElementById("r3x1").value,
                    "x2": document.getElementById("r3x2").value,
                    "result": document.getElementById("r3Result").value
                }
            ],
            "funcaoObjetiva": {
                "objetivo": "MAX",
                "x1": document.getElementById("fox1").value,
                "x2": document.getElementById("fox2").value
            }
        }
        //converte o objeto problema em json
    post(JSON.stringify(problema));
}

//Função responsável por remover elemento através de seu id
function remover(idElemento) {
    document.getElementById(idElemento).style.display = "none";
}

//Função responsável por adicionar elemento através de seu id
function adicionar(idElemento) {
    document.getElementById(idElemento).style.display = "block";
}
//Função responsável por adicionar html  através de seu id
function adicionarHTML(idElemento, conteudo) {
    document.getElementById(idElemento).innerHTML = conteudo;
}