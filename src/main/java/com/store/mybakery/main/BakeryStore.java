package com.store.mybakery.main;

import java.util.Map;

import org.json.simple.JSONObject;

import com.store.mybakery.core.GenerateBill;
import com.store.mybakery.core.LoadProducts;
import com.store.mybakery.impl.CheckoutOrder;
import com.store.mybakery.model.ProductDetails;
import com.store.mybakery.model.ProductInput;
import com.store.mybakery.util.UserInput;

/**
 * 
 * @author Yogesh Shisode
 * @description This is main class (starting point of the BakeryStore application).
 */
public class BakeryStore
{
    public static void main( String[] args )
    {
    	CheckoutOrder checkoutOrder = new CheckoutOrder();
		try{
			JSONObject products = LoadProducts.loadProducts();
			ProductInput input = UserInput.input();
			Map<Integer, Double> packDetails = LoadProducts.getPackDetails(products, input);
			
			ProductDetails checkoutDetails = checkoutOrder.calculate(products, packDetails, input);
			System.out.println(GenerateBill.print(checkoutDetails, packDetails, input));
		}catch(Exception ex){
			ex.printStackTrace();
		}
    }
}
