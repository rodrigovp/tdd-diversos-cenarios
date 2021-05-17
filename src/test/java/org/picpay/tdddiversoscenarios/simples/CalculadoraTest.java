package org.picpay.tdddiversoscenarios.simples;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class CalculadoraTest {
	
	private Calculadora calculadora = new Calculadora();
	
	@Test
	void testarSoma() {
		assertThat(calculadora.somar(1, 2)).isEqualTo(3);
	}
}
