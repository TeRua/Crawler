package newsdock.co.kr.DO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import newsdock.co.kr.Connector.HttpConnector;

public class Page {
	// private Date date; // crawled date
	private Document document; // crawled page
	private String feedName;
	private String feedPrefix;
	private HttpConnector hc;

	private void initHC() {
		hc = new HttpConnector();
		hc.initConnector();
	}
	
	public Page(Document doc, String _name) {
		// date = _date;
		document = doc;
		feedName = _name;
		initHC();
	}

	public Page(Document doc, String _name, String _prefix) {
		// date = _date;
		document = doc;
		feedName = _name;
		feedPrefix = _prefix;
		System.out.println("Feed Prefix : " + feedPrefix);
		initHC();
	}

	public ArrayList<Post> parsePost(Tag _tag) {
		ArrayList<Post> res = new ArrayList<Post>();
		// System.out.println(document);
		Elements posts = document.select(_tag.getPost());
		for (Element elem : posts) {
			System.out.println("=====================");
			String title = elem.select(_tag.getTitle()).text();
			String date = elem.select(_tag.getDate()).text();
			String writer = elem.select(_tag.getWriter()).text();
			String url = elem.select(_tag.getUrl()).attr("href");
			System.out.println("Title : " + title);
			System.out.println("Date : " + date);
			System.out.println("Writer : " + writer);
			if (!url.startsWith("http://")) {
				url = feedPrefix + url;
			}
			String cont = getContent(url, _tag.getContent());
			res.add(new Post(elem.select(_tag.getTitle()).text(), elem.select(_tag.getWriter()).text(),
					elem.select(_tag.getDate()).text(), url, cont));
		}
		return res;
	}

	public String getContent(String _url, String _tag) {
		System.out.println("Get Content");
		Document doc = null;
		String cont = null;
		System.out.println("TAG :: " + _tag);
		System.out.println("URL :: " + _url);
		try {
			hc.setUrl(_url);
//			doc = Jsoup.connect(_url).get();
			doc = Jsoup.parse(hc.connect());
			cont = doc.select(_tag).html();
			System.out.println("Cont = " + cont);
			Thread.sleep(500);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cont;
	}
}
