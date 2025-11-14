package com.cts.restapidemo.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.restapidemo.entities.Contact;

public interface ContactRepo extends JpaRepository<Contact, Integer> {
	 
}
