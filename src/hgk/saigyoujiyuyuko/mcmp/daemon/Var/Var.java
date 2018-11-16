package hgk.saigyoujiyuyuko.mcmp.daemon.Var;

import java.util.HashMap;
import java.util.Map;

import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.listener.ListenerFactory;

import com.sun.net.httpserver.HttpServer;

import ch.ubique.inieditor.IniEditor;
import hgk.saigyoujiyuyuko.ftp.core.Ftp;
import hgk.saigyoujiyuyuko.http.core.Http;
import hgk.saigyoujiyuyuko.mcmp.daemon.core.Container;
import hgk.saigyoujiyuyuko.mcmp.daemon.tools.EncodingConversion;
import hgk.saigyoujiyuyuko.mcmp.daemon.tools.FileTools;
import hgk.saigyoujiyuyuko.mcmp.daemon.tools.Logger;

public class Var {
	
	/**
	 * ======== Config ================
	 */
	
	//Server
	public static String ip = "0.0.0.0";
	public static String key = "123123";
	public static int port = 6060;
	
	//Ftp
	public static String ftpIp = "0.0.0.0";
	public static int ftpPort = 6060;
	
	
	/**
	 *========= Tools=================
	 */
	public static final Logger logger = new Logger();
	public static final IniEditor iniEditor = new IniEditor();
	public static final FileTools fileTools = new FileTools();
	public static final EncodingConversion encodingC =new EncodingConversion();
	
	
	/**
	 *========= Var=================
	 */
	
	//level of err
	public static final int INFO = 0;
	public static final int WARN = 1;
	public static final int ERROR = 2;
	
	//Conteiner
	public static Map<String, Container> conteinerMap =new HashMap<String, Container>();
	public static Map<String, Thread> threadMap =new HashMap<String, Thread>();
	
	
	/**
	 *========= Class=================
	 */
	
	//Http
	public static HttpServer httpServer = null;
	public static Http http = null;
	
	//Ftp
	public static Ftp ftpThread =null;
	public static ListenerFactory listenerFactory =null;
	public static FtpServerFactory ftpServerFactory =null;
}
