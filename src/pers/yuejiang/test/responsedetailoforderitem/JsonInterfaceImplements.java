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

/** �ӿ�JsonUtils�Ľӿ��嶨�塣 */
public class JsonInterfaceImplements implements JsonUtils {
	@Override
	public String ReadFile(final String readpath) {
		//File file = new File(path);
		//BufferedReader bufferedReader = null;
		String result = "";
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(readpath)); 
			//ȡ���½�Fileʵ��
			String tempString = null;
			while ((tempString = bufferedReader.readLine()) != null) {  
			// һ�ζ���һ�У�ֱ������nullΪ�ļ�����
				result = result + tempString;
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * ����ļ�
	 * @param writepath ���·��
	 * @param data ����
	 * @throws IOException
	 */
	public void WriteFile(final String writepath, final String data) 
			throws IOException {
		Writer write = new FileWriter(writepath);
	    write.write(data);
	    write.flush();
	    write.close();
	}

	/**
	 * ������ȡjsonObject
	 * @param mainObj Ҫ��ѯ��json����
	 * @param tagetKeyName Ŀ���ѯkey
	 * @param queryString
	 * @return resultObj
	 */
	@Override
	public List<JSONObject> GetJsonObjectByQuery(
			final JSONObject mainObj, final String tagetKeyName, 
			final String queryString) {
		List<JSONObject> resultObj = new ArrayList<JSONObject>();
		if (queryString == null) {
			return resultObj;
		}
		//��ȡָ��key��JSON����
		JSONArray jsonArray = mainObj.getJSONArray(tagetKeyName);
		//�ָ��ѯ����
		String[] queryArray = queryString.split(",");
		int len = jsonArray.size();
		for (int i = 0; i < len; i++) {
			JSONObject subObj = jsonArray.getJSONObject(i);
			boolean resultBoolean = true;
			//������ѯ����������������ѯ
			if (queryArray.length != 0) {
				for (String querySplit : queryArray) {
					String name = querySplit.split("=")[0];
					String value = querySplit.split("=")[1];
					String item = subObj.getString(name);
					resultBoolean = resultBoolean && item.equals(value);
				}
			}
			//�õ�����������json����
			if (resultBoolean) {
				System.out.println(subObj);
				resultObj.add(subObj);
			}
		}
		return resultObj;
	}

	/**
	 * ��ȡ��ѯ������jsonObject������
	 * @param jsonObj json����
	 * @param queryKeyName ��ѯ��key
	 * @param queryKeyValue ��ѯ��ֵ
	 * @return count
	 * @Exception JSONException
	 */
	//����JSONObject�г���Ŀ��ֵ����
	@SuppressWarnings("unchecked")
	public int GetJSONObjDstNum(final JSONObject jsonObj, 
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
			//�ݹ飨�ݹ����������ʽ��JSONArray��JSONObject��
			Object sub_obj = jsonObj.get(key);
			count += getObjDstNum(sub_obj, queryKeyName, queryKeyValue);
		}
		return count;
	}
	//�ж϶�������object����array
	public static int getObjDstNum(final Object obj, final String queryKeyName,  
			final String queryKeyValue) {
		try {
			JSONArray object = (JSONArray) obj;
			if (object != null) {
				return getJSONArrayDstNum (object, queryKeyName, queryKeyValue);
			}
		} catch (ClassCastException e) {
		}

		try {
			JSONObject object = (JSONObject) obj;
			if (object != null) {
				JsonInterfaceImplements jsonInterfaceImplement = new JsonInterfaceImplements();
				int coun = jsonInterfaceImplement.GetJSONObjDstNum (object, queryKeyName, queryKeyValue);
				return coun;
			}
		} catch (ClassCastException e) {
		}
		return 0;
	}
	//����JSONArray�г���Ŀ��ֵ����
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