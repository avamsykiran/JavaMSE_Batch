package com.cts.webmvcdemo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.webmvcdemo.entities.Contact;
import com.cts.webmvcdemo.repos.ContactRepo;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepo contactRepo;
	
	@Override
	public Contact add(Contact contact) {
		return contactRepo.save(contact);
	}

	@Override
	public void deleteById(int contactId) {
		contactRepo.deleteById(contactId);
	}

	@Override
	public Contact getById(int contactId) {
		return contactRepo.findById(contactId).orElse(null);
	}

	@Override
	public List<Contact> getAll() {
		return contactRepo.findAll();
	}

	@Override
	public Contact update(Contact contact) {
		if(contactRepo.existsById(contact.getContactId())) {
			contact = contactRepo.save(contact);
		}
		return contact;
	}
}