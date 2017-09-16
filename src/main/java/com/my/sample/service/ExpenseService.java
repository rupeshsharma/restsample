package com.my.sample.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.my.sample.data.ExpenseData;

public interface ExpenseService {

	List<ExpenseData> getExpenseForCurrentDate();

	ExpenseData createOrUpdateExpense(ExpenseData expenseData);

	List<ExpenseData> searchExpenseInRange(Date fromExpenseDate, Date toExpenseDate);

	BigDecimal getTotalExpenseInRange(Date fromExpenseDate, Date toExpenseDate);

}
