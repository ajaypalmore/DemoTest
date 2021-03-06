package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
	public static Properties prop;
	public int RESPONSE_STATUS_200 = 200;
	public int RESPONSE_STATUS_201 = 201;
	public int RESPONSE_STATUS_404 = 404;
	public int RESPONSE_STATUS_500 = 500;
	public int RESPONSE_STATUS_400 = 400;
	public int RESPONSE_STATUS_401 = 401;
	public int RESPONSE_STATUS_402 = 402;
	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("C:\\Users\\Lenovo\\workspace\\Api_Test\\src\\main\\java\\com\\qa\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
