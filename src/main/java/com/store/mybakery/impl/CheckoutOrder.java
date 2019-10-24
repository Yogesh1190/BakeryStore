package com.store.mybakery.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONObject;

import com.store.mybakery.model.ProductDetails;
import com.store.mybakery.model.ProductInput;
import com.store.mybakery.util.Permutation;
import com.store.mybakery.util.Constants;

/**
 * 
 * @author Yogesh Shisode
 * @description This class is used to calculate the price as per input request and as per pack structure.
 * 
 */
public class CheckoutOrder {

	/**
	 * This function is responsible for calculating best match product packs as per the users input.
	 * This is find out best product at low price.
	 * @param products	This contains all available product details from bakery store.
	 * @param packDetails	This contains matched product with pack quantity and price.
	 * @param input	This contains users input.
	 * @return This will return class object which has best match product pack value.
	 */
	public ProductDetails calculate(JSONObject products, Map<Integer, Double> packDetails, ProductInput input){
		Set<Integer> set = packDetails.keySet();

		Integer integerArray[] = set.toArray(new Integer[0]);;

		List<Map<Integer, Integer>> finalPackList = new ArrayList<>();
		List<Integer[]> packsCombination = new ArrayList<>();
		packsCombination = Permutation.permute(integerArray, 0, packsCombination);

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

		double productCost = Constants.MAX_VAL_CONST;
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
