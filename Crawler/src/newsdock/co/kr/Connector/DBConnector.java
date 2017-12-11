package newsdock.co.kr.Connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import newsdock.co.kr.DO.Feed;
import newsdock.co.kr.DO.Post;
import newsdock.co.kr.DO.Tag;

public class DBConnector {
	private final String host = "jdbc:mysql://localhost:3306/newsdog";
	private String id = "root";
	private String pw = "KMs02580K";
	// DB 호스트, 계정
	private Connection conn;

	// DB 커넥션
	@SuppressWarnings("finally")
	public Connection initConnector() {
		// DBConnector 초기화 => DB 커넥션 획득
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(host, id, pw);
		} catch (SQLException e) {
			System.out.println("DB server can not be connected.");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("JDBC Driver not found.");
		} finally {
			return conn;
		}
	}

	public ArrayList<Feed> loadFeeds() {
		ArrayList<Feed> res = new ArrayList<Feed>();
		String sql = "SELECT * FROM Community;";
		Statement st;
		ResultSet rs;
		if (conn != null) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery(sql);
				while (rs.next()) {
					System.out.println(rs.getString("Prefix"));
					res.add(new Feed(rs.getString("URL"), rs.getString("Prefix"), rs.getString("Name"), rs.getString("Date")));
				}
			} catch (SQLException e) {
				System.out.println("SQL Execute Error");
			}
		} else {
			System.out.println("DB Connection is lost.");
		}
		return res;
	}

	public ArrayList<Tag> loadTags() {
		ArrayList<Tag> res = new ArrayList<Tag>();
		String sql = "SELECT * FROM Tag;";
		Statement st;
		ResultSet rs;
		if (conn != null) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery(sql);
				while (rs.next()) {
					res.add(new Tag(rs.getString("post"), rs.getString("title"), rs.getString("date"), rs.getString("url"),
							rs.getString("writer"), rs.getString("content"), rs.getString("info"),
							rs.getString("reply")));
				}
			} catch (SQLException e) {
				System.out.println("SQL Execute Error");
			}
		} else {
			System.out.println("DB Connection is lost.");
		}
		return res;
	}

	public boolean storePosts(ArrayList<Post> _posts, int com_id) {
		try {
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Post (COM_ID, Title, Writer, Contents, Date, URL) VALUES(?, ?, ?, ?, ?, ?);");
			for (Post post : _posts) {
				pstmt.setInt(1, com_id);
				pstmt.setString(2, post.getTitle());
				pstmt.setString(3, post.getWriter());
				pstmt.setString(4, post.getContents());
				pstmt.setString(5, post.getDate());
				pstmt.setString(6, post.getUrl());
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	public Connection getConnector() {
		return this.conn;
	}

}
