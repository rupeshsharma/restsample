package com.my.sample.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.my.sample.data.DailyRevenueChartData;
import com.my.sample.data.DailySalesChartData;
import com.my.sample.data.DashboardChartDataRequest;
import com.my.sample.data.DashboardChartDataResponse;
import com.my.sample.data.GraphData;
import com.my.sample.data.MonthlyRevenueChartData;
import com.my.sample.data.MonthlySalesChartData;
import com.my.sample.data.RevenueGraphData;
import com.my.sample.data.YearlyRevenueChartData;
import com.my.sample.data.YearlySalesChartData;
import com.my.sample.domain.Item;
import com.my.sample.service.ChartService;
import com.my.sample.service.ExpenseService;
import com.my.sample.service.MenuService;
import com.my.sample.service.OrderService;
import com.my.sample.util.AppConstants;

@Service("chartService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class ChartServiceImpl implements ChartService {
	private final MenuService menuService;
	private final OrderService orderService;
	private final ExpenseService expenseService;

	@Autowired
	public ChartServiceImpl(MenuService menuService, OrderService orderService, ExpenseService expenseService) {
		super();

		if (menuService == null) {
			throw new IllegalArgumentException("menuService cannot be null");
		}
		if (orderService == null) {
			throw new IllegalArgumentException("orderService cannot be null");
		}
		if (expenseService == null) {
			throw new IllegalArgumentException("expenseService cannot be null");
		}
		this.menuService = menuService;
		this.orderService = orderService;
		this.expenseService = expenseService;
	}

	@Override
	public List<GraphData> getWholeItemGraphData() {
		List<GraphData> yGraphDataList = new ArrayList<GraphData>();

		List<Item> itemList = menuService.getAllItemDataMin();
		GraphData yGraphData = null;
		for (Item item : itemList) {
			yGraphData = new GraphData();
			yGraphData.setLabel(item.getTitle());
			yGraphData.setY(orderService.getTotalItemSoldById(item.getId()));
			yGraphDataList.add(yGraphData);
		}
		return yGraphDataList;
	}

	@Override
	public List<GraphData> getWholeItemGraphDataInRange(Date fromDate, Date toDate) {
		List<GraphData> yGraphDataList = new ArrayList<GraphData>();

		List<Item> itemList = menuService.getAllItemDataMin();
		GraphData yGraphData = null;
		for (Item item : itemList) {
			yGraphData = new GraphData();
			yGraphData.setLabel(item.getTitle());
			yGraphData.setY(orderService.getTotalItemSoldByIdInRange(item.getId(), fromDate, toDate));
			yGraphDataList.add(yGraphData);
		}
		return yGraphDataList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public DashboardChartDataResponse getDashboardGraphData(DashboardChartDataRequest dashboardChartDataRequest)
			throws ParseException {
		DashboardChartDataResponse dashboardChartDataResponse = new DashboardChartDataResponse();
		@SuppressWarnings("rawtypes")
		List data1 = null;
		@SuppressWarnings("rawtypes")
		List data2 = null;
		Integer year = dashboardChartDataRequest.getYear();
		Integer daysCount = getDaysCount(dashboardChartDataRequest.getMonth());
		Integer month = dashboardChartDataRequest.getMonth();
		Date fromDate = null;
		Date toDate = null;
		if (dashboardChartDataRequest.getMonth() != null) {
			fromDate = new SimpleDateFormat(AppConstants.DATE_FORMAT).parse("1-" + month + "-" + year);
			toDate = new SimpleDateFormat(AppConstants.DATE_FORMAT).parse(daysCount + "-" + month + "-" + year);
		} else {
			fromDate = new SimpleDateFormat(AppConstants.DATE_FORMAT).parse("1-1-" + year);
			toDate = new SimpleDateFormat(AppConstants.DATE_FORMAT).parse("31-12-" + year);
		}

		if (AppConstants.Chart.TYPE_SALES.equals(dashboardChartDataRequest.getChartType())) {
			data1 = new ArrayList<GraphData>();
			data2 = new ArrayList<GraphData>();
			if (AppConstants.Chart.RENDER_BY_DAILY.equals(dashboardChartDataRequest.getRenderChartBy())) {
				List<DailySalesChartData> orderData = orderService.getDailyChartOrderDataInRange(fromDate, toDate);
				GraphData orderGraphData = null;
				for (DailySalesChartData dailyChartData : orderData) {
					orderGraphData = new GraphData();
					orderGraphData
							.setLabel(new SimpleDateFormat(AppConstants.DATE_FORMAT).format(dailyChartData.getDate()));
					orderGraphData.setY(dailyChartData.getData());
					data1.add(orderGraphData);
				}

				List<DailySalesChartData> itemData = orderService.getDailyChartItemDataInRange(fromDate, toDate);
				GraphData itemGraphData = null;
				for (DailySalesChartData dailyChartData : itemData) {
					itemGraphData = new GraphData();
					itemGraphData
							.setLabel(new SimpleDateFormat(AppConstants.DATE_FORMAT).format(dailyChartData.getDate()));
					itemGraphData.setY(dailyChartData.getData());
					data2.add(itemGraphData);
				}

			} else if (AppConstants.Chart.RENDER_BY_MONTHLY.equals(dashboardChartDataRequest.getRenderChartBy())) {
				List<MonthlySalesChartData> orderData = orderService.getMonthlyChartOrderDataInRange(fromDate, toDate);
				GraphData orderGraphData = null;
				for (MonthlySalesChartData monthlyChartData : orderData) {
					orderGraphData = new GraphData();
					orderGraphData.setLabel(monthlyChartData.getMonth());
					orderGraphData.setY(monthlyChartData.getData());
					data1.add(orderGraphData);
				}

				List<MonthlySalesChartData> itemData = orderService.getMonthlyChartItemDataInRange(fromDate, toDate);
				GraphData itemGraphData = null;
				for (MonthlySalesChartData monthlyChartData : itemData) {
					itemGraphData = new GraphData();
					itemGraphData.setLabel(monthlyChartData.getMonth());
					itemGraphData.setY(monthlyChartData.getData());
					data2.add(itemGraphData);
				}
			} else {
				List<YearlySalesChartData> orderData = orderService.getYearlyChartOrderDataInRange();
				GraphData orderGraphData = null;
				for (YearlySalesChartData yearlyChartData : orderData) {
					orderGraphData = new GraphData();
					orderGraphData.setLabel(yearlyChartData.getYear());
					orderGraphData.setY(yearlyChartData.getData());
					data1.add(orderGraphData);
				}

				List<YearlySalesChartData> itemData = orderService.getYearlyChartItemDataInRange();
				GraphData itemGraphData = null;
				for (YearlySalesChartData yearlyChartData : itemData) {
					itemGraphData = new GraphData();
					itemGraphData.setLabel(yearlyChartData.getYear());
					itemGraphData.setY(yearlyChartData.getData());
					data2.add(itemGraphData);
				}
			}
		} else {
			data1 = new ArrayList<RevenueGraphData>();
			data2 = new ArrayList<RevenueGraphData>();
			if (AppConstants.Chart.RENDER_BY_DAILY.equals(dashboardChartDataRequest.getRenderChartBy())) {
				List<DailyRevenueChartData> collectionData = orderService.getDailyChartCollectionDataInRange(fromDate,
						toDate);
				RevenueGraphData collectionGraphData = null;
				for (DailyRevenueChartData dailyChartData : collectionData) {
					collectionGraphData = new RevenueGraphData();
					collectionGraphData
							.setLabel(new SimpleDateFormat(AppConstants.DATE_FORMAT).format(dailyChartData.getDate()));
					collectionGraphData.setY(dailyChartData.getData());
					data1.add(collectionGraphData);
				}

				List<DailyRevenueChartData> expenseData = expenseService.getDailyChartExpenseDataInRange(fromDate,
						toDate);
				RevenueGraphData expenseGraphData = null;
				for (DailyRevenueChartData dailyChartData : expenseData) {
					expenseGraphData = new RevenueGraphData();
					expenseGraphData
							.setLabel(new SimpleDateFormat(AppConstants.DATE_FORMAT).format(dailyChartData.getDate()));
					expenseGraphData.setY(dailyChartData.getData());
					data2.add(expenseGraphData);
				}
			} else if (AppConstants.Chart.RENDER_BY_MONTHLY.equals(dashboardChartDataRequest.getRenderChartBy())) {
				List<MonthlyRevenueChartData> collectionData = orderService
						.getMonthlyChartCollectionDataInRange(fromDate, toDate);
				RevenueGraphData collectionGraphData = null;
				for (MonthlyRevenueChartData monthlyChartData : collectionData) {
					collectionGraphData = new RevenueGraphData();
					collectionGraphData.setLabel(monthlyChartData.getMonth());
					collectionGraphData.setY(monthlyChartData.getData());
					data1.add(collectionGraphData);
				}

				List<MonthlyRevenueChartData> expenseData = expenseService.getMonthlyChartExpenseDataInRange(fromDate,
						toDate);
				RevenueGraphData expenseGraphData = null;
				for (MonthlyRevenueChartData monthlyChartData : expenseData) {
					expenseGraphData = new RevenueGraphData();
					expenseGraphData.setLabel(monthlyChartData.getMonth());
					expenseGraphData.setY(monthlyChartData.getData());
					data2.add(expenseGraphData);
				}
			} else {
				List<YearlyRevenueChartData> collectionData = orderService.getYearlyChartCollectionDataInRange();
				RevenueGraphData collectionGraphData = null;
				for (YearlyRevenueChartData yearlyChartData : collectionData) {
					collectionGraphData = new RevenueGraphData();
					collectionGraphData.setLabel(yearlyChartData.getYear());
					collectionGraphData.setY(yearlyChartData.getData());
					data1.add(collectionGraphData);
				}

				List<YearlyRevenueChartData> expenseData = expenseService.getYearlyChartExpenseDataInRange();
				RevenueGraphData expenseGraphData = null;
				for (YearlyRevenueChartData yearlyChartData : expenseData) {
					expenseGraphData = new RevenueGraphData();
					expenseGraphData.setLabel(yearlyChartData.getYear());
					expenseGraphData.setY(yearlyChartData.getData());
					data2.add(expenseGraphData);
				}
			}
		}

		dashboardChartDataResponse.setData1(data1);
		dashboardChartDataResponse.setData2(data2);
		return dashboardChartDataResponse;
	}

	private Integer getDaysCount(Integer month) {
		if (month == null) {
			return 0;
		}
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			return 31;
		} else if (month == 4 || month == 6 || month == 9 || month == 13) {
			return 30;
		} else {
			return 28;
		}
	}

}
