package com.my.sample.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ORDER")
public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5064224669903519343L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column(name = "ORDER_NUMBER")
	private Long orderNumber;

	@Column(name = "CREATED_DATE")
	private Date createdDate;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "AMOUNT")
	private BigDecimal amount;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CUSTOMER")
	private Customer customer;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "ORDER_DETAIL_MAPPING", joinColumns = @JoinColumn(name = "ORDER_ID"), inverseJoinColumns = @JoinColumn(name = "ORDER_DETAIL_ID"))
	@Column(name = "ORDER_DETAIL")
	private List<OrderDetail> orderDetail;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Long orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public List<OrderDetail> getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(List<OrderDetail> orderDetail) {
		this.orderDetail = orderDetail;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
