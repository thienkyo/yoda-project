package com.yoda.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShipCost {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "INT(8) UNSIGNED")
	private int shipCostId;
	
	@Column(nullable=false,length = 200)
	private String region;
	
	@Column(nullable=false, columnDefinition = "INT(8) UNSIGNED")
	private int price;

	@Column(nullable=false, columnDefinition = "INT(8) UNSIGNED")
	private int distance;

	@Column(nullable=false, columnDefinition = "INT(2) UNSIGNED default 0")
	private int status;

	public ShipCost() {
		super();
	}

	public ShipCost(int shipCostId, String region, int price, int distance) {
		super();
		this.shipCostId = shipCostId;
		this.region = region;
		this.price = price;
		this.distance = distance;
	}
	
	public ShipCost(int shipCostId) {
		super();
		this.shipCostId = shipCostId;
	}

	public int getShipCostId() {
		return shipCostId;
	}

	public void setShipCostId(int shipCostId) {
		this.shipCostId = shipCostId;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ShipCost [shipCostId=" + shipCostId + ", region=" + region + ", price=" + price + ", distance="
				+ distance + "]";
	}
}
