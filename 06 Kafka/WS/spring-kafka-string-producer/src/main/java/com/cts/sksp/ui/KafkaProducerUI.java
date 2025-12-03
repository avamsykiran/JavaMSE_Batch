package com.cts.sksp.ui;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cts.sksp.service.KafkaProductionService;

@Component
public class KafkaProducerUI implements CommandLineRunner {

	@Autowired
	private KafkaProductionService kps;
	
	@Override
	public void run(String... args) throws Exception {
		Scanner scan = new Scanner(System.in);
		
		String key="";
		String msg ="";
		
		while(!"bye".equals(msg)) {
			System.out.println("Key> ");
			key = scan.nextLine();
			System.out.println("Message> ");
			msg = scan.nextLine();
			kps.send(key, msg);
		}
	}

}
