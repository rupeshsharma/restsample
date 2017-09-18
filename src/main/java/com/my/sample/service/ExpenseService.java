package com.my.sample.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.my.sample.data.DailyRevenueChartData;
import com.my.sample.data.ExpenseData;
import com.my.sample.data.MonthlyRevenueChartData;
import com.my.sample.data.YearlyRevenueChartData;

public interface ExpenseService {

	List<ExpenseData> getExpenseForCurrentDate();

	ExpenseData createOrUpdateExpense(ExpenseData expenseData);

	List<ExpenseData> searchExpenseInRange(Date fromExpenseDate, Date toExpenseDate);

	BigDecimal getTotalExpenseInRange(Date fromExpenseDate, Date toExpenseDate);

	List<DailyRevenueChartData> getDailyChartExpenseDataInRange(Date fromDate, Date toDate);

	List<MonthlyRevenueChartData> getMonthlyChartExpenseDataInRange(Date fromDate, Date toDate);

	List<YearlyRevenueChartData> getYearlyChartExpenseDataInRange();

}
