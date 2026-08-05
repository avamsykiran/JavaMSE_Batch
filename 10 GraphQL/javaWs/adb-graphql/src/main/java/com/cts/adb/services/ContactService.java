package com.cts.adb.services;

import java.util.List;

import com.cts.adb.entities.Contact;
import com.cts.adb.exceptions.ResourceNotFoundException;

public interface ContactService {

	Contact add(Contact contact);

	Contact update(Contact contact);

	void deleteById(int contactId) throws ResourceNotFoundException;

	Contact getById(int contactId);
	
	Contact getByMobileNumber(String mobileNumber);

	List<Contact> getAll();

}
 