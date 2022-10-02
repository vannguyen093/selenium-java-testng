package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Button {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if(osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		sleepInSecond(4);
		
//		//Close popup
//		driver.findElement(By.cssSelector("a#NC_CLOSE_ICON>img")).click();
//		sleepInSecond(3);
		
		//Chuyển qua tab Login
		driver.findElement(By.cssSelector(".pop-login-tab-login")).click();
		
		//Verify Đăng Nhập btn is disabled
		Assert.assertFalse(driver.findElement(By.cssSelector(".fhs-btn-login")).isEnabled());
		
		//Enter value to Email/ Password textbox
		driver.findElement(By.cssSelector("#login_username")).sendKeys("vannguyen093@gmail.com");
		driver.findElement(By.cssSelector("#login_password")).sendKeys("123456");
		sleepInSecond(3);
		
		//Verify Đăng nhập btn is enabled and have attribute color is red
		Assert.assertTrue(driver.findElement(By.cssSelector(".fhs-btn-login")).isEnabled());
		String rgbaColor = driver.findElement(By.cssSelector(".fhs-btn-login")).getCssValue("background-color");
		//Convert to Hexa color
		String hexaColor = Color.fromString(rgbaColor).asHex().toUpperCase();
		//Verify background color
		Assert.assertEquals(hexaColor, "C92127");
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public void sleepInSecond (long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}