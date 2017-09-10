package com.my.sample.data;

import java.io.Serializable;

public class UserData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 172627438244844172L;
	private Long id;
	private String name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
