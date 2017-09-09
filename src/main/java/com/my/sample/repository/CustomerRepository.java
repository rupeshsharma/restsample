package com.my.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.my.sample.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	@Query("Select c FROM Customer c WHERE c.mobile = :mobile")
	Customer findCustomerByMobile(@Param("mobile")String mobile);

}
