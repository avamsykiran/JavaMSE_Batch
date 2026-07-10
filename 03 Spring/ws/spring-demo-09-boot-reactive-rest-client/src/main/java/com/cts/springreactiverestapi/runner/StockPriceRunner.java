package com.cts.springreactiverestapi.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cts.springreactiverestapi.service.StockConsumerService;

@Component
public class StockPriceRunner implements CommandLineRunner {
	
	@Autowired
	private StockConsumerService service;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("--- Starting Stock Observer ---");
		service.consumeStockStream("GOLD");
        
        // Keep the application alive since the stream is non-blocking
        Thread.sleep(10000);	
	}

}
