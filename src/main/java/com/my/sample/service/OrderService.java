package com.my.sample.service;

import java.util.List;

import com.my.sample.data.OrderData;

public interface OrderService {

	Long createOrder(OrderData orderData);

	List<OrderData> getOrder();

}
