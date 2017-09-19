package com.my.sample.data;

import java.io.Serializable;

public class ChangePasswordData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8686085957616384264L;
	String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
