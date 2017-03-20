package com.yoda.DAO;

import java.util.Date;

import com.yoda.models.Members;


public class MembersDAO {
	//private int memberId;
	private String fullName;
	private String email;
	//private String pass;
	private String phone;
	private String country;
	private String city;
	private String district;
	private String street;
	private String address;
	private String postCode;
	private int status;
	private Date modDate;
	
	public MembersDAO(String fullName, String email, String phone, String country, String city, String district,
			String street, String address, String postCode, int status, Date modDate) {
		super();
		this.fullName = fullName;
		this.email = email;
		this.phone = phone;
		this.country = country;
		this.city = city;
		this.district = district;
		this.street = street;
		this.address = address;
		this.postCode = postCode;
		this.status = status;
		this.modDate = modDate;
	}
	
	public MembersDAO(Members mem) {
		super();
		this.fullName = mem.getFullName();
		this.email = mem.getEmail();
		this.phone = mem.getPhone();
		this.country = mem.getCountry();
		this.city = mem.getCity();
		this.district = mem.getDistrict();
		this.street = mem.getStreet();
		this.address = mem.getAddress();
		this.postCode = mem.getPostCode();
		this.status = mem.getStatus();
		this.modDate = mem.getModDate();
	}

	public MembersDAO() {
		super();
		// TODO Auto-generated constructor stub
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

	@Override
	public String toString() {
		return "MembersDAO [fullName=" + fullName + ", email=" + email + ", phone=" + phone + ", country=" + country
				+ ", city=" + city + ", district=" + district + ", street=" + street + ", address=" + address
				+ ", postCode=" + postCode + ", status=" + status + ", modDate=" + modDate + "]";
	}
}
