package com.sp.xwjlibrary.util;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class JSONUtil {
	
	/**
	 * json解析,没嵌套
	 * @param jsonFile
	 * @return
	 */
	public synchronized static HashMap<String, Object> parseJSON(String jsonFile){
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			JSONObject jsonObject = new JSONObject(jsonFile);
			Iterator<?> iterator = jsonObject.keys();
			String key ;
			Object value ;
			while(iterator.hasNext()){
				key = (String) iterator.next();
				value = jsonObject.get(key);
				map.put(key, value);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return map;
	}

    /**
     * json解析,没嵌套
     * @param jsonObject
     * @return
     */
	public synchronized static HashMap<String, Object> parseJSON(JSONObject jsonObject){
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			Iterator<?> iterator = jsonObject.keys();
			String key ;
			Object value ;
			while(iterator.hasNext()){
				key = (String) iterator.next();
				value = jsonObject.get(key);
				map.put(key, value);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 创建json,指单条数据
	 * @param map
	 * @return
	 */
	public synchronized static JSONObject createJson(HashMap<String,Object> map){

		JSONObject jsonObject = new JSONObject();

		Iterator<Entry<String, Object>> iterator = map.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<String, Object> entry = iterator.next();
			String key = entry.getKey();
			Object value = entry.getValue();
			try {
				jsonObject.put(key, value);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return jsonObject;
	}
	
	/**
	 * 创建jsonArray,指多条数据
	 * @param list
	 * @return
	 */
	public synchronized static JSONArray createJsonArray(ArrayList<HashMap<String,Object>> list){
		JSONArray jsonArray = new JSONArray();
		
		for (HashMap<String, Object> map : list) {
			
			JSONObject jsonObject = new JSONObject();

			Iterator<Entry<String, Object>> iterator = map.entrySet().iterator();
			while(iterator.hasNext()){
				Entry<String, Object> entry = iterator.next();
				String key = entry.getKey();
				Object value = entry.getValue();
				try {
					jsonObject.put(key, value);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			jsonArray.put(jsonObject);
		}
		return jsonArray;
	}

	/**
	 * json解析
	 * @param jsonFile
	 * JSON Sting文件
	 * @param t
	 * 返回文件类型 .Class文件
	 * @return
	 */
	public synchronized static <T> T parseJSON(String jsonFile,Class<T> t){
		Gson gson = new Gson();
		T json = gson.fromJson(jsonFile, t);
		return json;
	}

	public synchronized static <T>  String toJson(Class<T> t){
		Gson gson = new Gson();
		String json = gson.toJson(  t);
		return json;
	}
	public synchronized static  <T>   String toJson(List<T> t) {
		Gson gson = new Gson();
		String json = gson.toJson(  t);
		return json;
	}
	/**
	 * jsonArray解析
	 * @param jsonFile
	 * @param type
	 *            例如： Type type = new TypeToken&ltArrayList&ltAnimeInfo>>(){}.getType();
	 * @return
	 */
	public synchronized static <T> T parseJSONArray(String jsonFile, Type type) {
		Gson gson = new Gson();
		T data = gson.fromJson(jsonFile, type);
		return data;
	}


}
