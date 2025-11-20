package com.cts.restapidemo.services;

import java.util.List;

import com.cts.restapidemo.entities.Contact;
import com.cts.restapidemo.exceptions.ResourceNotFoundException;

public interface ContactService {

	Contact add(Contact contact);

	Contact update(Contact contact);

	void deleteById(int contactId) throws ResourceNotFoundException;

	Contact getById(int contactId);

	List<Contact> getAll();

}
 