package org.rodnet.tdddiversoscenarios.monolito;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Gerente {

	private RepositorioTransacoes repositorioTransacoes;
	
	public Gerente(RepositorioTransacoes repositorioTransacoes) {
		this.repositorioTransacoes = repositorioTransacoes;
	}

	public void selecionar(List<Transacao> transacoes) {
		transacoes = removerRepetidos(transacoes);
		transacoes.forEach(transacao -> {
			if(transacao.possuiValorBaixo()) {
				repositorioTransacoes.guardar(transacao);
			}
		});
	}

	List<Transacao> removerRepetidos(List<Transacao> transacoes) {
		return new ArrayList<>(new HashSet<>(transacoes));
	}
}
