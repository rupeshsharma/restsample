package com.my.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.sample.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
