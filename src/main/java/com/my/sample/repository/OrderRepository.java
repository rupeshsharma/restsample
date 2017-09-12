package com.my.sample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.my.sample.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query("SELECT MAX(o.orderNumber) from Order o where o.orderDate = :orderDate")
	Long findMaxOrderNumberForDate(@Param("orderDate") String orderDate);
	
	@Query("SELECT o from Order o where o.orderDate = :orderDate")
	List<Order> getOrderForCurrentDate(@Param("orderDate") String orderDate);

}
