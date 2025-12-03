package com.cts.sksc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumptionService {
	
	private Logger logger;

	public KafkaConsumptionService() {
		logger=LoggerFactory.getLogger(this.getClass());
	}
	
	@KafkaListener(topics = {"chat"},groupId = "sksc")
	public void receive(String message) {
		logger.info(message);
	}
}
