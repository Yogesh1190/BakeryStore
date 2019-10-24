package com.store.mybakery.core;

import java.io.FileReader;
import java.io.Reader;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.store.mybakery.model.ProductInput;
import com.store.mybakery.util.Constants;

/**
 * 
 * @author Yogesh Shisode
 * @description This class is used to load product details from datastore. For now we are loading JSON file
 * 				containing product details.
 */
public class LoadProducts {
	
	/**
	 * This function is created to load the available products from the datastore.
	 * @return This will return the product list.
	 */
	public static JSONObject loadProducts(){
		Reader reader = null;
		JSONObject products = null;
		JSONParser parser = new JSONParser();
		try {
			reader = new FileReader(Constants.RESOURCE_FILE_PATH);
			products = (JSONObject) parser.parse(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return products;
	}
	
	/**
	 * This is function is created for fetching available pack details based on users input.
	 * @param products	This contains all the available products from the bakery.
	 * @param input	This is user input parameter.
	 * @return This is return available pack details based on users input.
	 */
	public static Map<Integer, Double> getPackDetails(JSONObject products, ProductInput input){
		JSONArray items = (JSONArray) products.get(Constants.PRODUCT_KEY);
		@SuppressWarnings("unchecked")
		Iterator<JSONObject> iterator = items.iterator();
		
		Map<Integer, Double> hashMap = new TreeMap<Integer, Double>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1);
			}
		});
		
		while(iterator.hasNext()){
			JSONObject test = iterator.next();
			if(String.valueOf(test.get(Constants.CODE_KEY)).equalsIgnoreCase(input.getCode())){
				JSONArray packs = (JSONArray) test.get(Constants.PACK_KEY);
				@SuppressWarnings("unchecked")
				Iterator<JSONObject> packsIterator = packs.iterator();
				while(packsIterator.hasNext()){
					JSONObject packDetails = packsIterator.next();
					int quantity = Integer.parseInt(packDetails.get(Constants.QUANTITY_KEY).toString());
					double cost = Double.parseDouble(packDetails.get(Constants.COST_KEY).toString());
					hashMap.put(quantity, cost);
				}
				break;
			}
		}
		return hashMap;
	}
}
