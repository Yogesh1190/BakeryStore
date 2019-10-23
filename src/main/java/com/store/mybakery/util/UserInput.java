package com.store.mybakery.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.store.mybakery.model.ProductInput;

public class UserInput {
	public static ProductInput input(){
		BufferedReader br = null;
		ProductInput productInput = new ProductInput();
		try{
			System.out.println("Please provide quantity and item code");
			br = new BufferedReader(new InputStreamReader(System.in));
			String input = br.readLine();
			
			String inputArray[] = input.split(" ");
			
			int quantity = Integer.parseInt(inputArray[0]);
			String itemCode = inputArray[1];
			
			productInput.setCode(itemCode);
			productInput.setQuantity(quantity);
			
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Please provide valid input");
		}
		
		return productInput;
	}
}
