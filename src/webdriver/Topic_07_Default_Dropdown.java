package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Default_Dropdown {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Select select;
	Random rand;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		driver = new FirefoxDriver();
		rand = new Random();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Default_Dropdown() {
		driver.get("https://demo.nopcommerce.com/");
		driver.findElement(By.cssSelector("a.ico-register")).click();

		driver.findElement(By.cssSelector("#FirstName")).sendKeys("Joe");
		driver.findElement(By.cssSelector("#LastName")).sendKeys("Biden");

		// Khởi tạo select để thao tác với Date Dropdown
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']")));
		select.selectByVisibleText("13");

		// Khởi tạo select để thao tác với Month Dropdown
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']")));
		select.selectByVisibleText("May");

		// Khởi tạo select để thao tác với Year Dropdown
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']")));
		select.selectByVisibleText("1965");
		
		String emailAddress = "joebiden" + rand.nextInt(9999) + "@gmail.com";
		driver.findElement(By.cssSelector("#Email")).sendKeys(emailAddress);
		driver.findElement(By.cssSelector("#Company")).sendKeys("White House");
		driver.findElement(By.cssSelector("#Password")).sendKeys("123456");
		driver.findElement(By.cssSelector("#ConfirmPassword")).sendKeys("123456");
		
		driver.findElement(By.cssSelector("#register-button")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector(".result")).getText(), "Your registration completed");
		
		driver.findElement(By.cssSelector("a.ico-account")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("#FirstName")).getAttribute("value"), "Joe");
		Assert.assertEquals(driver.findElement(By.cssSelector("#LastName")).getAttribute("value"), "Biden");
		
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "13");
		
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "May");
		
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "1965");
		
		Assert.assertEquals(driver.findElement(By.cssSelector("#Email")).getAttribute("value"), emailAddress);
		Assert.assertEquals(driver.findElement(By.cssSelector("#Company")).getAttribute("value"), "White House");
		
	}
	
	@Test
	public void TC_02_Default_Dropdown() {
		driver.get("https://rode.com/en/support/where-to-buy");
		select = new Select(driver.findElement(By.id("country")));
		Assert.assertFalse(select.isMultiple());
		select.selectByValue("Vietnam");
		sleepInSecond(3);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Vietnam");
		List<WebElement> dealers = driver.findElements(By.cssSelector("#map h4"));
		for (WebElement element : dealers) {
			System.out.println(element.getText());
		}
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