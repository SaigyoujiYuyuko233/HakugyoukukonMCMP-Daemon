package hgk.saigyoujiyuyuko.mcmp.daemon.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import hgk.saigyoujiyuyuko.mcmp.daemon.Var.Var;

public class FileTools {
	
	/**
	 * @author SaigyoujiYuyuko
	 * 
	 * @param fileTools	文件对象
	 * @param key	获取的key
	 * 
	 * @usage 读取文件全部内容
	 * 
	 * @return String 获取的内容
	 */
	
	
	public String ReadFile(File file)throws IOException {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
		
		String content = "";
	    for (String text = ""; (text = bReader.readLine()) != null;) {
	    	content = content + text + "\n";
	    }
	    
	    bReader.close();
		return content;
	}
	
	
	/**
	 * @param fileTools 文件对象
	 * @param Path 文件路径[不包含文件名]
	 * @param put 写入内容
	 */
	
	public void WriteFile(File file,String put) {
		try {
			String FileContent = Var.fileTools.ReadFile(file);
			BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
			bWriter.write(FileContent + put);
			bWriter.close();
		} catch (IOException e) {e.printStackTrace(); Var.logger.info("文件 I/O 异常", Var.ERROR);}
	}
	
}
