package com.StudentMGMT.entities;

import java.io.Serializable;
import java.util.UUID;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private UUID id;
	private String login;
	private String email;
	private String password;
    private String role;  // New field for role
	
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
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
	public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}
