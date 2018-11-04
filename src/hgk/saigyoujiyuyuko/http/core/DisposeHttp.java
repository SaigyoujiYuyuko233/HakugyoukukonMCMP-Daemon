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
				
				//Debug: path
				Var.logger.info(requestPath, Var.INFO);
				
				//Chose
				switch (this.requestPath) {
				case "/":
					 this.html = Var.hpMaker.Make(200, Var.file.ReadFile("static/hello.html"));
					break;
					
				case "/qwq/":
					this.html = Var.hpMaker.Make(200,"测试一下路由");
					break;

				default:
					this.html = Var.hpMaker.Make(200, Var.file.ReadFile("static/hello.html"));
					break;
				}				
				
				
				//Send
				try {bWriter.write(this.html); } catch (IOException e) { Var.logger.info("读取主页失败: I/O错误", Var.ERROR); e.printStackTrace(); }
				
				//flush
				this.bWriter.flush();
				
				//Close
				this.bWriter.close();
				this.bReader.close();
				this.socket.close();
				
			} catch (IOException e) { Var.logger.info("无法接受连接: I/O错误",Var.ERROR); e.printStackTrace(); }
			/*=============================================================*/
			
	}
}
