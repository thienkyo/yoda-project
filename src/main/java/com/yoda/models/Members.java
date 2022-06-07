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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
/*
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "memberId")*/
public class Members {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "INT(8) UNSIGNED")
	private int memberId;
	
	@Column(nullable=false,length = 70)
	private String fullName;
	
	@Column(nullable=false,length = 100, unique=true)
	private String email;
	
	@Column(nullable=false,length = 700)
	private String pass;
	
	@Column(nullable=true,length = 12)
	private String phone;
	
	@Column(nullable=true,length = 40)
	private String country;
	
	@Column(nullable=true,length = 40)
	private String city;
	
	@Column(nullable=true,length = 40)
	private String district;
	
	@Column(nullable=true,length = 80)
	private String street;
	
	@Column(nullable=true,length = 255)
	private String address;
	
	@Column(nullable=true,length = 20)
	private String postCode;
	
	@Column(nullable=false, columnDefinition = "INT(2) UNSIGNED")
	private int status;
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date modDate;
	
	@OneToOne
    @JoinColumn(name = "shipCostId")
	private ShipCost shipCost;
	
	//@JsonBackReference
	@JsonIgnore
	@OneToMany(mappedBy = "member",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	//@JoinColumn(name = "orderId")
	private List<Orders> orders;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "memberId")
	private List<MemberRole> role;

	public Members(String fullName, String email, String pass, String phone, String country, String city,
			String district, String street, String address, String postCode, int status, ShipCost shipCost, Date modDate) {
		super();
		this.fullName = fullName;
		this.email = email;
		this.pass = pass;
		this.phone = phone;
		this.country = country;
		this.city = city;
		this.district = district;
		this.street = street;
		this.address = address;
		this.postCode = postCode;
		this.status = status;
		this.shipCost = shipCost;
		this.modDate = modDate;
	}
	
	public Members() {
		super();
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getModDate() {
		return modDate;
	}

	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}
	
	public ShipCost getShipCost() {
		return shipCost;
	}

	public void setShipCost(ShipCost shipCost) {
		this.shipCost = shipCost;
	}

	public List<MemberRole> getRole() {
		return role;
	}

	public void setRole(List<MemberRole> role) {
		this.role = role;
	}

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "Members [memberId=" + memberId + ", fullName=" + fullName + ", email=" + email + ", pass=" + pass
				+ ", phone=" + phone + ", country=" + country + ", city=" + city + ", district=" + district
				+ ", street=" + street + ", address=" + address + ", postCode=" + postCode + ", status=" + status
				+ ", modDate=" + modDate + ", shipCost=" + shipCost + ", orders=" + orders + ", role=" + role + "]";
	}
}
