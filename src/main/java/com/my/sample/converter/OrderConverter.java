package com.my.sample.converter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
		SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy");
		target.setOrderDate(sm.format(new Date()));
		target.setStatus(OrderStatus.PROCESSING.getValue());
		target.setCustomer(new Customer(source.getCustomer().getId()));
		target.setDiningMode(source.getDiningMode());
		target.setDiscount(source.getDiscount());
		target.setPaymentType(source.getPaymentType());
//		target.setCreatedBy(new User(source.getCreatedBy().getId()));
		List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
		OrderDetail orderDetail = null;
		for (OrderDetailData orderDetailData : source.getOrderDetail()) {
			orderDetail = new OrderDetail();
			OrderDetailConverter.reverse(orderDetailData, orderDetail);
			orderDetailList.add(orderDetail);
		}
		target.setOrderDetail(orderDetailList);
	}

	public static void convert(Order source, OrderData target) {
		target.setId(source.getId());
		target.setAmount(source.getAmount());
		target.setOrderNumber(source.getOrderNumber());
		target.setStatus(source.getStatus());
		target.setDiningMode(source.getDiningMode());
		target.setDiscount(source.getDiscount());
		target.setPaymentType(source.getPaymentType());
		List<OrderDetailData> orderDetailDataList = new ArrayList<OrderDetailData>();
		OrderDetailData orderDetailData = null;
		for(OrderDetail orderDetail : source.getOrderDetail()){
			orderDetailData = new OrderDetailData();
			OrderDetailConverter.convert(orderDetail,orderDetailData);
			orderDetailDataList.add(orderDetailData);
		}
		target.setCreatedDate(source.getCreatedDate());
		target.setOrderDate(source.getOrderDate());
		target.setOrderDetail(orderDetailDataList);
//		UserData userData = new UserData();
//		UserConverter.convert(source.getCreatedBy(), userData);
//		target.setCreatedBy(userData);
	}

}
