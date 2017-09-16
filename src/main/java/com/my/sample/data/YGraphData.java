package com.my.sample.data;

import java.io.Serializable;

public class YGraphData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2914802116654185942L;
	Long y;
	String label;

	public Long getY() {
		return y;
	}

	public void setY(Long y) {
		this.y = y;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
}
