package four.videos.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import four.videos.constants.AppConstants;
import four.videos.utils.ElementUtil;

public class RegistrationPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By firstname = By.id("input-firstname");
	private By lastname = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By conpassword = By.id("input-confirm");
	private By subYes = By.xpath("//label[normalize-space()='Yes']/input[@type='radio']");
	private By subNo = By.xpath("//label[normalize-space()='No']/input[@type='radio']");
	private By check = By.name("agree");
	private By continueBtn = By.xpath("//input[@type='submit' and @value='Continue']");
	private By registerLink = By.linkText("Register");
	private By logout = By.linkText("Logout");
	private By succMsg = By.cssSelector("div#content h1");
	
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	public boolean regUser(String firstname,String lastname,String email,String telephone,String password,String subscribe) {
		eleUtil.waitForElementVisible(this.firstname, AppConstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(firstname);
		eleUtil.doSendKeys(this.lastname, lastname);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(conpassword, password);
		if(subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subYes);
		}
		else {
			eleUtil.doClick(subNo);
		}
		eleUtil.doClick(check);
		eleUtil.doActionClick(continueBtn);
		String msg = eleUtil.waitForElementVisible(succMsg, AppConstants.DEFAULT_MEDIUM_TIME_OUT).getText();
		System.out.println("Message: "+msg);
		
		if(msg.contains(AppConstants.REGISTRATION_SUCC_MSG)) {
			eleUtil.doClick(logout);
			eleUtil.doClick(registerLink);
			return true;
		}
		return false;
	}

}
