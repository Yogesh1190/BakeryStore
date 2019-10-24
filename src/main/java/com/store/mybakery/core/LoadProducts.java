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
 * @description This class is used to load product details from database. For now we are loading JSON file
 * 				containing product details.
 */
public class LoadProducts {
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
	
	public static Map<Integer, Double> getPackDetails(JSONObject products, ProductInput input){
		JSONArray items = (JSONArray) products.get("products");
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
			if(String.valueOf(test.get("code")).equalsIgnoreCase(input.getCode())){
				JSONArray packs = (JSONArray) test.get("pack");
				@SuppressWarnings("unchecked")
				Iterator<JSONObject> packsIterator = packs.iterator();
				while(packsIterator.hasNext()){
					JSONObject packDetails = packsIterator.next();
					int quantity = Integer.parseInt(packDetails.get("quantity").toString());
					double cost = Double.parseDouble(packDetails.get("cost").toString());
					hashMap.put(quantity, cost);
				}
				break;
			}
		}
		
		return hashMap;
	}
}
