package com.QuizeApplication.exceptions;

import org.springframework.stereotype.Component;

@Component
public class BadRequestException extends Exception {

	String message;
	public BadRequestException(String message) {
		super(message);
		this.message=message;
	}

	public BadRequestException(Exception ex) {
		super(ex);
		this.message=ex.getMessage();
	}
	
	public String toString() {
		return this.message;	
	}


	public BadRequestException() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
