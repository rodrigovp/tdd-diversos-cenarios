package org.rodnet.tdddiversoscenarios.sproutMethod;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static java.math.BigDecimal.TEN;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.rodnet.tdddiversoscenarios.sproutMethod.TransacaoTest.umaTransacaoValidaQualquer;

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
}
