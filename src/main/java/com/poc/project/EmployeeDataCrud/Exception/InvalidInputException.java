package com.poc.project.EmployeeDataCrud.Exception;

//@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidInputException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public InvalidInputException() {
		super();
	}
	
	public InvalidInputException(final String message) {
		super(message);
	}

}
