package hgk.saigyoujiyuyuko.http.core;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.spi.HttpServerProvider;

import hgk.saigyoujiyuyuko.http.controllers.DeleteServer;
import hgk.saigyoujiyuyuko.http.controllers.GetInfo;
import hgk.saigyoujiyuyuko.http.controllers.Input;
import hgk.saigyoujiyuyuko.http.controllers.MainPage;
import hgk.saigyoujiyuyuko.http.controllers.Output;
import hgk.saigyoujiyuyuko.http.controllers.ServerAdd;
import hgk.saigyoujiyuyuko.mcmp.daemon.Var.Var;

public class Http implements Runnable{
	public Http() {
		@SuppressWarnings("unused")
		HttpServerProvider httpServerProvider =HttpServerProvider.provider();
		
		try {
			Var.httpServer = HttpServer.create(new InetSocketAddress(Var.ip, Var.port), 0);
		} catch (IOException e) { Var.logger.info("I/O 流异常 创建Http服务器失败", Var.ERROR); e.printStackTrace(); }
	}

	@Override
	public void run() {
		/**
		 * 路由
		 */
		
		//Main
		Var.httpServer.createContext("/",new MainPage());
		
		//功能
		Var.httpServer.createContext("/ServerAdd", new ServerAdd());
		Var.httpServer.createContext("/ServerDelete", new DeleteServer());
		Var.httpServer.createContext("/GetInfo", new GetInfo());
		
		//服务器
		Var.httpServer.createContext("/ServerConsole/Output", new Output());
		Var.httpServer.createContext("/ServerConsole/Input", new Input());

		
		/**
		 * 启动
		 */
		
		Var.httpServer.start();
	}
	
}
