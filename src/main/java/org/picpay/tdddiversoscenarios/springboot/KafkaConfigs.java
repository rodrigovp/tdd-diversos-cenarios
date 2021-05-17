package org.picpay.tdddiversoscenarios.springboot;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.picpay.tdddiversoscenarios.monolito.Transacao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaConfigs {

	@Value("${spring.kafka.bootstrap-servers}")
	private String kafkaServerUrl;
	
	@Value("${spring.kafka.group.id}")
	private String groupId;
	
	@Value("${spring.kafka.auto.offset.reset}")
	private String autoOffsetReset;
	
	/*******************************************
	 * Configurações gerais de consumer
	 *******************************************/
	
	@Bean
	public Map<String, Object> consumerConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServerUrl);
	
		return props;
	}
	
	/*******************************************
	 * Produtor de transações
	 *******************************************/

	@Bean
	public Map<String, Object> transacaoProducerConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServerUrl);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return props;
	}

	@Bean
	public ProducerFactory<String, Transacao> transacaoProducerFactory() {
		return new DefaultKafkaProducerFactory<>(transacaoProducerConfigs());
	}

	@Bean
	public KafkaTemplate<String, Transacao> transacaoKafkaTemplate() {
		return new KafkaTemplate<>(transacaoProducerFactory());
	}

	/*******************************************
	 * Consumidor de transações
	 *******************************************/

	@Bean
	public ConsumerFactory<String, Transacao> transacaoConsumerFactory() {
		JsonDeserializer<Transacao> transacaoDeserializer = new JsonDeserializer<>();
		transacaoDeserializer.addTrustedPackages("*");
		return new DefaultKafkaConsumerFactory<>(
				consumerConfigs(),
				new StringDeserializer(),
				transacaoDeserializer);
	}

	@Bean
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Transacao>> kafkaListenerTransacaoContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, Transacao> factory =
				new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(transacaoConsumerFactory());
		return factory;
	}
}
