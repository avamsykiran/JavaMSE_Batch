package com.cts.springdatamongodemo.ui;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cts.springdatamongodemo.entities.Contact;
import com.cts.springdatamongodemo.service.ContactService;

@Component
public class ContactCRUDScreen implements CommandLineRunner {

	@Autowired
	private Scanner kbin;
	
	@Autowired
	private ContactService contactService;
	
	@Override
	public void run(String... args) throws Exception {
		
		boolean shallContinue = true;
		
		while(shallContinue) {
			System.out.print("Cmd (add/delete/list/quit): ");
			String cmd = kbin.next().toLowerCase();
			
			switch(cmd) {
			case "add": 
				doAdd();
				break;
			case "delete": 
				doDelete();
				break;
			case "list": 
				doList();
				break;
			case "quit": 
				shallContinue=false;
				System.out.println("App Terminated");
				break;
			default: System.out.println("Unknown command");
			}
		}
	}

	private void doList() {
		List<Contact> contacts = contactService.getAll();
		
		if(contacts==null || contacts.isEmpty()) {
			System.out.println("No Records Found");
		}else{
			contacts.stream().forEach(System.out::println);
		}		
	}

	private void doDelete() {
		System.out.print("Contact Id: ");
		long contactId = kbin.nextLong();
		
		contactService.deleteById(contactId);
	}

	private void doAdd() {
		Contact contact = new Contact();
		
		System.out.print("Full Name: ");
		contact.setFullName(kbin.next());
		System.out.print("Mobile Number: ");
		contact.setMobileNumber(kbin.next());
		System.out.print("Mail Id: ");
		contact.setMailId(kbin.next());
		System.out.print("Date Of Birth ('YYYY-mm-DD'): ");
		contact.setDateOfBith(LocalDate.parse(kbin.next()));
		
		contact = contactService.add(contact);
		System.out.println(contact);
	}

}
