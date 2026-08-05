package com.cts.adb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.cts.adb.exceptions.InvalidRequestBodyException;
import com.cts.adb.exceptions.ResourceNotFoundException;

import graphql.GraphQLError;
import graphql.schema.DataFetchingEnvironment;

@ControllerAdvice
public class AddressBookAdvice {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@GraphQlExceptionHandler(ResourceNotFoundException.class)
	public GraphQLError handleResourceNotFoundException(ResourceNotFoundException exp, DataFetchingEnvironment env) {
		logger.error(exp.getMessage(), exp);
		return GraphQLError.newError().errorType(ErrorType.NOT_FOUND).message(exp.getMessage())
				.path(env.getExecutionStepInfo().getPath()).location(env.getField().getSourceLocation())				
				.build();
	}

	@GraphQlExceptionHandler(InvalidRequestBodyException.class)
	public GraphQLError handleInvalidRequestBodyException(InvalidRequestBodyException exp,
			DataFetchingEnvironment env) {
		logger.error(exp.getMessage(), exp);
		return GraphQLError.newError().errorType(ErrorType.BAD_REQUEST).message(exp.getMessage())
				.path(env.getExecutionStepInfo().getPath()).build();
	}

	@GraphQlExceptionHandler(Exception.class)
	public GraphQLError handleAnyOtherException(Exception exp, DataFetchingEnvironment env) {
		logger.error(exp.getMessage(), exp);
		return GraphQLError.newError().errorType(ErrorType.INTERNAL_ERROR)
				.message("An unexpected internal error occurred.").path(env.getExecutionStepInfo().getPath()).build();
	}
}