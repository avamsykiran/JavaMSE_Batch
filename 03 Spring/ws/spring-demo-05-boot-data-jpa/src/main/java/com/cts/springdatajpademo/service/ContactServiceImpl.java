package com.cts.springdatajpademo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.springdatajpademo.entities.Contact;
import com.cts.springdatajpademo.repo.ContactRepo;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepo contactRepo;

	@Override
	public Contact add(Contact contact) {
		if (contact != null) {
			return contactRepo.save(contact);
		}
		return null;
	}

	@Override
	public void deleteById(long contactId) {
		contactRepo.deleteById(contactId);
	}

	@Override
	public Contact getById(long contactId) {
		return contactRepo.findById(contactId).orElse(null);
	}

	@Override
	public List<Contact> getAll() {
		return contactRepo.findAll();
	}

	@Override
	public Contact update(Contact contact) {
		if (contact != null) {
			if (contactRepo.existsById(contact.getContactId())) {
				contact = contactRepo.save(contact);
			}
		}
		return contact;
	}
}