package com.yoda.models;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(columnDefinition = "INT(8) UNSIGNED")
	private int orderId;
	
	@Column(nullable=false,length = 500)
	private String shippingAddress;
	
	@Column(nullable=true,length = 300)
	private String description;
	
	@Column(nullable=false, columnDefinition = "INT(2) UNSIGNED")
	private int status;
	
	@Column(nullable=true, columnDefinition = "INT(3) UNSIGNED")
	private int discount;
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date modDate;
	
	@ManyToOne
    @JoinColumn(name = "memberId")
	private Members member;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<OrderDetails> orderDetails;


	public Orders(String shippingAddress, String description, int status, int discount, Date modDate) {
		super();
		this.shippingAddress = shippingAddress;
		this.description = description;
		this.status = status;
		this.discount = discount;
		this.modDate = modDate;
	}

	public Orders() {
		super();
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public Date getModDate() {
		return modDate;
	}

	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}

	public Members getMember() {
		return member;
	}

	public void setMember(Members member) {
		this.member = member;
	}

	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}

	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", shippingAddress=" + shippingAddress + ", description=" + description
				+ ", status=" + status + ", discount=" + discount + ", modDate=" + modDate + ", member=" + member
				+ ", orderDetails=" + orderDetails + "]";
	}
	
}
