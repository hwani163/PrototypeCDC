package util;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class LogMinerConfig {
	public LogMinerConfig(){
		try{
			JSONParser parser = new JSONParser();
			JSONObject config = (JSONObject) parser.parse(new FileReader("./config/logminer.json"));			
			this.setDictionaryFileName((String)config.get("dictionaryFileName"));
			this.setDictionaryDirectory((String)config.get("dictionaryDirectory"));
			this.setLogfileName((String)config.get("logfileName"));
			this.setLogfileDirectory((String)config.get("logfileDirectory"));
			this.setStartTime((String)config.get("startTime"));
			this.setEndTime((String)config.get("endTime"));
			this.setSegOwner((String)config.get("segOwner"));
			this.setSegName((String)config.get("segName"));			
			System.out.println("Logminer Configuating value Setting conplete!");
			System.out.println(toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Override
	public String toString() {
		return "LogMinerConfig [dictionaryFileName=" + dictionaryFileName
				+ ", dictionaryDirectory=" + dictionaryDirectory
				+ ", logfileName=" + logfileName + ", logfileDirectory="
				+ logfileDirectory + ", startTime=" + startTime + ", endTime="
				+ endTime + ", segOwner=" + segOwner + ", segName=" + segName
				+ "]";
	}
	String dictionaryFileName;
	String dictionaryDirectory;
	String logfileName;
	String logfileDirectory;
	String startTime;
	String endTime;
	String segOwner;
	String segName;
	
	
	public String getDictionaryFileName() {
		return dictionaryFileName;
	}
	public void setDictionaryFileName(String dictionaryFileName) {
		this.dictionaryFileName = dictionaryFileName;
	}
	public String getDictionaryDirectory() {
		return dictionaryDirectory;
	}
	public void setDictionaryDirectory(String dictionaryDirectory) {
		this.dictionaryDirectory = dictionaryDirectory;
	}
	public String getLogfileName() {
		return logfileName;
	}
	public void setLogfileName(String logfileName) {
		this.logfileName = logfileName;
	}
	public String getLogfileDirectory() {
		return logfileDirectory;
	}
	public void setLogfileDirectory(String logfileDirectory) {
		this.logfileDirectory = logfileDirectory;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getSegOwner() {
		return segOwner;
	}
	public void setSegOwner(String segOwner) {
		this.segOwner = segOwner;
	}
	public String getSegName() {
		return segName;
	}
	public void setSegName(String segName) {
		this.segName = segName;
	}
	
	
	

}
