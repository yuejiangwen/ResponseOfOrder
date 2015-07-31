package pers.yuejiang.test.responsedetailoforderitem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class JsonUtils {

	/**
	 * 读取文件
	 * @param path 路径
	 * @return
	 */
	public static String readFile(String path) {
		File file = new File(path);
		BufferedReader bufferedReader = null;
		String result = "";
		try {
			bufferedReader = new BufferedReader(new FileReader(file));
			String tempString = null;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = bufferedReader.readLine()) != null) {
				result = result + tempString;
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 输出文件
	 * @param path 输出路径
	 * @param data 数据
	 * @throws IOException
	 */
	public static void writeFile(String path,String data) throws IOException{
		Writer write = new FileWriter(path);  
	    write.write(data);  
	    write.flush();  
	    write.close(); 
	}
	
	/**
	 * 获取查询条件下jsonObject的数量
	 * @param obj json对象
	 * @param queryKeyName 查询的key
	 * @param queryKeyValue 查询的值
	 * @return
	 * @Exception JSONException
	 */

	public static int getJSONObjDstNum(JSONObject obj,String queryKeyName,String queryKeyValue) throws JSONException {
		int count = 0;
		Iterator<String> iterator = obj.keys();
		while (iterator.hasNext()) {
			String key = iterator.next();
			if (key.equals(queryKeyName)) {
				try {
					
					if (obj.getString(key).equals(queryKeyValue)) {
						count++;
					}
					continue; 
				} catch (JSONException e) {
					e.printStackTrace();
				} finally {
				}
			}
			//递归（递归存在两种形式，JSONArray和JSONObject）
			Object sub_obj = obj.get(key);
			count += getObjDstNum(sub_obj,queryKeyName,queryKeyValue);
		}
		return count;
	}
	
	public static int getJSONArrayDstNum(JSONArray array,String queryKeyName,String queryKeyValue) throws JSONException {
		int count = 0;
		int len = array.size();
		for (int i = 0; i < len; i++) {
			Object sub_obj = array.get(i);
			count += getObjDstNum(sub_obj,queryKeyName,queryKeyValue);
		}
		return count;
	}
	public static int getObjDstNum(Object obj,String queryKeyName,String queryKeyValue) throws JSONException {
		try {
			JSONArray object = (JSONArray) obj;
			if (object != null) {
				return getJSONArrayDstNum(object,queryKeyName,queryKeyValue);
			}
		} catch (ClassCastException e) {
		} finally {
		}

		try {
			JSONObject object = (JSONObject) obj;
			if (object != null) {
				return getJSONObjDstNum(object,queryKeyName,queryKeyValue);
			}
		} catch (ClassCastException e) {
		} finally {
		}
		return 0;
	}
	
	/**
	 * 条件获取jsonObject
	 * @param mainObj 要查询的json对象
	 * @param tagetKeyName 目标查询key
	 * @param queryString 
	 * @return
	 */
	public static List<JSONObject> getJsonObjectByQuery(JSONObject mainObj,String tagetKeyName,String queryString){
		List<JSONObject> resultObj = new ArrayList<JSONObject>();
		if(queryString==null){
			return resultObj;
		}
		//获取指定第一级key的JSON数组
		JSONArray jsonArray = mainObj.getJSONArray(tagetKeyName);
		//分割查询条件
		String[] queryArray = queryString.split(",");
		int len = jsonArray.size();
		for (int i = 0; i < len; i++) {
			JSONObject subObj = jsonArray.getJSONObject(i);
			boolean resultBoolean = true;
			//遍历查询条件，条件联合查询
			if(queryArray.length!=0){
				for (String querySplit : queryArray) {
					String name = querySplit.split("=")[0];
					String value = querySplit.split("=")[1];
					String item = subObj.getString(name);
					resultBoolean = resultBoolean && item.equals(value);
				}
			}
			//得到符合条件的json对象
			if(resultBoolean){
				System.out.println(subObj);
				resultObj.add(subObj);
			}
		}
		return resultObj;
	}
}
