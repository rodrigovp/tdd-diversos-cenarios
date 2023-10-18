package org.rodnet.tdddiversoscenarios.testeMetodoEstatico;

import java.time.LocalDate;
import java.time.Period;

import static java.lang.Math.abs;

public class OutraClasseQualquer {

    public int diasEntreHojeEData(LocalDate data){
        final var now = LocalDate.now();
        var periodo = Period.between(now, data);
        return abs(periodo.getDays());
    }
}
