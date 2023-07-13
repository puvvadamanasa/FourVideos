package four.videos.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import four.videos.constants.AppConstants;
import four.videos.utils.ElementUtil;

public class SearchPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private By prodCount = By.cssSelector("div#content div.product-layout");
	
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	public int getProductCount() {
		int count = eleUtil.waitForElementsVisible(prodCount, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
		System.out.println("Products Count: "+count);
		return count;
	}
	public ProductInfoPage getProduct(String proName) {
		By pro = By.linkText(proName);
		eleUtil.waitForElementVisible(pro, AppConstants.DEFAULT_MEDIUM_TIME_OUT).click();
		return new ProductInfoPage(driver);
	}

}
