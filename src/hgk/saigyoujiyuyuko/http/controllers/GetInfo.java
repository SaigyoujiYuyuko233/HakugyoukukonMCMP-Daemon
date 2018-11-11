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

public class GetInfo implements HttpHandler {
	Map<String, String> getMap =new HashMap<String,String>();

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		
		/**
		 * 初始化 Init==================================
		 */
		
		OutputStream oStream = exchange.getResponseBody();
		
		//Header
		exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*"); //跨域请求同意
		
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
		 * 线程状态
		 */
		
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
		 * 获取内容 + 发送
		 */
		
        OutputStream os = exchange.getResponseBody();
        
        //获取
        int op = container.getOP();
        int mp = container.getMP();
        
        //转换
        String json = "{" + "\"online\":" + op + ",";
        json = json + "\"max\":" + mp + "}";
        
        os.write(json.getBytes());
        os.close();
	}

}
