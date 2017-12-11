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

public class Crawler extends Thread {
	private final double interval = 500; // ms
	private HttpConnector httpCon = null;
	private Feed feed;
	private Tag tag;
	private SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd HH:mm");
	private Connection conn = null;
	
	public Crawler(Feed _feed, Tag _tag, Connection _conn) {
		initCrawler(_feed, _tag, _conn);
	}

	public boolean initCrawler(Feed _feed, Tag _tag, Connection _conn) {
		httpCon = new HttpConnector();
		httpCon.initConnector();
		feed = _feed;
		tag = _tag;
		conn = _conn;
		return true;
	}

	public int startCrawl() {
		String result;
		Page page;

		for (int i = 1; i <= 20; i++) {
			httpCon.setUrl(feed.getUrl(), i);
			result = httpCon.connect();
			Document doc = Jsoup.parse(result);
			// System.out.println(doc);
			// System.out.println(doc.toString());
			System.out.println(tag.getPost());
			page = new Page(doc, feed.getName(), feed.getPrefix());
			dbConector.storePosts(page.parsePost(tag), idx + 1);
			feed.get(idx).setLastCrawled(new Date()); // need to updated on
														// DB***

		}

		return 1; // Success Code
	}
}
