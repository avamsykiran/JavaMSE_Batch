package com.cts.webmvcdemo.services;

import java.util.List;

import com.cts.webmvcdemo.entities.Contact;

public interface ContactService {

	Contact add(Contact contact);

	Contact update(Contact contact);

	void deleteById(int contactId);

	Contact getById(int contactId);

	List<Contact> getAll();

}
 