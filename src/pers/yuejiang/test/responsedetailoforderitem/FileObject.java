package pers.yuejiang.test.responsedetailoforderitem;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileObject {
	public static void main(String[] args) {
		try {
			FileInputStream fileInputStream = new FileInputStream("responseDetailsOfSalesorderItem.json");
		    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
		    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		    String string; //ÿ�ζ�ȡһ�У�������Ϊ��ʱ����
		    
		    
		    while((string = bufferedReader.readLine()) != null){
		    
		    	System.out.println("read:" + string);
		    }
		    bufferedReader.close();
		    }
		catch(IOException e) {
			System.out.println(e);
			}
		}
	}