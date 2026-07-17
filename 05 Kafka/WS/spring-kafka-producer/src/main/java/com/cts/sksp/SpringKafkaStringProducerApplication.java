package com.cts.sksp;

import java.util.Map;
import java.util.TreeMap;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.cts.sksp.model.ProductDetails;

@SpringBootApplication
public class SpringKafkaStringProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringKafkaStringProducerApplication.class, args);
	}

	@Bean
	public ProducerFactory<String,String> producerStringFactory(){
		Map<String,Object> configs = new TreeMap<>();
		
		configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
		
		return new DefaultKafkaProducerFactory<>(configs);
	}
	
	@Bean
	public KafkaTemplate<String,String> kafkaStringTemplate(){
		return new KafkaTemplate<>(producerStringFactory());
	}
	
	@Bean
	public ProducerFactory<String,ProductDetails> producerObjectFactory(){
		Map<String,Object> configs = new TreeMap<>();
		
		configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());
		configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
		
		return new DefaultKafkaProducerFactory<>(configs);
	}
	
	@Bean
	public KafkaTemplate<String,ProductDetails> kafkaObjectTemplate(){
		return new KafkaTemplate<>(producerObjectFactory());
	}
}
