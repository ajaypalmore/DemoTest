package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.restclient;
import com.qa.data.users;

public class PostAPITest extends TestBase {
	TestBase testbase;
	String serviceURL;
	String APIURL;
	String appURL;
	CloseableHttpResponse httpresponse;
	restclient restclt;

	@BeforeMethod
	public void StepUp() throws ClientProtocolException, IOException {
		testbase = new TestBase();
		serviceURL = prop.getProperty("URL");
		APIURL = prop.getProperty("serviceurl");
		appURL = serviceURL + APIURL;

	}

	@Test
	public void postAPITest() throws JsonGenerationException, JsonMappingException, IOException {
		restclt = new restclient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// JackSon API
		ObjectMapper mapper = new ObjectMapper();
		users user = new users("morpheus", "leader"); // Expected user object

		// Java object to JSON
		mapper.writeValue(
				new File("C:\\Users\\Lenovo\\workspace\\Api_Test\\src\\main\\java\\com\\qa\\data\\users.json"), user);

		// Object to JSON
		String usersJsonString = mapper.writeValueAsString(user);
		System.out.println(usersJsonString);
		
		CloseableHttpResponse httpresponse = restclt.Post(appURL, usersJsonString, headerMap);// Calling API
		
		// Validating Response 
		//1.
		int statusCode = httpresponse.getStatusLine().getStatusCode();
		System.out.println("Respone Code ----->"+statusCode);
		Assert.assertEquals(statusCode, RESPONSE_STATUS_201," Status code not Match" );
		
		//2. stringJSON
		String response = EntityUtils.toString(httpresponse.getEntity(),"UTF-8");
		JSONObject jsonobject = new JSONObject(response);
		System.out.println("JSON Respone from API----->"+jsonobject);
		
		// Json to Java
		users usrObj = mapper.readValue(response, users.class); // Actual users Object
		
		Assert.assertTrue(usrObj.getName().equals(user.getName()));
		Assert.assertTrue(usrObj.getJob().equals(user.getJob()));
		System.out.println("Created Date :::::====>"+usrObj.getcreatedAt());
		System.out.println("User ID :::::====>"+usrObj.getId());
		
	}
}
