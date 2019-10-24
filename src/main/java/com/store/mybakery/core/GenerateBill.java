package com.store.mybakery.core;

import java.text.DecimalFormat;
import java.util.Map;

import com.store.mybakery.model.ProductDetails;
import com.store.mybakery.model.ProductInput;

/**
 * 
 * @author yogesh
 * @description This class is used to generate bill for given order.
 */
public class GenerateBill {
	public static String print(ProductDetails checkoutDetails, Map<Integer, Double> packDetails, ProductInput input){
		DecimalFormat df = new DecimalFormat("0.00");
		Map<Integer, Integer> packInfo = checkoutDetails.getPackDetails();
		String productDetails = "";
		
		if(packInfo.size() <= 0){
			System.out.println("Please provide valid input");
		}else{
			productDetails = checkoutDetails.getQuantity() + " " + checkoutDetails.getCode() + " &" + df.format(checkoutDetails.getPrice());
			for (Map.Entry<Integer, Double> packList : packDetails.entrySet()) {
				if(packInfo.get(packList.getKey()) != null){
					productDetails += "\n\t" + packList.getKey() + " X " + packInfo.get(packList.getKey()) + " $" + packList.getValue();				
				}
			}	
		}
		
		return productDetails;
	}
}