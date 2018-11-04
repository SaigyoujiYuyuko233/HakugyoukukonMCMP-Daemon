package hgk.saigyoujiyuyuko.mcmp.daemon.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
	
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
		
		System.out.println("[" + date + "/" + threadName + " " + levelName + "] " + log);
	}
	
}
