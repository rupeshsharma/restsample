package com.my.sample.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.my.sample.converter.CustomerConverter;
import com.my.sample.data.CustomerData;
import com.my.sample.domain.Customer;
import com.my.sample.repository.CustomerRepository;
import com.my.sample.service.CustomerService;

@Service("customerService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;

	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		super();

		if (customerRepository == null) {
			throw new IllegalArgumentException("customerRepository cannot be null");
		}
		this.customerRepository = customerRepository;
	}

	@Override
	public CustomerData getOrCreateCustomer(String mobile) {
		Customer customer = customerRepository.findCustomerByMobile(mobile);
		CustomerData customerData = new CustomerData();
		if (customer == null) {
			customer = new Customer();
			Date date = new Date();
			customer.setCreatedDate(date);
			customer.setLastVisited(date);
			customer.setMobile(mobile);
			customer = customerRepository.save(customer);
		}
		CustomerConverter.convert(customer, customerData);

		return customerData;
	}

	@Override
	public CustomerData updateCustomer(CustomerData customerData) {
		Customer customer = customerRepository.findOne(customerData.getId());
		customer.setModifiedDate(new Date());
		CustomerConverter.reverse(customerData, customer);
		customer = customerRepository.save(customer);
		CustomerConverter.convert(customer, customerData);
		return customerData;
	}

	@Override
	public void updateLastVisitedDate(Long id) {
		Customer customer = customerRepository.findOne(id);
		customer.setLastVisited(new Date());
		customerRepository.save(customer);
	}

	@Override
	public List<CustomerData> getCustomerVisitedBeforeDate(Date lastVisitedDate) {
		List<CustomerData> customerDataList = new ArrayList<CustomerData>();
		List<Customer> customerList = customerRepository.getCustomerVisitedBeforeDate(lastVisitedDate);
		CustomerData customerData = null;
		for (Customer customer : customerList) {
			customerData = new CustomerData();
			CustomerConverter.convert(customer, customerData);
			customerDataList.add(customerData);
		}
		return customerDataList;
	}

}
