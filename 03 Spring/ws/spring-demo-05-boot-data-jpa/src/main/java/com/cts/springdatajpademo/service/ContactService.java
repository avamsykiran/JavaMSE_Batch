package com.cts.springdatajpademo.service;

import java.util.List;

import com.cts.springdatajpademo.entities.Contact;

public interface ContactService {

	Contact add(Contact contact);

	Contact update(Contact contact);

	void deleteById(long contactId);

	Contact getById(long contactId);

	List<Contact> getAll();

}
 