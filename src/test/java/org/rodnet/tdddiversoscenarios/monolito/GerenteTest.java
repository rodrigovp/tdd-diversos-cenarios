package org.rodnet.tdddiversoscenarios.monolito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static java.math.BigDecimal.TEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.rodnet.tdddiversoscenarios.monolito.TransacaoTest.umaTransacaoValidaQualquer;

class GerenteTest {
	
	private Gerente gerente;
	private RepositorioTransacoes repositorioTransacoes;
	
	@BeforeEach
	void setUp() {
		this.repositorioTransacoes = mock(RepositorioTransacoes.class);
		this.gerente = new Gerente(repositorioTransacoes);
	}
	
	@Test
	void somenteGuardarTransacoesComValorMaiorQue10() {
		var transacaoPequena = umaTransacaoValidaQualquer(TEN);
		var transacaoGrande = umaTransacaoValidaQualquer(TEN.add(BigDecimal.ONE));
		
		gerente.selecionar(Arrays.asList(transacaoPequena, transacaoGrande));
		
		verify(repositorioTransacoes).guardar(transacaoPequena);
		verify(repositorioTransacoes, never()).guardar(transacaoGrande);
	}
	
	@Test
	void naoGuardarTransacoesRepetidas() {
		var transacao = umaTransacaoValidaQualquer(TEN);
		
		gerente.selecionar(Arrays.asList(transacao, transacao));
		
		verify(repositorioTransacoes, times(1)).guardar(transacao);
	}
	
	@Test
	void removerRepetidos() {
		var transacao = umaTransacaoValidaQualquer(TEN);
		List<Transacao> transacoes = Arrays.asList(transacao, transacao);
		
		assertThat(transacoes).contains(transacao, transacao);
		
		List<Transacao> novaLista = gerente.removerRepetidos(transacoes);
		
		assertThat(novaLista).containsOnly(transacao);
	}
}
