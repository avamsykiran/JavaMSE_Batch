package com.cts.webmvcdemo.services;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cts.webmvcdemo.exceptions.RestApiCallException;
import com.cts.webmvcdemo.model.Contact;

@Service
public class ContactServiceImpl implements ContactService {

	//@Value("${restapi.baseurl:http://localhost:9999}")
	private String baseUrl="http://localhost:9999";

	private RestTemplate client;

	private String contactUrl;

	private Logger logger;

	public ContactServiceImpl() {
		this.client = new RestTemplate();
		this.contactUrl = baseUrl + "/contacts";
		this.logger = LoggerFactory.getLogger(this.getClass());
	}

	@Override
	public Contact add(Contact contact) throws RestApiCallException {
		ResponseEntity<Contact> resp = client.postForEntity(contactUrl, contact, Contact.class);

		if (resp.getStatusCode() != HttpStatus.CREATED && resp.getStatusCode() != HttpStatus.OK) {
			logger.error(resp.toString());
			throw new RestApiCallException("Unable to add the record");
		}

		return resp.getBody();
	}

	@Override
	public Contact update(Contact contact) throws RestApiCallException {
		ResponseEntity<Contact> resp = client.exchange(contactUrl, HttpMethod.PUT, new HttpEntity<Contact>(contact),
				Contact.class);

		if (resp.getStatusCode() != HttpStatus.ACCEPTED && resp.getStatusCode() != HttpStatus.OK) {
			logger.error(resp.toString());
			throw new RestApiCallException("Unable to update the record");
		}

		return resp.getBody();
	}

	@Override
	public void deleteById(int contactId) throws RestApiCallException {
		ResponseEntity<Void> resp = client.exchange(contactUrl + "/" + contactId, HttpMethod.DELETE, null, Void.class);

		if (resp.getStatusCode() != HttpStatus.NO_CONTENT && resp.getStatusCode() != HttpStatus.OK) {
			logger.error(resp.toString());
			throw new RestApiCallException("Unable to delete the record");
		}
	}

	@Override
	public Contact getById(int contactId) throws RestApiCallException {
		ResponseEntity<Contact> resp = client.getForEntity(contactUrl + "/" + contactId, Contact.class);

		if (resp.getStatusCode() == HttpStatus.NOT_FOUND) {
			throw new RestApiCallException("No such record found!");
		} else if (resp.getStatusCode() != HttpStatus.OK) {
			logger.error(resp.toString());
			throw new RestApiCallException("Unable to fetech the record");
		}

		return resp.getBody();
	}

	@Override
	public List<Contact> getAll() throws RestApiCallException {		
		return Arrays.asList(client.getForObject(contactUrl,Contact[].class));
	}

}