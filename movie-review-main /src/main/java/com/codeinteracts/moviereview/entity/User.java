package com.codeinteracts.moviereview.entity;

import java.io.Serializable;
import java.util.Set;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="users")

@NamedQueries({
	@NamedQuery(name = "User.findByUsernameAndOTP", 
			query = "SELECT u FROM User u WHERE u.username = ?1 and u.otp= ?2"),
		@NamedQuery(name = "User.findByUsernameAndPassword", 
		query = "SELECT u FROM User u WHERE u.username = ?1 and u.password= ?2"),
		@NamedQuery(name = "User.findByUsername", 
		query = "SELECT u FROM User u WHERE u.username = ?1")
})

public class User implements Serializable {

	private static final long serialVersionUID = -8465435978921775452L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String firstName;
	
	private String lastName;

	private String username;

	private String email;

	private String password;
	
	private Boolean otpVerified;
	
	private String role;
	
//	@Getter
//	@Setter
	private String otp;

	@ColumnDefault("true")
	private Boolean active;
	
	@OneToMany(targetEntity=MovieReview.class, mappedBy="reviewer", cascade=CascadeType.ALL, fetch = FetchType.LAZY) 
    private Set<MovieReview> reviews;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<MovieReview> getReviews() {
		return reviews;
	}

	public void setReviews(Set<MovieReview> reviews) {
		this.reviews = reviews;
	}

	public Boolean getOtpVerified() {
		return otpVerified;
	}

	public void setOtpVerified(Boolean otpVerified) {
		this.otpVerified = otpVerified;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
