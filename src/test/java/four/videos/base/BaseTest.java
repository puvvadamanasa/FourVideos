package four.videos.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import four.videos.factory.DriverFactory;
import four.videos.pages.AccountsPage;
import four.videos.pages.LoginPage;
import four.videos.pages.ProductInfoPage;
import four.videos.pages.RegistrationPage;
import four.videos.pages.SearchPage;

public class BaseTest {
	DriverFactory df;
	WebDriver driver;
	protected LoginPage loginPage;
	protected AccountsPage accPage;
	protected Properties prop;
	protected SearchPage searchPage;
	protected ProductInfoPage proInfoPage;
	protected SoftAssert softAssert;
	protected RegistrationPage regPage;
	
	@BeforeTest
	public void setup() {
		df = new DriverFactory();
		prop = df.initProp();
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
