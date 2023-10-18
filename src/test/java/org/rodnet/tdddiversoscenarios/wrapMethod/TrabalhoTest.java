package org.rodnet.tdddiversoscenarios.wrapMethod;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.YearMonth;

import static java.math.BigDecimal.TEN;
import static org.assertj.core.api.Assertions.assertThat;

class TrabalhoTest {

    private static final LocalDate AGORA = LocalDate.now();

    @Test
    void feitoNesteAnoMes(){
        var anoMes = YearMonth.from(AGORA);
        var trabalho = new Trabalho(AGORA, TEN);

        assertThat(trabalho.feitoNeste(anoMes)).isTrue();
    }

    @Test
    void naoFeitoNesteAnoMes(){
        var anoMes = YearMonth.from(AGORA).minusMonths(1);
        var trabalho = new Trabalho(AGORA, TEN);

        assertThat(trabalho.feitoNeste(anoMes)).isFalse();
    }
}