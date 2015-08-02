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
			JsonInterfaceImplements Obj = new JsonInterfaceImplements();
		String json = Obj.ReadFile("./responseDetailsOfSalesorderItem.json"); 
		JSONTokener jsonParser = new JSONTokener(json);
		JSONObject mainObj = (JSONObject) jsonParser.nextValue();

/** JSON�ڵ��ѯ.*/
		System.out.println("a.��ȡ��SD_DOC=0000000151, PARTN_ROLE=AG��ORDER_PARTNERS_OUT�ӽڵ㡣");
		JsonInterfaceImplements jsonUtils = new JsonInterfaceImplements();
		List<JSONObject> list = jsonUtils.GetJsonObjectByQuery(mainObj, 
				"ORDER_PARTNERS_OUT", "SD_DOC=0000000151,PARTN_ROLE=AG");

		
		
/** JSON�ڵ��޸�.*/
		System.out.println("\nc.��SD_DOC=0000000151, PARTN_ROLE=AG��ORDER_PARTNERS_OUT�ӽڵ��LEVEL_NR����Ϊ100, "
        		+ "���������������һ��json�ļ��С�");
		String resultJson = "";
        for (JSONObject subObj : list) {
			System.out.println("�޸�ǰ��"+ subObj);
			//�޸��Ѵ������ݵĽڵ�
			subObj.element("LEVEL_NR", 100);   //100Ϊ�޸Ľڵ�LEVEL_NR����ֵ
			System.out.println("�޸ĺ�" + subObj);
			resultJson += subObj.toString();
		}
        //���·��
		String path = "./responseDetailsOfSalesorderItem2.json"; 
		System.out.println("������ļ���" + path);
		jsonUtils.WriteFile(path, resultJson);

	}
}


