package newsdock.co.kr.Connector;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class HttpConnector {
	private String url;

	private HttpClient httpClient;
	private HttpGet httpGet; // GET Request
	private HttpResponse httpResponse;
	private HttpEntity httpEntity;

	public void initConnector() {
		httpClient = HttpClientBuilder.create().build();
	}

	public void setUrl(String _url, int page) {
		this.url = _url + page;
		httpGet = new HttpGet(this.url);
		// HttpHost = new HttpPost(this.url);
		System.out.println("Current URL is : " + httpGet.getURI());
	}

	public void setUrl(String _url) {
		this.url = _url;
		httpGet = new HttpGet(this.url);
		// HttpHost = new HttpPost(this.url);
		System.out.println("Current URL is : " + httpGet.getURI());
	}

	public String connect() {
		System.out.println("Connecting to " + httpGet.getURI());
		String res = "None";
		int statusCode = 0;
		try {
			httpResponse = httpClient.execute(httpGet);
			httpEntity = httpResponse.getEntity();
			statusCode = httpResponse.getStatusLine().getStatusCode();
			System.out.println("Status Cod :: " + statusCode);
			res = EntityUtils.toString(httpEntity);
			//웃대 euc-kr 깨짐, 조건문 어떻게 넣을지 고려
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("IOException Error");
		}
		return res;
	}
}
