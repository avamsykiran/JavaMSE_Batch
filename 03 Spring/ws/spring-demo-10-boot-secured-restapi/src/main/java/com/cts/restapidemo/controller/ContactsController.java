package com.cts.restapidemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.restapidemo.entities.Contact;
import com.cts.restapidemo.exceptions.InvalidRequestBodyException;
import com.cts.restapidemo.exceptions.ResourceNotFoundException;
import com.cts.restapidemo.services.ContactService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/contacts")
public class ContactsController {

	@Autowired
	private ContactService contactService;
	
	@GetMapping
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	public ResponseEntity<List<Contact>> getContactsListAction() {
		return ResponseEntity.ok(contactService.getAll());
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Contact> getContactByIdAction(@PathVariable("id") int cid) throws ResourceNotFoundException {
		Contact contact = contactService.getById(cid);
		
		if(contact==null) {
			throw new ResourceNotFoundException("No Such Contact Found");
		}
		
		return ResponseEntity.ok(contact);
	}
		
	@PostMapping
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	public ResponseEntity<Contact> addContactAction
		(@RequestBody @Valid Contact contact, BindingResult bindingResult ) throws InvalidRequestBodyException {
				
		if(bindingResult.hasErrors()) {
			throw new InvalidRequestBodyException(bindingResult);
		}	
		
		return new ResponseEntity<Contact>(contactService.add(contact), HttpStatus.CREATED);
	}

	@PutMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Contact> updateContactAction
		(@RequestBody @Valid Contact contact, BindingResult bindingResult ) throws InvalidRequestBodyException {
				
		if(bindingResult.hasErrors()) {
			throw new InvalidRequestBodyException(bindingResult);
		}
		
		return new ResponseEntity<Contact>(contactService.update(contact), HttpStatus.ACCEPTED);
	}
		
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> delContactAction(@PathVariable("id") int cid) throws ResourceNotFoundException {
		contactService.deleteById(cid);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
