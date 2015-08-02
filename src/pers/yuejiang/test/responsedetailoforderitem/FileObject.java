package pers.yuejiang.test.responsedetailoforderitem;


import java.io.IOException;
import java.util.List;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONTokener;

/** 三个功能：JSON节点查询、计数及修改.*/
public class FileObject {
		public static void main(final String[] args) throws JSONException, IOException {
		//读取文件
			JsonInterfaceImplements resultObj1 = new JsonInterfaceImplements();
		String json = resultObj1.ReadFile("./responseDetailsOfSalesorderItem.json"); 
		JSONTokener jsonParser = new JSONTokener(json);
		JSONObject mainObj = (JSONObject) jsonParser.nextValue();

		System.out.println("a.提取出SD_DOC=0000000151, PARTN_ROLE=AG的ORDER_PARTNERS_OUT子节点。");
		JsonInterfaceImplements resultObj2 = new JsonInterfaceImplements();
		@SuppressWarnings("unused")
		List<JSONObject> list = resultObj2.GetJsonObjectByQuery(mainObj, 
				"ORDER_PARTNERS_OUT", "SD_DOC=0000000151,PARTN_ROLE=AG");
		}

}


