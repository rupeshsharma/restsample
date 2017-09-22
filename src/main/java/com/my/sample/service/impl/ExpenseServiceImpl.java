package com.my.sample.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.my.sample.config.security.SecurityUserContext;
import com.my.sample.converter.ExpenseConverter;
import com.my.sample.data.DailyRevenueChartData;
import com.my.sample.data.ExpenseData;
import com.my.sample.data.MonthlyRevenueChartData;
import com.my.sample.data.YearlyRevenueChartData;
import com.my.sample.domain.Expense;
import com.my.sample.domain.User;
import com.my.sample.repository.ExpenseRepository;
import com.my.sample.service.ExpenseService;

@Service("expenseService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class ExpenseServiceImpl implements ExpenseService {

	private final ExpenseRepository expenseRepository;
	private final SecurityUserContext securityUserContext;

	@Autowired
	public ExpenseServiceImpl(ExpenseRepository expenseRepository, SecurityUserContext securityUserContext) {
		super();

		if (expenseRepository == null) {
			throw new IllegalArgumentException("expenseRepository cannot be null");
		}
		if (securityUserContext == null) {
			throw new IllegalArgumentException("securityUserContext cannot be null");
		}
		this.expenseRepository = expenseRepository;
		this.securityUserContext = securityUserContext;
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
			expense.setCreatedBy(new User(securityUserContext.getCurrentUser().getId()));
		} else {
			expense.setModifiedDate(new Date());
			expense.setUpdatedBy(new User(securityUserContext.getCurrentUser().getId()));
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

	@Override
	public List<DailyRevenueChartData> getDailyChartExpenseDataInRange(Date fromDate, Date toDate) {
		return expenseRepository.getDailyChartExpenseDataInRange(fromDate, toDate);
	}

	@Override
	public List<MonthlyRevenueChartData> getMonthlyChartExpenseDataInRange(Date fromDate, Date toDate) {
		return expenseRepository.getMonthlyChartExpenseDataInRange(fromDate, toDate);
	}

	@Override
	public List<YearlyRevenueChartData> getYearlyChartExpenseDataInRange() {
		return expenseRepository.getYearlyChartExpenseDataInRange();
	}

}
