package newsdock.co.kr.DO;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Post {
	private String title;
	private String contents;
	private String writer;
	private String date;
	private String url;
	private String info;
	private SimpleDateFormat sdf_in = new SimpleDateFormat("yy/MM/dd HH:mm");
	private SimpleDateFormat sdf_out = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	public Post(String _title, String _writer, String _date, String _url, String _cont) {
		setTitle(_title);
		setWriter(_writer);
		setDate(_date);
		setUrl(_url);
		setContents(_cont);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		try {
			this.date = sdf_out.format(sdf_in.parse(date));
		} catch(ParseException e) {
			System.out.println("Parse date failed.");
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	public void parseContent() {
		
	}
}
