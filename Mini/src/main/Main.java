package main;

import run.RunLogMiner;
import util.WatchingPaths;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		RunLogMiner a = new RunLogMiner();
//		a.makeDictionaryFile();
		run();
		a.closeLogminer();

	}

	private static void run() {
		WatchingPaths.checkDirectory();
		
	}
}
