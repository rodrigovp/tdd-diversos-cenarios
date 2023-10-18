package org.rodnet.tdddiversoscenarios.testeMetodoEstatico;

import java.time.LocalDate;
import java.time.Period;

import static java.lang.Math.abs;

public class UmaClasseQualquer {

    public int diasEntreHojeEData(LocalDate data){
        var periodo = Period.between(LocalDate.now(), data);
        return abs(periodo.getDays());
    }
}
