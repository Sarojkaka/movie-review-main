package com.codeinteracts.moviereview.service.impl;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class OTPService {
	
	
	String generateOTP() {
		Integer number =  100000 + new Random().nextInt(900000);
		return String.valueOf(number);
	}
 
}
