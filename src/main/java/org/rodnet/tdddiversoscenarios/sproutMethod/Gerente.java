package org.rodnet.tdddiversoscenarios.sproutMethod;

import java.util.List;

public class Gerente {

	private RepositorioTransacoes repositorioTransacoes;
	
	public Gerente(RepositorioTransacoes repositorioTransacoes) {
		this.repositorioTransacoes = repositorioTransacoes;
	}

	public void selecionar(List<Transacao> transacoes) {
		transacoes.forEach(transacao -> {
			if(transacao.possuiValorBaixo()) {
				repositorioTransacoes.guardar(transacao);
			}
		});
	}
}
