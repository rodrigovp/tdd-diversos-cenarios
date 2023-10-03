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
		UUID id = UUID.randomUUID();
		Transacao umaTransacao = new Transacao(id, ONE);
		Transacao outraTransacao = new Transacao(id, TEN);
		
		assertThat(umaTransacao).isEqualTo(outraTransacao);
	}
	
	@Test
	void desigualdadeDeTransacoes() {
		Transacao umaTransacao = umaTransacaoValidaQualquer();
		Transacao outraTransacao = umaTransacaoValidaQualquer();
		
		assertThat(umaTransacao).isNotEqualTo(outraTransacao);
	}
	
	@Test
	void transacaoBaixa() {
		Transacao transacaoBaixa = umaTransacaoValidaQualquer(new BigDecimal("9.99"));
		Transacao outraTransacaoBaixa = umaTransacaoValidaQualquer(BigDecimal.TEN);
		
		assertThat(transacaoBaixa.possuiValorBaixo()).isTrue();
		assertThat(outraTransacaoBaixa.possuiValorBaixo()).isTrue();
	}
	
	@Test
	void transacaoAlta() {
		Transacao transacaoAlta = umaTransacaoValidaQualquer(new BigDecimal("10.01"));
		
		assertThat(transacaoAlta.possuiValorBaixo()).isFalse();

	}
	
	public static final Transacao umaTransacaoValidaQualquer() {
		return new Transacao(randomUUID(), TEN);
	}
	
	public static final Transacao umaTransacaoValidaQualquer(BigDecimal valor) {
		return new Transacao(randomUUID(), valor);
	}
}
