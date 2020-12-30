package com.pageobjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.github.javafaker.Faker;
import com.utils.Commons;
import com.utils.PropertyReaderUtil;

public class WaldenUniversityPurchaseCoursePageObjects extends Commons {

	private WebDriver remDriver;

	public WaldenUniversityPurchaseCoursePageObjects(WebDriver webDriver) {
		super(getWebDriver());
		this.remDriver = webDriver;
	}

	Faker faker = new Faker();

	private By searchTextField() {
		return By.cssSelector("div.c-navbar-header__col input[name=q]");
	}

	private By searchButton() {
		return By.cssSelector("div.c-navbar-header__col button[name=search-button]");
	}

	private By selectCourseLink(String course) {
		return By.xpath("//a[contains(text(),'" + course + "')]");
	}

	private By courseFeeInCounsellingPage() {
		return By.cssSelector("div.desktop p.value");
	}

	private By addToCartButton() {
		return By.cssSelector("div.desktop button.add-to-cart");
	}

	private By clickCart() {
		return By.cssSelector("a.minicart-link");
	}

	private By courseNameInCartPage() {
		return By.cssSelector("div.line-item-name");
	}

	private By courseFee() {
		return By.cssSelector("span.sales");
	}

	private By checkoutButton() {
		return By.xpath("//a[contains(text(),'Checkout')]");
	}

	private By oktaSigninUsername() {
		return By.cssSelector("input#okta-signin-username");
	}

	private By oktaSigninPassword() {
		return By.cssSelector("input#okta-signin-password");
	}

	private By oktaSigninButton() {
		return By.cssSelector("input#okta-signin-submit");
	}

	private By oktaSignUpLink() {
		return By.cssSelector("a.registration-link");
	}

	private By oktaEmail() {
		return By.cssSelector("input[name=email]");
	}

	private By oktaPassword() {
		return By.cssSelector("input[name=password]");
	}

	private By oktaFirstName() {
		return By.cssSelector("input[name=firstName]");
	}

	private By oktaLastName() {
		return By.cssSelector("input[name=lastName]");
	}

	private By oktaCountrySelection() {
		return By.cssSelector("span.o-form-input-name-countryName a"); // United States
	}

	private By oktaInterestedIn() {
		return By.cssSelector("span.o-form-input-name-interestedIn a"); // Professional Development
	}

	private By oktaRegister() {
		return By.cssSelector("input[value=Register]");
	}

	private By checkoutDob() {
		return By.cssSelector("input#dob");
	}

	private By checkoutStreetAddress() {
		return By.cssSelector("input#address1");
	}

	private By checkoutState() {
		return By.cssSelector("select#state");
	}

	private By checkoutCity() {
		return By.cssSelector("input#city");
	}

	private By checkoutZipCode() {
		return By.cssSelector("input#zipCode");
	}

	private By checkoutPhone() {
		return By.cssSelector("input#phone");
	}

	private By checkoutTermsCheckBox() {
		return By.cssSelector("label[for=termsandconditions]");
	}

	private By checkoutContPayment() {
		return By.cssSelector("button#reg-button");
	}

	private By clickPaypal() {
		return By.cssSelector("span.paypal-button-text");
	}

	private By paypalEmail() {
		return By.cssSelector("input#email");
	}

	private By paypalNext() {
		return By.cssSelector("button#btnNext");
	}

	private By paypalPassword() {
		return By.cssSelector("input#password");
	}

	private By paypalLogin() {
		return By.cssSelector("button#btnLogin");
	}

	private By paypalHeaderTotal() {
		return By.cssSelector("span[data-testid=header-cart-total]");
	}

	private By paypalSubmitPayment() {
		return By.cssSelector("button#payment-submit-btn");
	}

	private By reviewAndPurchase() {
		return By.cssSelector("div.payment-summary button[value=place-order]");
	}
	
	private By searchDialog() {
		return By.cssSelector("div.uiInput input.slds-input");
	}
	
	private By clickSearchResultLinkOnSF(String name) {
		return By.cssSelector("th a[title='"+name+"']");
	}
	
