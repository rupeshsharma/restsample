package com.my.sample.service;

import java.util.List;

import com.my.sample.data.OrderData;
import com.my.sample.data.OrderReviewData;

public interface OrderService {

	Long createOrder(OrderData orderData);

	List<OrderData> getOrder();

	List<OrderData> getOrderForCurrentDate();

	OrderData getOrderDetailById(Long id);

	OrderReviewData getOrderReviewDataForDate(String orderDate);

}
