package hgk.saigyoujiyuyuko.http.controllers;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;

import hgk.saigyoujiyuyuko.mcmp.daemon.Var.Var;


public class MainPage implements com.sun.net.httpserver.HttpHandler{

	@Override
	public void handle(HttpExchange exchange) throws IOException {
        String response = Var.file.ReadFile("static/hello.html");
        
        exchange.sendResponseHeaders(200, 0);
        OutputStream os = exchange.getResponseBody();
        
        os.write(response.getBytes());
        os.close();
        
        //Logger
        String ip = exchange.getRemoteAddress().getHostString();
        int port = exchange.getRemoteAddress().getPort();
        
        String loggerContent = ip + ":" + port + "->"+ Var.port + " " + exchange.getProtocol() + " " + exchange.getRequestURI() + " 200 OK";
        Var.logger.info(loggerContent, Var.INFO);
	}

}
