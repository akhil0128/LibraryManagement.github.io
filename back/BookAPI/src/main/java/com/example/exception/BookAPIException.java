package com.example.exception;

public class BookAPIException extends RuntimeException {

	public BookAPIException(String message) {
		super(message);
	}
}
