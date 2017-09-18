package com.my.sample.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DashboardChartDataResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6895105813487850876L;
	List<GraphData> data1 = new ArrayList<GraphData>();
	List<GraphData> data2 = new ArrayList<GraphData>();

	public List<GraphData> getData1() {
		return data1;
	}

	public void setData1(List<GraphData> data1) {
		this.data1 = data1;
	}

	public List<GraphData> getData2() {
		return data2;
	}

	public void setData2(List<GraphData> data2) {
		this.data2 = data2;
	}

}
