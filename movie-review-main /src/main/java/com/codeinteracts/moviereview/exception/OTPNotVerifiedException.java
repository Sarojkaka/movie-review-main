package com.codeinteracts.moviereview.exception;

public class OTPNotVerifiedException extends Exception {

	private static final long serialVersionUID = 5514921199322765907L;
	
	String message;

	public OTPNotVerifiedException(String message) {
		super();
		this.message = message;
	}

	public OTPNotVerifiedException() {
		super();
		this.message = "OTP is not verified";
	}
	
	

}
