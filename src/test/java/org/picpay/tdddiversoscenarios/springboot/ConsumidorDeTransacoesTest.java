package org.picpay.tdddiversoscenarios.springboot;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.picpay.tdddiversoscenarios.monolito.TransacaoTest.umaTransacaoValidaQualquer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.picpay.tdddiversoscenarios.monolito.Transacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;

@EmbeddedKafka
@SpringBootTest(properties = "spring.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ConsumidorDeTransacoesTest {
	
	@SpyBean
	private ConsumidorDeTransacoes consumidor;
	
	@Value("${spring.kafka.topico.nome}")
	private String topico;
	
	@Autowired
	private KafkaTemplate<String, Transacao> kafkaTemplate;
	
	@Captor
	private ArgumentCaptor<Transacao> captor;
	
	@Test
	void receberUmaTransacao() {
		Transacao transacao = umaTransacaoValidaQualquer();
		kafkaTemplate.send(topico, transacao);
		
		verify(consumidor, timeout(3000).times(1)).consumir(captor.capture());
		
		assertThat(captor.getValue()).isEqualTo(transacao);
	}
}
