package com.cts.webmvcdemo.runners;

import java.time.LocalDate;
import java.time.Month;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cts.webmvcdemo.entities.Contact;
import com.cts.webmvcdemo.repos.ContactRepo;

@Component
public class HypotheticalDataFillingRunner implements CommandLineRunner {

	@Autowired
	private ContactRepo contactRepo;
	
	@Override
	public void run(String... args) throws Exception {
		contactRepo.save(new Contact(null, "Vamsy Kiran", "1234512341", "vamsy@gmail.com", LocalDate.of(1985, Month.JUNE, 11)));
		contactRepo.save(new Contact(null, "Murthy", "1234512342", "murthy@gmail.com", LocalDate.of(1985, Month.JUNE, 12)));
		contactRepo.save(new Contact(null, "Suresh", "1234512343", "suresh@gmail.com", LocalDate.of(1985, Month.JUNE, 13)));
	}

}
