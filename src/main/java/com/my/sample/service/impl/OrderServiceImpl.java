package com.my.sample.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.my.sample.converter.OrderConverter;
import com.my.sample.data.OrderData;
import com.my.sample.domain.Order;
import com.my.sample.repository.OrderDetailRepository;
import com.my.sample.repository.OrderRepository;
import com.my.sample.service.OrderService;

@Service("orderService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;
	private final OrderDetailRepository orderDetailRepsitory;

	@Autowired
	public OrderServiceImpl(OrderRepository orderRepository, OrderDetailRepository orderDetailRepsitory) {
		super();

		if (orderRepository == null) {
			throw new IllegalArgumentException("orderRepository cannot be null");
		}
		if (orderDetailRepsitory == null) {
			throw new IllegalArgumentException("orderDetailRepsitory cannot be null");
		}

		this.orderRepository = orderRepository;
		this.orderDetailRepsitory = orderDetailRepsitory;
	}

	@Override
	public Long createOrder(OrderData orderData) {
		Order order = new Order();
		OrderConverter.convert(orderData, order);
		order = orderRepository.save(order);
		return order.getOrderNumber();
	}

}
