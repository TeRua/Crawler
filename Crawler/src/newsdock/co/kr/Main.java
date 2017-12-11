package newsdock.co.kr;

public class Main {

	public static void main(String[] args) {
//		System.setProperty("http.proxyHost", "210.101.131.229");
//		System.setProperty("http.proxyPort", "8080");
		Launcher launcher = new Launcher();
		System.out.println("NewsDog Crawler.");
		launcher.init();
		launcher.startCrawling();
	}
}
