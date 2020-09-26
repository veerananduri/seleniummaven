package com.pageobjects;

import org.openqa.selenium.By;

public class WordPressLoginPO {
	
	public By loginField() {
		By loginField = By.id("user_login");
		return loginField;
	}
	
	public By pwdField() {
		By pwdField = By.id("user_pass");
		return pwdField;
	}
	
	public By clickSubmit() {
		By clickLogin = By.id("wp-submit");
		return clickLogin;
	}
	
}
