package com.my.sample.data;

import java.io.Serializable;

public class ItemGraphDataRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6025928591377885397L;
	private String renderChartBy;
	private Integer year;
	private Integer month;

	public String getRenderChartBy() {
		return renderChartBy;
	}

	public void setRenderChartBy(String renderChartBy) {
		this.renderChartBy = renderChartBy;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}
}
