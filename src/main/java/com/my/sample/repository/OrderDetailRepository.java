package com.my.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.sample.domain.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

}
