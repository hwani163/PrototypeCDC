package util;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

import run.LetsView;
import vo.LogMinerConfig;

public class WatchingPaths {

	public static void checkDirectory() {
		try {
			FileSystem fs = FileSystems.getDefault();
			Path watchPath = fs.getPath(new LogMinerConfig().getLogfileDirectory());
			LetsView lb = new LetsView();
			WatchService watchService = fs.newWatchService();
			watchPath.register(watchService,
					StandardWatchEventKinds.ENTRY_CREATE);
			System.out.println(watchPath.toString());

			while (true) {
				try {

					// 지정된 디렉토리에 변경이되는지 이벤트를 모니터링한다.
					WatchKey changeKey = watchService.take();

					List<WatchEvent<?>> watchEvents = changeKey.pollEvents();

					for (WatchEvent<?> watchEvent : watchEvents) {
						// Ours are all Path type events:
						WatchEvent<Path> pathEvent = (WatchEvent<Path>) watchEvent;

						Path path = pathEvent.context();
						WatchEvent.Kind<Path> eventKind = pathEvent.kind();

						System.out.println(eventKind + " for path: " + path.toString());
						lb.letsView(path.toString());
						
					}

					changeKey.reset(); // Important!

				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
