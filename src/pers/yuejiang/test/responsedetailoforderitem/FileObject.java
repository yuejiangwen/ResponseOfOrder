package pers.yuejiang.test.responsedetailoforderitem;


import java.io.IOException;
import java.util.List;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONTokener;

/** �������ܣ�JSON�ڵ��ѯ���������޸�.*/
public class FileObject {
		public static void main(final String[] args) throws JSONException, IOException {
		//��ȡ�ļ�
			JsonInterfaceImplements resultObj1 = new JsonInterfaceImplements();
		String json = resultObj1.ReadFile("./responseDetailsOfSalesorderItem.json"); 
		JSONTokener jsonParser = new JSONTokener(json);
		JSONObject mainObj = (JSONObject) jsonParser.nextValue();

		System.out.println("a.��ȡ��SD_DOC=0000000151, PARTN_ROLE=AG��ORDER_PARTNERS_OUT�ӽڵ㡣");
		JsonInterfaceImplements resultObj2 = new JsonInterfaceImplements();
		@SuppressWarnings("unused")
		List<JSONObject> list = resultObj2.GetJsonObjectByQuery(mainObj, 
				"ORDER_PARTNERS_OUT", "SD_DOC=0000000151,PARTN_ROLE=AG");
		}

}


