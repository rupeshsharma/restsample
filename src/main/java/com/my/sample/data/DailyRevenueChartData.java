package com.my.sample.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DailyRevenueChartData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1031276614257538252L;
	Date date;
	BigDecimal data;

	public DailyRevenueChartData(Date date, BigDecimal data) {
		this.data = data;
		this.date = date;
	}

	public DailyRevenueChartData() {

	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getData() {
		return data;
	}

	public void setData(BigDecimal data) {
		this.data = data;
	}
}
