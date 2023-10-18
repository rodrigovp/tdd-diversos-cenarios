package org.rodnet.tdddiversoscenarios.testeMetodoEstatico;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class OutraClasseQualquerTest {

    private final OutraClasseQualquer outraClasseQualquer = new OutraClasseQualquer();
    private static final LocalDate HOJE = LocalDateTime.now().toLocalDate();

    @Test
    void diasIguais(){
        assertThat(outraClasseQualquer.diasEntreHojeEData(HOJE)).isEqualTo(0);
        try(var mockedStatic = Mockito.mockStatic(LocalDate.class)){
            mockedStatic.when(LocalDate::now).thenReturn(HOJE);

            assertThat(outraClasseQualquer.diasEntreHojeEData(HOJE)).isEqualTo(0);
        }
    }

    @Test
    void diaAnteriorAHoje(){
        final var diferencaEsperada = 1;
        final var diferenca = outraClasseQualquer.diasEntreHojeEData(HOJE.minusDays(diferencaEsperada));

        assertThat(diferenca).isEqualTo(diferencaEsperada);
    }

    @Test
    void diaPosteriorAHoje(){
        final var diferencaEsperada = 1;
        final var diferenca = outraClasseQualquer.diasEntreHojeEData(HOJE.plusDays(diferencaEsperada));

        assertThat(diferenca).isEqualTo(diferencaEsperada);
    }
}