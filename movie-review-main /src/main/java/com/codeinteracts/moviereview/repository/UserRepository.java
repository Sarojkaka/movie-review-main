package com.codeinteracts.moviereview.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeinteracts.moviereview.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByUsernameAndOTP(String username, String otp);
	
	public User findByUsernameAndPassword(String username, String password);

	public User findByUsername(String username);

}
