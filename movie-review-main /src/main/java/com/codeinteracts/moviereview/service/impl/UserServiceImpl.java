package com.codeinteracts.moviereview.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.codeinteracts.moviereview.dto.UserDTO;
import com.codeinteracts.moviereview.entity.User;
import com.codeinteracts.moviereview.exception.DuplicateUserNameException;
import com.codeinteracts.moviereview.exception.OTPNotVerifiedException;
import com.codeinteracts.moviereview.repository.UserRepository;
import com.codeinteracts.moviereview.service.UserService;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService{
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
			
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	OTPService otpService;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public User create(UserDTO userDTO) throws DuplicateUserNameException {
		//Check if username already exists
		User existingUser = get(userDTO.getUsername());
		
		if (existingUser != null ) {
			throw new DuplicateUserNameException("Username already exists");
		}
		
		User user = new User();
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setEmail(userDTO.getEmail());
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		user.setUsername(userDTO.getUsername());
		user.setActive(Boolean.TRUE);
		user.setRole("ROLE_USER");
		
		user.setOtp(otpService.generateOTP());
		user.setOtpVerified(Boolean.FALSE);
		
		
		userRepository.save(user);
		
		logger.info("\n\n\n\n {} \n\n\n", user.getOtp());
		
		return user;
	}

	@Override
	public List<User> list() {
		return userRepository.findAll();
	}

	@Override
	public User get(Long id) {
		return userRepository.findById(id).get();
	}
	
	@Override
	public User get(String username) {
//		return userRepository.findBy(null, null);
		return null;
	}
	
	@Override
	public User getByUsernameAndPassword(String username, String password) {
		return userRepository.findByUsernameAndOTP(username, password);
	}


	@Override
	public User update(UserDTO userDTO) {
		User user = new User();
		user.setId(userDTO.getId());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setPassword(userDTO.getPassword());
		user.setUsername(userDTO.getUsername());
		user.setEmail(userDTO.getEmail());
		user.setActive(Boolean.TRUE);
		userRepository.save(user);
		return user;
	}
	
	@Override
	public User delete(Long id) {
		User user = userRepository.findById(id).get();
		user.setActive(Boolean.FALSE);
		return user;
	}

	@Override
	public User verifyOtp(UserDTO userDTO) throws OTPNotVerifiedException {
		User user = userRepository.findByUsernameAndOTP(userDTO.getUsername(), userDTO.getOtp());
		
		if (user == null) {
			throw new OTPNotVerifiedException("OTP is not verified");
		} else {
			user.setOtpVerified(Boolean.TRUE);
			userRepository.save(user);
		}
		
		return user;
		
		
	}

}
