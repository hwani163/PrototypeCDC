package main;

import run.LetsView;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
//		run();
//		nodictionarayRun();
		Thread thread = new Thread(new LetsView());
		thread.start();
		
		
		
	}
}
