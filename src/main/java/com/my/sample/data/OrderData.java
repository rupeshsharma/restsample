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

	private String orderDate;
	
	private Date createdDate;

	private String paymentType;

	private String diningMode;

	private UserData createdBy;

	private String status;

	private Integer discount;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getDiningMode() {
		return diningMode;
	}

	public void setDiningMode(String diningMode) {
		this.diningMode = diningMode;
	}

	public UserData getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UserData createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
