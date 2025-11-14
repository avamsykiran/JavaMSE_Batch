package com.cts.restapidemo.exceptions;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class InvalidRequestBodyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidRequestBodyException(BindingResult bindingResult) {
		super(
				bindingResult
					.getAllErrors()
					.stream()
					.map(ObjectError::getDefaultMessage)
					.reduce("",(m1, m2) -> m1 + "," + m2)
		);
	}

}
