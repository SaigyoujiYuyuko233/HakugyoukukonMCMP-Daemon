package hgk.saigyoujiyuyuko.mcmp.daemon.core;

import java.io.BufferedReader;

import hgk.saigyoujiyuyuko.mcmp.daemon.Var.Var;

public class Reader implements Runnable{
	String uuid = "";
	
	public Reader(String uuid) {
		this.uuid =uuid;
	}
	
	@Override
	public void run() {
		Container container = Var.conteinerMap.get(this.uuid);
		
		try {
			BufferedReader bReader =container.getBR();
			
			for(String line="";(line=bReader.readLine()) != null;) {
				container.setOutput(container.getOutput() + line + "<br>");
			}
			
		} catch (Exception e) {e.printStackTrace();}
		
	}
	
}