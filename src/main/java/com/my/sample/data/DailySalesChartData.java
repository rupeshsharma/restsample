package com.my.sample.data;

import java.io.Serializable;
import java.util.Date;

public class DailySalesChartData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8890480066928774337L;
	Date date;
	Long data;

	public DailySalesChartData(Date date, Long data) {
		this.data = data;
		this.date = date;
	}

	public DailySalesChartData() {

	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getData() {
		return data;
	}

	public void setData(Long data) {
		this.data = data;
	}
}
