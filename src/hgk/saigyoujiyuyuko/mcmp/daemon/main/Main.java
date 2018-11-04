package hgk.saigyoujiyuyuko.mcmp.daemon.main;

import java.io.IOException;

import hgk.saigyoujiyuyuko.mcmp.daemon.Var.Var;

public class Main {
	public static void main(String[] args) throws IOException {
		System.out.println("Loading libraries, please wait...");
		System.out.println(Var.logger.info("Hakugyoukukon MC Server management - Daemon", 0));
		System.out.println(Var.logger.info("CopyRight (C) 2017-2018 HakugyoukuKonStudio All Right Reserved.", 0));
		
		/**
		 * Read Config
		 */
		System.out.println(Var.logger.info("Reading the Config", 0));
		
		//read
		Var.iniEditor.load("Config.ini");
		Var.ip = Var.iniEditor.get("Server", "ip");
		Var.port = Integer.valueOf(Var.iniEditor.get("Server", "port"));
		Var.key = Var.iniEditor.get("Server", "key");
		
		/**
		 * Start Web Server
		 */
		System.out.println(Var.logger.info("Starting the Web Server", 0));
	}
}
