package com.my.sample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.my.sample.data.OrderReviewData;
import com.my.sample.domain.OrderDomain;

public interface OrderRepository extends JpaRepository<OrderDomain, Long> {

	@Query("SELECT MAX(o.orderNumber) from OrderDomain o where o.orderDate = :orderDate")
	Long findMaxOrderNumberForDate(@Param("orderDate") String orderDate);
	
	@Query("SELECT o from OrderDomain o where o.id = :id")
	OrderDomain findOne(@Param("id") String id);

	@Query("SELECT New OrderDomain(o.id, o.orderNumber, o.orderDate, o.createdDate, o.paymentType, o.diningMode, o.amount, o.customer) from OrderDomain o where o.orderDate = :orderDate")
	List<OrderDomain> getOrderForCurrentDate(@Param("orderDate") String orderDate);

	@Query("SELECT New com.my.sample.data.OrderReviewData(MAX(o.orderNumber), SUM(o.amount), SUM(od.quantity)) from OrderDomain o join o.orderDetail od where o.orderDate = :orderDate")
	OrderReviewData getOrderReviewData(@Param("orderDate") String orderDate);

}
