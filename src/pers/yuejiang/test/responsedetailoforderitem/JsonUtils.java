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
import net.sf.json.util.JSONTokener;


/**JSON文件节点查询操作工具类. */
interface JsonUtils {
	String ReadFile (String readpath);
	List<JSONObject> GetJsonObjectByQuery (
			final JSONObject mainObj, final String tagetKeyName, 
			final String queryString);
	int GetJSONObjDstNum(final JSONObject jsonObj, 
			final String queryKeyName, final String queryKeyValue);
	void WriteFile(final String writepath, final String data) 
			throws IOException;
	

public static void main(final String[] args) 
		throws JSONException, IOException {
	//读取文件
		JsonInterfaceImplements Obj = new JsonInterfaceImplements();
	String json = Obj.ReadFile("./responseDetailsOfSalesorderItem.json"); 
	JSONTokener jsonParser = new JSONTokener(json);
	JSONObject mainObj = (JSONObject) jsonParser.nextValue();

/** JSON节点查询.*/
	System.out.println("a.提取出SD_DOC=0000000151, "
			+ "PARTN_ROLE=AG的ORDER_PARTNERS_OUT子节点。");
	JsonInterfaceImplements jsonUtils = new JsonInterfaceImplements();
	List<JSONObject> list = jsonUtils.GetJsonObjectByQuery(mainObj, 
			"ORDER_PARTNERS_OUT", "SD_DOC=0000000151,PARTN_ROLE=AG");

/** JSON节点计数.*/
	System.out.println("\nb.统计一下SD_DOC=0000000151一共出现了几次。");
    System.out.println("SD_DOC=0000000151一共出现了" 
    		+ jsonUtils.GetJSONObjDstNum (mainObj, "SD_DOC", "0000000151") + "次。");
	
/** JSON节点修改.*/
	System.out.println("\nc.将SD_DOC=0000000151, "
			+ "PARTN_ROLE=AG的ORDER_PARTNERS_OUT子节点的LEVEL_NR设置为100, "
    		+ "并将结果保存在另一个json文件中。");
	String resultJson = "";
    for (JSONObject subObj : list) {
		System.out.println("修改前："+ subObj);
		//修改已存在数据的节点
		subObj.element("LEVEL_NR", 100);   //100为修改节点LEVEL_NR后数值
		System.out.println("修改后：" + subObj);
		resultJson += subObj.toString();
	}
    //输出路径
	String path = "./responseDetailsOfSalesorderItem2.json"; 
	System.out.println("输出到文件：" + path);
	jsonUtils.WriteFile(path, resultJson);

}
}