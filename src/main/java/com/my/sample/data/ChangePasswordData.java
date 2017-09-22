package com.my.sample.data;

import java.io.Serializable;

public class ChangePasswordData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8686085957616384264L;
	
	private Long id;
	private String password;
	
	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
