package com.cts.adb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cts.adb.entities.Contact;
import com.cts.adb.exceptions.ResourceNotFoundException;
import com.cts.adb.services.ContactService;

import jakarta.validation.Valid;

@Controller
@Validated
@RequestMapping("/contacts")
public class ContactsController {

	@Autowired
	private ContactService contactService;
	
	// --- QUERIES ---

    @QueryMapping
    public Contact contactById(@Argument Integer contactId) {
        return contactService.getById(contactId);
    }
    
    @QueryMapping
    public List<Contact> allContacts() {
        return contactService.getAll();
    }
    
    //--- MUTATIONS ---

    @MutationMapping
    public Contact createContact(@Argument @Valid Contact input) {
        return contactService.add(input);
    }

    @MutationMapping
    public Contact updateContact(@Argument Integer contactId, @Argument @Valid Contact input) {
    	input.setContactId(contactId);
        return contactService.update(input);
    }

    @MutationMapping
    public Boolean deleteContact(@Argument Integer contactId) throws ResourceNotFoundException {
        contactService.deleteById(contactId);
        return true;
    }
}
