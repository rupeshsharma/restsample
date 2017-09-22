package com.my.sample.data;

import java.io.Serializable;

public class UserData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5134437131764628872L;

	private Long id;

	private String userName;

	private String password;

	private String name;

	private String email;

	private String contact;

	private String role;
	
	public UserData(Long id, String userName, String name, String contact, String role){
	    this.id = id;
	    this.userName = userName;
	    this.name = name;
	    this.contact = contact;
	    this.role = role;
	}
	
	public UserData(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
