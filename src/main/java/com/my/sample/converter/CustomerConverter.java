package com.my.sample.converter;

import com.my.sample.data.CustomerData;
import com.my.sample.domain.Customer;

public class CustomerConverter {
	
	public static void convert(Customer source, CustomerData target){
		target.setEmail(source.getEmail());
		target.setId(source.getId());
		target.setLastVisited(source.getLastVisited());
		target.setMobile(source.getMobile());
		target.setName(source.getName());
		target.setCreatedDate(source.getCreatedDate());
		target.setModifiedDate(source.getModifiedDate());
		target.setAddress(source.getAddress());
	}
	
	public static void reverse(CustomerData source, Customer target){
		target.setEmail(source.getEmail());
		target.setMobile(source.getMobile());
		target.setName(source.getName());
		target.setAddress(source.getAddress());
	}

}
