package run;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.ViewVO;
import util.DatabaseUtil;
import util.LogMinerConfig;

public class RunLogMiner {

	Connection con = DatabaseUtil.getConnection();
	LogMinerConfig logminerConfig = new LogMinerConfig();

	String logfileName = logminerConfig.getLogfileName();
	String logfileDirectory = logminerConfig.getLogfileDirectory();
	String dictionaryFileName = logminerConfig.getDictionaryFileName();
	String dictionaryDirectory = logminerConfig.getDictionaryDirectory();
	String startTime = logminerConfig.getStartTime();
	String endTime = logminerConfig.getEndTime();
	String segOwner = logminerConfig.getSegOwner();
	String segName = logminerConfig.getSegName();

	public boolean makeDictionaryFile() {
		PreparedStatement psmt = null;

		try {
			psmt = con.prepareStatement("call dbms_logmnr_d.build(?,?)");
			psmt.setString(1, dictionaryFileName);
			psmt.setString(2, dictionaryDirectory);
			System.out.println("dictionary file 생성중...");
			psmt.executeUpdate();
			System.out.println("dictionary file 생성완료");
			psmt.close();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean addorDeleteLogFile(String archiveFile,int status) {
		PreparedStatement psmt = null;

		try {
			psmt = con.prepareStatement("call dbms_logmnr.add_logfile(?,?)");
			psmt.setString(1, logfileDirectory+"\\"+archiveFile);
			psmt.setInt(2, status);
			System.out.println("로그파일 추가중");
			psmt.executeUpdate();
			System.out.println("추가완료");
			psmt.close();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}


	public boolean startLogMiner() {
		PreparedStatement psmt = null;

		try {
			psmt = con
					.prepareStatement("call dbms_logmnr.start_logmnr(DictFileName=>?)");
			psmt.setString(1, dictionaryDirectory + "\\" + dictionaryFileName);
//			psmt.setString(2, startTime);
//			psmt.setString(3, endTime);
			psmt.execute();
			System.out.println("logminer Start!");
			psmt.close();
			
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public ArrayList<ViewVO> excuteView() {
		PreparedStatement psmt = null;
		ArrayList<ViewVO> list = null;
		System.out.println("excuteView()");
		try {
			
			psmt = con.prepareStatement("select seg_owner, seg_name, sql_redo, sql_undo " +
					"from v$logmnr_contents where seg_owner=?");
			psmt.setString(1, segOwner);
			ResultSet rs = psmt.executeQuery();

			list = new ArrayList<ViewVO>();
			while (rs.next()) {
				ViewVO view = new ViewVO();
				view.setSeg_owner(rs.getString(1));
				view.setSeg_name(rs.getString(2));
//				view.setOperation(rs.getString(3));
				view.setSql_redo(rs.getString(3));
				view.setSql_undo(rs.getString(4));
				list.add(view);
			}
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	public boolean closeLogminer() {
		PreparedStatement psmt = null;
		try {
			psmt = con.prepareStatement("call dbms_logmnr.end_logmnr()");
			System.out.println("logminer를 종료합니다.");
			psmt.execute();
			System.out.println("logminer 종료됨");
			psmt.close();
			con.close();
			System.out.println("connection을 종료합니다.");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
