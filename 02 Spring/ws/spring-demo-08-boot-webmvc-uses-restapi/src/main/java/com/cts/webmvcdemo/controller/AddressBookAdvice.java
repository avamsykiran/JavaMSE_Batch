package com.cts.webmvcdemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.cts.webmvcdemo.exceptions.RestApiCallException;

@ControllerAdvice
public class AddressBookAdvice {

	private Logger logger;
	
	public AddressBookAdvice() {
		this.logger=LoggerFactory.getLogger(this.getClass());
	}
	
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
		return "AddressBook 1.0";
	}
	
	@ExceptionHandler(RestApiCallException.class)
	public ModelAndView handleRestApiCallException(RestApiCallException exp) {
		return new ModelAndView("error-page","errMsg",exp.getMessage());
	}
	
}