package four.videos.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import four.videos.base.BaseTest;
import four.videos.constants.AppConstants;


public class AccountsPageTest extends BaseTest{
	@BeforeClass
	public void accPageloginTest() {
		accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());;
	}
	@Test
	public void accountPageTitleTest() {
		String actTitle = accPage.getAccountsPageTitle();
		Assert.assertEquals(actTitle, AppConstants.ACCOUNTS_PAGE_TITLE_VALUE);
	}
	@Test
	public void accountPageURLTest() {
		String actUrl = accPage.getAccountsPageURL();
		Assert.assertTrue(actUrl.contains(AppConstants.ACCOUNTS_PAGE_URL_VALUE));
	}
	@Test
	public void accountPageLogoutTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	@Test
	public void searchExistTest() {
		Assert.assertTrue(accPage.isSearchExist());
	}
	@Test
	public void accPageHeadersCountTest() {
		Assert.assertEquals(accPage.getAccHeadersCount(),AppConstants.ACCOUNTS_PAGE_HEADERS_COUNT);
	}
	@Test
	public void accPageHeadersValTest() {
		System.out.println("App const headers list:" +AppConstants.ACCOUNTS_PAGE_HEADERS_LIST);
		Assert.assertEquals(accPage.getAccHeadersValList(),AppConstants.ACCOUNTS_PAGE_HEADERS_LIST);
	}
	@DataProvider
	public Object[][] searchProductCountData() {
		return new Object[][] {
			{"MacBook"},
			{"Samsung"},
			{"iMac"},
			{"Apple"}
		};
	}
	@Test(dataProvider = "searchProductCountData")
	public void searchProductCountTest(String searchKey) {
		searchPage = accPage.productSearch(searchKey);
		Assert.assertTrue(searchPage.getProductCount()>0);
	}
	@DataProvider
	public Object[][] searchTestData() {
		return new Object[][] {
			{"MacBook","MacBook Pro"},
			{"Samsung","Samsung SyncMaster 941BW"},
			{"iMac","iMac"},
			{"Apple","Apple Cinema 30\""}
		};
	}
	@Test(dataProvider = "searchTestData")
	public void searchTest(String searchKey,String proName) {
		searchPage = accPage.productSearch(searchKey);
		if(searchPage.getProductCount()>0) {
			proInfoPage = searchPage.getProduct(proName);
			Assert.assertEquals(proInfoPage.getProdHeader(),proName);
		}
	}

}
