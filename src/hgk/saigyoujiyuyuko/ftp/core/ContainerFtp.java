package hgk.saigyoujiyuyuko.ftp.core;

import java.util.ArrayList;
import java.util.List;

import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.apache.ftpserver.usermanager.impl.WritePermission;

import hgk.saigyoujiyuyuko.mcmp.daemon.Var.Var;

public class ContainerFtp {
	
	/**
	 * @param name 用户名
	 * @param pass 密码
	 * @param path 路径
	 */
	
	public void addUser(String name,String pass,String path) {
		
		/**
		 * Make user
		 */
		
		//new a obj
		BaseUser user =new BaseUser();
		
		//info
		user.setName(name);
		user.setPassword(pass);
		user.setHomeDirectory(path);
		
		//Authorities
		List<Authority> authorities = new ArrayList<Authority>();
		authorities.add(new WritePermission());
		
		//set 
		user.setAuthorities(authorities);
		
		//save user
		try {
			
			Var.ftpServerFactory.getUserManager().save(user);
			
		} catch (FtpException e) {Var.logger.info("FTP用户创建失败: " + name, Var.ERROR);e.printStackTrace();}
		
	}
	
}
