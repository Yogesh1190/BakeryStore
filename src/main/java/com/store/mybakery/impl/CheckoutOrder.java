package com.store.mybakery.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONObject;

import com.store.mybakery.model.ProductDetails;
import com.store.mybakery.model.ProductInput;
import com.store.mybakery.util.Combination;

/**
 * 
 * @author Yogesh Shisode
 * @description This class is used to calculate the price as per input request and as per pack structure.
 * 
 */
public class CheckoutOrder {

	public ProductDetails calculate(JSONObject products, Map<Integer, Double> packDetails, ProductInput input){
		Set<Integer> set = packDetails.keySet();

		Integer integerArray[] = set.toArray(new Integer[0]);;

		List<Map<Integer, Integer>> finalPackList = new ArrayList<>();
		List<Integer[]> packsCombination = new ArrayList<>();
		packsCombination = Combination.permute(integerArray, 0, packsCombination);

		for (Integer[] integers : packsCombination) {
			Map<Integer, Integer> response = new HashMap<Integer, Integer>();
			int inputQty = input.getQuantity();
			for (int i = 0; i < integers.length; i++) {
				int packSet = inputQty / integers[i];
				if(packSet > 0){
					response.put(integers[i], packSet);
					inputQty = inputQty % integers[i];
				}
			}
			if(inputQty == 0)
				finalPackList.add(response);
		}

		double productCost = 99999;
		Map<Integer, Integer> packInfo = new HashMap<>();

		for(Map<Integer, Integer> finalPacks : finalPackList) {
			double tempCost = 0;
			Map<Integer, Integer> tempPackInfo = new HashMap<>();
			for(Map.Entry<Integer, Integer> packs : finalPacks.entrySet()){
				tempCost += packDetails.get(packs.getKey()) * packs.getValue();
				tempPackInfo.put(packs.getKey(), packs.getValue());
			}

			if(tempCost < productCost){
				productCost = tempCost;
				packInfo = tempPackInfo;
			}
		}

		ProductDetails finalProductDetails = new ProductDetails();
		finalProductDetails.setCode(input.getCode());
		finalProductDetails.setQuantity(input.getQuantity());
		finalProductDetails.setPrice(productCost);
		finalProductDetails.setPackDetails(packInfo);
		return finalProductDetails;
	}
}