	private By clickOpportunityLink(String name) {
		return By.xpath("//a[contains(text(),'"+name+"')]");
	}
	
	private By selectOpportunityDetailsTab() {
		return By.xpath("//a[@id='detailTab__item']");
	}
	
	private By opportunityLabels() {
		return By.xpath("//div[@class='slds-form']//span[@class='test-id__field-label']");
	}
	
	private By opportunityValues() {
		return By.xpath("//span[starts-with(@class,'test-id__field-value')]/slot/slot");
	}
	
	//Login locators (test.salesforce.com)
	private By username() {
		By username = By.id("username");
		return username;
	}

	private By pwd() {
		By pwd = By.id("password");
		return pwd;
	}

	private By clickLogin() {
		By login = By.id("Login");
		return login;
	}
	

	/***************************************************************************
	 * Second level methods starts from here
	 ***************************************************************************/

	public void launchWaldenUniversityUrl() {
		// Launch URL
		String url = PropertyReaderUtil.getProperty("waldenURL");
		remDriver.get(url);
	}

	public void searchCourse(Map<String, String> data) {

		String course = data.get("course");
		typeValue(searchTextField(), course);
		clickElement(searchButton());

		if (isElementAvailable(selectCourseLink(course))) {
			clickElement(selectCourseLink(course));
			reportLog("Selected the course in search results page: " + course);
		} else {
			reportLog("Course not displayed in search results: " + course);
		}
	}

	public Map<String, String> addToCartandCheckout(Map<String, String> data) {
		String priceInCart = getText(courseFeeInCounsellingPage());
		clickElement(addToCartButton());
		clickElement(clickCart());
		String courseName = getText(courseNameInCartPage());
		if (courseName.contains(data.get("course"))) {
			reportLog("Course name displayed correctly in cart page");
		} else {
			reportLog("Improper Course name displayed in cart page");
		}

		String feeInCheckout = getText(courseFee());
		if (priceInCart.equals(feeInCheckout)) {
			reportLog("Couse fee displayed in cart and checkout page are same");
			data.put("Price", feeInCheckout);
		} else {
			reportLog("Mismatch in Couse fee displayed in cart and checkout page");
		}
		
		clickElement(checkoutButton());
		return data;
	}

	public Map<String, String> oktaSignupForCheckout(Map<String, String> data) {

		// Get the input data
		String firstName = "ZZ_" + faker.address().firstName().replaceAll("[^A-Za-z0-9]", "");
		String lastName = "ZZ_" + faker.address().lastName().replaceAll("[^A-Za-z0-9]", "");
		String email = firstName + "_" + lastName + "@gmail.com";

		clickElement(oktaSignUpLink());

		typeValue(oktaEmail(), email);
		typeValue(oktaPassword(), "Test1234");
		typeValue(oktaFirstName(), firstName);
		typeValue(oktaLastName(), lastName);

		clickElementWithAction(oktaCountrySelection());
		clickElementWithAction(oktaInterestedIn());

		clickElement(oktaRegister());
		
		data.put("oktaEmail", email);
		data.put("firstname", firstName);
		data.put("lastname", lastName);
		return data;
	}

	public void continueCheckout() {

		String address = faker.address().streetAddress();
		String state = faker.address().state();
		String city = faker.address().city();
		String zipCode = faker.address().zipCode();
		String phoneNumber = faker.phoneNumber().cellPhone().replaceAll("[^\\.A-Za-z0-9_]", "");
		// String fullAddress = faker.address().fullAddress();

		typeValue(checkoutDob(), "07011990");
		typeValue(checkoutStreetAddress(), address);
		selectByVisibleText(checkoutState(), state);
		typeValue(checkoutCity(), city);
		typeValue(checkoutZipCode(), zipCode);
		typeValue(checkoutPhone(), phoneNumber);
		sleep(1);
		actionClickElement(checkoutTermsCheckBox());
		clickElement(checkoutContPayment());
	}

