package four.videos.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import four.videos.base.BaseTest;
import four.videos.constants.AppConstants;

public class LoginPageTest extends BaseTest{
	
	@Test(priority=1)
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE_VALUE);
	}
	@Test(priority=2)
	public void loginPageURLTest() {
		String actURL = loginPage.getLoginPageURL();
		Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_URL_VALUE));
	}
	@Test(priority=3)
	public void searchExistTest() {
		Assert.assertTrue(loginPage.isSearchExist());
	}
	@Test(priority=4)
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	@Test(priority=5)
	public void loginTest() {
		accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}

}
