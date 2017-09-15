package com.my.sample.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.my.sample.config.DefaultDateSerializer;
import com.my.sample.config.DefaultDateTimeSerializer;
import com.my.sample.config.DefaultJsonDateDeserializer;

public class ExpenseData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2571249274520990551L;

	private Long id;

	@JsonSerialize(using = DefaultDateTimeSerializer.class)
	private Date createdDate;
	
	@JsonSerialize(using = DefaultDateTimeSerializer.class)
	private Date modifiedDate;

	@JsonSerialize(using = DefaultDateSerializer.class)
	private Date dateOfExpense;

	private String category;

	private String type;

	private String description;

	private BigDecimal amount;

	private String paymentMode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getDateOfExpense() {
		return dateOfExpense;
	}

	@JsonDeserialize(using =DefaultJsonDateDeserializer.class)
	public void setDateOfExpense(Date dateOfExpense) {
		this.dateOfExpense = dateOfExpense;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
}
