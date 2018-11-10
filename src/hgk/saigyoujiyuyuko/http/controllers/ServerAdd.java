package hgk.saigyoujiyuyuko.http.controllers;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import hgk.saigyoujiyuyuko.mcmp.daemon.Var.Var;

public class ServerAdd implements HttpHandler{
	Map<String, String> getMap = new HashMap<>();
	
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		/**
		 * 初始化 Init==================================
		 */
		
		OutputStream oStream = exchange.getResponseBody();
		exchange.sendResponseHeaders(200, 0);
		
        String ip = exchange.getRemoteAddress().getHostString();
        int port = exchange.getRemoteAddress().getPort();
		
        
		/**
		 * Get 参数获取==================================
		 */
        
		if (exchange.getRequestURI().getQuery() == null) {
			
			//send
			String response = "Get value Can not be null";
			oStream.write(response.getBytes());
			
			//logger
	        String loggerContent = ip + ":" + port + "->"+ Var.port + " " + exchange.getProtocol() + " " + exchange.getRequestURI() + " NullValue";
	        Var.logger.info(loggerContent, Var.WARN);
			
			oStream.close();
			return;
			
		}
		
		//split 
		String[] keyValue = exchange.getRequestURI().getQuery().split("&");
		
		for(int i=0; i < keyValue.length; i++) {
			//split
			String[] keyAndValue = keyValue[i].split("=");
			this.getMap.put(keyAndValue[0], keyAndValue[1]);
		}
		
		//debug
		//Var.logger.info(getMap.get("qwq"), Var.WARN);
		//System.out.println(keyValue);
		
		
		/**
		 * 信息获取==============================================
		 */
		
		String uuid = this.getMap.get("uuid");
		
		/**
		 * 创建目录==============================================
		 */
		
		//数据目录
		File serversPath =new File("Servers");
		if (serversPath.exists() == false) {
			Var.logger.info("Create the data path", Var.INFO);
			serversPath.mkdirs();
		}
		
		//创建服务器目录
		File serverPath =new File("Servers/" + uuid);
		boolean status = serverPath.mkdirs();
		
		if (status == true) {
			Var.logger.info("Successful create server: " + uuid, Var.INFO);
		}else {
			Var.logger.info("failure create server: " + uuid, Var.INFO);
		}
		
		
		/**
		 * 随便返回点东西===========================================
		 */
		
		//只是为了让他转到string
		String response = "" + status;
		oStream.write(response.getBytes());
		
		
		/**
		 * 日志==========================================
		 */
		
        //Logger
        String loggerContent = ip + ":" + port + "->"+ Var.port + " " + exchange.getProtocol() + " " + exchange.getRequestURI() + " 200 OK";
        Var.logger.info(loggerContent, Var.INFO);
		
        
		/**
		 * 关闭
		 */
        
		oStream.close();
	}

}
