package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class restclient {

	// Get Method
	public CloseableHttpResponse Get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);// Http get request
		CloseableHttpResponse httpresp = httpclient.execute(httpget);

		return httpresp;

	}

	// Get Method
	public CloseableHttpResponse Get(String url, HashMap<String, String> headermap)
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);// Http get request
		for (Map.Entry<String, String> entry : headermap.entrySet()) {
			httpget.addHeader(entry.getKey(), entry.getValue());
		}

		CloseableHttpResponse httpresp = httpclient.execute(httpget);

		return httpresp;

	}

	// Get Method
	public CloseableHttpResponse  Post (String url,String entityString, HashMap<String, String> headermap)
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);
		httppost.setEntity(new StringEntity(entityString)); // For Payload
		
		for (Map.Entry<String, String> entry : headermap.entrySet()) {
			httppost.addHeader(entry.getKey(), entry.getValue());
		}
		
		CloseableHttpResponse httpresponse = httpclient.execute(httppost);

		return httpresponse;
	}
}
