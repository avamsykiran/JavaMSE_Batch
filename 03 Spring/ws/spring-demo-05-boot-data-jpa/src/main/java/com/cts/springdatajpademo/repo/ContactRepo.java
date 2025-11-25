package com.cts.springdatajpademo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.springdatajpademo.entities.Contact;

public interface ContactRepo extends JpaRepository<Contact, Long> {
	
}
