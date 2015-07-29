package pers.yuejiang.test.responsedetailoforderitem;

import java.io.IOException;
import java.util.List;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONTokener;

public class FileObject {
	public static void main(String[] args) throws JSONException, IOException {
		//读取文件
		String json = JsonUtils.readFile("D:\\responseDetailsOfSalesorderItem.json");
		JSONTokener jsonTokener = new JSONTokener(json);
		JSONObject jsonObject = (JSONObject) jsonTokener.nextValue();
		
		System.out.println("a.提取出SD_DOC=0000000151, PARTN_ROLE=AG的ORDER_PARTNERS_OUT子节点");
		List<JSONObject> resultObj = JsonUtils.getJsonObjectByQuery(jsonObject, "ORDER_PARTNERS_OUT","SD_DOC=0000000151,PARTN_ROLE=AG");

	}
}
