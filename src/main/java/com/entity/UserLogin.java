package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.springframework.stereotype.Component;

@Entity
@Component
public class UserLogin {
	 @Column(name = "username")
	   private String username;

	 public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "password")
	  private String password;

	public UserLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

}
