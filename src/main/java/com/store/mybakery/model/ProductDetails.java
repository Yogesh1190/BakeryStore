package com.store.mybakery.model;

import java.util.Map;

public class ProductDetails {
	private String code;
	private int quantity;
	private double price;
	private Map<Integer, Integer> packDetails;
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public Map<Integer, Integer> getPackDetails() {
		return packDetails;
	}
	
	public void setPackDetails(Map<Integer, Integer> packDetails) {
		this.packDetails = packDetails;
	}
}