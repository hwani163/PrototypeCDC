package run;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import vo.ViewVO;


public class LetsView {
	Queue<ViewVO> queue = new LinkedList<ViewVO>();

	public void letsView(String archiveFile) throws IOException {
		RunLogMiner init = new RunLogMiner();
		init.addorDeleteLogFile(archiveFile, 1);
		init.startLogMiner();
		queue.addAll(init.excuteView());
		System.out.println("큐의 사이즈"+queue.size());
		BufferedWriter fw = new BufferedWriter(new FileWriter("example.txt", true));
		
		while (!queue.isEmpty()) {
			System.out.println(queue.peek().toString());
			letsShoot(queue.poll(),fw);
		}
		System.out.println(queue.toString());
		fw.close();
		
		
	}

	public void letsShoot(ViewVO value,BufferedWriter fw) {
		try {
			
			fw.write(value.getSeg_owner() + "    " + value.getSql_redo() + "\n");
			System.out.println(value.getSeg_owner());


		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
