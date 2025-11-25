package com.cts.springdatamongodemo.service;

import java.util.List;

import com.cts.springdatamongodemo.entities.Contact;

public interface ContactService {

	Contact add(Contact contact);

	Contact update(Contact contact);

	void deleteById(long contactId);

	Contact getById(long contactId);

	List<Contact> getAll();

}
 