package com.my.sample.data;

import java.io.Serializable;

public class DashboardChartDataRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8830136710499140141L;
	private String chartType;
	private String renderChartBy;
	private Integer year;
	private Integer month;

	public String getChartType() {
		return chartType;
	}

	public void setChartType(String chartType) {
		this.chartType = chartType;
	}

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
