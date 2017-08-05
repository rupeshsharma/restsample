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

public class OrderConverter {

	public static void reverse(OrderData source, Order target) {
		target.setAmount(source.getAmount());
		SimpleDateFormat sm = new SimpleDateFormat("dd-mm-yyyy");
		target.setOrderDate(sm.format(new Date()));
		target.setCreatedDate(new Date());
		target.setStatus("Processing");
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
		target.setCreatedDate(source.getCreatedDate());
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
