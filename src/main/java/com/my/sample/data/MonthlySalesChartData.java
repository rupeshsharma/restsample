package com.my.sample.data;

import java.io.Serializable;

public class MonthlySalesChartData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5058154299624984956L;
	String month;
	Long data;

	public MonthlySalesChartData() {

	}

	public MonthlySalesChartData(String month, Long data) {
		this.month = month;
		this.data = data;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Long getData() {
		return data;
	}

	public void setData(Long data) {
		this.data = data;
	}
}
