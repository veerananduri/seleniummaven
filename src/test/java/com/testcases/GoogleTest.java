package com.testcases;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.pageobjects.WordPressLoginPO;
import com.utils.Commons;
import com.utils.Setup;

public class GoogleTest extends Setup {
	
	Commons commons;
	
	public void initTest() {
		RemoteWebDriver driver = callDriver();
		super.driver = driver;
		commons = new Commons(driver);
	}	
	
	WordPressLoginPO wordPressLoginPO = new WordPressLoginPO();
	
	@Test(priority=1)
	public void printTest() {
		System.out.println("================================================");
		System.out.println("================TEST METHOD 1===================");
		System.out.println("================================================");
		System.out.println("This is my first test");
	}
	
	@Test(priority=2)
	public void googleSearchTest() {
		System.out.println("================================================");
		System.out.println("================TEST METHOD 2===================");
		System.out.println("================================================");
		
		//Initialize the driver
		initTest();
		
		//Open url and get the title
		driver.get("https://google.com");
		String title = driver.getTitle();
		Assert.assertEquals(title, "Google", "Title Validation");
		
	}
	
	@Test(priority=3)
	public void loginTest() {
		System.out.println("================================================");
		System.out.println("================TEST METHOD 3===================");
		System.out.println("================================================");
		
		//Initialize the driver
		initTest();
		
		//Login to the Website
		driver.get("https://s1.demo.opensourcecms.com/wordpress/wp-login.php");
		commons.typeValue(wordPressLoginPO.loginField(), "opensourcecms");
		commons.typeValue(wordPressLoginPO.pwdField(), "opensourcecms");
		commons.clickElement(wordPressLoginPO.clickSubmit());
		
	}
}
