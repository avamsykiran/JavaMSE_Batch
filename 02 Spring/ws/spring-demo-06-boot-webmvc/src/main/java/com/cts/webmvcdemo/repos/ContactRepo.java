package com.cts.webmvcdemo.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.webmvcdemo.entities.Contact;

public interface ContactRepo extends JpaRepository<Contact, Integer> {
	 
}
