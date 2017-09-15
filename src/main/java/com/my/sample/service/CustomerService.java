package com.my.sample.service;

import com.my.sample.data.CustomerData;

public interface CustomerService {
	
	CustomerData getOrCreateCustomer(String mobile);
	CustomerData updateCustomer(CustomerData customerData);
	void updateLastVisitedDate(Long id);

}
