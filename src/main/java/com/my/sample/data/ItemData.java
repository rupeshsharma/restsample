package com.my.sample.data;

import java.io.Serializable;
import java.math.BigDecimal;

public class ItemData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7410234462501987005L;

	private Long id;

	private String title;

	private String description;

	private BigDecimal price;

	private Long category;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Long getCategory() {
		return category;
	}

	public void setCategory(Long category) {
		this.category = category;
	}
	
}
