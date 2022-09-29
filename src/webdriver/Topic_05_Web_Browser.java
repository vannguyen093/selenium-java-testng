package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser {
	// Khai báo
	WebDriver driver;
	WebElement element;

	// Khai báo + Khởi tạo
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}

		// Khởi tạo
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Browser() {
		// Các hàm tương tác với Browser sẽ thông qua biến driver

		// Đóng 1 tab/ window đang active
		driver.close(); //**
		// Đóng browser
		driver.quit(); //**

		// Tìm ra 1 element (single)
		WebElement loginButton = driver.findElement(By.cssSelector("")); //**
		// Tìm ra nhiều element (multiple)
		List <WebElement> links = driver.findElements(By.cssSelector("")); //**
		
		//Mở ra cái Url truyền vào
		driver.get("https://www.facebook.com/"); //**
		//Trả về 1 Url tại page đang đứng
		String gamePageUrl = driver.getCurrentUrl();
		
		//Lấy Page Source
		String gamePageSourceCode = driver.getPageSource();
		
		//Lấy ra cái ID của tab/ window đang active(Windows/Tab)
		driver.getWindowHandle(); //1 //**
		driver.getWindowHandles(); //Tất cả //**
		
		driver.manage().getCookies(); //Bài Cookies (Framework) //**
		driver.manage().logs().getAvailableLogTypes(); //Log (Framework)
		driver.manage().window().fullscreen(); //full viền
		driver.manage().window().maximize(); //không full viền //**
	
		//Test GUI 
		//Font/ Size/ Color/ Position/ Location/...
		//Ưu tiên làm chức năng trước (Functional UI), làm giao diện sau (Graphic UI)
		
		//Chờ cho element được tìm thấy trong khoảng time xx giây
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);//**
		//Chờ cho page load thành công sau xx giây
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		//Chờ cho script được inject thành công vào browser/ element sau xx giây (JSExecutor)
		driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
		
		//Navigate
		driver.navigate().back();
		driver.navigate().forward();
		driver.navigate().refresh();
		driver.navigate().to("https://www.facebook.com/");
		
		//Alert/ Frame (Iframe)/ Window (Tab)
		driver.switchTo().alert();//**
		
		driver.switchTo().frame(0);//**
		
		driver.switchTo().window("");//**
		
	}

	@Test
	public void TC_02_Element() {
		// Các hàm tương tác với Element sẽ thông qua các element
	}

	@Test
	public void TC_03_() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}