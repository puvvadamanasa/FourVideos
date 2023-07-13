package four.videos.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import four.videos.constants.AppConstants;
import four.videos.utils.ElementUtil;

public class AccountsPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private By logout = By.linkText("Logout");
	private By searchBtn = By.cssSelector("button.btn.btn-default");
	private By accHeaders = By.cssSelector("div#content h2");
	private By search = By.name("search");
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	public String getAccountsPageTitle() {
		String title = eleUtil.waitForTitleIsandFetch(AppConstants.DEFAULT_SHORT_TIME_OUT, AppConstants.ACCOUNTS_PAGE_TITLE_VALUE);
		System.out.println("Accounts Page Title: "+title);
		return title;
	}
	public String getAccountsPageURL() {
		String url = eleUtil.waitForURLContainsandFetch(AppConstants.DEFAULT_SHORT_TIME_OUT, AppConstants.ACCOUNTS_PAGE_URL_VALUE);
		System.out.println("Accounts Page URL: "+url);
		return url;
	}
	public boolean isLogoutLinkExist() {
		return eleUtil.waitForElementVisible(logout, AppConstants.DEFAULT_SHORT_TIME_OUT).isDisplayed();
	}
	public int getAccHeadersCount() {
		return eleUtil.waitForElementsVisible(accHeaders, AppConstants.DEFAULT_SHORT_TIME_OUT).size();
	}
	public List<String> getAccHeadersValList() {
		List<String> accHeaderVal = new ArrayList<>();
		List<WebElement> accHeader = eleUtil.waitForElementsVisible(accHeaders, AppConstants.DEFAULT_MEDIUM_TIME_OUT);
		for(WebElement e:accHeader) {
			String text = e.getText();
			accHeaderVal.add(text);
		}
		System.out.println("Acc headers value: "+accHeaderVal);
		return accHeaderVal;
	}
	public boolean isSearchExist() {
		return eleUtil.waitForElementVisible(search,AppConstants.DEFAULT_SHORT_TIME_OUT).isDisplayed();
	}
	public SearchPage productSearch(String searchKey) {
		if(isSearchExist()) {
			eleUtil.doSendKeys(search, searchKey);
			eleUtil.doClick(searchBtn);
			return new SearchPage(driver);
		}
		else {
			System.out.println("Search Not exist");
			return null;
		}
	}

}
