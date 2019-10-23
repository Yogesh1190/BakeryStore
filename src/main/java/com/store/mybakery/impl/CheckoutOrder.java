package com.store.mybakery.impl;

import org.json.simple.JSONObject;

import com.store.mybakery.model.ProductInput;

/**
 * 
 * @author Yogesh Shisode
 * @description This class is used to calculate the price as per input request and as per pack structure.
 * 
 */
public class CheckoutOrder {
	
	public void calculate(JSONObject products, ProductInput input){
		
		System.out.println("Welcome to checkout order funtion");
		System.out.println("Input request is : " + input.getCode() + " -- " + input.getQuantity());
		System.out.println("Available Data is : " + products);
	}
}
