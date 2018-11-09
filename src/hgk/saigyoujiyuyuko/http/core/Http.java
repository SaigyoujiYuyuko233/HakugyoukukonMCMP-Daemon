package hgk.saigyoujiyuyuko.http.core;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

import hgk.saigyoujiyuyuko.mcmp.daemon.Var.Var;

public class Http implements Runnable{
	Socket socket =null;
	
	@Override
	public void run() {
		//Create a ServerSocket
		try { Var.serverSocket = new ServerSocket(Var.port); } 
		catch (BindException e) { Var.logger.info("无法创建WebServer: 此端口已经被使用",Var.ERROR);e.printStackTrace(); }
		catch (IOException e) { Var.logger.info("无法创建WebServer: I/O错误",Var.ERROR);e.printStackTrace(); }
		
		
		/*===========================*/
		while (true) {
			/*===========================*/
			try {
				//accept
				this.socket =Var.serverSocket.accept();
				
				//Run Thread
				DisposeHttp disposeHttp = new DisposeHttp(this.socket);
				
				//start and rename
				new Thread(disposeHttp,"Http").start();
				
			} catch (IOException e) { Var.logger.info("无法接受连接: I/O错误",Var.ERROR); e.printStackTrace(); }
			/*===========================*/
		}
		/*===========================*/
	}

}
