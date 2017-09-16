package com.my.sample.service;

import java.util.Date;
import java.util.List;

import com.my.sample.data.CustomerData;

public interface CustomerService {
	
	CustomerData getOrCreateCustomer(String mobile);
	CustomerData updateCustomer(CustomerData customerData);
	void updateLastVisitedDate(Long id);
	List<CustomerData> getCustomerVisitedBeforeDate(Date lastVisitedDate);

}
