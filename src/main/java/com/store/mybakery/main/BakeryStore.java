package com.store.mybakery.main;

import java.util.Map;

import org.apache.log4j.Logger;
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
	static final Logger logger = Logger.getLogger(BakeryStore.class);
	
	/**
	 * This is starting point of the application.
	 * @param args
	 */
    public static void main( String[] args )
    {
    	CheckoutOrder checkoutOrder = new CheckoutOrder();
		try{
			logger.info("Loading bakery products from the datastore");
			JSONObject products = LoadProducts.loadProducts();
			
			logger.info("Fetching user input");
			ProductInput input = UserInput.input();
			
			logger.info("Calculating pack details as per users input");
			Map<Integer, Double> packDetails = LoadProducts.getPackDetails(products, input);
			
			logger.info("Checkout the product based on user input and available pack");
			ProductDetails checkoutDetails = checkoutOrder.calculate(products, packDetails, input);
			
			logger.info("Generate bill of the order");
			System.out.println(GenerateBill.print(checkoutDetails, packDetails, input));
			
		}catch(Exception ex){
			logger.error("Exception occured : ", ex);
		}
    }
}
