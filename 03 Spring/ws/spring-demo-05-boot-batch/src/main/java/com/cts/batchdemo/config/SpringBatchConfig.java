package com.cts.batchdemo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.cts.batchdemo.job.SimpleItemProcessor;
import com.cts.batchdemo.job.SimpleItemReader;
import com.cts.batchdemo.job.SimpleItemWriter;

@Configuration
public class SpringBatchConfig {
	
	@Autowired
	private SimpleItemProcessor processor;
	
	@Autowired
	private SimpleItemReader reader;
	
	@Autowired
	private SimpleItemWriter writer;
	
	@Bean
	public Job importUserJob(JobRepository jobRepository, Step step1) {
	    return new JobBuilder("importUserJob", jobRepository)
	      .incrementer(new RunIdIncrementer())	      
	      .flow(step1)	      
	      .end()
	      .build();
	}
	
	@Bean
	public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
	    return new StepBuilder("step1", jobRepository)
	      .<String, String> chunk(10, transactionManager)
	      .reader(reader)
	      .processor(processor)
	      .writer(writer)
	      .build();
	}	
}