package com.yoda.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Banner {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "INT(8) UNSIGNED")
	private int bannerId;
	
	@Column(nullable=false,length = 150)
	private String bannerName;
	
	@Column(nullable=true,length = 1000)
	private String description;
	
	@Column(nullable=true,length = 100)
	private String image;
	
	@Column(nullable=true,length = 100)
	private String link;
	
	@Column(nullable=false,columnDefinition = "INT(2) UNSIGNED")
	private int status;
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date modDate;

	public int getBannerId() {
		return bannerId;
	}

	public void setBannerId(int bannerId) {
		this.bannerId = bannerId;
	}

	public String getBannerName() {
		return bannerName;
	}

	public void setBannerName(String bannerName) {
		this.bannerName = bannerName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
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
		return "Banner [bannerId=" + bannerId + ", bannerName=" + bannerName + ", description=" + description
				+ ", image=" + image + ", link=" + link + ", status=" + status + ", modDate=" + modDate + "]";
	}
	
}
