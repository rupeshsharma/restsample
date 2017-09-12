package com.my.sample.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.my.sample.converter.OrderConverter;
import com.my.sample.data.OrderData;
import com.my.sample.data.OrderReviewData;
import com.my.sample.domain.OrderDomain;
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
		OrderDomain order = new OrderDomain();
		OrderConverter.reverse(orderData, order);
		SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy");
		order.setOrderNumber(findMaxOrderNumberForDate(sm.format(new Date())) + 1l);
		order = orderRepository.save(order);
		return order.getOrderNumber();
	}

	private Long findMaxOrderNumberForDate(String orderDate) {
		Long orderNumber = orderRepository.findMaxOrderNumberForDate(orderDate);
		if (orderNumber == null) {
			orderNumber = 0l;
		}
		return orderNumber;
	}

	@Override
	public List<OrderData> getOrder() {
		List<OrderData> orderDataList = new ArrayList<OrderData>();
		List<OrderDomain> orderList = orderRepository.findAll();
		OrderData orderData = null;
		for (OrderDomain order : orderList) {
			orderData = new OrderData();
			OrderConverter.convert(order, orderData, Boolean.FALSE);
			orderDataList.add(orderData);
		}
		return orderDataList;
	}

	@Override
	public List<OrderData> getOrderForCurrentDate() {
		List<OrderData> orderDataList = new ArrayList<OrderData>();
		SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy");
		List<OrderDomain> orderList = orderRepository.getOrderForCurrentDate(sm.format(new Date()));
		OrderData orderData = null;
		for (OrderDomain order : orderList) {
			orderData = new OrderData();
			OrderConverter.convert(order, orderData, Boolean.FALSE);
			orderDataList.add(orderData);
		}
		return orderDataList;
	}

	@Override
	public OrderData getOrderDetailById(Long id) {
		OrderDomain order = orderRepository.findOne(id);
		OrderData orderData = new OrderData();
		OrderConverter.convert(order, orderData, Boolean.TRUE);
		return orderData;
	}

	@Override
	public OrderReviewData getOrderReviewDataForDate(String orderDate) {
		return orderRepository.getOrderReviewData(orderDate);
	}

}
