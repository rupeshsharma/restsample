package com.my.sample.data;

import java.io.Serializable;
import java.math.BigDecimal;

public class RevenueGraphData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -623307834525838729L;
	BigDecimal y;
	String label;

	public BigDecimal getY() {
		return y;
	}

	public void setY(BigDecimal y) {
		this.y = y;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
