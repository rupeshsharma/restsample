package com.my.sample.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.my.sample.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	@Query("Select c FROM Customer c WHERE c.mobile = :mobile")
	Customer findCustomerByMobile(@Param("mobile") String mobile);

	@Query("Select c FROM Customer c WHERE c.lastVisited <= :lastVisitedDate")
	List<Customer> getCustomerVisitedBeforeDate(@Param("lastVisitedDate") Date lastVisitedDate);

}
