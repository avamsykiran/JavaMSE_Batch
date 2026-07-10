package com.cts.springreactiverestapi.service;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

@Service
public class StockPriceService {
	
	public record StockPrice(String symbol, double price, LocalDateTime timestamp) {}

	public Flux<StockPrice> getStockPriceStream(String symbol) {
        // Simulates an infinite stream emitting every 1 second
        return Flux.interval(Duration.ofSeconds(1))
                .map(tick -> new StockPrice(
                        symbol,
                        100 + Math.random() * 10, // Random price logic
                        LocalDateTime.now()
                ))                
                .log(); // Logs the signals (onNext, onSubscribe) to the console
    }
}
