package com.pageobjects;

import org.openqa.selenium.By;

public class SalesForceNewLeadPageObjects {

	public By username() {
		By username = By.id("username");
		return username;
	}
	
	public By pwd() {
		By pwd = By.id("password");
		return pwd;
	}
	
	public By clickLogin() {
		By login = By.id("Login");
		return login;
	}
	
	public By clickProfile() {
		By profile = By.className("uiImage");
		return profile;
	}
	
	public By clickSwitch() {
		By switchProfile = By.xpath("//h3[text()='OPTIONS']/../div[5]/a");
		return switchProfile; 
	}
	
	public By clickAllTabs() {
		By tab = By.xpath("//*[@id='AllTab_Tab']/a");
		return tab;
	}
	
	public By leadsLink() {
		By lead = By.xpath("//a[text()='Leads']");
		return lead;
	}
	
	public By newButton() {
		By newbtn = By.cssSelector("input[name=new]");
		return newbtn;
	}
	
	public By firstName() {
		By firstName = By.id("name_firstlea2");
		return firstName;
	}
	
	public By lastName() {
		By lastName = By.id("name_lastlea2");
		return lastName;
	}
	
	public By company() {
		By company = By.id("lea3");
		return company;
	}
	
	public By email() {
		By email = By.id("lea11");
		return email;
	}
	
	public By searchPickProgram() {
		By program = By.cssSelector("span.lookupInput a");
		return program;
	}
	
	//There is an iframe title = Search 
	//Program - B.S in Business Administration
	
	public By searchProgramText() {
		By text = By.id("lksrch");
		return text;
	}
	
	public By clickGo() {
		By go = By.name("go");
		return go;
	}
	
	//There is an iframe for search results, and title = Results
	
	public By selectSearchResult(String searchText) {
		By result = By.xpath("//a[text()='"+searchText+"']");
		return result;
	}
	
	
	public By enterPhone() {
		By phone = By.id("lea8");
		return phone;
	}
	
	public By saveLead() {
		By save = By.name("save");
		return save;
	}
	
	public By selectOpportunity(String company) {
		By opportunity = By.xpath("//a[text()='"+company+"']");
		return opportunity;
	}
	
	public By clickEdit() {
		By edit = By.name("edit");
		return edit;
	}
	
	
}
