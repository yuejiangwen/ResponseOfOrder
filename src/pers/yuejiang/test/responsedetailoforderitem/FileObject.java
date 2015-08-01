package pers.yuejiang.test.responsedetailoforderitem;


import java.io.IOException;
import java.util.List;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONTokener;

/** �������ܣ�JSON�ڵ��ѯ�������� �޸�.*/
public class FileObject {
		public static void main(final String[] args) throws JSONException, IOException {
		//��ȡ�ļ�
		String json = JsonUtils.readFile("./responseDetailsOfSalesorderItem.json");//responseDetailsOfSalesorderItem
		JSONTokener jsonParser = new JSONTokener(json);
		JSONObject mainObj = (JSONObject) jsonParser.nextValue();

		System.out.println("a.��ȡ��SD_DOC=0000000151, PARTN_ROLE=AG��ORDER_PARTNERS_OUT�ӽڵ�");
		List<JSONObject> resultObj = JsonUtils.getJsonObjectByQuery(mainObj, "ORDER_PARTNERS_OUT", "SD_DOC=0000000151,PARTN_ROLE=AG");

		System.out.println("\nb.ͳ��һ��SD_DOC=0000000151һ�������˼���");
		int count = JsonUtils.getJSONObjDstNum(mainObj, "SD_DOC", "0000000151");
        System.out.println("SD_DOC=0000000151һ��������" + count + "��");

        System.out.println("\nc.��SD_DOC=0000000151, PARTN_ROLE=AG��ORDER_PARTNERS_OUT�ӽڵ��LEVEL_NR����Ϊ100, ���������������һ��json�ļ���");
		String resultJson = "";
        for (JSONObject subObj : resultObj) {
			System.out.println("�޸�ǰ��"+ subObj);
			//�޸��Ѵ������ݵĽڵ�
			subObj.element("LEVEL_NR", 100);   //�޸Ľڵ���ֵΪ100
			System.out.println("�޸ĺ�"+ subObj);
			resultJson += subObj.toString();
		}
        //���·��
		String path = "./responseDetailsOfSalesorderItem2.json"; //responseDetailsOfSalesorderItem
		System.out.println("������ļ���" + path);
		JsonUtils.writeFile(path, resultJson);

	}
}


