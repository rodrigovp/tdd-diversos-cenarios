package org.rodnet.tdddiversoscenarios.testeMetodoEstatico;

import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

import static java.lang.Math.abs;

@AllArgsConstructor
public class UmaClasseQualquer {

    private Calendario calendario;

    public int diasEntreHojeEData(LocalDate data){
        var periodo = Period.between(calendario.hoje(), data);
        return abs(periodo.getDays());
    }
}
