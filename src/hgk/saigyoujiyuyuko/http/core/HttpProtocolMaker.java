package hgk.saigyoujiyuyuko.http.core;

import java.io.UnsupportedEncodingException;

import hgk.saigyoujiyuyuko.mcmp.daemon.Var.Var;

public class HttpProtocolMaker {
	public String httpCode = "200 OK";
	
	/**
	 * @param httpCode 状态码
	 * @param body
	 * 
	 * @return 整个网页
	 */
	
	public String Make(String httpCode,String body) {
		String html = "";
		String header = "HTTP/1.1 " + httpCode + " OK\r\n";
		this.httpCode = httpCode;
		
		header =header + "Server: SaigyoujiYuyukoHttpServer\r\n";
		header =header + "Accept-Ranges: bytes\r\n";
		header =header +  "Vary: Accept-Encoding\r\n";
		header =header + "Content-Encoding: UTF-8\r\n";
		header =header +  "Content-Type: text/html\r\n";
		
		//转码
		 try 
		 {body = new String(body.getBytes("UTF-8"), "UTF-8");} 
		 catch (UnsupportedEncodingException e) {Var.logger.info("不支持此编码", Var.ERROR);e.printStackTrace();}
		
		html =header + "\r\n" + body;
		return html;
	}
	
	public String getCode() {
		return this.httpCode;
	}
	
}
