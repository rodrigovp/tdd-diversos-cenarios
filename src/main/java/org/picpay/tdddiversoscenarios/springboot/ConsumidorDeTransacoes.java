package org.picpay.tdddiversoscenarios.springboot;

import org.picpay.tdddiversoscenarios.monolito.Transacao;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumidorDeTransacoes {

	@KafkaListener(
			topics = "${spring.kafka.topico.nome}"
			, groupId = "${spring.kafka.group.id}"
			, containerFactory = "kafkaListenerTransacaoContainerFactory")
	public void consumir(Transacao t) {
		// TODO Auto-generated method stub
		
	}
	
	
}
