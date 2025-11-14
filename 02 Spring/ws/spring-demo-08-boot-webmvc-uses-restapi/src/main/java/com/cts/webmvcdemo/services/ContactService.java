package com.cts.webmvcdemo.services;

import java.util.List;

import com.cts.webmvcdemo.exceptions.RestApiCallException;
import com.cts.webmvcdemo.model.Contact;

public interface ContactService {

	Contact add(Contact contact) throws RestApiCallException;
	Contact update(Contact contact) throws RestApiCallException;
	void deleteById(int contactId) throws RestApiCallException;
	Contact getById(int contactId) throws RestApiCallException;
	List<Contact> getAll() throws RestApiCallException;

}
 