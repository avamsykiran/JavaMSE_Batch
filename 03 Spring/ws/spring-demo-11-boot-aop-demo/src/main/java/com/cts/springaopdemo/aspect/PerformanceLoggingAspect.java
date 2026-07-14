package com.cts.springaopdemo.aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceLoggingAspect {

	private static final Logger logger = LoggerFactory.getLogger(PerformanceLoggingAspect.class);

	// Pointcut expression targets all methods in OrderService
	@Pointcut("execution(* com.cts.springaopdemo.service.GreetService.*(..))")
	public void greetServiceMethods() {
	}

	@Around("greetServiceMethods()")
	public Object profileMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();

		String methodName = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		logger.info("Entering method: {} with arguments: {}", methodName, Arrays.toString(args));

		Object result;
		try {
			// This line triggers the execution of the actual business method
			result = joinPoint.proceed();
		} catch (Throwable throwable) {
			logger.error("Exception thrown in method: {}", methodName, throwable);
			throw throwable; // Rethrow so normal flow isn't broken
		}

		long executionTime = System.currentTimeMillis() - startTime;
		logger.info("Exiting method: {}. Result: {}. Execution time: {} ms", methodName, result, executionTime);

		return result;
	}
}