package scraper.connection;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public abstract class ConnectionThread implements IonReturn{
	private Document doc;
	
	public ConnectionThread(String url) {
		Thread thread = new Thread() {
			@Override
			public void run() {
					try {
						doc = Jsoup.connect(url).get();
					} catch (IOException e) {
						e.printStackTrace();
					}
					onReturn(doc);
			}
		};
		thread.start();
	}
}
