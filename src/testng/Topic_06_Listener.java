package testng;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import listener.ExtentReportListener;

@Listeners(ExtentReportListener.class)
public class Topic_06_Listener {
	public static WebDriver driver;
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
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test()
	public void TC_01_Register(){
		driver.get("http://live.techpanda.org/index.php/customer/account/create/");

		driver.findElement(By.id("firstname")).sendKeys("Van");
		driver.findElement(By.id("lastname")).sendKeys("Nguyen");
		driver.findElement(By.id("email_address")).sendKeys(randomEmail());
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.id("confirmation")).sendKeys("123456");
		driver.findElement(By.cssSelector("button[title='Register']")).click();
		Assert.assertFalse(driver.findElement(By.xpath("//ul[@class='messages']//span[text()='Thank you for registering with Main Website Store.']")).isDisplayed());
		
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public String randomEmail() {
		Random rand = new Random();
		return "vannguyen" + rand.nextInt(99999) + "@gmail.com";
	}
}