	public void clickPaypalPayment(Map<String, String> data) {
		sleep(3);
		
		remDriver.switchTo().frame(remDriver.findElement(By.cssSelector("iframe[title=PayPal]")));
		actionClickElement(clickPaypal());
		String winHandleBefore = remDriver.getWindowHandle();
		
		// Switch to new window opened
		for (String winHandle : remDriver.getWindowHandles()) {
			if (!winHandle.equals(winHandleBefore)) {
				remDriver.switchTo().window(winHandle);
				sleep(1);
			}
		}
		sleep(2);
		typeValue(paypalEmail(), "sb-t1kie1366969@personal.example.com");
		clickElement(paypalNext());
		sleep(2);
		typeValue(paypalPassword(), "p-%#sR2/");
		sleep(2);
		clickElement(paypalLogin());
		sleep(4);
		String headerTotal = getText(paypalHeaderTotal()).replaceAll("[a-zA-Z ]", "");;
		String actualPrice = data.get("Price");
		
		if (actualPrice.equals(headerTotal))
			reportLog("Couse fee displayed in checkout page and in Paypal webpage are same: "+headerTotal);
		else
			reportLog("Couse fee displayed in checkout page and in Paypal webpage are not same: "+headerTotal);
		
		jseClickElement(paypalSubmitPayment());
		sleep(5);
		
		// Switch back to the default window
		remDriver.switchTo().window(winHandleBefore);
		remDriver.switchTo().defaultContent();
		
		//Open new tab
		((JavascriptExecutor) remDriver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		remDriver.switchTo().window(tabs.get(1));
		
		//Login to Salesforce to check for the data
		loginToSalesforce();
		
		//Search with the registered email
		sleep(20);
		typeValue(searchDialog(), data.get("oktaEmail"));
		remDriver.findElement(searchDialog()).sendKeys(Keys.ENTER);
		
		sleep(2);
		clickElement(clickSearchResultLinkOnSF(data.get("firstname")+" "+data.get("lastname")));
		
		sleep(3);
		clickElement(clickOpportunityLink(data.get("firstname")+" "+data.get("lastname")));
		
		sleep(2);
		List<WebElement> opptabs = driver.findElements(selectOpportunityDetailsTab());
		opptabs.get(1).click();
		
		sleep(2);
		Map<String, String> opportunityData = validateTableDataandReturnAsKeyValuePair(opportunityLabels(), opportunityValues(), 0);
		reportLog("Opportunity Data : {}"+ opportunityData);
		
		if(opportunityData.get("Stage").equals("Application Started") && opportunityData.get("Learner Program").isEmpty()) {
			reportLog("Validated the salesforce application before completed the purchase : "+opportunityData.get("Stage"));
		} else {
			reportLog("Unable to validate the salesforce application before completed the purchase : "+opportunityData.get("Stage"));
		}
		
		//Switch to the main tab
		remDriver.switchTo().window(tabs.get(0));
		
		List<WebElement> buttons = remDriver.findElements(reviewAndPurchase());
		buttons.get(1).click();
		sleep(8);
		
		//Switch to the other tab again
		remDriver.switchTo().window(tabs.get(1));
		remDriver.navigate().refresh();
		
		sleep(3);
		clickElement(selectOpportunityDetailsTab());		
		
		opportunityData = validateTableDataandReturnAsKeyValuePair(opportunityLabels(), opportunityValues(), 0);
		reportLog("Opportunity Data : {}"+ opportunityData);
		
		if(opportunityData.get("Stage").equals("Learner") && opportunityData.get("Learner Program").contains("LP-")) {
			reportLog("Validated the salesforce application after completed the purchase : "+opportunityData.get("Stage"));
		} else {
			reportLog("Unable to validate the salesforce application after completed the purchase : "+opportunityData.get("Stage"));
		}
	}
	

	/**
	 * Login Method 
	 */
	public void loginToSalesforce() {
		// Launch URL
		String url = PropertyReaderUtil.getProperty("url");
		driver.get(url);

		// Enter Credentials and Login
		typeValue(username(), "suryakumar.reddi@laureate.net.ml.l3qa");
		typeValue(pwd(), "Vijayam1");
		clickElement(clickLogin());
	}

}
