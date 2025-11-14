package com.cts.webmvcdemo.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class AddressBookAdvice {
	
	@ModelAttribute("links")
	public String[][] links(){
		return new String[][] { 
			{ "Home", "/home" } ,
			{ "Contacts", "/contacts/list" },
			{ "New Contact", "/contacts/add" } 
		};
	}

	@ModelAttribute("appName")
	public String appName(){
		return "AddressBook 3.0";
	}
		
}