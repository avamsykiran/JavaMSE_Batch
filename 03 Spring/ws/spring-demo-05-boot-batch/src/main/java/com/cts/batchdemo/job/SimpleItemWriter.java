package com.cts.batchdemo.job;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class SimpleItemWriter implements ItemWriter<String> {
    
	@Override
	public void write(Chunk<? extends String> items) throws Exception {
	    items.forEach(System.out::println); // Print items to console	
	}
}