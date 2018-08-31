package WebDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import RestAssured.ApiHelper;


public class BaseWebDriver{
	ApiHelper helper;
	protected WebDriver driver;
	
	@BeforeTest
	public void setup() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Downloads\\chromedriver_win32\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-infobars");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		helper = new ApiHelper();
		helper.login();
		helper.createProject();
		driver.get(helper.url());
		
		((JavascriptExecutor) driver).executeScript("window.localStorage.setItem('token','\""+helper.accessToken()+"\"');");
		((JavascriptExecutor) driver).executeScript("window.localStorage.setItem('userInfo','"+helper.responseAsString()+"');");
//	    for(Cookie cookie:driver.manage().getCookies()) {
//		driver.manage().addCookie(cookie);
//	    }
	}
	
//	@AfterTest
//	public void tearDown() throws InterruptedException {
//	helper.deleteProject();
//	driver.close();
//		
//	}

}
