package hgk.saigyoujiyuyuko.mcmp.daemon.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import hgk.saigyoujiyuyuko.mcmp.daemon.Var.Var;

public class Logger {
	File logFile =null;
	
	
	public Logger() {
		
		/**
		 * 记录-初始化
		 */
		try {
			//文件夹
			java.io.File path =new java.io.File("log/");
			
			if (path.exists() == false) {
				path.mkdirs();
			}
			
			//文件
			java.io.File file =new java.io.File("log/" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".log" );
			
			if (file.exists() == true) {
				
				/**
				 * 判断同名文件数量
				 */
				
				//计数器
				int sameFileNumber = 0;
				for (int i = 0; i < path.listFiles().length; i++) {
					//文件名
					String filename = path.listFiles()[i].getName();
					
					if (filename.contains(new SimpleDateFormat("yyyy-MM-dd").format(new Date()))) {
						sameFileNumber++;
					}
				}
				
				//重命名
				java.io.File newName =new File("log/" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "-" + sameFileNumber + ".log");
				file.renameTo(newName);
			}
			
			//创建文件
			file.createNewFile();
			this.logFile = file;
			
		}catch (IOException e) {e.printStackTrace();}
		
	}
	
	
	
	/**
	 * @param log 输出内容
	 * @param level 等级
	 * 
	 * @return 日志
	 */
	public void info(String log,int level) {
		
		//获取时间和线程名字
		String date =new SimpleDateFormat("hh:mm:ss").format(new Date());
		
		String threadName = Thread.currentThread().getName();
		threadName = threadName.replaceFirst(threadName.substring(0, 1),threadName.substring(0, 1).toUpperCase());
		
		String levelName = "";
		
		switch (level) {
		case 0:
			levelName = "INFO";
			break;
			
		case 1:
			levelName = "WARN";
			break;
			
		case 2:
			levelName = "ERROR";
			break;

		default:
			levelName = "INFO";
			break;
		}
		
		String out = "[" + date + "/" + threadName + " " + levelName + "] " + log;
		
		System.out.println(out);
		
		try {
			String FileContent = Var.fileTools.ReadFile(this.logFile);
			BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.logFile), "UTF-8"));
			bWriter.write(FileContent + out);
			bWriter.close();
		} catch (IOException e) {e.printStackTrace(); this.info("日志文件读/写失败", Var.ERROR);}
		
	}
}
