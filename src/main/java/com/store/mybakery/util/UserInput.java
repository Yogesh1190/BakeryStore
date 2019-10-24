package com.store.mybakery.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.store.mybakery.model.ProductInput;

/**
 * 
 * @author yogesh
 * @description This class is created to fetch inputs from the user.
 */
public class UserInput {
	
	/**
	 * This function will take input from the user.
	 * @return This function will return user input object.
	 */
	public static ProductInput input(){
		BufferedReader br = null;
		ProductInput productInput = new ProductInput();
		try{
			System.out.println(Constants.USER_INPUT_STATEMENT);
			br = new BufferedReader(new InputStreamReader(System.in));
			String input = br.readLine();
			
			String inputArray[] = input.split(Constants.BY_SPACE);
			
			int quantity = Integer.parseInt(inputArray[0]);
			String itemCode = inputArray[1];
			
			productInput.setCode(itemCode);
			productInput.setQuantity(quantity);
			
		}catch(Exception ex){
			System.out.println(Constants.USER_INPUT_INVALID_STATEMENT);
		}
		
		return productInput;
	}
}
