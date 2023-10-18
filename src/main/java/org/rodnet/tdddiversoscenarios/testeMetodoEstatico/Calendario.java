package org.rodnet.tdddiversoscenarios.testeMetodoEstatico;

import java.time.LocalDate;

import static java.time.LocalDate.now;

public interface Calendario {

    default LocalDate hoje(){
        return now();
    }
}
