package com.my.sample.REST.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.my.sample.data.OrderData;
import com.my.sample.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {
	
private final OrderService orderService;
	
	@Autowired
	public OrderController(OrderService orderService) {
		super();

		if (orderService == null) {
			throw new IllegalArgumentException("orderService cannot be null");
		}

		this.orderService = orderService;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addCategory(@RequestBody OrderData orderData) throws Exception {
		return new ResponseEntity<>(orderService.createOrder(orderData),
				HttpStatus.OK);
	}

}
