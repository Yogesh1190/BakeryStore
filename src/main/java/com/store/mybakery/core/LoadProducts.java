package com.store.mybakery.core;

import java.io.FileReader;
import java.io.Reader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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
}
