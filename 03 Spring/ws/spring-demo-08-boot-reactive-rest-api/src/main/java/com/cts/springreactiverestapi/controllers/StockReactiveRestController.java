package com.cts.springreactiverestapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.springreactiverestapi.service.StockPriceService;
import com.cts.springreactiverestapi.service.StockPriceService.StockPrice;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/stockPrice")
public class StockReactiveRestController {

	@Autowired
	private StockPriceService stockPriceService;
	
	@GetMapping(value = "/{symbol}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<StockPrice> streamStockPrices(@PathVariable String symbol) {
        return stockPriceService.getStockPriceStream(symbol);
    }
}
