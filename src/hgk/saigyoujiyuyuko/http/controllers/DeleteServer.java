package hgk.saigyoujiyuyuko.http.controllers;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import hgk.saigyoujiyuyuko.http.core.TokenV;
import hgk.saigyoujiyuyuko.mcmp.daemon.Var.Var;

public class DeleteServer implements HttpHandler{
	
	Map<String, String> getMap =new HashMap<String,String>();

	/**
	 * 删除服务器
	 */
	
	@SuppressWarnings("deprecation")
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
		
		
		/**
		 * 删除
		 */
		
		//停止服务器
		Var.conteinerMap.get(uuid).send("^C");
		
		//停止线程
		Var.threadMap.get(uuid).stop();
		
		//删除文件
		File server =new File("Servers/" + uuid);
		boolean status = server.delete();
		
		if (status == true) {
			Var.logger.info("Successful delete server: " + uuid, Var.INFO);
		}else {
			Var.logger.info("failure delete server: " + uuid, Var.WARN);
		}
		
		
		/**
		 * 随便发点东西
		 */
		
        OutputStream os = exchange.getResponseBody();
        
        String response = String.valueOf(status);
        
        os.write(response.getBytes());
        os.close();
	}

}
