package com.example.demo.jwt;

import java.io.Serializable;

public class JwtResponseToken implements Serializable {
	private static final long serialVersionUID = -1939575958434254192L;

	private String token;

	public JwtResponseToken(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
