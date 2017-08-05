package com.my.sample.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.my.sample.service.OrderService;

@Service("orderService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class OrderServiceImpl implements OrderService {

}
