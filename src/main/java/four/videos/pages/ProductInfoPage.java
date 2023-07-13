package four.videos.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import four.videos.constants.AppConstants;
import four.videos.utils.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private Map<String, String> prodInfoMap;
	private By quantity = By.id("input-quantity");
	private By addToCartBtn = By.id("button-cart");
	private By succMsg = By.cssSelector("div.alert.alert-success");
	private By prodHeader = By.tagName("h1");
	private By images = By.cssSelector("ul.thumbnails img");
	private By metaprice = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By metatax = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	public String getProdHeader() {
		String text = eleUtil.getElementText(prodHeader);
		System.out.println("Product Header Text: "+text);
		return text;
	}
	public int getProdImagesCount() {
		int imgcount = eleUtil.waitForElementsVisible(images, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
		System.out.println("Images Count: "+imgcount);
		return imgcount;
	}
	public Map<String, String> getMetaData() {
		prodInfoMap = new LinkedHashMap<String, String>();
		String prodName = getProdHeader();
		prodInfoMap.put("ProductHeader", prodName);
		getMetaPrice();
		getMetaTax();
		System.out.println("Final Map: "+prodInfoMap);
		return prodInfoMap;
	}
	private void getMetaPrice() {
		//prodInfoMap = new HashMap<String, String>();
		//prodInfoMap = new TreeMap<String, String>();
		List<WebElement> proList = eleUtil.getElements(metaprice);
		for(WebElement e:proList) {
			String text = e.getText();
			String[] product = text.split(":");
			String key = product[0].trim();
			String value = product[1].trim();
			prodInfoMap.put(key, value);
		}
	}
	private void getMetaTax() {
		List<WebElement> proTax = eleUtil.getElements(metatax);
		String tax = proTax.get(0).getText().trim();
		prodInfoMap.put("price", tax);
		String extax = proTax.get(1).getText().split(":")[1].trim();
		prodInfoMap.put("extax", extax);
	}
	public void prodQuantity(int quan) {
		eleUtil.doSendKeys(quantity, String.valueOf(quan));
	}
	public String addToCart() {
		eleUtil.doClick(addToCartBtn);
		String msg = eleUtil.waitForElementVisible(succMsg, AppConstants.DEFAULT_MEDIUM_TIME_OUT).getText();
		msg = msg.substring(0, msg.length()-1).replace("\n", "");
		System.out.println("Success Msg: "+msg);
		return msg;
	}
}
