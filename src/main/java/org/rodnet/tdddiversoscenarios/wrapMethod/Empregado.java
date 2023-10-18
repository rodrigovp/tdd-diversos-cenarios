package org.rodnet.tdddiversoscenarios.wrapMethod;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;

import static java.math.BigDecimal.ZERO;

@AllArgsConstructor
public class Empregado {

    private List<Trabalho> trabalhos;
    private RH rh;

    public void pagar(YearMonth anoMes){
        despacharPagamento(anoMes);
        informarPagamento();
    }

    private void despacharPagamento(YearMonth anoMes){
        rh.pagar(trabalhos.stream()
                .filter(t -> t.feitoNeste(anoMes))
                .map(Trabalho::valor)
                .reduce(ZERO, BigDecimal::add));
    }

    private void informarPagamento(){
        // informando um sistema qualquer sobre o pagamento.
    }
}
