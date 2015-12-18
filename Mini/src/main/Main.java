package main;

import java.util.ArrayList;

import run.RunLogMiner;
import run.ViewVO;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RunLogMiner init = new RunLogMiner();
		init.makeDictionaryFile();
		init.addorDeleteLogFile(1);
		init.startLogMiner();
		ArrayList<ViewVO> list = init.excuteView();
		init.closeLogminer();
		
		System.out.println(list.toString());
		
	}

}
