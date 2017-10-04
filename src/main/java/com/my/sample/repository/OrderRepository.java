package com.my.sample.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.my.sample.data.OrderReviewData;
import com.my.sample.data.YearlyRevenueChartData;
import com.my.sample.data.YearlySalesChartData;
import com.my.sample.data.DailyRevenueChartData;
import com.my.sample.data.DailySalesChartData;
import com.my.sample.data.MonthlyRevenueChartData;
import com.my.sample.data.MonthlySalesChartData;
import com.my.sample.domain.OrderDomain;

public interface OrderRepository extends JpaRepository<OrderDomain, Long> {

	@Query("SELECT MAX(o.orderNumber) from OrderDomain o where o.orderDate = :orderDate")
	Long findMaxOrderNumberForDate(@Param("orderDate") Date orderDate);

	@Query("SELECT o from OrderDomain o where o.id = :id")
	OrderDomain findOne(@Param("id") String id);

	@Query("SELECT New OrderDomain(o.id, o.orderNumber, o.orderDate, o.createdDate, o.paymentType, o.diningMode, o.grandTotal, o.customer) from OrderDomain o where o.orderDate = :orderDate")
	List<OrderDomain> getOrderForCurrentDate(@Param("orderDate") Date orderDate);

	@Query("SELECT New com.my.sample.data.OrderReviewData(COUNT(o), SUM(o.grandTotal)) from OrderDomain o where o.orderDate = :orderDate")
	OrderReviewData getOrderReviewData(@Param("orderDate") Date orderDate);

	@Query("SELECT New OrderDomain(o.id, o.orderNumber, o.orderDate, o.createdDate, o.paymentType, o.diningMode, o.grandTotal, o.customer) from OrderDomain o where o.orderDate BETWEEN :fromOrderDate AND :toOrderDate")
	List<OrderDomain> searchOrderHistoryInRange(@Param("fromOrderDate") Date fromOrderDate,
			@Param("toOrderDate") Date toOrderDate);

	@Query("SELECT COUNT(o) from OrderDomain o where o.orderDate BETWEEN :fromOrderDate AND :toOrderDate")
	Long getTotalOrderInRange(@Param("fromOrderDate") Date fromOrderDate, @Param("toOrderDate") Date toOrderDate);

	@Query("SELECT SUM(o.grandTotal) from OrderDomain o where o.orderDate BETWEEN :fromOrderDate AND :toOrderDate")
	BigDecimal getTotalCollectionInRange(@Param("fromOrderDate") Date fromOrderDate,
			@Param("toOrderDate") Date toOrderDate);

	@Query("SELECT New OrderDomain(o.id, o.orderNumber, o.orderDate, o.createdDate, o.paymentType, o.diningMode, o.grandTotal, o.customer) from OrderDomain o where o.customer.id = :id")
	List<OrderDomain> getOrderHistoryForCustomer(@Param("id") Long id);

	@Query("SELECT New com.my.sample.data.DailySalesChartData(o.orderDate, COUNT(o)) from OrderDomain o where o.orderDate BETWEEN :fromOrderDate AND :toOrderDate group by o.orderDate")
	List<DailySalesChartData> getDailyChartOrderDataInRange(@Param("fromOrderDate") Date fromOrderDate,
			@Param("toOrderDate") Date toOrderDate);

	@Query("SELECT New com.my.sample.data.DailyRevenueChartData(o.orderDate, SUM(o.grandTotal)) from OrderDomain o where o.orderDate BETWEEN :fromOrderDate AND :toOrderDate group by o.orderDate")
	List<DailyRevenueChartData> getDailyChartCollectionDataInRange(@Param("fromOrderDate") Date fromOrderDate,
			@Param("toOrderDate") Date toOrderDate);

	@Query("SELECT New com.my.sample.data.MonthlySalesChartData(DATE_FORMAT(o.orderDate,'%M'), COUNT(o)) from OrderDomain o where o.orderDate BETWEEN :fromOrderDate AND :toOrderDate group by DATE_FORMAT(o.orderDate,'%M')")
	List<MonthlySalesChartData> getMonthlyChartOrderDataInRange(@Param("fromOrderDate") Date fromOrderDate,
			@Param("toOrderDate") Date toOrderDate);

	@Query("SELECT New com.my.sample.data.MonthlyRevenueChartData(DATE_FORMAT(o.orderDate,'%M'), SUM(o.grandTotal)) from OrderDomain o where o.orderDate BETWEEN :fromOrderDate AND :toOrderDate group by DATE_FORMAT(o.orderDate,'%M')")
	List<MonthlyRevenueChartData> getMonthlyChartCollectionDataInRange(@Param("fromOrderDate") Date fromOrderDate,
			@Param("toOrderDate") Date toOrderDate);

	@Query("SELECT New com.my.sample.data.YearlySalesChartData(DATE_FORMAT(o.orderDate,'%Y'), COUNT(o)) from OrderDomain o group by DATE_FORMAT(o.orderDate,'%Y')")
	List<YearlySalesChartData> getYearlyChartOrderDataInRange();

	@Query("SELECT New com.my.sample.data.YearlyRevenueChartData(DATE_FORMAT(o.orderDate,'%Y'), SUM(o.grandTotal)) from OrderDomain o group by DATE_FORMAT(o.orderDate,'%Y')")
	List<YearlyRevenueChartData> getYearlyChartCollectionDataInRange();
	
	@Query("SELECT o.orderNumber from OrderDomain o where o.status LIKE '%Serving%'")
	List<Long> getOrderByStatusForCustomer();
	
	@Query("SELECT o from OrderDomain o where o.status NOT LIKE '%Completed%'")
	List<OrderDomain> getOrderByStatusForStaff();

}
