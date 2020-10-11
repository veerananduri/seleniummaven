package com.testcases;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pageobjects.WordPressLoginPO;
import com.utils.Commons;
import com.utils.ExcelUtils;
import com.utils.PropertyReaderUtil;
import com.utils.Setup;

public class GoogleTest extends Setup {
	
	Commons commons;
	PropertyReaderUtil propUtil = new PropertyReaderUtil();
	
	public void initTest() {
		commons = new Commons(getWebDriver());
	}	
	
	WordPressLoginPO wordPressLoginPO = new WordPressLoginPO();
	
	@Test(priority=4)
	public void printTest() {
		System.out.println("================================================");
		System.out.println("================printTest()=====================");
		System.out.println("================================================");
		System.out.println("This is my first test");
	}
	
	@Test(priority=2)
	public void googleSearchTest() {
		System.out.println("================================================");
		System.out.println("================googleSearchTest()==============");
		System.out.println("================================================");
		
		//Initialize the driver
		initTest();
		
		//Open url and get the title
		getWebDriver().get("https://google.com");
		String title = getWebDriver().getTitle();
		Assert.assertEquals(title, "Google", "Title Validation");
		Assert.fail("Testing failed test case");
		
	}
	
	@Test(priority=3, groups = "test")
	public void loginTest() {
		System.out.println("================================================");
		System.out.println("================loginTest()=====================");
		System.out.println("================================================");
		
		//Initialize the driver
		initTest();
		
		//Login to the Website
		getWebDriver().get(PropertyReaderUtil.getProperty("url"));
		commons.typeValue(wordPressLoginPO.loginField(), "opensourcecms");
		commons.typeValue(wordPressLoginPO.pwdField(), "opensourcecms");
		commons.clickElement(wordPressLoginPO.clickSubmit());
		Assert.fail();
		
	}
	
	@Test(priority=1, dataProvider = "Authentication")
	public void dataProviderTest(String username, String password) {
		System.out.println("================================================");
		System.out.println("================dataProviderTest()==============");
		System.out.println("================================================");
		
		//Initialize the driver
		initTest();
		
		//Login to the Website
		getWebDriver().get("https://s1.demo.opensourcecms.com/wordpress/wp-login.php");
		commons.typeValue(wordPressLoginPO.loginField(), username);
		commons.typeValue(wordPressLoginPO.pwdField(), password);
		commons.clickElement(wordPressLoginPO.clickSubmit());
		
	}
	
	@DataProvider(name = "Authentication")
	public static Object[][] Authentication() throws Exception{
		 
        Object[][] testObjArray = ExcelUtils.readExcel(System.getProperty("user.dir")+"\\src\\test\\resources\\TestData.xlsx",
       		 "Sheet1");

        return (testObjArray);
	 }
}
