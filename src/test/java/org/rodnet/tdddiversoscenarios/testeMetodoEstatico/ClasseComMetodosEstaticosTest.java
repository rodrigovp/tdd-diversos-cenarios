package org.rodnet.tdddiversoscenarios.testeMetodoEstatico;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;

class ClasseComMetodosEstaticosTest {

    @Test
    void testeComMetodoEstaticoSimples(){
        assertThat(ClasseComMetodosEstaticos.nome()).isEqualTo("Rodrigo");

        try(var mockedStatic = Mockito.mockStatic(ClasseComMetodosEstaticos.class)){
            mockedStatic.when(ClasseComMetodosEstaticos::nome).thenReturn("Leonardo");

            assertThat(ClasseComMetodosEstaticos.nome()).isEqualTo("Leonardo");
        }
        assertThat(ClasseComMetodosEstaticos.nome()).isEqualTo("Rodrigo");
    }

    @Test
    void testeComMetodosEstaticosQueRecebemArgumentos(){
        assertThat(ClasseComMetodosEstaticos.saudacao("Rodrigo")).isEqualTo("Olá Rodrigo!");

        try(var mockedStatic = Mockito.mockStatic(ClasseComMetodosEstaticos.class)){
            mockedStatic.when(() -> ClasseComMetodosEstaticos.saudacao(anyString())).thenReturn("Tchau fulano!");

            assertThat(ClasseComMetodosEstaticos.saudacao("qualquer string")).isEqualTo("Tchau fulano!");
        }
        assertThat(ClasseComMetodosEstaticos.saudacao("Rodrigo")).isEqualTo("Olá Rodrigo!");
    }
}