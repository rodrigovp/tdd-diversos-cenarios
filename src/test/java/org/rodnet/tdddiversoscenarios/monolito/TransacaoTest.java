package org.rodnet.tdddiversoscenarios.monolito;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;
import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;

public class TransacaoTest {

	@Test
	void igualdadeDeTransacoes() {
		var id = randomUUID();
		var umaTransacao = new Transacao(id, ONE);
		var outraTransacao = new Transacao(id, TEN);
		
		assertThat(umaTransacao).isEqualTo(outraTransacao);
	}
	
	@Test
	void desigualdadeDeTransacoes() {
		var umaTransacao = umaTransacaoValidaQualquer();
		var outraTransacao = umaTransacaoValidaQualquer();
		
		assertThat(umaTransacao).isNotEqualTo(outraTransacao);
	}
	
	@Test
	void transacaoBaixa() {
		var transacaoBaixa = umaTransacaoValidaQualquer(new BigDecimal("9.99"));
		var outraTransacaoBaixa = umaTransacaoValidaQualquer(BigDecimal.TEN);
		
		assertThat(transacaoBaixa.possuiValorBaixo()).isTrue();
		assertThat(outraTransacaoBaixa.possuiValorBaixo()).isTrue();
	}
	
	@Test
	void transacaoAlta() {
		var transacaoAlta = umaTransacaoValidaQualquer(new BigDecimal("10.01"));
		
		assertThat(transacaoAlta.possuiValorBaixo()).isFalse();

	}
	
	public static final Transacao umaTransacaoValidaQualquer() {
		return new Transacao(randomUUID(), TEN);
	}
	
	public static final Transacao umaTransacaoValidaQualquer(BigDecimal valor) {
		return new Transacao(randomUUID(), valor);
	}
}
