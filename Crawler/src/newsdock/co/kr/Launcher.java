package newsdock.co.kr;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import newsdock.co.kr.Connector.DBConnector;
import newsdock.co.kr.DO.Feed;
import newsdock.co.kr.DO.Tag;

public class Launcher {
	// private Crawler crawler;

	private ArrayList<Feed> feeds;
	private ArrayList<Tag> tags;
	private DBConnector dbConnector;
	private Connection conn = null;
	
	public void init() {
		// Get DB Connection.
		try {
			if (conn.isClosed() || conn.equals(null)) {
				// Check whether connection is closed or created.
				dbConnector = new DBConnector();
				conn = dbConnector.initConnector();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Acquire Feeds.
		feeds = dbConnector.loadFeeds();
		// Acquire Tag Informations.
		tags = dbConnector.loadTags();

	}

	public void startCrawling() {

	}
}
