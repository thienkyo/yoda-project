package com.yoda.models;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class OrderDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "INT(8) UNSIGNED")
	private int orderDetailId;

/*	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "orderId",referencedColumnName = "orderId")
	private Orders order;
*/	
	@OneToOne
    @JoinColumn(name = "prodId")
	private Products product;
	
	@Column(nullable=false, columnDefinition = "INT(8) UNSIGNED")
	private int priceAtThatTime;
	
	@Column(nullable=false, columnDefinition = "INT(4) UNSIGNED default 0 ")
	private int quantity;
	
	@Column(nullable=true, columnDefinition = "DOUBLE(2,1) default 1.0 ")
	private double weight;
	
	@Column(nullable=false, columnDefinition = "INT(4) UNSIGNED default  0 ")
	private int discountAtThatTime;
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date modDate;

	/**
	 * save image name.
	 */
	@Column(nullable=true,length = 600)
	private String imageNames;

	
	public OrderDetails(int orderDetailId, Products product, int priceAtThatTime,
			int quantity, int discountAtThatTime, Date modDate) {
		super();
		this.orderDetailId = orderDetailId;
		this.product = product;
		this.priceAtThatTime = priceAtThatTime;
		this.quantity = quantity;
		this.discountAtThatTime = discountAtThatTime;
		this.modDate = modDate;
	}

	public OrderDetails() {
		super();
	}

	public int getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(int orderDetailId) {
		this.orderDetailId = orderDetailId;
	}
/*
	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}
*/
	public Products getProduct() {
		return product;
	}

	public void setProduct(Products product) {
		this.product = product;
	}

	public int getPriceAtThatTime() {
		return priceAtThatTime;
	}

	public void setPriceAtThatTime(int priceAtThatTime) {
		this.priceAtThatTime = priceAtThatTime;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getDiscountAtThatTime() {
		return discountAtThatTime;
	}

	public void setDiscountAtThatTime(int discountAtThatTime) {
		this.discountAtThatTime = discountAtThatTime;
	}

	public Date getModDate() {
		return modDate;
	}

	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}

	public String getImageNames() {
		return imageNames;
	}

	public void setImageNames(String imageNames) {
		this.imageNames = imageNames;
	}

	@Override
	public String toString() {
		return "OrderDetails [orderDetailId=" + orderDetailId 
				+ ", product=" + product + ", priceAtThatTime=" + priceAtThatTime + ", quantity=" + quantity
				+ ", discountAtThatTime=" + discountAtThatTime + ", modDate=" + modDate + "]";
	}
	
	
}
