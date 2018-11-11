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
				String output =container.getOutput() + line + "<br>";
				
				/**
				 * 玩家人数信息
				 */
				
				//获取最大玩家
				if(line.contains("Done") == true) {
					//开启后执行命令
					Thread.sleep(400);
					container.send("list");
				}
				
				if (line.contains("There are") == true) {
					String player = line.split(" ")[4];
					String mp = player.split("/")[1];
					
					container.setMP(Integer.valueOf(mp));
					//System.out.println(mp);
				}
				
					
				//判断是否有玩家进入
				if (line.contains("logged in with entity id") == true) {
					container.setOP(container.getOP() + 1);
					//System.out.println("Join");
				}
				
				//判断是否有玩家离开
				if (line.contains("left the game") == true) {
					container.setOP(container.getOP() - 1);
					//System.err.println("left the game");
				}
				
				//服务器关闭
				if (line.contains("Stopping the server") == true) {
					container.setOP(0);
				}
				
				container.setOutput(output);
			}
			
		} catch (Exception e) {e.printStackTrace();}
		
	}
	
}