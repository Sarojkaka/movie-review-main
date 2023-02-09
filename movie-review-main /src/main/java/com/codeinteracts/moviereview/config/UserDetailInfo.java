package com.codeinteracts.moviereview.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.codeinteracts.moviereview.entity.User;

public class UserDetailInfo implements UserDetails {
	
	private static final long serialVersionUID = 8707880180797755921L;
	
	private User user;
	
	public UserDetailInfo(User user) {
		this.user = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
//		List<GrantedAuthority> authorities = new ArrayList<>();
//		String[] roles = user.getRole().split(",");
//		for(String role: roles) {
//			authorities.add(new SimpleGrantedAuthority(role));
//		}
//		return authorities;
		
		
		
		List<GrantedAuthority> authorities = Arrays.stream(user.getRole().split(",")).map(SimpleGrantedAuthority:: new).collect(Collectors.toList());
		
		System.out.println(authorities);
		return authorities;
		
		
		
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
