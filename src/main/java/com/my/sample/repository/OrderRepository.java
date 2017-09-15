package com.my.sample.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.my.sample.data.OrderReviewData;
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

}
