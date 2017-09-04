package com.my.sample.converter;

import java.util.ArrayList;
import java.util.List;

import com.my.sample.data.OrderData;
import com.my.sample.data.OrderDetailData;
import com.my.sample.domain.Customer;
import com.my.sample.domain.Order;
import com.my.sample.domain.OrderDetail;
import com.my.sample.enums.OrderStatus;

public class OrderConverter {

	public static void reverse(OrderData source, Order target) {
		target.setAmount(source.getAmount());
		target.setOrderDate(source.getOrderDate());
		target.setStatus(OrderStatus.PROCESSING);
		target.setCustomer(new Customer(source.getCustomer().getId()));
		List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
		OrderDetail orderDetail = null;
		for (OrderDetailData orderDetailData : source.getOrderDetail()) {
			orderDetail = new OrderDetail();
			OrderDetailConverter.reverse(orderDetailData, orderDetail);
			orderDetailList.add(orderDetail);
		}
	}

	public static void convert(Order source, OrderData target) {
		target.setId(source.getId());
		target.setAmount(source.getAmount());
		target.setOrderNumber(source.getOrderNumber());
		target.setStatus(source.getStatus());
		List<OrderDetailData> orderDetailDataList = new ArrayList<OrderDetailData>();
		OrderDetailData orderDetailData = null;
		for(OrderDetail orderDetail : source.getOrderDetail()){
			orderDetailData = new OrderDetailData();
			OrderDetailConverter.convert(orderDetail,orderDetailData);
			orderDetailDataList.add(orderDetailData);
		}
		target.setOrderDetail(orderDetailDataList);
	}

}
