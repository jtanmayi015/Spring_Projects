package com.app.customexcep;

public class ResourceNotFoundExcep extends RuntimeException {

	public ResourceNotFoundExcep(String message) {
		super(message);
	}

}
