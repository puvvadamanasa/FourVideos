package four.videos.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.aspectj.util.FileUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
	public WebDriver driver;
	public Properties prop;
	public static String highlight;
	public static ThreadLocal<WebDriver> tlocal = new ThreadLocal<WebDriver>();
	public WebDriver initDriver(Properties prop) {
		OptionsManager o = new OptionsManager(prop);
		highlight = prop.getProperty("highlight").trim();
		String browserName = prop.getProperty("browser").trim().toLowerCase();
		System.out.println("BrowserName: "+browserName);
		String url = prop.getProperty("url").trim();
		System.out.println(url);
		if(browserName.equalsIgnoreCase("chrome")) {
			//driver = new ChromeDriver(o.getChromeOptions());
			tlocal.set(new ChromeDriver(o.getChromeOptions()));
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			//driver = new FirefoxDriver(o.getFirefoxOptions());
			tlocal.set(new FirefoxDriver(o.getFirefoxOptions()));
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			//driver = new EdgeDriver(o.getEdgeOptions());
			tlocal.set(new EdgeDriver(o.getEdgeOptions()));
		}
		else {
			System.out.println("Please pass right browser name");
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(url);
		return getDriver();
	}
	public static synchronized WebDriver getDriver() {
		return tlocal.get();
	}
	public Properties initProp() {
		prop = new Properties();
		try {
			FileInputStream fi = new FileInputStream("./src/test/resources/config/config.properties");
			prop.load(fi);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	public static String getScreenshot() {
		File srcFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis()+".png";
		File dest = new File(path);
		try {
			FileUtil.copyFile(srcFile, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}
