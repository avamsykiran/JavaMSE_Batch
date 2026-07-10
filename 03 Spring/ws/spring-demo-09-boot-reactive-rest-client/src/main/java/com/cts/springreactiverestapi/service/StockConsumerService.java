package com.cts.springreactiverestapi.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;

@Service
public class StockConsumerService {

    private final WebClient webClient;

    public StockConsumerService() {
        // Initialize the WebClient with the base URL of your producer
        this.webClient = WebClient.create("http://localhost:9090");
    }
    
    public record StockPrice(String symbol, double price, LocalDateTime timestamp) {}

    public void consumeStockStream(String symbol) {
        Flux<StockPrice> stockFlux = webClient.get()
                .uri("/stockPrice/{symbol}", symbol)
                .retrieve()
                .bodyToFlux(StockPrice.class); // Deserializes the SSE stream into POJOs

        // The 'Subscribe' action is what triggers the flow of data
        stockFlux.subscribe(
            stock -> System.out.println("Received: " + stock.symbol() + " at $" + stock.price()),
            error -> System.err.println("Error on stream: " + error.getMessage()),
            () -> System.out.println("Stream Completed")
        );
    }
}