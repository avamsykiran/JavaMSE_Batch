package com.cts.restapidemo.exceptions;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

@SuppressWarnings("serial")
public class InvalidRequestBodyException extends Exception {
	

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
