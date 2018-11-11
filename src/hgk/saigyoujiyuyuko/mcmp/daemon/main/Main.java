package hgk.saigyoujiyuyuko.mcmp.daemon.main;

import java.io.IOException;

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
		
		//read
		Var.iniEditor.load("Config.ini");
		Var.ip = Var.iniEditor.get("Server", "ip");
		Var.port = Integer.valueOf(Var.iniEditor.get("Server", "port"));
		Var.key = Var.iniEditor.get("Server", "key");
		
		/**
		 * Start Web Server
		 */
		Var.logger.info("Starting the Web Server", Var.INFO);
		Var.http = new Http();
		new Thread(Var.http,"Http").start();
		
		Var.logger.info("Server is running on port: " + Var.port, Var.INFO);
	}
}
