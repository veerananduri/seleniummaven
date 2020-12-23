package com.pageobjects;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
		return By.cssSelector("//a[contains(text(),'"+course+"')]");
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
		return By.cssSelector("input[name=countryName]"); //United States
	}
	
	private By oktaInterestedIn() {
		return By.cssSelector("input[name=interestedIn]"); //Professional Development
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
		return By.cssSelector("input#termsandconditions");
	}
	
	private By checkoutContPayment() {
		return By.cssSelector("button#reg-button");
	}
	
	private By clickPaypal() {
		return By.cssSelector("div.paypal-button");
	}
	
	private By paypalEmail() {
		return By.cssSelector("input#email");
	}
	
	private By paypalNext() {
		return By.cssSelector("button#btnNext");
	}
	
	private By paypalPassword() {
		return By.cssSelector("p-%#sR2/");
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
		
		if(isElementDisplayed(selectCourseLink(course))) {
			clickElement(selectCourseLink(course));
			reportLog("Selected the course in search results page: " + course);
		} else {
			reportLog("Course not displayed in search results: " + course);
		}
	}
	
	public void addToCartandCheckout() {
		
	}
	
}
