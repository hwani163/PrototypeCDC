package vo;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DatabaseConfig {
	public DatabaseConfig(){
		try{
			JSONParser parser = new JSONParser();
			JSONObject config = (JSONObject) parser.parse(new FileReader("./config/sourceDBconfig.json"));
			
			this.setUrl((String)config.get("url"));
			this.setId((String)config.get("id"));
			this.setPassword((String)config.get("password"));
			this.setSid((String)config.get("sid"));
			this.setPort((String)config.get("port"));
			System.out.println("Database configuartion Setting Success!");
			System.out.println(toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	String url;
	String port;
	String id;
	String password;
	String sid;
	
	@Override
	public String toString() {
		return "DatabaseConfig [url=" + url + ", port=" + port + ", id=" + id
				+ ", password=" + password + ", sid=" + sid + "]";
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	

}
