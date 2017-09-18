package com.my.sample.data;

import java.math.BigDecimal;

public class YearlyRevenueChartData {
	String year;
	BigDecimal data;

	public YearlyRevenueChartData() {

	}

	public YearlyRevenueChartData(String year, BigDecimal data) {
		this.year = year;
		this.data = data;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public BigDecimal getData() {
		return data;
	}

	public void setData(BigDecimal data) {
		this.data = data;
	}
}
