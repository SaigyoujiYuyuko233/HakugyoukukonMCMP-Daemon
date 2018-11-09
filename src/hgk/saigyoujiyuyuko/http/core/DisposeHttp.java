package hgk.saigyoujiyuyuko.http.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import hgk.saigyoujiyuyuko.mcmp.daemon.Var.Var;

public class DisposeHttp implements Runnable{
	Socket socket =null;
	BufferedReader bReader =null;
	String requestPath = "";
	BufferedWriter bWriter =null;
	String html = "";
	
	public DisposeHttp(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		
		//Server
			/*=============================================================*/
			try {
				
				//Get the input Stream
				this.bReader =new BufferedReader(new InputStreamReader(this.socket.getInputStream(),"UTF-8"));	
				this.bWriter = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
				
				//Get path
				this.requestPath = this.bReader.readLine().split(" ")[1];
				
				// 去掉尾部的 '/'
				if ((this.requestPath.substring(this.requestPath.length() -1 , this.requestPath.length())).equals("/") && this.requestPath.equals("/") == false) {
					this.requestPath = this.requestPath.substring(0, this.requestPath.length() - 1);
				}
				
				//获取客户端信息
				String ip = this.socket.getInetAddress().getHostName();
				int port = this.socket.getPort();

				
				//Debug: path
				//Var.logger.info(this.requestPath, Var.INFO);
				
				//Var.logger.info("[" + ip + ":" + port + "] " + this.requestPath, Var.INFO);
				
				
				//Chose or Route
				switch (this.requestPath) {
					case "/":
						 this.html = Var.hpMaker.Make("200 OK", Var.file.ReadFile("static/hello.html"));
						break;
						
					case "/ServerAdd":
						this.html = Var.hpMaker.Make("200 OK","23333");
						break;
						
					default:
						this.html = Var.hpMaker.Make("404 Not Found", "此控制器不存在");
						break;
				}				
				
				
				//Send
				try {bWriter.write(this.html); } catch (IOException e) { Var.logger.info("读取主页失败: I/O错误", Var.ERROR); e.printStackTrace(); }
				
				
				//Log 日志输出
				Var.logger.info(ip + ":" + port + "->6000 " + this.requestPath + " " + Var.hpMaker.getCode(), Var.INFO);
				
				
				//Close
				this.bWriter.close();
				this.bReader.close();
				this.socket.close();
				
			}
			catch (NullPointerException e) {}
			catch (IOException e) { Var.logger.info("无法接受连接: I/O错误",Var.ERROR); e.printStackTrace(); }
			/*=============================================================*/
			
	}
}
