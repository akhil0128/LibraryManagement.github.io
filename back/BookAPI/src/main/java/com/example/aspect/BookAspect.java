package com.example.aspect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.example.exception.BookAPIException;
@Aspect
@Component
public class BookAspect {

	private static final Log Logger = LogFactory.getLog(BookAspect.class);
	
	
	@AfterThrowing(pointcut = "execution(* com.example.controller.BookController.addBook(..))", throwing = "exception")
	public void afterThrowing(BookAPIException exception) {
		Logger.info("@AfterThrowing: Exception occured");
		Logger.error(exception.getMessage());
	}
}
