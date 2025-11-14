package com.cts.webmvcdemo.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cts.webmvcdemo.model.Contact;

@FeignClient(name="contactsClient") //,url = "http://localhost:9999/contacts")
public interface ContactService {

	@PostMapping
	Contact add(@RequestBody Contact contact);
	
	@PutMapping
	Contact update(@RequestBody Contact contact);
	
	@DeleteMapping("/{id}")
	void deleteById(@PathVariable("id") int contactId);
	
	@GetMapping("/{id}")
	Contact getById(@PathVariable("id") int contactId);
	
	@GetMapping
	List<Contact> getAll();

}
 