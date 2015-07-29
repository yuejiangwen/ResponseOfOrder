package pers.yuejiang.test.responsedetailoforderitem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
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

	public static String readFile(String path) throws FileNotFoundException {
		File file = new File(path);
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = null;
		String result = "";
		try {
			bufferedReader = new BufferedReader(fileReader);// * 用Reader中的read方法读取字符，用字符流读取
			String tempString = null;
			// 一次读取一行，直到读入null为文件结尾
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
	 * 获取查询条件下jsonObject的值
	 */
	
	
	/**
	 * 条件获取jsonObject	 
	 */
	public static List<JSONObject> getJsonObjectByQuery(JSONObject jsonObject,String key,String inqueryString){
		List<JSONObject> resultObj = new ArrayList<JSONObject>();
		if(inqueryString==null){
			return resultObj;
		}
		//获取指定key的JSON数组
		JSONArray jsonArray = jsonObject.getJSONArray(key);
		//分割查询条件
		String[] inqueryArray = inqueryString.split(",");
		int len = jsonArray.size();
		for (int i = 0; i < len; i++) {
			JSONObject subObj = jsonArray.getJSONObject(i);
			boolean resultBoolean = true;
			//遍历查询条件，多重条件查询
			if(inqueryArray.length!=0){
				for (String inqueryS : inqueryArray) {
					String name = inqueryS.split("=")[0];
					String value = inqueryS.split("=")[1];
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
