package hgk.saigyoujiyuyuko.http.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import hgk.saigyoujiyuyuko.http.core.TokenV;
import hgk.saigyoujiyuyuko.mcmp.daemon.Var.Var;
import hgk.saigyoujiyuyuko.mcmp.daemon.core.Container;


public class Input implements HttpHandler{
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
	        String loggerWeb = ip + ":" + port + "->"+ Var.port + " " + exchange.getProtocol() + " " + exchange.getRequestURI() + " NullValue";
	        Var.logger.info(loggerWeb, Var.WARN);
			
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
		
		
		/**
		 * 身份验证==============================================
		 */
		
		if (TokenV.Authentication(this.getMap.get("key"), exchange) == false) {
			return;
		}
		
		
		/**
		 * 信息获取==============================================
		 */
		
		String uuid = this.getMap.get("uuid");	//uuid
		String cmd = this.getMap.get("cmd");	//命令
		
		
		/**
		 * 开始判断Conteiner状态
		 */
		
		//线程未启动
		if (Var.conteinerMap.get(uuid) == null) {
			//实例化
			Container container = new Container(uuid);
			Thread thread = new Thread(container);
			
			//放入Map
			Var.conteinerMap.put(uuid, container);
			Var.threadMap.put(uuid, thread);
			
			//启动
			thread.start();
		}
		
		Container container = Var.conteinerMap.get(uuid);
		
		
		/**
		 * 输入命令
		 */
		
		container.send(cmd);
		
		//logger
        String loggerRunning = "[" + uuid + "] Run command: " + cmd;
        Var.logger.info(loggerRunning, Var.INFO);
        
        //String loggerWeb = ip + ":" + port + "->"+ Var.port + " " + exchange.getProtocol() + " " + exchange.getRequestURI() + " 200 OK";
        //Var.logger.info(loggerWeb, Var.INFO);
		
		/**
		 * 随便发点东西
		 */
		
        OutputStream os = exchange.getResponseBody();
        
        String response = "";
        
        os.write(response.getBytes());
        os.close();
	}

}
