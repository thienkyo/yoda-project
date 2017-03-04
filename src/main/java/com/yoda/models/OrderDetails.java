package com.yoda.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class OrderDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(columnDefinition = "INT(8) UNSIGNED")
	private int orderDetailId;

	@ManyToOne
    @JoinColumn(name = "orderId")
	private Orders order;
	
	@ManyToOne
    @JoinColumn(name = "prodId")
	private Products product;
	
	@Column(nullable=false, columnDefinition = "INT(8) UNSIGNED")
	private int priceAtThatTime;
	
	@Column(nullable=false, columnDefinition = "INT(4) UNSIGNED default '0'")
	private int quantity;
	
	@Column(nullable=false, columnDefinition = "INT(4) UNSIGNED default '0'")
	private int discountAtThatTime;

	public OrderDetails(Orders order, Products product, int priceAtThatTime, int quantity, int discountAtThatTime) {
		super();
		this.order = order;
		this.product = product;
		this.priceAtThatTime = priceAtThatTime;
		this.quantity = quantity;
		this.discountAtThatTime = discountAtThatTime;
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

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

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

	public int getDiscountAtThatTime() {
		return discountAtThatTime;
	}

	public void setDiscountAtThatTime(int discountAtThatTime) {
		this.discountAtThatTime = discountAtThatTime;
	}

	@Override
	public String toString() {
		return "OrderDetails [orderDetailId=" + orderDetailId + ", order=" + order + ", product=" + product
				+ ", priceAtThatTime=" + priceAtThatTime + ", quantity=" + quantity + ", discountAtThatTime="
				+ discountAtThatTime + "]";
	}
	
	
}
