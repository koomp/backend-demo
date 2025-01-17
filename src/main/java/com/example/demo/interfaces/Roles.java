package com.example.demo.interfaces;

import org.springframework.security.core.GrantedAuthority;

public enum Roles implements GrantedAuthority {
	USER, ADMIN;

	@Override
	public String getAuthority() {
		return this.toString();
	}
}
