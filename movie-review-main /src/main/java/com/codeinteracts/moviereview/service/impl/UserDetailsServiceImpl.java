package com.codeinteracts.moviereview.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.codeinteracts.moviereview.config.UserDetailInfo;
import com.codeinteracts.moviereview.entity.User;
import com.codeinteracts.moviereview.repository.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException(username);
		}

		UserDetailInfo userDetailInfo = new UserDetailInfo(user);
		return userDetailInfo;

	}

}
