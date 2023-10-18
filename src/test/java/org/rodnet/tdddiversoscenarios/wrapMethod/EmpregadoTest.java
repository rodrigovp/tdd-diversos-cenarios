package org.rodnet.tdddiversoscenarios.wrapMethod;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import static java.math.BigDecimal.TEN;
import static java.time.LocalDate.now;
import static java.time.Month.APRIL;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class EmpregadoTest {

    public static final Trabalho trabalhoRealizadoNoMesCorrente = new Trabalho(now(), TEN);
    public static final Trabalho trabalhoRealizadoForaDoMesCorrente = new Trabalho(LocalDate.of(2021, APRIL, 1),
            TEN);
    private RH rh;
    private YearMonth anoMesCorrente;

    @BeforeEach
    void setUp(){
        rh = mock(RH.class);
        anoMesCorrente = YearMonth.from(now());
    }

    @Test
    void naoReceberPagamentoPoisNaoRealizouTrabalhos(){
        var empregado = new Empregado(List.of(), rh);
        empregado.pagar(anoMesCorrente);

        verify(rh).pagar(BigDecimal.ZERO);
    }

    @Test
    void naoReceberPagamentoPoisRealizouTrabalhosEmOutroMesQueNaoOCorrente(){
        var empregado = new Empregado(List.of(trabalhoRealizadoForaDoMesCorrente), rh);
        empregado.pagar(anoMesCorrente);

        verify(rh).pagar(BigDecimal.ZERO);
    }

    @Test
    void receberPagamentoPoisRealizouUmTrabalhoNoMesCorrente(){
        var empregado = new Empregado(List.of(
                trabalhoRealizadoNoMesCorrente),
                rh);

        empregado.pagar(anoMesCorrente);

        verify(rh).pagar(TEN);
    }

    @Test
    void receberPagamentoPoisRealizouDoisTrabalhosNoMesCorrente(){
        var empregado = new Empregado(List.of(
                trabalhoRealizadoNoMesCorrente,
                trabalhoRealizadoNoMesCorrente),
                rh);

        empregado.pagar(anoMesCorrente);

        verify(rh).pagar(TEN.add(TEN));
    }

    @Test
    void receberPagamentosApenasDeTrabalhosRealizadosNoMesCorrente(){
        var empregado = new Empregado(List.of(
                trabalhoRealizadoNoMesCorrente,
                trabalhoRealizadoForaDoMesCorrente),
                rh);

        empregado.pagar(anoMesCorrente);

        verify(rh).pagar(TEN);
    }
}