package org.rodnet.tdddiversoscenarios.wrapClass;

import lombok.AllArgsConstructor;
import org.rodnet.tdddiversoscenarios.wrapMethod.RH;
import org.rodnet.tdddiversoscenarios.wrapMethod.Trabalho;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;

import static java.math.BigDecimal.ZERO;

@AllArgsConstructor
public class EmpregadoSimples implements Empregado {

    private List<Trabalho> trabalhos;
    private RH rh;
    @Override
    public void pagar(YearMonth anoMes) {
        rh.pagar(trabalhos.stream()
                .filter(t -> t.feitoNeste(anoMes))
                .map(Trabalho::valor)
                .reduce(ZERO, BigDecimal::add));
    }
}
