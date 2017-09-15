package com.my.sample.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.my.sample.domain.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

	@Query("SELECT ex from Expense ex where ex.dateOfExpense = :dateOfExpense")
	List<Expense> getExpenseForDate(@Param("dateOfExpense") Date dateOfExpense);

	@Query("SELECT ex from Expense ex where ex.dateOfExpense BETWEEN :fromOrderDate AND :toOrderDate")
	List<Expense> searchExpenseInRange(@Param("fromOrderDate") Date fromOrderDate,
			@Param("toOrderDate") Date toOrderDate);

	@Query("SELECT SUM(ex.amount) from Expense ex where ex.dateOfExpense BETWEEN :fromOrderDate AND :toOrderDate")
	BigDecimal getTotalExpenseInRange(@Param("fromOrderDate") Date fromOrderDate,
			@Param("toOrderDate") Date toOrderDate);

}
