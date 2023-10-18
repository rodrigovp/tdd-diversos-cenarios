package org.rodnet.tdddiversoscenarios.testeMetodoEstatico;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class UmaClasseQualquerTest {

    private final UmaClasseQualquer umaClasseQualquer = new UmaClasseQualquer();
    private static final LocalDate HOJE = LocalDate.now();

    @Test
    void diasIguais(){
        assertThat(umaClasseQualquer.diasEntreHojeEData(HOJE)).isEqualTo(0);
    }

    @Test
    void diaAnteriorAHoje(){
        final var diferencaEsperada = 1;
        final var diferenca = umaClasseQualquer.diasEntreHojeEData(HOJE.minusDays(diferencaEsperada));

        assertThat(diferenca).isEqualTo(diferencaEsperada);
    }

    @Test
    void diaPosteriorAHoje(){
        final var diferencaEsperada = 1;
        final var diferenca = umaClasseQualquer.diasEntreHojeEData(HOJE.plusDays(diferencaEsperada));

        assertThat(diferenca).isEqualTo(diferencaEsperada);
    }
}