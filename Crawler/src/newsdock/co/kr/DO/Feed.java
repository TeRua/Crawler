package newsdock.co.kr.DO;

import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;

public class Feed {
	private String url;
	private String prefix;
	private Date lastCrawled;
	private String name;
	private HashMap<String, String> tag;
	
	public Feed(String _url, String _prefix, String _name, String _lastCrawled) {
		setUrl(_url);
		setName(_name);
		setLastCrawled(lastCrawled);
		setPrefix(_prefix);
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getLastCrawled() {
		return lastCrawled;
	}

	public void setLastCrawled(Date lastCrawled) {
		this.lastCrawled = lastCrawled;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HashMap<String, String> getTag() {
		return tag;
	}

	public void setTag(ResultSet _tag) {
		this.tag = tag;
	}
	
	
	
	
}
