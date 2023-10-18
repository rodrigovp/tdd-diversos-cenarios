package org.rodnet.tdddiversoscenarios.sproutMethod;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Transacao {

	@EqualsAndHashCode.Include
	private UUID id;
	
	private BigDecimal valor;

	public boolean possuiValorBaixo() {
		return valor.compareTo(BigDecimal.TEN) <= 0;
	}
}
