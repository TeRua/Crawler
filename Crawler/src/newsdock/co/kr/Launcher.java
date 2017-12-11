package newsdock.co.kr;

public class Launcher {
	private Crawler crawler;

	public void init() {
		crawler = new Crawler();
		crawler.initCrawler();
	}

	public void startCrawling() {
		crawler.startCrawl();
	}
}
