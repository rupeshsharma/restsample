package com.my.sample.data;

import java.io.Serializable;

public class YearlySalesChartData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -648545290025744759L;
	String year;
	Long data;

	public YearlySalesChartData() {

	}

	public YearlySalesChartData(String year, Long data) {
		this.year = year;
		this.data = data;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Long getData() {
		return data;
	}

	public void setData(Long data) {
		this.data = data;
	}
}
