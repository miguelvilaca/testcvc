package com.testcvc.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedHashMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class Quarto implements Serializable{
	
	private Long roomID;
	private String categoryName;
	private LinkedHashMap<String, BigDecimal> price;
	private BigDecimal totalPrice;
	private LinkedHashMap<String, BigDecimal> priceDetail;
	
	public Long getRoomID() {
		return roomID;
	}
	public void setRoomID(Long roomID) {
		this.roomID = roomID;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public LinkedHashMap<String, BigDecimal> getPriceDetail() {
		return priceDetail;
	}
	public void setPriceDetail(LinkedHashMap<String, BigDecimal> priceDetail) {
		this.priceDetail = priceDetail;
	}
	public LinkedHashMap<String, BigDecimal> getPrice() {
		return price;
	}
	public void setPrice(LinkedHashMap<String, BigDecimal> price) {
		this.price = price;
	}
}