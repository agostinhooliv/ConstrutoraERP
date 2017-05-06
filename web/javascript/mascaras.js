/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function MascaraCPFCNPJ(elem) {

    campo = document.getElementById(elem.id).value.replace(/\D+/g, "");
    document.getElementById(elem.id).value = campo;
    //000.000
    if (campo.length > 4 && campo.length <= 6) {
        escreve_1 = campo.substring(0, 3);
        escreve_2 = campo.substring(3, 6);
        document.getElementById(elem.id).value = escreve_1 + ".";
        document.getElementById(elem.id).value += escreve_2;
    }

    //000.000.000
    if (campo.length > 6 && campo.length <= 9) {
        escreve_1 = campo.substring(0, 3);
        escreve_2 = campo.substring(3, 6);
        escreve_3 = campo.substring(6, 9);
        document.getElementById(elem.id).value = escreve_1 + ".";
        document.getElementById(elem.id).value += escreve_2 + ".";
        document.getElementById(elem.id).value += escreve_3;
    }

    //000.000.000
    if (campo.length > 9 && campo.length <= 11) {
        escreve_1 = campo.substring(0, 3);
        escreve_2 = campo.substring(3, 6);
        escreve_3 = campo.substring(6, 9);
        escreve_4 = campo.substring(9, 11);
        document.getElementById(elem.id).value = escreve_1 + ".";
        document.getElementById(elem.id).value += escreve_2 + ".";
        document.getElementById(elem.id).value += escreve_3 + "-";
        document.getElementById(elem.id).value += escreve_4;
    }

    //00.000.000/0000-00
    if (campo.length > 11 && campo.length <= 14) {
        escreve_1 = campo.substring(0, 2);
        escreve_2 = campo.substring(2, 5);
        escreve_3 = campo.substring(5, 8);
        escreve_4 = campo.substring(8, 12);
        escreve_5 = campo.substring(12, 14);
        document.getElementById(elem.id).value = escreve_1 + ".";
        document.getElementById(elem.id).value += escreve_2 + ".";
        document.getElementById(elem.id).value += escreve_3 + "/";
        document.getElementById(elem.id).value += escreve_4 + "-";
        document.getElementById(elem.id).value += escreve_5;
    }
}

function MascaraRG(elem) {

    campo = document.getElementById(elem.id).value.replace(/\D+/g, "");
    document.getElementById(elem.id).value = campo;
    //0.000
    if (campo.length > 2 && campo.length <= 4) {
        escreve_1 = campo.substring(0, 1);
        escreve_2 = campo.substring(1, 4);
        document.getElementById(elem.id).value = escreve_1 + ".";
        document.getElementById(elem.id).value += escreve_2;
    }

    //0.000.000
    if (campo.length > 4 && campo.length <= 7) {
        escreve_1 = campo.substring(0, 1);
        escreve_2 = campo.substring(1, 4);
        escreve_3 = campo.substring(4, 7);
        document.getElementById(elem.id).value = escreve_1 + ".";
        document.getElementById(elem.id).value += escreve_2 + ".";
        document.getElementById(elem.id).value += escreve_3;
    }
}

function MascaraTelefone(elem) {

    campo = document.getElementById(elem.id).value.replace(/\D+/g, "");
    document.getElementById(elem.id).value = campo;

    //(00)
    if (campo.length > 4 && campo.length <= 8) {
        escreve_1 = campo.substring(0, 4);
        escreve_2 = campo.substring(4, 8);
        document.getElementById(elem.id).value = escreve_1 + "-";
        document.getElementById(elem.id).value += escreve_2;
    }
}

function MascaraTelefone2(elem) {

    campo = document.getElementById(elem.id).value.replace(/\D+/g, "");
    document.getElementById(elem.id).value = campo;

    if (campo.length > 5 && campo.length <= 9) {
        escreve_1 = campo.substring(0, 5);
        escreve_2 = campo.substring(5, 9);
        document.getElementById(elem.id).value = escreve_1 + "-";
        document.getElementById(elem.id).value += escreve_2;
    }
}

function UpperCase(elem) {
    campo = document.getElementById(elem.id).value.toUpperCase();
    document.getElementById(elem.id).value = campo;
}

function LowerCase(elem) {
    campo = document.getElementById(elem.id).value.toLowerCase();
    document.getElementById(elem.id).value = campo;
}

function MascaraCEP(elem) {

    campo = document.getElementById(elem.id).value.replace(/\D+/g, "");
    document.getElementById(elem.id).value = campo;

    if (campo.length > 5 && campo.length <= 9) {
        escreve_1 = campo.substring(0, 5);
        escreve_2 = campo.substring(5, 9);
        document.getElementById(elem.id).value = escreve_1 + "-";
        document.getElementById(elem.id).value += escreve_2;
    }
}

function MascaraInscricaoEstadual(elem) {

    campo = document.getElementById(elem.id).value.replace(/\D+/g, "");
    document.getElementById(elem.id).value = campo;
    //00.000
    if (campo.length > 2 && campo.length <= 5) {
        escreve_1 = campo.substring(0, 2);
        escreve_2 = campo.substring(2, 5);
        document.getElementById(elem.id).value = escreve_1 + ".";
        document.getElementById(elem.id).value += escreve_2;
    }

    //00.000.000
    if (campo.length > 5 && campo.length <= 8) {
        escreve_1 = campo.substring(0, 2);
        escreve_2 = campo.substring(2, 5);
        escreve_3 = campo.substring(5, 8);
        document.getElementById(elem.id).value = escreve_1 + ".";
        document.getElementById(elem.id).value += escreve_2 + ".";
        document.getElementById(elem.id).value += escreve_3;
    }

    //00.000.000-0
    if (campo.length > 8 && campo.length <= 9) {
        escreve_1 = campo.substring(0, 2);
        escreve_2 = campo.substring(2, 5);
        escreve_3 = campo.substring(5, 8);
        escreve_4 = campo.substring(8, 9);
        document.getElementById(elem.id).value = escreve_1 + ".";
        document.getElementById(elem.id).value += escreve_2 + ".";
        document.getElementById(elem.id).value += escreve_3 + "-";
        document.getElementById(elem.id).value += escreve_4;
    }
}

function MascaraDATA(elem) {

    campo = document.getElementById(elem.id).value.replace(/\D+/g, "");
    document.getElementById(elem.id).value = campo;

    if (campo.length > 2 && campo.length <= 8) {
        escreve_1 = campo.substring(0, 2);
        escreve_2 = campo.substring(2, 4);
        escreve_3 = campo.substring(4, 8);
        document.getElementById(elem.id).value = escreve_1 + "/";
        document.getElementById(elem.id).value += escreve_2 + "/";
        document.getElementById(elem.id).value += escreve_3;
    }
}

function MascaraMoeda(){
    
}