package com.my.sample.service.impl;

import java.math.BigDecimal;
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
		order.setOrderNumber(findMaxOrderNumberForDate(new Date()) + 1l);
		order = orderRepository.save(order);
		return order.getOrderNumber();
	}

	private Long findMaxOrderNumberForDate(Date orderDate) {
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
		List<OrderDomain> orderList = orderRepository.getOrderForCurrentDate(new Date());
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
	public OrderReviewData getOrderReviewDataForDate(Date orderDate) {
		OrderReviewData orderReviewData = orderRepository.getOrderReviewData(orderDate);
		if (orderReviewData.getTotalOrder() != null) {
			orderReviewData.setTotalItemsSold(orderDetailRepsitory.getTotalItemSoldForDate(orderDate));
		}
		return orderReviewData;
	}

	@Override
	public List<OrderData> searchOrderHistoryInRange(Date fromOrderDate, Date toOrderDate) {
		List<OrderData> orderDataList = new ArrayList<OrderData>();
		List<OrderDomain> orderList = orderRepository.searchOrderHistoryInRange(fromOrderDate, toOrderDate);
		OrderData orderData = null;
		for (OrderDomain order : orderList) {
			orderData = new OrderData();
			OrderConverter.convert(order, orderData, Boolean.FALSE);
			orderDataList.add(orderData);
		}
		return orderDataList;
	}

	@Override
	public Long getTotalOrderInRange(Date fromOrderDate, Date toOrderDate) {
		return orderRepository.getTotalOrderInRange(fromOrderDate,toOrderDate);
	}

	@Override
	public Long getTotalItemInRange(Date fromOrderDate, Date toOrderDate) {
		return orderDetailRepsitory.getTotalItemInRange(fromOrderDate, toOrderDate);
	}
	
	@Override
	public BigDecimal getTotalCollectionInRange(Date fromOrderDate, Date toOrderDate){
		return orderRepository.getTotalCollectionInRange(fromOrderDate,toOrderDate);
	}

}
