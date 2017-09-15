package com.my.sample.converter;

import com.my.sample.data.ExpenseData;
import com.my.sample.domain.Expense;

public class ExpenseConverter {

	public static void convert(Expense source, ExpenseData target) {
		target.setId(source.getId());
		target.setAmount(source.getAmount());
		target.setCategory(source.getCategory());
		target.setCreatedDate(source.getCreatedDate());
		target.setDateOfExpense(source.getDateOfExpense());
		target.setDescription(source.getDescription());
		target.setModifiedDate(source.getModifiedDate());
		target.setPaymentMode(source.getPaymentMode());
		target.setType(source.getType());
	}

	public static void reverse(ExpenseData source, Expense target) {
		target.setAmount(source.getAmount());
		target.setCategory(source.getCategory());
		target.setDateOfExpense(source.getDateOfExpense());
		target.setDescription(source.getDescription());
		target.setPaymentMode(source.getPaymentMode());
		target.setType(source.getType());
	}
}
