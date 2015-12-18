package run;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

	public void makeDictionaryFile() {
		PreparedStatement psmt = null;

		try {
			psmt = con.prepareStatement("call dbms_logmnr_d.build(?,?)");
			psmt.setString(1, dictionaryFileName);
			psmt.setString(2, dictionaryDirectory);
			System.out.println("dictionary file 생성중...");
			psmt.executeUpdate();
			System.out.println("dictionary file 생성완료");
			psmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addorDeleteLogFile(int status) {
		PreparedStatement psmt = null;

		try {
			psmt = con.prepareStatement("call dbms_logmnr.add_logfile(?,?)");
			psmt.setString(1, logfileDirectory + "/" + logfileName);
			psmt.setInt(2, status);
			System.out.println("로그파일 추가중");
			psmt.executeUpdate();
			System.out.println("추가완료");
			psmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void startLogMiner() {
		PreparedStatement psmt = null;

		try {
			psmt = con
					.prepareStatement("call dbms_logmnr.start_logmnr(DictFileName=>?"
							+ ",startTime=>to_date(?,'yyyy/mm/dd hh24:mi:ss')"
							+ ",endTime=>to_date(?,'yyyy/mm/dd hh24:mi:ss'))");
			psmt.setString(1, dictionaryDirectory + "\\" + dictionaryFileName);
			psmt.setString(2, startTime);
			psmt.setString(3, endTime);
			psmt.execute();
			System.out.println("logminer Start!");
			psmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<ViewVO> excuteView() {
		PreparedStatement psmt = null;
		ArrayList<ViewVO> list = null;
		try {
			psmt = con.prepareStatement("select"
					+ " seg_owner, seg_name, operation, sql_redo, sql_undo "
					+ "from v$logmnr_contents " + "where 1=1"
					+ "and seg_name=?" + "and seg_owner=?");

			psmt.setString(1, segName);
			psmt.setString(2, segOwner);
			ResultSet rs = psmt.executeQuery();

			list = new ArrayList<ViewVO>();
			while (rs.next()) {
				ViewVO view = new ViewVO();
				view.setSeg_owner(rs.getString(1));
				view.setSeg_name(rs.getString(2));
				view.setOperation(rs.getString(3));
				view.setSql_redo(rs.getString(4));
				view.setSql_undo(rs.getString(5));
				list.add(view);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	public void closeLogminer() {
		PreparedStatement psmt = null;
		try {
			psmt = con.prepareStatement("call dbms_logmnr.end_logmnr()");
			System.out.println("logminer를 종료합니다.");
			psmt.execute();
			System.out.println("logminer 종료됨");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}