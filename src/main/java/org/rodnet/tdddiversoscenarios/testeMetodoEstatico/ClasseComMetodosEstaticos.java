package org.rodnet.tdddiversoscenarios.testeMetodoEstatico;

import static java.lang.String.format;

public class ClasseComMetodosEstaticos {

    static String nome(){
        return "Rodrigo";
    }

    static String saudacao(String nome){
        return format("Olá %s!", nome);
    }
}
