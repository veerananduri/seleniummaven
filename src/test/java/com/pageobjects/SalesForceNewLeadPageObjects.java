package com.pageobjects;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.github.javafaker.Faker;
import com.utils.Commons;
import com.utils.PropertyReaderUtil;

public class SalesForceNewLeadPageObjects {

	private RemoteWebDriver remDriver;
	Commons commons;
	Faker faker = new Faker();

	public SalesForceNewLeadPageObjects(RemoteWebDriver driver) {
		this.remDriver = driver;
		commons = new Commons(remDriver);
	}

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

	// There is an iframe title = Search
	// Program - B.S in Business Administration

	public By searchProgramText() {
		By text = By.name("lksrch");
		return text;
	}

	public By clickGo() {
		By go = By.name("go");
		return go;
	}

	// There is an iframe for search results, and title = Results

	public By selectSearchResult(String searchText) {
		By result = By.xpath("//a[text()='" + searchText + "']");
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
		By opportunity = By.xpath("//a[text()='" + company + "']");
		return opportunity;
	}

	// New Lead saved Page
	public By clickEdit() {
		By edit = By.name("edit");
		return edit;
	}

	public By selectDatePicker() {
		By date = By.cssSelector("span.lookupInput a[title*=Preferred]");
		return date;
	}

	public By selectStartDate(String program) {
		By date = By.xpath("//td[text()='" + program + "']/preceding-sibling::th/a");
		return date;
	}

	public By selectStage() {
		By stage = By.id("//*[@id='opp11']");
		return stage;
	}

	public By selectAdmissionStatus() {
		By status = By.xpath("//label[text()='Admissions Status']/../following-sibling::td//select");
		return status;
	}

	public By selectContact() {
		By contact = By.xpath("//td[text()='Contact']/following-sibling::td//a");
		return contact;
	}

	// Lead Save page
	public By studentProgramNum() {
		By num = By.xpath("//td[text()='Student Program']/following-sibling::td//a");
		return num;
	}

	// Contact view page
	public By bannerId() {
		By banner = By.xpath("//td[text()='Banner Id']/following-sibling::td/div");
		return banner;
	}

	// Below two are from search field on top header
	public By searchHeader() {
		By search = By.id("phSearchInput");
		return search;
	}

	public By clickSearch() {
		By search = By.id("phSearchButton");
		return search;
	}

	// This is in search results page
	public By clickStudentProgramNum(String spnum) {
		By num = By.xpath("//a[text()='" + spnum + "']");
		return num;
	}

	// This is from student program info page
	public By getStudentSubscription() {
		By subscr = By.xpath("//a[contains(text(),'SUB')]");
		return subscr;
	}

	public By searchClickCurrentStudentSubscript() {
		By search = By.cssSelector("span.lookupInput a[title*=Current]");
		return search;
	}

	public By searchInputInFrame() {
		By search = By.xpath("//label[text()='Search']/following-sibling::input[1]");
		return search;
	}

	public By clickGoInFrame() {
		By search = By.xpath("//label[text()='Search']/following-sibling::input[2]");
		return search;
	}

	public By selectSearchResultOfSubscriptionNum(String subnum) {
		By num = By.xpath("//a[text()='" + subnum + "']");
		return num;
	}

	public By selectIsStudentProgActive() {
		By select = By.xpath("//label[text()='IsStudentProgramActive']/../../following-sibling::td/input");
		return select;
	}

	/******************************************************************************************
	 *********************** Second Level methods starts from here ****************************
	 *****************************************************************************************/

	/**
	 * Login Method
	 * 
	 * @param username
	 * @param password
	 */
	public void loginToSalesforce(String username, String password) {

		// Launch URL
		String url = PropertyReaderUtil.getProperty("url");
		remDriver.get(url);

		// Enter Credentials and Login
		commons.typeValue(username(), username);
		commons.typeValue(pwd(), password);
		commons.clickElement(clickLogin());
		commons.sleep(3);

	}

	/**
	 * Navigate to classic view
	 */
	public void navigateToClassicView() {
		commons.clickElement(clickProfile());
		commons.clickElement(clickSwitch());
		commons.sleep(2);
	}

	/**
	 * Click on All Tabs ('+' Icon) and Select Leads link to create New Lead
	 */
	public void createNewLead(String program) {

		String firstName = faker.address().firstName();
		String lastName = faker.address().lastName();
		String company = faker.company().name();
		String email = firstName + "_" + lastName + "@gmail.com";
		String phone = faker.phoneNumber().cellPhone();

		// Click Leads Link
		commons.clickElement(clickAllTabs());
		commons.clickElement(leadsLink());
		commons.sleep(2);

		// Click New Button
		commons.clickElement(newButton());

		// Enter New Lead data
		commons.typeValue(firstName(), firstName);
		commons.typeValue(lastName(), lastName);
		commons.typeValue(company(), company);
		commons.typeValue(email(), email);
		commons.typeValue(enterPhone(), phone);

		// Select Program Interest
		commons.clickElement(searchPickProgram());

		// Store the current window handle
		String winHandleBefore = remDriver.getWindowHandle();
		
		// Switch to new window opened
		for (String winHandle : remDriver.getWindowHandles()) {
			if(!winHandle.equals(winHandleBefore)) {
				remDriver.switchTo().window(winHandle);
				commons.sleep(1);
			}			
		}
		
		//Switch to Iframe in new lookup window  and search the text
		remDriver.switchTo().frame("searchFrame");
		commons.typeValue(searchInputInFrame(), program);
		commons.clickElement(clickGo());
		
		commons.sleep(2);
		
		//Switch to another results iframe and select the result
		remDriver.switchTo().defaultContent();
		remDriver.switchTo().frame("resultsFrame");
		commons.clickElement(selectSearchResult(program));
		
		commons.sleep(2);
		
		//Switch back to the default window
		remDriver.switchTo().window(winHandleBefore);
		
		//Save the lead
		commons.clickElement(saveLead());
	}

}
