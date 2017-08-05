package com.my.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.my.sample.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query("SELECT MAX(o.orderNumber) from Order o where o.orderDate = :orderDate")
	Long findMaxOrderNumberForDate(@Param("orderDate") String orderDate);

}
