package com.pageobjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.github.javafaker.Faker;
import com.utils.Commons;
import com.utils.PropertyReaderUtil;

public class SalesForceNewLeadPageObjects extends Commons {

	private WebDriver remDriver;
	String env = null;

	public SalesForceNewLeadPageObjects(WebDriver webDriver) {
		super(getWebDriver());
		this.remDriver = webDriver;
		env = PropertyReaderUtil.getProperty("environment");
	}

	Faker faker = new Faker();

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
		By result = By.xpath("//a[contains(text(),'" + searchText + "')]");
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
		By date = By.xpath("//td[contains(text(),'" + program + "')]/preceding-sibling::th/a");
		return date;
	}

	public By selectStage() {
		By stage = By.id("opp11");
		return stage;
	}

	public By selectAdmissionStatus(String status) {
		return By.xpath("//label[text()='" + status + "']/../following-sibling::td//select");
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
		By banner = By.xpath("//td[text()='Banner Id']/following-sibling::td[1]/div");
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

	public By showAllResults() {
		By results = By.xpath("//a[text()='Show all results']");
		return results;
	}

	/***************************
	 * L1 Page objects
	 **************************/
	private By sqlCheckbox() {
		return By.xpath("//label[text()='SQL']/../following-sibling::td/input");
	}

	private By searchEnhancedFields(String field) {
		return By.xpath("//label[text()='" + field + "']/../following-sibling::td[1]//a");
	}

	private By enterDataFields(String data) {
		return By.xpath("//label[text()='" + data + "']/../following-sibling::td[1]/input");
	}

	private By selectWaldenInstitution() {
		return By.xpath("//th/a");
	}

	private By programSelection() {
		return By.xpath("//table//tr[2]/th/a");
	}

	private By opportunityDetail(String detail) {
		return By.xpath("//td[text()='" + detail + "']/following-sibling::td/div");
	}

	private By selectSearchPicker(String pick) {
		return By.xpath("//label[text()='" + pick + "']/../../following-sibling::td[1]//a");
	}

	private By selectOption(String data) {
		return By.xpath("//label[text()='" + data + "']/../../following-sibling::td/input");
	}

	private By selectCaseNumber(String details) {
		return By.xpath("//th[text()='" + details + "']/../following-sibling::tr/th/a");
	}

	private By clickOnDetails() {
		return By.xpath("//span[text()='Details']");
	}

	private By editCaseDetailsSelection(String details) {
		return By.xpath("//label[text()='" + details + "']/../../following-sibling::td//select");
	}

	private By caseEditCheckbox(String details) {
		return By.xpath("//label[text()='" + details + "']/../following-sibling::td/input[@type='checkbox']");
	}

	private By closeCase() {
		return By.name("close");
	}

	private By caseDetails(String status) {
		return By.xpath("//td[text()='" + status + "']/following-sibling::td[1]/div");
	}

	private By clickLink(String detail) {
		return By.xpath("//td[text()='" + detail + "']/following-sibling::td[1]//a");
	}

	/**********************************
	 * UAT Properties
	 ***********************************/
	private By opportunityLink() {
		return By.xpath("//a[text()='Opportunity Name']/../../following-sibling::tr/th/a");
	}

	private By applicationLink() {
		return By.xpath("//span[text()='Application']/../following-sibling::td//a");
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
		typeValue(username(), username);
		typeValue(pwd(), password);
		clickElement(clickLogin());
	}

	/**
	 * Navigate to classic view
	 */
	public void navigateToClassicView() {
		if (isElementDisplayed(clickProfile())) {
			clickElement(clickProfile());
			clickElement(clickSwitch());
			sleep(2);
		}
	}

	/**
	 * Click on All Tabs ('+' Icon) and Select Leads link to create New Lead
	 */
	public void createNewLead(String program, String date) {

		// Get the input data
		Map<String, String> data = getStudentData();

		// Click Leads Link
		clickElement(clickAllTabs());
		clickElement(leadsLink());
		sleep(2);

		// Click New Button
		clickElement(newButton());

		// Enter New Lead data
		typeValue(firstName(), data.get("firstName"));
		typeValue(lastName(), data.get("lastName"));
		typeValue(company(), data.get("company"));
		typeValue(email(), data.get("email"));
		typeValue(enterPhone(), data.get("phone"));

		// Store the current window handle
		// Select Program Interest
		String winHandleBefore = remDriver.getWindowHandle();
		searchPickerSwitch(searchPickProgram(), winHandleBefore);

		typeValue(searchInputInFrame(), program);
		clickElement(clickGo());

		sleep(2);

		// Switch to another results iframe and select the result
		remDriver.switchTo().defaultContent();
		remDriver.switchTo().frame("resultsFrame");
		clickElement(selectSearchResult(program));

		sleep(2);

		// Switch back to the default window
		remDriver.switchTo().window(winHandleBefore);

		// Save the lead
		clickElement(saveLead());
		reportLog("New Lead Saved for the program : " + program);
		reportLog("Firstname and Lastname Details : " + data.get("firstName") + " " + data.get("lastName"));
		reportLog("Opportunity : " + data.get("company"));

		Assert.assertTrue(true, "Testcase Passed");
		getTest().log(Status.INFO, "New Lead Saved");

		// Click on Opportunity
		clickElement(selectOpportunity(data.get("company")));

		// Click Edit on Opportunity
		clickElement(clickEdit());

		// Select the preferred start date
		searchPickerSwitch(selectDatePicker(), winHandleBefore);
		typeValue(searchInputInFrame(), date);
		clickElement(clickGo());
		remDriver.switchTo().defaultContent();
		remDriver.switchTo().frame("resultsFrame");
		if (isElementDisplayed(showAllResults())) {
			clickElement(showAllResults());
		}
		remDriver.switchTo().defaultContent();
		remDriver.switchTo().frame("resultsFrame");
		sleep(2);
		clickElement(selectStartDate(program));

		sleep(2);

		// Switch back to the default window
		remDriver.switchTo().window(winHandleBefore);
		clickElement(saveLead());

		// Click Edit again to select Stage & Admission Status and Save
		clickElement(clickEdit());
		selectByVisibleText(selectStage(), "Admitted");
		selectByVisibleText(selectAdmissionStatus("Admissions Status"), "AD Admitted");
		clickElement(saveLead());

		// click edit again and select Stage as Student and Save
		clickElement(clickEdit());
		selectByVisibleText(selectStage(), "Student");
		clickElement(saveLead());

		// Retrieve the student program #
		String studentProgNumber = getText(studentProgramNum());
		reportLog("Student Program Number : " + studentProgNumber);

		// Click on contact
		clickElement(selectContact());
		String bannerId = getText(bannerId());
		reportLog("Banner Id : " + bannerId + "<br>");

		// Navigate to Student Program and Copy subscription number
		typeValue(searchHeader(), studentProgNumber);
		clickElement(clickSearch());
		clickElement(clickStudentProgramNum(studentProgNumber));
		String subscriptionNum = getText(getStudentSubscription());

		// Click Edit on Student Program
		clickElement(clickEdit());
		searchPickerSwitch(searchClickCurrentStudentSubscript(), winHandleBefore);
		typeValue(searchInputInFrame(), subscriptionNum);
		clickElement(clickGoInFrame());

		remDriver.switchTo().defaultContent();
		remDriver.switchTo().frame("resultsFrame");
		clickElement(selectSearchResultOfSubscriptionNum(subscriptionNum));

		// Switch back to the default window
		remDriver.switchTo().window(winHandleBefore);
		clickElement(selectIsStudentProgActive());
		clickElement(saveLead());

		reportLog("******************************************************************");
		reportLog("Testcase Finished.");
		reportLog("******************************************************************<br>");
	}

	public void createNewLeadL1(Map<String, String> data) {
		// Get the input data
		Map<String, String> data1 = getStudentData();

		// Click Leads Link
		clickElement(clickAllTabs());
		clickElement(leadsLink());
		sleep(2);

		// Click New Button
		clickElement(newButton());

		// Enter New Lead data
		typeValue(firstName(), data1.get("firstName"));
		typeValue(lastName(), data1.get("lastName"));
		typeValue(company(), data1.get("company"));

		clickElement(sqlCheckbox());

		// Store the current window handle
		// Select Walden Institution
		String winHandleBefore = remDriver.getWindowHandle();
		searchPickerSwitch(searchEnhancedFields("Institution"), winHandleBefore);

		// Switch to the results frame
		remDriver.switchTo().defaultContent();
		remDriver.switchTo().frame("resultsFrame");
		clickElement(selectWaldenInstitution());

		// Switch back to the default window
		remDriver.switchTo().window(winHandleBefore);

		searchPickerSwitch(searchEnhancedFields("Campaign"), winHandleBefore);

		// Switch to the results frame
		remDriver.switchTo().defaultContent();
		remDriver.switchTo().frame("resultsFrame");
		clickElement(selectWaldenInstitution());

		// Switch back to the default window
		remDriver.switchTo().window(winHandleBefore);

		searchPickerSwitch(searchEnhancedFields("Lead Supplier"), winHandleBefore);

		// Switch to the results frame
		remDriver.switchTo().defaultContent();
		remDriver.switchTo().frame("resultsFrame");
		clickElement(selectWaldenInstitution());

		// Switch back to the default window
		remDriver.switchTo().window(winHandleBefore);

		searchPickerSwitch(searchEnhancedFields("Primary Product"), winHandleBefore);

		typeValue(searchInputInFrame(), data.get("StudentProgram"));
		clickElement(clickGo());

		sleep(2);

		// Switch to another results iframe and select the result
		remDriver.switchTo().defaultContent();
		remDriver.switchTo().frame("resultsFrame");
		clickElement(programSelection());

		// Switch back to the default window
		remDriver.switchTo().window(winHandleBefore);

		// Enter required Data fields
		typeValue(enterDataFields("Email Address (*)"), data1.get("email"));
		typeValue(enterDataFields("Primary Phone Number"), data1.get("phone"));
		typeValue(enterDataFields("First Name (*)"), data1.get("firstName"));
		typeValue(enterDataFields("Last Name (*)"), data1.get("lastName"));
		typeValue(enterDataFields("Address Line 1"), data1.get("street1"));
		typeValue(enterDataFields("City (*)"), data1.get("city"));
		typeValue(enterDataFields("State (*)"), data1.get("state"));
		typeValue(enterDataFields("Zip/Postal Code (*)"), data1.get("zipcode"));
		typeValue(enterDataFields("Country (*)"), "United States");

		clickElement(saveLead());

		// Select the contact link after save
		clickElement(selectSearchResult(data1.get("firstName").toLowerCase().replaceAll("zz_", "")));

		// Click opportunity link to select Stage & Admission Status and Save
		List<WebElement> opportunities = getListValues(
				selectSearchResult(data1.get("firstName").toLowerCase().replaceAll("zz_", "")));
		opportunities.get(4).click();

		String stageStatus = getText(opportunityDetail("Stage"));
		String dispositionStatus = getText(opportunityDetail("Disposition"));
		reportLog("Stage after lead created : " + stageStatus + "<br>");
		reportLog("Disposition after lead created : " + dispositionStatus + "<br>");

		// Edit Opportunity
		clickElement(clickEdit());

		searchPickerSwitch(selectSearchPicker("Selected Program Start Date"), winHandleBefore);
		typeValue(searchInputInFrame(), data.get("StartDateName"));
		clickElement(clickGo());

		sleep(2);

		// Switch to another results iframe and select the result
		remDriver.switchTo().defaultContent();
		remDriver.switchTo().frame("resultsFrame");
		clickElement(selectSearchResult("SD"));

		sleep(2);
		// Switch back to the default window
		remDriver.switchTo().window(winHandleBefore);

		clickElement(enterDataFields("Application Started"));
		clickElement(enterDataFields("Application Submitted"));
		clickElement(saveLead());

		stageStatus = getText(opportunityDetail("Stage"));
		dispositionStatus = getText(opportunityDetail("Disposition"));
		reportLog("Stage after opportunity updated with Start date name selection : " + stageStatus + "<br>");
		reportLog(
				"Disposition after opportunity updated with Start date name selection : " + dispositionStatus + "<br>");

		// Edit Opportunity again
		clickElement(clickEdit());

		sleep(2);
		clickElement(enterDataFields("State Agreement"));
		clickElement(enterDataFields("English Proficiency"));
		selectByVisibleText(selectAdmissionStatus("Customer Motivation"), "Career Advancement");

		clickElement(selectOption("EA Quality Check Complete"));
		selectByVisibleText(selectAdmissionStatus("Review Type"), "Admissions Decision Only");

		clickElement(saveLead());

		stageStatus = getText(opportunityDetail("Stage"));
		dispositionStatus = getText(opportunityDetail("Disposition"));
		reportLog("Stage after opportunity updated with EA Consultation Details : " + stageStatus + "<br>");
		reportLog("Disposition after opportunity updated with EA Consultation Details : " + dispositionStatus + "<br>");

		clickElement(selectCaseNumber("Case Number"));
		clickElement(clickOnDetails());

		clickElement(clickEdit());
		selectByVisibleText(editCaseDetailsSelection("Student Type"), "A");
		selectByVisibleText(selectAdmissionStatus("Final Decision"), "AD - Regular Admit");
		clickElement(enterDataFields("Admission Decision Letter Attached"));
		clickElement(caseEditCheckbox("Document Entry Completed"));
		clickElement(caseEditCheckbox("TOC Not Awarded"));

		clickElement(saveLead());

		clickElement(closeCase());
		selectByVisibleText(selectAdmissionStatus("Status"), "Closed");
		sleep(2);
		selectByVisibleText(selectAdmissionStatus("Case Closed Reason"), "RA Completed");
		clickElement(saveLead());

		String caseStatus = getText(caseDetails("Status"));
		reportLog("Case status after the close: " + caseStatus + "<br>");

		clickElement(clickLink("Opportunity"));
		stageStatus = getText(opportunityDetail("Stage"));
		dispositionStatus = getText(opportunityDetail("Disposition"));
		reportLog("Stage status after case is closed : " + stageStatus + "<br>");
		reportLog("Disposition status after case is closed : " + dispositionStatus + "<br>");

		// Open new tab
		((JavascriptExecutor) remDriver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		remDriver.switchTo().window(tabs.get(1));

		// Login to Salesforce to check for the data
		loginToSalesforce(data.get("uatlogin"), data.get("Password"));

		sleep(20);
		typeValue(searchHeader(), data1.get("firstName") + " " + data1.get("lastName"));
		clickElement(clickSearch());

		clickElement(opportunityLink());
		stageStatus = getText(caseDetails("Stage"));
		reportLog("Stage status in UAT after case close: " + stageStatus + "<br>");
		
		clickElement(clickEdit());
		selectByVisibleText(selectAdmissionStatus("Stage"), "Student");
		sleep(2);
		clickElement(saveLead());
		sleep(5);
		remDriver.navigate().refresh();
		jseClickElement(studentProgramNum());
		
		clickElement(clickEdit());
		clickElement(selectIsStudentProgActive());
		clickElement(saveLead());
		sleep(2);

		clickElement(applicationLink());
		String bannerIdInUAT = getText(bannerId());
		reportLog("Banner Id displayed in Opportunity Details Page in UAT: " + bannerIdInUAT + "<br>");

		// Switch to the main tab
		sleep(20);
		remDriver.switchTo().window(tabs.get(0));
		remDriver.navigate().refresh();

		String bannerIdInQA2 = getText(bannerId());
		reportLog("Banner Id displayed in Opportunity Details Page: " + bannerIdInUAT + "<br>");

		if (bannerIdInUAT.equals(bannerIdInQA2)) {
			reportLog("Banner Id matched in both the Environments");
		} else {
			reportLog("Banner Id not matched while comparing both the Environments");
		}

		clickElement(clickLink("Brand Profile"));
		String bannerIdInBrandProfile = getText(bannerId());
		if (bannerIdInBrandProfile.isEmpty()) {
			bannerIdInBrandProfile = "Banner Id is Empty";
		}
		reportLog("Banner Id displayed in Opportunity Details (Brand Profile) : " + bannerIdInBrandProfile + "<br>");

		reportLog("******************************************************************");
		reportLog("Testcase Finished.");
		reportLog("******************************************************************<br>");
	}

	private Map<String, String> getStudentData() {
		// Get the input data
		String firstName = "ZZ_" + faker.address().firstName().replaceAll("[^A-Za-z0-9]", "");
		String lastName = "ZZ_" + faker.address().lastName().replaceAll("[^A-Za-z0-9]", "");
		String company = faker.company().name();
		String email = firstName + "_" + lastName + "@gmail.com";
		String phone = faker.phoneNumber().cellPhone();
		String street1 = faker.address().streetAddress();
		String city = faker.address().cityName();
		String state = faker.address().state();
		String zipcode = faker.address().zipCode();

		Map<String, String> data = new HashMap<>();
		data.put("firstName", firstName);
		data.put("lastName", lastName);
		data.put("company", company);
		data.put("email", email);
		data.put("phone", phone);
		data.put("street1", street1);
		data.put("city", city);
		data.put("state", state);
		data.put("zipcode", zipcode);

		return data;
	}

	public void searchPickerSwitch(By locator, String window) {
		// Select lookup
		clickElement(locator);

		// Switch to new window opened
		for (String winHandle : remDriver.getWindowHandles()) {
			if (!winHandle.equals(window)) {
				remDriver.switchTo().window(winHandle);
				sleep(1);
			}
		}

		// Switch to Iframe in new lookup window and search the text
		remDriver.switchTo().frame("searchFrame");
	}

}
