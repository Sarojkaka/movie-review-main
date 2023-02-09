package com.codeinteracts.moviereview.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
	JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	String sender;
	
	public String sendMail(String userEmail, String username) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(userEmail);
		message.setFrom(sender);
		message.setSubject("Welcome EMAIL!!!");
		
		String welcomeMessage = "Hello, "+ username + ". Welcome to Movie Review Application. Start adding reviews to movies";
		
		message.setText(welcomeMessage);
		
//		javaMailSender.send(message);
		
		return "Successfull";
	}
	
	
	public String sendOtpMail(String userEmail, String username, String otp) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(userEmail);
		message.setFrom(sender);
		message.setSubject("Welcome EMAIL!!!");
		
		String welcomeMessage = "Hello, "+ username + ". Welcome to Movie Review Application. Your otp is <strong>"+otp+"</strong>.";
		
		message.setText(welcomeMessage);
		
//		javaMailSender.send(message);
		
		return "Successfull";
	}
	
}
