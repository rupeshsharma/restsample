package com.my.sample.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5913754722411604140L;

	private Long id;

	private Long orderNumber;

	private Date createdDate;

	private String status;

	private BigDecimal amount;

	private CustomerData customer;

	private List<OrderDetailData> orderDetail;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Long orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public CustomerData getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerData customer) {
		this.customer = customer;
	}

	public List<OrderDetailData> getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(List<OrderDetailData> orderDetail) {
		this.orderDetail = orderDetail;
	}

}
