package pers.yuejiang.test.responsedetailoforderitem;
import org.json.*;
import java.io.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStreamReader;
public class ResposeDetailOfSalesoderItem {
	public static void main(String[] args) throws JSONException, IOException {
		{
			File inf = new File("D:/responseDetailsOfSalesorderItem.json");
			FileInputStream is = new FileInputStream(inf);
			JSONTokener tokener = new JSONTokener(is);
			JSONObject obj = new JSONObject(tokener);
			System.out.println(getJSONObjDstNum(obj));
		}
		public static int getObjDstNum(Object obj){
			try{
				JSONArray object = (JSONArray)obj;
				if (object != null){
					