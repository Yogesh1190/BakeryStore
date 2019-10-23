package com.store.mybakery.impl;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.json.simple.JSONArray;
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
		Map<Integer, Integer> response = new HashMap<Integer, Integer>();
		
		Map<Integer, Double> hashMap = getPackDetails(products, input);
		int inputQty = input.getQuantity();
		
		for(Map.Entry<Integer, Double> packs : hashMap.entrySet()){
			if((inputQty / packs.getKey()) > 0){
				int packQty = packs.getKey();
				response.put(packQty, inputQty / packQty);
				inputQty = inputQty % packQty;
			}
		}
		
		for(Map.Entry<Integer, Integer> resp : response.entrySet()){
			System.out.println(resp.getKey() + " -- " + resp.getValue());
		}
	}
	
	public Map<Integer, Double> getPackDetails(JSONObject products, ProductInput input){
		JSONArray items = (JSONArray) products.get("products");
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
