package com.store.mybakery.core;

import java.text.DecimalFormat;
import java.util.Map;

import com.store.mybakery.model.ProductDetails;
import com.store.mybakery.model.ProductInput;
import com.store.mybakery.util.Constants;

/**
 * 
 * @author yogesh
 * @description This class is used to generate bill for given order.
 */
public class GenerateBill {
	public static String print(ProductDetails checkoutDetails, Map<Integer, Double> packDetails, ProductInput input){
		DecimalFormat df = new DecimalFormat(Constants.DECIMAL_FORMAT);
		Map<Integer, Integer> packInfo = checkoutDetails.getPackDetails();
		String productDetails = "";
		
		if(packInfo.size() == Constants.EMPTY){
			System.out.println(Constants.USER_INPUT_INVALID_STATEMENT);
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