package com.my.sample.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.my.sample.data.DashboardChartDataRequest;
import com.my.sample.data.DashboardChartDataResponse;
import com.my.sample.data.GraphData;
import com.my.sample.data.ItemGraphDataRequest;

public interface ChartService {
	List<GraphData> getWholeItemGraphData();

	List<GraphData> getWholeItemGraphDataInRange(Date fromDate, Date toDate);

	DashboardChartDataResponse getDashboardGraphData(DashboardChartDataRequest dashboardChartDataRequest) throws ParseException;

	List<GraphData> getPerItemGraphData(ItemGraphDataRequest itemGraphDataRequest, Long id) throws ParseException;

}
