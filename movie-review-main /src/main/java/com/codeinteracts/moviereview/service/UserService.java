package com.codeinteracts.moviereview.service;

import java.util.List;

import com.codeinteracts.moviereview.dto.UserDTO;
import com.codeinteracts.moviereview.entity.User;
import com.codeinteracts.moviereview.exception.DuplicateUserNameException;
import com.codeinteracts.moviereview.exception.OTPNotVerifiedException;

public interface UserService {
	
	User create(UserDTO userDTO) throws DuplicateUserNameException;

	List<User> list();

	User get(Long id);

	User get(String username);

	User update(UserDTO userDTO);

	User delete(Long id);

	User getByUsernameAndPassword(String username, String password);

	User verifyOtp(UserDTO userDTO) throws OTPNotVerifiedException;

}
