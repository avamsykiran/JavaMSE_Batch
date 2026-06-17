package com.cts.sksc;

import java.util.Map;
import java.util.TreeMap;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.cts.sksc.models.ProductDetails;

@EnableKafka
@SpringBootApplication
public class SpringKafkaStringConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringKafkaStringConsumerApplication.class, args);
	}

	@Bean
	public ConsumerFactory<String, String> consumerStringFactory() {
		Map<String, Object> configs = new TreeMap<>();

		configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

		return new DefaultKafkaConsumerFactory<>(configs);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaStringListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String,String> kfc = 
				new ConcurrentKafkaListenerContainerFactory<>();
		kfc.setConsumerFactory(consumerStringFactory());
		return kfc;
	}
	

	@Bean
	public ConsumerFactory<String, ProductDetails> consumerObjectFactory() {
		Map<String, Object> configs = new TreeMap<>();

		configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class.getName());
		configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

		return new DefaultKafkaConsumerFactory<>(configs);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, ProductDetails> kafkaObjectListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String,ProductDetails> kfc = 
				new ConcurrentKafkaListenerContainerFactory<>();
		kfc.setConsumerFactory(consumerObjectFactory());
		return kfc;
	}
}
