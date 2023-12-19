package com.app.custom_exceptions;

public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(Long id) {
		super("Could Not Found The User By This Id"+id);
	}

}
