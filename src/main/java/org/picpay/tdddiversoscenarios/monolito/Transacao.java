package org.picpay.tdddiversoscenarios.monolito;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@NoArgsConstructor
public class Transacao {

	@EqualsAndHashCode.Include
	private UUID id;
	
	private BigDecimal valor;

	public boolean possuiValorBaixo() {
		return valor.compareTo(BigDecimal.TEN) <= 0;
	}
}
