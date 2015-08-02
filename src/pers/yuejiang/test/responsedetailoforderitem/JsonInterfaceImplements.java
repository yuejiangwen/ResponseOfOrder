package pers.yuejiang.test.responsedetailoforderitem;

import java.io.BufferedReader;
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

public class JsonInterfaceImplements implements JsonUtils {


@Override
public String ReadFile(final String readpath) {
	//File file = new File(path);
	//BufferedReader bufferedReader = null;
	String result = "";
	try {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(readpath)); 
		//取消新建File实例
		String tempString = null;
		while ((tempString = bufferedReader.readLine()) != null) {  
		// 一次读入一行，直到读入null为文件结束
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
 * @param writepath 输出路径
 * @param data 数据
 * @throws IOException
 */


/**
 * 条件获取jsonObject
 * @param mainObj 要查询的json对象
 * @param tagetKeyName 目标查询key
 * @param queryString
 * @return resultObj
 */
@Override
public List<JSONObject> GetJsonObjectByQuery(
		final JSONObject mainObj, final String tagetKeyName, final String queryString) {
	List<JSONObject> resultObj = new ArrayList<JSONObject>();
	if (queryString == null) {
		return resultObj;
	}
	//获取指定key的JSON数组
	JSONArray jsonArray = mainObj.getJSONArray(tagetKeyName);
	//分割查询条件
	String[] queryArray = queryString.split(",");
	int len = jsonArray.size();
	for (int i = 0; i < len; i++) {
		JSONObject subObj = jsonArray.getJSONObject(i);
		boolean resultBoolean = true;
		//遍历查询条件，多重条件查询
		if (queryArray.length != 0) {
			for (String querySplit : queryArray) {
				String name = querySplit.split("=")[0];
				String value = querySplit.split("=")[1];
				String item = subObj.getString(name);
				resultBoolean = resultBoolean && item.equals(value);
			}
		}
		//得到符合条件的json对象
		if (resultBoolean) {
			System.out.println(subObj);
			resultObj.add(subObj);
		}
	}
	return resultObj;
}

/**
 * 获取查询条件下jsonObject的数量
 * @param jsonObj json对象
 * @param queryKeyName 查询的key
 * @param queryKeyValue 查询的值
 * @return count
 * @Exception JSONException
 */
}