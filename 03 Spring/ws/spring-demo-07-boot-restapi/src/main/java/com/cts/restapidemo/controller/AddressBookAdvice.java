package com.cts.restapidemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cts.restapidemo.exceptions.InvalidRequestBodyException;
import com.cts.restapidemo.exceptions.ResourceNotFoundException;
import com.cts.restapidemo.models.ErrorMessage;

@RestControllerAdvice
public class AddressBookAdvice {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorMessage> handleResourceNotFoundException(ResourceNotFoundException exp){
		logger.error(exp.getMessage(), exp);
		return new ResponseEntity<ErrorMessage>(new ErrorMessage(exp.getMessage()), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidRequestBodyException.class)
	public ResponseEntity<ErrorMessage> handleInvalidRequestBodyException(InvalidRequestBodyException exp){
		logger.error(exp.getMessage(), exp);
		return new ResponseEntity<ErrorMessage>(new ErrorMessage(exp.getMessage()), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> handleAnyOtherException(Exception exp){
		logger.error(exp.getMessage(), exp);
		return new ResponseEntity<ErrorMessage>(new ErrorMessage(exp.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}