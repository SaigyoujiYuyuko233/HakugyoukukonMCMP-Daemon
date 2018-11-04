package hgk.saigyoujiyuyuko.mcmp.daemon.Var;

import ch.ubique.inieditor.IniEditor;
import hgk.saigyoujiyuyuko.mcmp.daemon.tools.Logger;

public class Var {
	//Tools
	public static Logger logger =new Logger();
	public static IniEditor iniEditor =new IniEditor();
	
	//Var
	public static String ip = "0.0.0.0";
	public static int port = 6060;
	public static String key = "123123";
}
