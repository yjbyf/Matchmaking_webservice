package com.baiyufan.utils;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONUtils extends JSONObject{
	
	public JSONUtils(String jsonStr) throws JSONException{
		super(jsonStr);
	}
	
	public Object getValue(String key){
		try {
			String result = (String) this.get(key);
			return result;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
