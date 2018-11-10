package hgk.saigyoujiyuyuko.mcmp.daemon.Var;

import com.sun.net.httpserver.HttpServer;

import ch.ubique.inieditor.IniEditor;
import hgk.saigyoujiyuyuko.http.core.Http;
import hgk.saigyoujiyuyuko.mcmp.daemon.tools.EncodingConversion;
import hgk.saigyoujiyuyuko.mcmp.daemon.tools.File;
import hgk.saigyoujiyuyuko.mcmp.daemon.tools.Logger;

public class Var {
	/**
	 *========= Tools=================
	 */
	public static final Logger logger = new Logger();
	public static final IniEditor iniEditor = new IniEditor();
	public static final File file = new File();
	public static final EncodingConversion encodingC =new EncodingConversion();
	
	
	/**
	 *========= Var=================
	 */
	//config
	public static String ip = "0.0.0.0";
	public static int port = 6060;
	public static String key = "123123";
	
	//level of err
	public static final int INFO = 0;
	public static final int WARN = 1;
	public static final int ERROR = 2;
	
	
	/**
	 *========= Class=================
	 */
	public static HttpServer httpServer = null;
	
	//Http
	public static Http http = null;
}
