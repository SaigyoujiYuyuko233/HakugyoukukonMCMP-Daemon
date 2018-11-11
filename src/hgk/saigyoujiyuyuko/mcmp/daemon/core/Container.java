package hgk.saigyoujiyuyuko.mcmp.daemon.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import hgk.saigyoujiyuyuko.mcmp.daemon.Var.Var;


public class Container implements Runnable{
	Process process = null;
	BufferedReader bReader = null;
	BufferedWriter bWriter = null;
	int OnlinePlayer = 0;
	int MaxPlayer =0;
	String uuid = "";
	String outPut = "";
	
	public Container(String uuid) {
		this.uuid =uuid;
	}
	
	
	@Override
	public void run() {
		try {
			
			/**
			 * 判断操作系统 选择相应的shell
			 */
			
			String osName = System.getProperties().getProperty("os.name");
			
			if (osName.contains("indows")) {
				this.process = new ProcessBuilder("cmd").start();
			}
			
			if (osName.contains("inux")) {
				this.process = new ProcessBuilder("/bin/bash").start();
			}
			
			
			/**
			 * 创建流
			 */
			
			this.bReader =new BufferedReader(new InputStreamReader(this.process.getInputStream(),"gbk"));
			this.bWriter =new BufferedWriter(new OutputStreamWriter(this.process.getOutputStream(), "gbk"));
			
			
			/**
			 * 初始化
			 */
			
			//目录存在性检测
			File path =new File("Servers/" + uuid);
			
			if (path.exists() == false) {
		        Var.logger.info("Can not found the data dir of [" + uuid + "]", Var.INFO);
		        Var.logger.info("We will make dir for [" + uuid + "]", Var.INFO);
				path.mkdirs();
			}
			
			//Cd 到目录
			this.bWriter.write("cd Servers/"+uuid+" \n");
			this.bWriter.flush();  //这个是重中之重啊!!!
			
			//启动读线程
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
	
	public BufferedReader getBR() {
		return this.bReader;
	}
	
	public int getOP() {
		return this.OnlinePlayer;
	}
	
	public int getMP() {
		return this.MaxPlayer;
	}
	
	
	public void send(String cmd) throws IOException {
		this.bWriter.write(cmd+"\n");
		this.bWriter.flush();  //这个是重中之重啊!!!!
	}
	
	public void setOP(int op) {
		this.OnlinePlayer = op;
	}
	
	public void setMP(int mp) {
		this.MaxPlayer = mp;
	}
	
	public void setOutput(String output) {
		this.outPut = output;
	}
	
	
}


