package newsdock.co.kr.DO;

public class Tag {
	private String post;
	private String title;
	private String date;
	private String url;
	private String writer;
	// 페이지에서

	private String content;
	private String info;
	private String reply;
	// 게시글 내에서

	public Tag(String post, String title, String date, String url, String writer, String content, String info, String reply) {
		setPost(post);
		setTitle(title);
		setDate(date);
		setUrl(url);
		setWriter(writer);
		setContent(content);
		setInfo(info);
		setReply(reply);
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

}
