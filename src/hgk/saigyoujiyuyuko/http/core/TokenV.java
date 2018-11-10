package hgk.saigyoujiyuyuko.http.core;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;

import hgk.saigyoujiyuyuko.mcmp.daemon.Var.Var;

public class TokenV {
	
	/**
	 * 身份验证的
	 * 
	 * @param Key
	 * @return yes/no
	 * @throws IOException 
	 */
	
	public static boolean Authentication(String Key,HttpExchange exchange) throws IOException {
		
		if (Key.equals(Var.key) == false) {	
			//send massage
	        OutputStream os = exchange.getResponseBody();
	        String response = Var.file.ReadFile("static/AuthFail.html");
	        
	        os.write(response.getBytes());
	        os.close();
	        
	        //Logger
	        String ip = exchange.getRemoteAddress().getHostString();
	        int port = exchange.getRemoteAddress().getPort();
	        
	        String loggerContent = ip + ":" + port + "->"+ Var.port + " " + exchange.getProtocol() + " " + exchange.getRequestURI() + " Authentication failed";
	        Var.logger.info(loggerContent, Var.WARN);
	        
	        exchange.close();
	        
	        return false;
		}
		
		return true;
	}
	
}
