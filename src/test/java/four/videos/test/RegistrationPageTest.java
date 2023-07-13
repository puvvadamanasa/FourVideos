package four.videos.test;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import four.videos.base.BaseTest;
import four.videos.constants.AppConstants;
import four.videos.utils.XLUtil;

public class RegistrationPageTest extends BaseTest{
	@BeforeClass
	public void RegistrationPagesetUp() {
		regPage = loginPage.navigateToRegistration();
	}
	public String reandomEmail() {
		Random random = new Random();
		String email = "automation"+System.currentTimeMillis()+"@gmail.com";
		return email;
	}
	@DataProvider
	public Object[][] getRegData() {
		Object data[][] = XLUtil.getData(AppConstants.XL_SHEET_NAME);
		return data;
	}
	@Test(dataProvider = "getRegData")
	public void regTest(String firstname,String lastname,String telephone,String password,String subscribe) {
		boolean actRes = regPage.regUser(firstname, lastname, reandomEmail(), telephone, password,subscribe);
		Assert.assertTrue(actRes);
	}

}
