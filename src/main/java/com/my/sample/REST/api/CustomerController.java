package com.my.sample.REST.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.my.sample.data.CustomerData;
import com.my.sample.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	private final CustomerService customerService;

	@Autowired
	public CustomerController(CustomerService customerService) {
		super();

		if (customerService == null) {
			throw new IllegalArgumentException("customerService cannot be null");
		}

		this.customerService = customerService;
	}

	@RequestMapping(value = "/getOrCreate/{mobile}", method = RequestMethod.GET)
	public ResponseEntity<?> getOrCreateCustomer(@PathVariable String mobile) {
		return new ResponseEntity<>(customerService.getOrCreateCustomer(mobile), HttpStatus.OK);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<?> updateCustomer(@RequestBody CustomerData customerData) {
		return new ResponseEntity<>(customerService.updateCustomer(customerData), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/lastVisit", method = RequestMethod.PUT)
	public ResponseEntity<?> updateLastVisitedDate(Long id) {
		customerService.updateLastVisitedDate(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
