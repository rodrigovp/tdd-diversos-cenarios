package org.rodnet.tdddiversoscenarios.wrapMethod;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;

@AllArgsConstructor
public class Empregado {

    private List<Trabalho> trabalhos;
    private RH rh;

    public void pagar(YearMonth anoMes){
        rh.pagar(trabalhos.stream()
                .filter(t -> t.feitoNeste(anoMes))
                .map(Trabalho::valor)
                .reduce(BigDecimal.ZERO, BigDecimal::add));
    }
}
