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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
/*
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "orderId")*/
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "INT(8) UNSIGNED")
	private int orderId;
	
	@Column(nullable=false,length = 1000)
	private String shippingAddress;
	
	@Column(nullable=true,length = 500)
	private String description;
	
	@Column(nullable=false, columnDefinition = "INT(2) UNSIGNED")
	private int status;
	
	@Column(nullable=true, columnDefinition = "INT(3) UNSIGNED")
	private int discount;
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date modDate;

	@Column(nullable=false, columnDefinition = "INT(8) UNSIGNED")
	private int shipCostFee;
	
	@Column(columnDefinition = "INT(3) UNSIGNED")
	private int shipCostId;

	@Column(nullable=false,length = 200)
	private String shippingName;

	@Column(nullable=true,length = 50)
	private String shippingPhoneNumber;
	
//	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "memberId")
	private Members member;
	
//	@JsonBackReference
//	@OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE , fetch=FetchType.LAZY,orphanRemoval=true)
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval=true, fetch=FetchType.LAZY)
	@JoinColumn(name = "orderId")
	private List<OrderDetails> orderDetails;

	public Orders(int orderId, String shippingAddress, String description, int status, int discount, Date modDate,
			int shipCostFee, List<OrderDetails> orderDetails) {
		super();
		this.orderId = orderId;
		this.shippingAddress = shippingAddress;
		this.description = description;
		this.status = status;
		this.discount = discount;
		this.modDate = modDate;
		this.shipCostFee = shipCostFee;
		this.orderDetails = orderDetails;
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

	public int getShipCostFee() {
		return shipCostFee;
	}

	public void setShipCostFee(int shipCostFee) {
		this.shipCostFee = shipCostFee;
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

	public int getShipCostId() {
		return shipCostId;
	}

	public void setShipCostId(int shipCostId) {
		this.shipCostId = shipCostId;
	}

	public String getShippingName() {
		return shippingName;
	}

	public void setShippingName(String shippingName) {
		this.shippingName = shippingName;
	}

	public String getShippingPhoneNumber() {
		return shippingPhoneNumber;
	}

	public void setShippingPhoneNumber(String shippingPhoneNumber) {
		this.shippingPhoneNumber = shippingPhoneNumber;
	}

	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", shippingAddress=" + shippingAddress + ", description=" + description
				+ ", status=" + status + ", discount=" + discount + ", modDate=" + modDate + ", shipCostFee="
				+ shipCostFee + ", shipCostId=" + shipCostId + ", member=" + member + ", orderDetails=" + orderDetails
				+ "]";
	}

}
