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
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Categories {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "INT(8) UNSIGNED")
	private int categoryId;
	
	@Column(nullable=false,length = 100)
	private String categoryName;
	
	@Column(nullable=false,columnDefinition = "INT(2) UNSIGNED")
	private int status;
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date mod_date;
	
	//@JsonIgnore
	@JsonBackReference
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Products> products;

	public Categories(int categoryId, String categoryName, int status) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.status = status;
	}

	public Categories() {
		super();
	}

	public Categories(int categoryId) {
		super();
		this.categoryId = categoryId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getMod_date() {
		return mod_date;
	}

	public void setMod_date(Date mod_date) {
		this.mod_date = mod_date;
	}

	public List<Products> getProducts() {
		return products;
	}

	public void setProducts(List<Products> products) {
		this.products = products;
	}

	@Override
	 public String toString() {
	  return "category [id=" + categoryId + ", taskName=" + categoryName+ "]";
	 }

}
