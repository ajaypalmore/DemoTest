package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.restclient;
import com.qa.util.TestUtil;

public class GetTest extends TestBase{
	TestBase testbase;
	String serviceURL;
	String APIURL;
	String appURL;
	CloseableHttpResponse httpresp;
	
	@BeforeMethod
	public void StepUp() throws ClientProtocolException, IOException
	{
		testbase = new TestBase();
		serviceURL = prop.getProperty("URL");
		APIURL = prop.getProperty("serviceurl");
		appURL = serviceURL+APIURL;
	
	}
	
	@Test
	public void GetTestMethod() throws ClientProtocolException, IOException
	{
		restclient restclt  = new restclient();
		httpresp = restclt.Get(appURL);
		// String value Assertion
		int statusCode = httpresp.getStatusLine().getStatusCode();
		System.out.println("Respone Code ----->"+statusCode);
		Assert.assertEquals(statusCode, RESPONSE_STATUS_200," Status code not Match" );
		
		String response = EntityUtils.toString(httpresp.getEntity(),"UTF-8");
		JSONObject respjson = new JSONObject(response);
		System.out.println("Respone Json from API ----->"+respjson);
		
		String perpagevaule = TestUtil.getValueByJPath(respjson, "/per_page");
		System.out.println("per_page value ----->"+perpagevaule);
		Assert.assertEquals(Integer.parseInt(perpagevaule), 3);
		
		String totalvaule = TestUtil.getValueByJPath(respjson, "/total");
		System.out.println("total vaule ----->"+totalvaule);
		Assert.assertEquals(Integer.parseInt(totalvaule), 12);
		
		// Get Value from JSON
		String lastname = TestUtil.getValueByJPath(respjson, "/data[0]/last_name");
		String id = TestUtil.getValueByJPath(respjson, "/data[0]/id");
		String avatar = TestUtil.getValueByJPath(respjson, "/data[0]/avatar");
		String firstname = TestUtil.getValueByJPath(respjson, "/data[0]/first_name");
		
		System.out.println("last_name ----->"+lastname);
		System.out.println("Id ----->"+id);
		System.out.println("Avatar ----->"+avatar);
		System.out.println("first_name ----->"+firstname);
		
		Header[] jsonheaderArr = httpresp.getAllHeaders();
		
		HashMap<String,String> allheader = new HashMap<String, String>();
		
		for (Header header:jsonheaderArr )
		{
			allheader.put(header.getName(), header.getValue());
		}
		
		System.out.println("Respone Json Header from API ----->"+allheader);
		
	}
	
	
	
	
	@Test
	public void GetTestWithHeader() throws ClientProtocolException, IOException
	{
		restclient restclt  = new restclient();
	HashMap<String,String> headerMap = new HashMap<String, String >();
	headerMap.put("Content-Type","application/json");
		
		
		httpresp = restclt.Get(appURL,headerMap);
		// String value Assertion
		int statusCode = httpresp.getStatusLine().getStatusCode();
		System.out.println("Respone Code ----->"+statusCode);
		Assert.assertEquals(statusCode, RESPONSE_STATUS_200," Status code not Match" );
		
		String response = EntityUtils.toString(httpresp.getEntity(),"UTF-8");
		JSONObject respjson = new JSONObject(response);
		System.out.println("Respone Json from API ----->"+respjson);
		
		String perpagevaule = TestUtil.getValueByJPath(respjson, "/per_page");
		System.out.println("per_page value ----->"+perpagevaule);
		Assert.assertEquals(Integer.parseInt(perpagevaule), 3);
		
		String totalvaule = TestUtil.getValueByJPath(respjson, "/total");
		System.out.println("total vaule ----->"+totalvaule);
		Assert.assertEquals(Integer.parseInt(totalvaule), 12);
		
		// Get Value from JSON
		String lastname = TestUtil.getValueByJPath(respjson, "/data[0]/last_name");
		String id = TestUtil.getValueByJPath(respjson, "/data[0]/id");
		String avatar = TestUtil.getValueByJPath(respjson, "/data[0]/avatar");
		String firstname = TestUtil.getValueByJPath(respjson, "/data[0]/first_name");
		
		System.out.println("last_name ----->"+lastname);
		System.out.println("Id ----->"+id);
		System.out.println("Avatar ----->"+avatar);
		System.out.println("first_name ----->"+firstname);
		
		Header[] jsonheaderArr = httpresp.getAllHeaders();
		
		HashMap<String,String> allheader = new HashMap<String, String>();
		
		for (Header header:jsonheaderArr )
		{
			allheader.put(header.getName(), header.getValue());
		}
		
		System.out.println("Respone Json Header from API ----->"+allheader);
		
	}
	
}
