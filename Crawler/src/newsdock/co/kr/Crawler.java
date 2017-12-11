package newsdock.co.kr;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import newsdock.co.kr.Connector.DBConnector;
import newsdock.co.kr.Connector.HttpConnector;
import newsdock.co.kr.DO.Feed;
import newsdock.co.kr.DO.Page;
import newsdock.co.kr.DO.Tag;

public class Crawler {
	private final double interval = 500; // ms
	private ArrayList<Feed> feeds;
	private ArrayList<Tag> tags;
	private DBConnector dbConector = new DBConnector();
	private Connection conn = null;
	private HttpConnector httpCon = null;
	private SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd HH:mm");

	public boolean initCrawler() {
		// DBConnector, HTTPConnector 초기화
		conn = this.dbConector.initConnector(); // can be made by Static method.
		// can be skipped to assign at conn, holding conn in DBConnector
		feeds = dbConector.loadFeeds();
		tags = dbConector.loadTags();
		httpCon = new HttpConnector();
		httpCon.initConnector();

		return true;
	}

	public int startCrawl() {
		String result;
		Page page;
		for(int idx = 0 ; idx < feeds.size() ; idx++) {
			for (int i = 1; i <= 20; i++) {
				httpCon.setUrl(feeds.get(idx).getUrl(), i);
				result = httpCon.connect();
				Document doc = Jsoup.parse(result);
//				System.out.println(doc);
//				System.out.println(doc.toString());
				System.out.println(tags.get(idx).getPost());
				page = new Page(doc, feeds.get(idx).getName(), feeds.get(idx).getPrefix());
				dbConector.storePosts(page.parsePost(tags.get(idx)), idx+1); 
				feeds.get(idx).setLastCrawled(new Date()); // need to updated on DB***
				
			}

		}
		return 1; // Success Code
	}
}
