package hgk.saigyoujiyuyuko.mcmp.daemon.main;

import java.io.IOException;

import hgk.saigyoujiyuyuko.ftp.core.Ftp;
import hgk.saigyoujiyuyuko.http.core.Http;
import hgk.saigyoujiyuyuko.mcmp.daemon.Var.Var;

public class Main {
	public static void main(String[] args) throws IOException {
		/**
		 * Main
		 */
		
		System.out.println("Loading libraries, please wait...");
		Var.logger.info("Hakugyoukukon MC Server management - Daemon", 0);
		Var.logger.info("CopyRight (C) 2017-2018 HakugyoukuKonStudio All Right Reserved.", 0);
		
		
		/**
		 * Read Config
		 */
		
		Var.logger.info("Reading the Config", Var.INFO);
		new ConfigCheck().Check();
		
		
		/**
		 * Start Web Server
		 */
		
		Var.logger.info("Starting the Web Server", Var.INFO);
		Var.logger.info("Starting the Ftp Server", Var.INFO);
		
		Var.http = new Http();
		Thread httpThread = new Thread(Var.http,"Http");
		
		httpThread.start();
		
		if (httpThread.getState().toString().equals("RUNNABLE") == true) {
			Var.logger.info("Http Server is running on port: " + Var.port, Var.INFO);
		}else {
			Var.logger.info("Fail to start http server: " + httpThread.getState().toString(), Var.ERROR);
			System.exit(1);
		}
		
		
		/**
		 * Start FTP Server
		 */
		
		Var.ftpThread = new Ftp();
		Thread ftpThread = new Thread(Var.ftpThread,"Ftp");
		
		ftpThread.start();
		
		if (ftpThread.getState().toString().equals("RUNNABLE") == true) {
			Var.logger.info("Ftp Server is running on port: " + Var.ftpPort, Var.INFO);
		}else {
			Var.logger.info("Fail to start ftp server: " + httpThread.getState().toString(), Var.ERROR);
			System.exit(1);
		}
		
		
	}
}
