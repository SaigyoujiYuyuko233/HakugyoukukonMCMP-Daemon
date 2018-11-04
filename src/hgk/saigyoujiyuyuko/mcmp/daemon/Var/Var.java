package hgk.saigyoujiyuyuko.mcmp.daemon.Var;

import java.net.ServerSocket;

import ch.ubique.inieditor.IniEditor;
import hgk.saigyoujiyuyuko.http.core.Http;
import hgk.saigyoujiyuyuko.http.core.HttpProtocolMaker;
import hgk.saigyoujiyuyuko.mcmp.daemon.tools.File;
import hgk.saigyoujiyuyuko.mcmp.daemon.tools.GcThread;
import hgk.saigyoujiyuyuko.mcmp.daemon.tools.Logger;

public class Var {
	/**
	 *========= Tools=================
	 */
	public static Logger logger = new Logger();
	public static IniEditor iniEditor = new IniEditor();
	public static File file = new File();
	public static HttpProtocolMaker hpMaker = new HttpProtocolMaker();
	public static GcThread gThread = new GcThread();
	
	
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
	public static ServerSocket serverSocket = null;
	
	//Http
	public static Http http = null;
}
