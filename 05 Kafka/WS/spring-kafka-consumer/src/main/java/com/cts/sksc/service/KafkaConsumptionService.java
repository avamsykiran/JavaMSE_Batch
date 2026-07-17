package com.cts.sksc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.cts.sksc.models.ProductDetails;

@Service
public class KafkaConsumptionService {
	
	private Logger logger;

	public KafkaConsumptionService() {
		logger=LoggerFactory.getLogger(this.getClass());
	}

	@KafkaListener(
			topics = {"messages"},groupId = "sksc",
			containerFactory = "kafkaStringListenerContainerFactory")
	public void receiveString(String message) {
		logger.info(message);
	}
	
	@KafkaListener(
			topics = {"products"},groupId = "sksc",
			containerFactory = "kafkaObjectListenerContainerFactory")
	public void receiveProductDetails(ProductDetails message) {
		logger.info(message.toString());
	}
}
