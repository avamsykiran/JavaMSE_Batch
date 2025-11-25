package com.cts.springdatamongodemo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cts.springdatamongodemo.entities.Contact;

public interface ContactRepo extends MongoRepository<Contact, Long> {
	
}
