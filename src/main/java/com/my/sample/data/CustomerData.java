package com.my.sample.data;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.my.sample.config.DefaultDateTimeSerializer;

public class CustomerData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3945630667248859857L;

	private Long id;

	private String name;

	private String mobile;
	
	private Date lastVisited;

	private String email;

	public CustomerData() {
	}

	public CustomerData(Long id, String name, String mobile) {
		this.id = id;
		this.name = name;
		this.mobile = mobile;
	}

	@JsonSerialize(using = DefaultDateTimeSerializer.class)
	private Date createdDate;
	@JsonSerialize(using = DefaultDateTimeSerializer.class)
	private Date modifiedDate;

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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Date getLastVisited() {
		return lastVisited;
	}

	public void setLastVisited(Date lastVisited) {
		this.lastVisited = lastVisited;
	}

}
