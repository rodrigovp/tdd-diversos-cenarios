package org.rodnet.tdddiversoscenarios.wrapClass;

import lombok.AllArgsConstructor;

import java.time.YearMonth;

@AllArgsConstructor
public class EmpregadoComInformeDePagamento implements Empregado {

    private Empregado empregado;
    @Override
    public void pagar(YearMonth anoMes) {
        empregado.pagar(anoMes);
        informarPagamento();
    }

    private void informarPagamento(){
        // informando um sistema qualquer sobre o pagamento.
    }
}
