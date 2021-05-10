package com.cg.fms.jwt;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private final String token;
 private String role;
	public JwtResponse(String token,String role) {
		this.token = token;
		this.role=role;
	}

	public String getToken() {
		return this.token;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}