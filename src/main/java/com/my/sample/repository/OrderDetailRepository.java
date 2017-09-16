package com.my.sample.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.my.sample.domain.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

	@Query("SELECT SUM(od.quantity) from OrderDetail od where od.createdDate = :orderDate")
	Long getTotalItemSoldForDate(@Param("orderDate") Date orderDate);

	@Query("SELECT SUM(od.quantity) from OrderDetail od where od.createdDate BETWEEN :fromOrderDate AND :toOrderDate")
	Long getTotalItemInRange(@Param("fromOrderDate") Date fromOrderDate, @Param("toOrderDate") Date toOrderDate);

	@Query("SELECT SUM(od.quantity) from OrderDetail od where od.item.id = :id")
	Long getTotalItemSoldById(@Param("id") Long id);

	@Query("SELECT SUM(od.quantity) from OrderDetail od where od.item.id = :id AND od.createdDate BETWEEN :fromOrderDate AND :toOrderDate")
	Long getTotalItemSoldByIdInRange(@Param("id") Long id, @Param("fromOrderDate") Date fromOrderDate,
			@Param("toOrderDate") Date toOrderDate);

}
