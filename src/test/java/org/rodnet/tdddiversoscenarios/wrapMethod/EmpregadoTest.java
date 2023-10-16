package org.rodnet.tdddiversoscenarios.wrapMethod;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;

import static java.time.LocalDate.now;
import static java.time.Month.APRIL;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class EmpregadoTest {

    private RH rh;
    private YearMonth anoMesCorrente;

    @BeforeEach
    void setUp(){
        rh = mock(RH.class);
        anoMesCorrente = YearMonth.from(now());
    }

    @Test
    void naoReceberPagamentoPoisNaoRealizouTrabalhos(){
        var empregado = new Empregado(emptyList(), rh);
        empregado.pagar(anoMesCorrente);

        verify(rh).pagar(BigDecimal.ZERO);
    }

    @Test
    void naoReceberPagamentoPoisRealizouTrabalhosEmOutroMesQueNaoOCorrente(){
        var empregado = new Empregado(singletonList(new Trabalho(LocalDate.of(2021, APRIL, 1),
                BigDecimal.TEN)), rh);

        empregado.pagar(anoMesCorrente);

        verify(rh).pagar(BigDecimal.ZERO);
    }

    @Test
    void receberPagamentoPoisRealizouUmTrabalhoNoMesCorrente(){
        var empregado = new Empregado(singletonList(new Trabalho(now(),
                BigDecimal.TEN)), rh);

        empregado.pagar(anoMesCorrente);

        verify(rh).pagar(BigDecimal.TEN);
    }
}