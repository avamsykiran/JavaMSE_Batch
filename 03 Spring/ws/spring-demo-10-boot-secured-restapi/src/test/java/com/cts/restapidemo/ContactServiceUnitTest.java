package com.cts.restapidemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cts.restapidemo.entities.Contact;
import com.cts.restapidemo.repos.ContactRepo;
import com.cts.restapidemo.services.ContactService;
import com.cts.restapidemo.services.ContactServiceImpl;

@ExtendWith(SpringExtension.class)
public class ContactServiceUnitTest {
	
	@TestConfiguration
	static class ContactServiceTestConfig {
				
		@Bean
		public ContactService contactService() {
			return new ContactServiceImpl();
		}
	}
	
	@Autowired
	private ContactService contactService;
	
	@MockitoBean
	private ContactRepo contactRepo;
	
	@Test
	public void getAll_test() throws Exception {
		List<Contact> contacts = Arrays.asList(
				new Contact(101, "Vamsy", "1234123412","vamsy@gmail.com",LocalDate.of(1985, Month.JULY, 10)));
		
		Mockito.when(contactRepo.findAll()).thenReturn(contacts);
		
		assertEquals(contactService.getAll(), contacts);		
	}
}
