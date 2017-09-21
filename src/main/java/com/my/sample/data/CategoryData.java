package com.my.sample.data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.my.sample.util.DefaultDateTimeSerializer;

public class CategoryData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2606525992694526442L;
	private Long id;
	private String title;
	@JsonSerialize(using = DefaultDateTimeSerializer.class)
	private Date createdDate;
	@JsonSerialize(using = DefaultDateTimeSerializer.class)
	private Date modifiedDate;
	private Set<ItemData> items = new HashSet<ItemData>();

	public CategoryData() {

	}

	public CategoryData(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<ItemData> getItems() {
		return items;
	}

	public void setItems(Set<ItemData> items) {
		this.items = items;
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

}
