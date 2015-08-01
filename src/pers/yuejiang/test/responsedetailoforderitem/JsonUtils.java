package pers.yuejiang.test.responsedetailoforderitem;

import java.io.BufferedReader;
//import java.io.File;
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


/**JSON文件节点查询操作工具类. */
public class JsonUtils {

	/**
	 * 读取文件
	 * @param readpath 路径
	 * @return result
	 */
	public static String readFile(final String readpath) {
		//File file = new File(path);
		BufferedReader bufferedReader = null;
		String result = "";
		try {
			bufferedReader = new BufferedReader(new FileReader(readpath)); 
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
	public static void writeFile(final String writepath, final String data) 
			throws IOException {
		Writer write = new FileWriter(writepath);
	    write.write(data);
	    write.flush();
	    write.close();
	}

	/**
	 * 条件获取jsonObject
	 * @param mainObj 要查询的json对象
	 * @param tagetKeyName 目标查询key
	 * @param queryString
	 * @return resultObj
	 */
	public static List<JSONObject> getJsonObjectByQuery(
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
	//计算JSONObject中出现目标值次数
	@SuppressWarnings("unchecked")
	public static int getJSONObjDstNum(final JSONObject jsonObj, 
			final String queryKeyName, final String queryKeyValue)
					throws JSONException {
		int count = 0;
		Iterator<String> iterator = jsonObj.keys();
		while (iterator.hasNext()) {
			String key = iterator.next();
			if (key.equals(queryKeyName)) {
				try {

					if (jsonObj.getString(key).equals(queryKeyValue)) {
						count++;
					}
					continue;
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			//递归（递归存在两种形式，JSONArray和JSONObject）
			Object sub_obj = jsonObj.get(key);
			count += getObjDstNum(sub_obj, queryKeyName, queryKeyValue);
		}
		return count;
	}
	//判断对象属于object还是array
	public static int getObjDstNum(final Object obj, final String queryKeyName,  
			final String queryKeyValue) {
		try {
			JSONArray object = (JSONArray) obj;
			if (object != null) {
				return getJSONArrayDstNum(object, queryKeyName, queryKeyValue);
			}
		} catch (ClassCastException e) {
		}

		try {
			JSONObject object = (JSONObject) obj;
			if (object != null) {
				return getJSONObjDstNum(object, queryKeyName, queryKeyValue);
			}
		} catch (ClassCastException e) {
		}
		return 0;
	}
	//计算JSONArray中出现目标值次数
	public static int getJSONArrayDstNum(final JSONArray array, 
			final String queryKeyName, final String queryKeyValue) {
		int count = 0;
		int len = array.size();
		for (int i = 0; i < len; i++) {
			Object sub_obj = array.get(i);
			count += getObjDstNum(sub_obj, queryKeyName, queryKeyValue);
		}
		return count;
	}
}
