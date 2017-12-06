var problema = {};
var xmlhttp;

function esconder(idElemento) {
    document.getElementById(idElemento).style.display = "none";
}

function exibir(idElemento) {
    document.getElementById(idElemento).style.display = "block";
}

function inserirHTML(idElemento, conteudo) {
    document.getElementById(idElemento).innerHTML = conteudo;
}

function pegarValor(idElemento) {
    return document.getElementById(idElemento).value;
}

function montarFuncaoObjetiva(colunas) {
    var linha = "<tr>";

    linha += "<th>Função Objetiva</th>";

    for (var i = 0; i < colunas; i++) {
        linha += "<td>";
        linha += "<label> X" + (i + 1) + "</label>";
        linha += "<input id=\"x-" + (i + 1) + "\" class=\"form-control\" type=\"number\" placeholder=\"X" + (i + 1) + "\">";
        linha += "</td>";
    }

    linha += "</tr>";

    return linha;
}

function montarCorpoTabela(linhas, colunas) {
    var corpo = "<tbody>";

    corpo += montarFuncaoObjetiva(colunas);

    for (var i = 1; i <= linhas; i++) {
        corpo += "<tr>";
        corpo += "<th scope=\"row\">"
        corpo += "Restrição " + i;
        corpo += "</th>";

        for (var j = 1; j <= colunas; j++) {
            corpo += "<td>";
            corpo += "<input id=\"val-" + i + "-" + j + "\" class=\"form-control\" type=\"number\" placeholder=\"X" + j + "\" min=\"0\">";
            corpo += " </td>";
        }
        corpo += "<td>"
        corpo += "<select class=\"form-control\" id=\"sel1\">"
        corpo += "<option value=\"menor\"> ≤ </option>";
        corpo += "<option value=\"maior\"> ≥ </option>";
        corpo += "<option value=\"igual\"> = </option>";
        corpo += "</select>";
        corpo += "</td>"
        corpo += "<td>";
        corpo += "<input id=\"tot-" + i + "\" class=\"form-control\" type=\"number\" placeholder=\"Disponibilidade\" min=\"0\">";
        corpo += " </td>";

        corpo += "</tr>";
    }
    corpo += "</tbody>";

    return corpo;
}

function montarTabela(linhas, colunas) {

    var tabela = "<div class=\"row\">";
    tabela += "<div class=\"col-md-12 col-xs-12 objetivo-funcao\">";
    tabela += "<div class=\"form-group\">";
    tabela += "<label for=\"objetivo\" class=\"label-objetivo-fo\">Objetivo da função:</label>";
    tabela += "<select id=\"objetivo-selecionado\" class=\"col-md-3 col-xs-3 form-control\" data-size=\"5\" data-live-search=\"true\" data-width=\"100%\" name=\"objetivo\" required>";
    tabela += "<option selected disabled>Escolha uma opção</option>";
    tabela += "<option value=\"MAX\">Maximizar</option>";
    tabela += "<option value=\"MIN\">Minimizar</option>";
    tabela += "</select>";
    tabela += "</div>";
    tabela += "</div>";
    tabela += "</div>";

    tabela += "<table class=\"table\" width=\"100%\">";
    var corpo = montarCorpoTabela(linhas, colunas);

    tabela += corpo + "</table>";

    return tabela;
}

function proximo() {

    esconder("inicio");
    exibir("problema");

    var tabela = montarTabela(pegarValor("inputIngredientes"), pegarValor("inputProdutos"));

    inserirHTML("divTabela", tabela);
}

function resolver() {
    esconder("problema");
    exibir("resolvido");

    problema.restricoes.tipoRestricao.push(pegarValor("sel1"));

    console.log(JSON.stringify(problema));
}

function post(dadosProblema) {

    xmlhttp = new XMLHttpRequest();

    var url = "https://tpsimplexapi.azurewebsites.net/api/otimizacao/simplex";
    xmlhttp.open("POST", url, true);
    xmlhttp.setRequestHeader("Content-type", "application/json");
    xmlhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            esconder("carregando");
            var response = [];
            response = this.responseText.split('x');

            document.getElementById("solucao").innerHTML =
                "<div> <span> A solução ótima é " + response[0] + "</span>" +
                "<br> <span> X" + response[1] + "</span>" +
                "<br> <span> X" + response[2] + "</span> </div>";
        }
    };
    xmlhttp.send(dadosProblema);
}