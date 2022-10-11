package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Wait_Element_Condition_Status {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 15);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

//	@Test
	public void TC_01_Visible_Display_Visibility() {
		driver.get("https://www.facebook.com/");

		// 1 - Có trên UI (Bắt buộc)
		// 1 - Có trong DOM (Bắt buộc)
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));

	}

//	@Test
	public void TC_02_Invisible_UnDisplay_Invisibiliy_I() {
		driver.get("https://www.facebook.com/");

		// 2 - Không có trên UI (Bắt buộc)
		// 1 - Có trong DOM
		driver.findElement(By.cssSelector("a[data-testid]")).click();

		explicitWait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));
	}

//	@Test
	public void TC_03_Invisible_UnDisplay_Invisibiliy_II() {
		driver.get("https://www.facebook.com/");

		// 2 - Không có trên UI (Bắt buộc)
		// 2 - Không có trong DOM

		explicitWait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));
	}

//	@Test
	public void TC_04_Presence_I() {
		driver.get("https://www.facebook.com/");

		// 1 - Có trên UI
		// 1 - Có trong DOM (Bắt buộc)
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
	}
	
//	@Test
	public void TC_05_Presence_II() {
		driver.get("https://www.facebook.com/");
		
		// 2 - Không có trên UI
		// 1 - Có trong DOM (Bắt buộc)
		driver.findElement(By.cssSelector("a[data-testid]")).click();
		
		explicitWait.until(ExpectedConditions
				.presenceOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));
	}
	
	@Test
	public void TC_06_Staleness() {
		driver.get("https://www.facebook.com/");
		
		// 2 - Không có trên UI (Bắt buộc)
		// 2 - Không có trong DOM (Bắt buộc)
		driver.findElement(By.cssSelector("a[data-testid]")).click();
		WebElement reEnterEmail = driver.findElement(By.cssSelector("input[name='reg_email_confirmation__']"));
		driver.findElement(By.cssSelector("img._8idr")).click();
		
		explicitWait.until(ExpectedConditions.stalenessOf(reEnterEmail));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}