
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
		            String s;
		            //每次读取一行，当改行为空时结束
		            while((s = bufferedReader.readLine()) != null){
		                System.out.println("read:" + s);
		            }
		            bufferedReader.close();
		        }
		        catch(IOException e) {
		            System.out.println(e);
		        }
		    }

		// TODO Auto-generated method stub

	}