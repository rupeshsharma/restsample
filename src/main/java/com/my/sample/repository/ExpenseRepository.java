package com.my.sample.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.my.sample.data.DailyRevenueChartData;
import com.my.sample.data.MonthlyRevenueChartData;
import com.my.sample.data.YearlyRevenueChartData;
import com.my.sample.domain.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

	@Query("SELECT ex from Expense ex where ex.dateOfExpense = :dateOfExpense")
	List<Expense> getExpenseForDate(@Param("dateOfExpense") Date dateOfExpense);

	@Query("SELECT ex from Expense ex where ex.dateOfExpense BETWEEN :fromExpenseDate AND :toExpenseDate")
	List<Expense> searchExpenseInRange(@Param("fromExpenseDate") Date fromExpenseDate,
			@Param("toExpenseDate") Date toExpenseDate);

	@Query("SELECT SUM(ex.amount) from Expense ex where ex.dateOfExpense BETWEEN :fromExpenseDate AND :toExpenseDate")
	BigDecimal getTotalExpenseInRange(@Param("fromExpenseDate") Date fromExpenseDate,
			@Param("toExpenseDate") Date toExpenseDate);

	@Query("SELECT New com.my.sample.data.DailyRevenueChartData(ex.dateOfExpense, SUM(ex.amount)) from Expense ex where ex.dateOfExpense BETWEEN :fromDate AND :toDate group by ex.dateOfExpense")
	List<DailyRevenueChartData> getDailyChartExpenseDataInRange(@Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate);

	@Query("SELECT New com.my.sample.data.MonthlyRevenueChartData(DATE_FORMAT(ex.dateOfExpense,'%M'), SUM(ex.amount)) from Expense ex where ex.dateOfExpense BETWEEN :fromDate AND :toDate group by DATE_FORMAT(ex.dateOfExpense,'%M')")
	List<MonthlyRevenueChartData> getMonthlyChartExpenseDataInRange(@Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate);

	@Query("SELECT New com.my.sample.data.YearlyRevenueChartData(DATE_FORMAT(ex.dateOfExpense,'%Y'), SUM(ex.amount)) from Expense ex group by DATE_FORMAT(ex.dateOfExpense,'%Y')")
	List<YearlyRevenueChartData> getYearlyChartExpenseDataInRange();

}
