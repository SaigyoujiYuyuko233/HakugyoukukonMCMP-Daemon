package hgk.saigyoujiyuyuko.mcmp.daemon.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import hgk.saigyoujiyuyuko.mcmp.daemon.Var.Var;


public class Container implements Runnable{
	Process process = null;
	BufferedReader bReader = null;
	BufferedWriter bWriter = null;
	String uuid = "";
	String outPut = "";
	
	public Container(String uuid) {
		this.uuid =uuid;
	}
	
	
	@Override
	public void run() {
		try {
			String osName = System.getProperties().getProperty("os.name");
			
			if (osName.contains("indows")) {
				this.process = new ProcessBuilder("cmd").start();
			}
			
			if (osName.contains("inux")) {
				this.process = new ProcessBuilder("/bin/bash").start();
			}
			
			
			this.bReader =new BufferedReader(new InputStreamReader(this.process.getInputStream(), "UTF-8"));
			this.bWriter =new BufferedWriter(new OutputStreamWriter(this.process.getOutputStream(), "UTF-8"));
			
			this.bWriter.write("cd Servers/"+uuid+" \n");
			this.bWriter.flush();  //这个是重中之重啊!!!!
			
			//读
			new Thread(new Reader(this.uuid)).start();
			
			
		} catch (IOException e) {Var.logger.info("容器 I/O异常", Var.ERROR);e.printStackTrace();}
	}
	
	
	
	/**
	 * Getter and Setter
	 * @return
	 */
	
	public String getOutput() {
		return this.outPut;
	}
	
	public String getUUID() {
		return this.uuid;
	}
	
	public Process getProcess() {
		return this.process;
	}
	
	public BufferedWriter getBW() {
		return this.bWriter;
	}
	
	public BufferedReader getBR() {
		return this.bReader;
	}
	
	public void send(String cmd) throws IOException {
		this.bWriter.write(cmd+"\n");
		this.bWriter.flush();  //这个是重中之重啊!!!!
	}
	
	public void setOutput(String output) {
		this.outPut = output;
	}
	
	
}


