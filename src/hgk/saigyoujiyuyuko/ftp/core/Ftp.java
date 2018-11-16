package hgk.saigyoujiyuyuko.ftp.core;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.listener.ListenerFactory;

import hgk.saigyoujiyuyuko.mcmp.daemon.Var.Var;

public class Ftp implements Runnable{
	public Ftp() {
		//Var.ftpServer = new ftp
	}
	
	@Override
	public void run() {
		
		/**
		 * create ftp
		 */
		
		//new 
		Var.listenerFactory =new ListenerFactory();
		
		//bind
		Var.listenerFactory.setPort(Var.ftpPort);
		
		//listener
		Var.ftpServerFactory = new FtpServerFactory();
		Var.ftpServerFactory.addListener("default", Var.listenerFactory.createListener());
		
        FtpServer server = Var.ftpServerFactory.createServer();  
        try {
			server.start();
		} catch (FtpException e) {e.printStackTrace(); Var.logger.info("Ftp服务启动失败", Var.ERROR); System.exit(4);}
	}
	
}
