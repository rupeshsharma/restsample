package com.my.sample.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.my.sample.util.DefaultDateSerializer;
import com.my.sample.util.DefaultDateTimeSerializer;

public class OrderData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5913754722411604140L;

	private Long id;

	private Long orderNumber;

	@JsonSerialize(using = DefaultDateSerializer.class)
	private Date orderDate;

	@JsonSerialize(using = DefaultDateTimeSerializer.class)
	private Date createdDate;

	private String paymentType;

	private String diningMode;

	private UserData createdBy;

	private String status;

	private Integer discount;

	private BigDecimal grandTotal;

	private BigDecimal cgst;
	
	private BigDecimal sgst;

	private BigDecimal total;

	private BigDecimal afterDiscountTotal;

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

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public BigDecimal getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getAfterDiscountTotal() {
		return afterDiscountTotal;
	}

	public void setAfterDiscountTotal(BigDecimal afterDiscountTotal) {
		this.afterDiscountTotal = afterDiscountTotal;
	}

	public BigDecimal getCgst() {
		return cgst;
	}

	public void setCgst(BigDecimal cgst) {
		this.cgst = cgst;
	}

	public BigDecimal getSgst() {
		return sgst;
	}

	public void setSgst(BigDecimal sgst) {
		this.sgst = sgst;
	}

}
