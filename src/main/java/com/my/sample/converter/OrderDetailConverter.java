package com.my.sample.converter;

import java.util.Date;

import com.my.sample.data.ItemData;
import com.my.sample.data.OrderDetailData;
import com.my.sample.domain.Item;
import com.my.sample.domain.OrderDetail;

public class OrderDetailConverter {
	
	public static void reverse(OrderDetailData source, OrderDetail target){
		target.setCreatedDate(new Date());
		target.setItem(new Item(source.getItem().getId()));
		target.setUnitPrice(source.getUnitPrice());
		target.setQuantity(source.getQuantity());
	}

	public static void convert(OrderDetail source, OrderDetailData target) {
		target.setId(source.getId());
		target.setCreatedDate(source.getCreatedDate());
		target.setQuantity(source.getQuantity());
		target.setUnitPrice(source.getUnitPrice());
		ItemData itemData = new ItemData();
		ItemConverter.convert(source.getItem(), itemData);
		target.setItem(itemData);
	}

}
