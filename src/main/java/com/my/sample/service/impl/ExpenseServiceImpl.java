package com.my.sample.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.my.sample.converter.ExpenseConverter;
import com.my.sample.data.ExpenseData;
import com.my.sample.domain.Expense;
import com.my.sample.repository.ExpenseRepository;
import com.my.sample.service.ExpenseService;

@Service("expenseService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class ExpenseServiceImpl implements ExpenseService {

	private final ExpenseRepository expenseRepository;

	@Autowired
	public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
		super();

		if (expenseRepository == null) {
			throw new IllegalArgumentException("expenseRepository cannot be null");
		}
		this.expenseRepository = expenseRepository;
	}

	@Override
	public List<ExpenseData> getExpenseForCurrentDate() {
		List<ExpenseData> expenseListData = new ArrayList<ExpenseData>();
		List<Expense> expenseList = expenseRepository.getExpenseForDate(new Date());
		ExpenseData expenseData = null;
		for (Expense expense : expenseList) {
			expenseData = new ExpenseData();
			ExpenseConverter.convert(expense, expenseData);
			expenseListData.add(expenseData);
		}
		return expenseListData;
	}

	@Override
	public ExpenseData createOrUpdateExpense(ExpenseData expenseData) {
		Expense expense = null;
		if (expenseData.getId() != null) {
			expense = expenseRepository.findOne(expenseData.getId());
		}
		if (expense == null) {
			expense = new Expense();
		}else{
			expense.setModifiedDate(new Date());
		}
		ExpenseConverter.reverse(expenseData, expense);
		expense = expenseRepository.save(expense);
		ExpenseConverter.convert(expense, expenseData);
		return expenseData;
	}

	@Override
	public List<ExpenseData> searchExpenseInRange(Date fromExpenseDate, Date toExpenseDate) {
		List<ExpenseData> expenseListData = new ArrayList<ExpenseData>();
		List<Expense> expenseList = expenseRepository.searchExpenseInRange(fromExpenseDate, toExpenseDate);
		ExpenseData expenseData = null;
		for (Expense expense : expenseList) {
			expenseData = new ExpenseData();
			ExpenseConverter.convert(expense, expenseData);
			expenseListData.add(expenseData);
		}
		return expenseListData;
	}

	@Override
	public BigDecimal getTotalExpenseInRange(Date fromExpenseDate, Date toExpenseDate) {
		return expenseRepository.getTotalExpenseInRange(fromExpenseDate, toExpenseDate);
	}

}
