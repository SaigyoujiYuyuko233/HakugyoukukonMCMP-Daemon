package hgk.saigyoujiyuyuko.mcmp.daemon.main;

import java.io.File;
import java.io.IOException;

import hgk.saigyoujiyuyuko.mcmp.daemon.Var.Var;
import hgk.saigyoujiyuyuko.mcmp.daemon.exception.CreateFileException;

public class ConfigCheck {
	
	public void Check(){
		
		try {
			
			/**
			 * 文件是否存在 - is fileTools exits
			 */
			
			File config =new File("Config.ini");
			
			//不存在则生成
			if (config.exists() == false) {
				
				//失败抛出err
				if (config.createNewFile() == false) {
					throw new CreateFileException();
				}
				
				String cfg = "[Server]\r\n" + 
									"# 服务器监听的IP 默认:0.0.0.0\r\n" + 
									"Ip=0.0.0.0\r\n" + 
									"# 服务器监听的端口 默认:6060\r\n" + 
									"Port=6060\r\n" + 
									"# ajax密码\r\n" + 
									"Key=123123\r\n" + 
									"\r\n" + 
									"# 面板添加如下\r\n" + 
									"# AJAX地址: 192.168.31.128:20220 [监听ip:监听端口]\r\n" + 
									"# 连接密码: 123123 [key]\r\n" + 
									"\r\n" + 
									"[Ftp]\r\n" + 
									"# ftp 服务器监听的ip\r\n" + 
									"Ip=0.0.0.0\r\n" + 
									"# ftp 服务器监听的端口\r\n" + 
									"Port=22";
				
				//写入
				Var.fileTools.WriteFile(config, cfg);
			}
			
			
			/**
			 * 读取
			 */
			
			Var.iniEditor.load("Config.ini");
			
			//Server config
			Var.ip = Var.iniEditor.get("Server", "Ip");
			Var.port = Integer.valueOf(Var.iniEditor.get("Server", "Port"));
			Var.key = Var.iniEditor.get("Server", "Key");
			
			//Ftp config
			Var.ftpIp = Var.iniEditor.get("Ftp", "Ip");
			Var.ftpPort = Integer.valueOf(Var.iniEditor.get("Ftp", "Port"));
		} 
		
		catch (CreateFileException e) {e.printStackTrace(); Var.logger.info("读取配置失败: 无法创建配置文件", Var.ERROR); System.exit(-1);}
		catch (IOException e) {e.printStackTrace(); Var.logger.info("读取配置失败: I/O错误", Var.ERROR); System.exit(-1);}
		
		
	}
	
}
