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
        BigDecimal salario = BigDecimal.ZERO;
        trabalhos.forEach(trabalho -> {
            if(trabalho.feitoNeste(anoMes)){
                salario = salario.add(trabalho.valor());
            }
        });
        rh.pagar(salario);
    }
}
