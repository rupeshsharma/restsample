package com.my.sample.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.my.sample.config.DefaultDateTimeSerializer;

public class OrderDetailData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5093761302892231689L;

	private Long id;

	private ItemData item;

	private Long quantity;

	private BigDecimal unitPrice;

	@JsonSerialize(using = DefaultDateTimeSerializer.class)
	private Date createdDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ItemData getItem() {
		return item;
	}

	public void setItem(ItemData item) {
		this.item = item;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
}
