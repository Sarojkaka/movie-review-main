package com.codeinteracts.moviereview.exception;

public class DuplicateUserNameException extends Exception {

	private static final long serialVersionUID = -4005683330377575486L;
	
	private String message;
	
	public DuplicateUserNameException(String message) {
		super();
		this.message = message;
	}
	
	public DuplicateUserNameException() {
		super();
		this.message = "Username already exists";
	}

}
