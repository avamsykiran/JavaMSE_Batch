package com.cts.sksp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.cts.sksp.model.ProductDetails;

@Service
public class KafkaProductionService {

	private Logger logger;	

	@Autowired
	@Qualifier("kafkaStringTemplate")
	private KafkaTemplate<String, String> kafkaStringTemplate;
	
	@Autowired
	@Qualifier("kafkaObjectTemplate")
	private KafkaTemplate<String, ProductDetails> kafkaProductDetailsTemplate;

	public KafkaProductionService() {
		this.logger = LoggerFactory.getLogger(this.getClass());
		this.topic = "chat";
	}

	public void send(String key, String msg) {
		String topic="messages";
		kafkaStringTemplate.send(topic, key, msg).addCallback((result) -> {
			logger.info("Message Send to Partiion: " + result.getProducerRecord().partition());
		}, (err) -> {
			logger.error(err.getMessage());
		});
	}
	
	public void sendProductDetails(String key, ProductDetails msg) {
		String topic="products";
		kafkaProductDetailsTemplate.send(topic, key, msg).addCallback((result) -> {
			logger.info("Message Send to Partiion: " + result.getProducerRecord().partition());
		}, (err) -> {
			logger.error(err.getMessage());
		});
	}
}
