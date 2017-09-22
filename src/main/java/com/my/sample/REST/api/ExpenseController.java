package com.my.sample.REST.api;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.my.sample.config.security.Authorities;
import com.my.sample.config.security.RestSecurity;
import com.my.sample.data.ExpenseData;
import com.my.sample.service.ExpenseService;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {
	private final ExpenseService expenseService;

	@Autowired
	public ExpenseController(ExpenseService expenseService) {
		super();

		if (expenseService == null) {
			throw new IllegalArgumentException("expenseService cannot be null");
		}

		this.expenseService = expenseService;
	}

	@RestSecurity(authority = { Authorities.ROLE_ADMIN })
	@RequestMapping(value = "/today", method = RequestMethod.GET)
	public ResponseEntity<?> getOrderForCurrentDate() throws Exception {
		return new ResponseEntity<>(expenseService.getExpenseForCurrentDate(), HttpStatus.OK);
	}

	@RestSecurity(authority = { Authorities.ROLE_ADMIN })
	@RequestMapping(value = "/createOrUpdate", method = RequestMethod.POST)
	public ResponseEntity<?> createOrUpdateExpense(@RequestBody ExpenseData expenseData) throws Exception {
		return new ResponseEntity<>(expenseService.createOrUpdateExpense(expenseData), HttpStatus.OK);
	}

	@RestSecurity(authority = { Authorities.ROLE_ADMIN })
	@RequestMapping(value = "/search/{fromExpenseDate}/{toExpenseDate}", method = RequestMethod.GET)
	public ResponseEntity<?> searchExpenseInRange(
			@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") Date fromExpenseDate,
			@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") Date toExpenseDate) throws Exception {
		return new ResponseEntity<>(expenseService.searchExpenseInRange(fromExpenseDate, toExpenseDate), HttpStatus.OK);
	}

	@RestSecurity(authority = { Authorities.ROLE_ADMIN })
	@RequestMapping(value = "/total/{fromExpenseDate}/{toExpenseDate}", method = RequestMethod.GET)
	public ResponseEntity<?> getTotalExpenseInRange(
			@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") Date fromExpenseDate,
			@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") Date toExpenseDate) throws Exception {
		return new ResponseEntity<>(expenseService.getTotalExpenseInRange(fromExpenseDate, toExpenseDate),
				HttpStatus.OK);
	}
}
