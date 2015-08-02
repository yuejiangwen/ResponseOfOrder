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
interface JsonUtils {
	String ReadFile (String readpath);
	List<JSONObject> GetJsonObjectByQuery (
			final JSONObject mainObj, final String tagetKeyName, final String queryString);

}