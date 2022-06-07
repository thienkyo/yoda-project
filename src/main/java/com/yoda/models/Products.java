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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
/*@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "prodId")*/
public class Products {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "INT(8) UNSIGNED")
	private int prodId;
	
	@Column(nullable=false,length = 500)
	private String prodName;
	
	@Column(columnDefinition = "TEXT")
	private String description;
	
	@Column(nullable=true,length = 300)
	private String notification;
	
	@Column(nullable=false, columnDefinition = "INT(8) UNSIGNED")
	private int price;
	
	@Column(nullable=false, columnDefinition = "INT(4) UNSIGNED")
	private int quantity;

	/**
	 * thumbnail
	 */
	@Column(nullable=true, length = 200)
	private String image;

	@Column(nullable=false,columnDefinition = "INT(2) UNSIGNED")
	private int status;
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date modDate;
	
	@Column(nullable=true, columnDefinition = "INT(3) UNSIGNED")
	private int discount;
	
	@Column(nullable=true, columnDefinition = "DOUBLE(2,1) default 1.0")
	private double weight;

	/**
	 * image upload from user, yes or no
	 */
	@Column(nullable=false,columnDefinition = "INT(2) UNSIGNED default 0")
	private int needImage;

	/**
	 * min number of image this product needs.
	 */
	@Column(nullable=true,columnDefinition = "INT(2) UNSIGNED default 0")
	private int minNumberOfImage;

	/**
	 * max number of image this product needs.
	 */
	@Column(nullable=true,columnDefinition = "INT(2) UNSIGNED default 0")
	private int maxNumberOfImage;
	
//	@JsonManagedReference
	@ManyToOne
    @JoinColumn(name = "categoryId")
	private Categories category;
	
//	@JsonBackReference
//	@JsonIgnore
//	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private List<OrderDetails> orderDetails;

	
	public Products(String prodName, String description, String notification, int price, int quantity, String image,
			int status, double weight) {
		super();
		this.prodName = prodName;
		this.description = description;
		this.notification = notification;
		this.price = price;
		this.quantity = quantity;
		this.image = image;
		this.status = status;
		this.weight = weight;
	}

	public Products() {
		super();
	}

	
	public int getProdId() {
		return prodId;
	}

	public void setProdId(int prodId) {
		this.prodId = prodId;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNotification() {
		return notification;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public Categories getCategory() {
		return category;
	}

	public void setCategory(Categories category) {
		this.category = category;
	}

	public int getNeedImage() {
		return needImage;
	}

	public void setNeedImage(int needImage) {
		this.needImage = needImage;
	}

	public int getMinNumberOfImage() {
		return minNumberOfImage;
	}

	public void setMinNumberOfImage(int minNumberOfImage) {
		this.minNumberOfImage = minNumberOfImage;
	}

	public int getMaxNumberOfImage() {
		return maxNumberOfImage;
	}

	public void setMaxNumberOfImage(int maxNumberOfImage) {
		this.maxNumberOfImage = maxNumberOfImage;
	}

	/*
                public List<OrderDetails> getOrderDetails() {
                    return orderDetails;
                }

                public void setOrderDetails(List<OrderDetails> orderDetails) {
                    this.orderDetails = orderDetails;
                }
            */
	@Override
	public String toString() {
		return "Products [prodID=" + prodId + ", prodName=" + prodName + ", description=" + description
				+ ", notification=" + notification + ", price=" + price + ", quantity=" + quantity + ", image=" + image
				+ ", status=" + status + ", mod_date=" + modDate + ", category=" + category + "]";
	}
}
