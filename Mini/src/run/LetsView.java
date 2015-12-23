package run;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.Queue;

import main.ViewVO;

public class LetsView {
	Queue<ViewVO> queue = new LinkedList<ViewVO>();

	public void letsView(String archiveFile) {
		RunLogMiner init = new RunLogMiner();
		init.addorDeleteLogFile(archiveFile, 1);
		init.startLogMiner();
		queue.addAll(init.excuteView());

		letsShoot(this.queue);
	}

	public void letsShoot(Queue queue) {
		try {
			System.out.println(queue.toString());
			BufferedWriter fw =null;
			System.out.println(queue.isEmpty());
			while (!queue.isEmpty()) {
				ViewVO value = (ViewVO) queue.poll();
				 fw= new BufferedWriter(new FileWriter(
						"example.txt", true));
				fw.write(value.getSeg_owner() + "    " + value.getSql_redo()+"\n");
			}
			if (fw!=null) {
				fw.close();				
			}
			System.out.println("파일쓰기 완료");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
