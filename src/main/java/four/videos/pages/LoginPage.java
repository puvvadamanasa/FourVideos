package four.videos.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import four.videos.constants.AppConstants;
import four.videos.utils.ElementUtil;

public class LoginPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private By emailid = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdlink = By.linkText("Forgotten Password");
	private By search = By.name("search");
	private By registerLink = By.linkText("Register");
	
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleIsandFetch(AppConstants.DEFAULT_SHORT_TIME_OUT, AppConstants.LOGIN_PAGE_TITLE_VALUE);
		System.out.println("Login Page Title: "+title);
		return title;
	}
	public String getLoginPageURL() {
		String url = eleUtil.waitForURLContainsandFetch(AppConstants.DEFAULT_SHORT_TIME_OUT, AppConstants.LOGIN_PAGE_URL_VALUE);
		System.out.println("Login Page URL: "+url);
		return url;
	}
	public boolean isForgotPwdLinkExist() {
		return eleUtil.waitForElementVisible(forgotPwdlink, AppConstants.DEFAULT_SHORT_TIME_OUT).isDisplayed();
	}
	public boolean isSearchExist() {
		return eleUtil.waitForElementVisible(search, AppConstants.DEFAULT_SHORT_TIME_OUT).isDisplayed();
	}
	public AccountsPage doLogin(String email,String pwd) {
		eleUtil.waitForElementVisible(emailid, AppConstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(email);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}
	public RegistrationPage navigateToRegistration() {
		eleUtil.doClick(registerLink);
		return new RegistrationPage(driver);
	}
}
