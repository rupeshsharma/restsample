package com.my.sample.data;

import java.io.Serializable;
import java.math.BigDecimal;

public class MonthlyRevenueChartData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7981985070449002926L;
	String month;
	BigDecimal data;

	public MonthlyRevenueChartData() {

	}

	public MonthlyRevenueChartData(String month, BigDecimal data) {
		this.month = month;
		this.data = data;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public BigDecimal getData() {
		return data;
	}

	public void setData(BigDecimal data) {
		this.data = data;
	}

}
