package com.my.sample.data;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderReviewData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4799807821511177110L;
	private Long totalOrder;
	private Long totalItemsSold;
	private BigDecimal totalCollection;

	public OrderReviewData() {

	}

	public OrderReviewData(Long totalOrder, BigDecimal totalCollection, Long totalItemsSold) {
		this.totalOrder = totalOrder;
		this.totalCollection = totalCollection;
		this.totalItemsSold = totalItemsSold;
	}
	
	public OrderReviewData(Long totalOrder, BigDecimal totalCollection) {
		this.totalOrder = totalOrder;
		this.totalCollection = totalCollection;
	}

	public Long getTotalOrder() {
		return totalOrder;
	}

	public void setTotalOrder(Long totalOrder) {
		this.totalOrder = totalOrder;
	}

	public Long getTotalItemsSold() {
		return totalItemsSold;
	}

	public void setTotalItemsSold(Long totalItemsSold) {
		this.totalItemsSold = totalItemsSold;
	}

	public BigDecimal getTotalCollection() {
		return totalCollection;
	}

	public void setTotalCollection(BigDecimal totalCollection) {
		this.totalCollection = totalCollection;
	}

}
