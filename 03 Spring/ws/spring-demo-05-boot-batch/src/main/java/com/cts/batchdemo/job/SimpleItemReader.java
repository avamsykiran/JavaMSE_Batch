package com.cts.batchdemo.job;

import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

@Component
public class SimpleItemReader implements ItemReader<String> {
    private String[] data = {"Alice", "Bob", "Charlie", "Diana"};
    private int index = 0;

    @Override
    public String read() {
        if (index < data.length) {
            return data[index++];
        }
        return null; // End of data
    }
}