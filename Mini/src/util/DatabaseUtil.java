package util;

import java.sql.Connection;
import java.sql.DriverManager;

import vo.DatabaseConfig;


public class DatabaseUtil {
	
	
	
	public static Connection getConnection(){
		Connection con = null;
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		DatabaseConfig dbconfig = new DatabaseConfig();
		String url = dbconfig.getUrl();
		String id = dbconfig.getId();
		String port = dbconfig.getPort();
		String password = dbconfig.getPassword();
		String sid = dbconfig.getSid();		
		con = DriverManager.getConnection("jdbc:oracle:thin:@"+url+":"+port+":"+sid+"", id, password);		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return con;
	
	}
	


}
