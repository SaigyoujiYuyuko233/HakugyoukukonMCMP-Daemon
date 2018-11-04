package hgk.saigyoujiyuyuko.mcmp.daemon.tools;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class File {
	
	/**
	 * @author SaigyoujiYuyuko
	 * 
	 * @param file	文件对象
	 * @param key	获取的key
	 * 
	 * @usage 读取文件全部内容
	 * 
	 * @return String 获取的内容
	 */
	
	
	public String ReadFile(String file)throws IOException {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
		
		String content = "";
	    for (String text = ""; (text = bReader.readLine()) != null;) {
	    	content = content + text + "\n";
	    }
	    
	    bReader.close();
		return content;
	}
}
