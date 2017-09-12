package com.my.sample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.my.sample.domain.OrderDomain;

public interface OrderRepository extends JpaRepository<OrderDomain, Long> {

	@Query("SELECT MAX(o.orderNumber) from OrderDomain o where o.orderDate = :orderDate")
	Long findMaxOrderNumberForDate(@Param("orderDate") String orderDate);

	@Query("SELECT New OrderDomain(o.id, o.orderNumber, o.orderDate, o.createdDate, o.paymentType, o.diningMode, o.amount, o.customer) from OrderDomain o where o.orderDate = :orderDate")
	List<OrderDomain> getOrderForCurrentDate(@Param("orderDate") String orderDate);

}
