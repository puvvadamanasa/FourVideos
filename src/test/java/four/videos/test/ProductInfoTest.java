package four.videos.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import four.videos.base.BaseTest;

public class ProductInfoTest extends BaseTest{
	@BeforeClass
	public void productInfoLogin() {
		accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());;
	}
	@DataProvider
	public Object[][] getMetaData() {
		return new Object[][] {
			{"Macbook","MacBook Pro","Brand","Apple"},
			{"Macbook","MacBook Pro","ProductHeader","MacBook Pro"},
			{"Samsung","Samsung SyncMaster 941BW","Availability","2-3 Days"},
			{"iMac","iMac","Product Code","Product 14"}
				
		};
	}
	@Test(dataProvider = "getMetaData")
	public void metaDataTest(String searchKey,String proName,String key,String value) {
		searchPage = accPage.productSearch(searchKey);
		proInfoPage = searchPage.getProduct(proName);
		Map<String ,String> actProdInfoMap = proInfoPage.getMetaData();
		softAssert.assertEquals(actProdInfoMap.get(key), value);
		softAssert.assertEquals(actProdInfoMap.get(key), value);
		softAssert.assertEquals(actProdInfoMap.get(key), value);
		softAssert.assertEquals(actProdInfoMap.get(key), value);
		softAssert.assertAll();
	}
	@DataProvider
	public Object[][] getImagesData() {
		return new Object[][] {
			{"Macbook","MacBook Pro",4},
			{"Macbook","MacBook Air",4},
			{"Samsung","Samsung SyncMaster 941BW",1},
			{"iMac","iMac",3}
		};
	}
	@Test(dataProvider = "getImagesData")
	public void imagesCountTest(String searchKey,String proName,int images) {
		searchPage = accPage.productSearch(searchKey);
		proInfoPage = searchPage.getProduct(proName);
		int actimages = proInfoPage.getProdImagesCount();
		Assert.assertEquals(actimages, images);	
	}
	@DataProvider
	public Object[][] addToCartData() {
		return new Object[][] {
			{"Macbook","MacBook Pro",1},
			{"Macbook","MacBook Air",2},
			{"Samsung","Samsung SyncMaster 941BW",3},
			{"iMac","iMac",4}
		};
	}
	@Test(dataProvider = "addToCartData")
	public void addToCartTest(String searchKey,String proName,int quantit) {
		searchPage = accPage.productSearch(searchKey);
		proInfoPage = searchPage.getProduct(proName);
		proInfoPage.prodQuantity(quantit);
		String actMsg = proInfoPage.addToCart();
		//Success: You have added MacBook Pro to your shopping cart!
		System.out.println("Actual Message"+actMsg);
		softAssert.assertTrue(actMsg.indexOf("Success")>=0);
		softAssert.assertTrue(actMsg.indexOf(proName)>=0);
		softAssert.assertEquals(actMsg, "Success: You have added "+proName+" to your shopping cart!");
		softAssert.assertAll();
	}
}
