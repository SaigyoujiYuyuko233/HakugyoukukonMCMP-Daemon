package hgk.saigyoujiyuyuko.http.controllers;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;

import hgk.saigyoujiyuyuko.mcmp.daemon.Var.Var;


public class MainPage implements com.sun.net.httpserver.HttpHandler{
	
	/**
	 * 欢迎页
	 */
	@SuppressWarnings("unused")
	@Override
	public void handle(HttpExchange exchange) throws IOException {
        String response = new String(Var.fileTools.ReadFile(new File("static/hello.html")).getBytes(), "UTF-8");
        
        exchange.sendResponseHeaders(200, 0);
        
        OutputStream os = exchange.getResponseBody();
        
        os.write(response.getBytes());
        os.close();
        
        //Logger
        String ip = exchange.getRemoteAddress().getHostString();
        int port = exchange.getRemoteAddress().getPort();
        
        String loggerContent = ip + ":" + port + "->"+ Var.port + " " + exchange.getProtocol() + " " + exchange.getRequestURI() + " 200 OK";
        //Var.logger.info(loggerContent, Var.INFO);
	}

}
