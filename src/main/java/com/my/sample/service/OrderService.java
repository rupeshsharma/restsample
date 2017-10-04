package com.my.sample.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.my.sample.data.OrderData;
import com.my.sample.data.OrderReviewData;
import com.my.sample.data.YearlyRevenueChartData;
import com.my.sample.data.YearlySalesChartData;
import com.my.sample.data.DailyRevenueChartData;
import com.my.sample.data.DailySalesChartData;
import com.my.sample.data.MonthlyRevenueChartData;
import com.my.sample.data.MonthlySalesChartData;

public interface OrderService {

	Long createOrder(OrderData orderData);

	List<OrderData> getOrder();

	List<OrderData> getOrderForCurrentDate();

	OrderData getOrderDetailById(Long id);

	OrderReviewData getOrderReviewDataForDate(Date orderDate);

	List<OrderData> searchOrderHistoryInRange(Date fromOrderDate, Date toOrderDate);

	Long getTotalOrderInRange(Date fromOrderDate, Date toOrderDate);

	Long getTotalItemInRange(Date fromOrderDate, Date toOrderDate);

	BigDecimal getTotalCollectionInRange(Date fromOrderDate, Date toOrderDate);

	List<OrderData> getOrderHistoryForCustomer(Long id);

	Long getTotalItemSoldById(Long id);

	Long getTotalItemSoldByIdInRange(Long id, Date fromDate, Date toDate);

	List<DailySalesChartData> getDailyChartOrderDataInRange(Date fromDate, Date toDate);

	List<DailySalesChartData> getDailyChartItemDataInRange(Date fromDate, Date toDate);

	List<DailyRevenueChartData> getDailyChartCollectionDataInRange(Date fromDate, Date toDate);

	List<MonthlySalesChartData> getMonthlyChartOrderDataInRange(Date fromDate, Date toDate);

	List<MonthlySalesChartData> getMonthlyChartItemDataInRange(Date fromDate, Date toDate);

	List<MonthlyRevenueChartData> getMonthlyChartCollectionDataInRange(Date fromDate, Date toDate);

	List<YearlySalesChartData> getYearlyChartOrderDataInRange();

	List<YearlySalesChartData> getYearlyChartItemDataInRange();

	List<YearlyRevenueChartData> getYearlyChartCollectionDataInRange();

	List<DailySalesChartData> getDailyChartPerItemDataInRange(Date fromDate, Date toDate, Long id);

	List<YearlySalesChartData> getYearlyChartPerItemDataInRange(Long id);

	List<MonthlySalesChartData> getMonthlyChartPerItemDataInRange(Date fromDate, Date toDate, Long id);
	
	List<Long> getOrderByStatusForCustomer();
	
	List<OrderData> getOrderByStatusForStaff();
	
	void setServeStatusForOrder(Long id);
	
	void setCompleteStatusForOrder(Long id);

}
