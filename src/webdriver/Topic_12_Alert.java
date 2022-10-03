package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Alert {
	WebDriver driver;
	JavascriptExecutor jsExcutor;
	Alert alert;
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
		jsExcutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

//	@Test
	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		//Bắt Locator button
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		//Switch to alert (Khi alert đang xuất hiện)
		alert = driver.switchTo().alert();
		//Verify alert title
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		//Accept alert
		alert.accept();
		//Verify accept alert thành công
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked an alert successfully");
	}

//	@Test
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		//Bắt Locator button
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		//Switch to alert (Khi alert đang xuất hiện)
		alert = driver.switchTo().alert();
		//Verify alert title
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		//Cancel alert
		alert.dismiss();
		//Verify cancel alert thành công
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked: Cancel");
	}

//	@Test
	public void TC_03_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		String keyword = "Van Nguyen";
		//Bắt Locator button
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		//Switch to alert (Khi alert đang xuất hiện)
		alert = driver.switchTo().alert();
		//Verify alert title
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		//Enter value in prompt alert then accept it
		alert.sendKeys(keyword);
		sleepInSecond(3);
		alert.accept();
		//Verify prompt alert thành công
		Assert.assertEquals(driver.findElement(By.cssSelector("#result")).getText(), "You entered: " + keyword);
	}
	
//	@Test
	public void TC_04_Accept_Alert_Login() {
		driver.get("https://demo.guru99.com/v4");
		
		//Click Login Button
		driver.findElement(By.cssSelector("input[name='btnLogin']")).click();
		//Switch to alert
		alert = driver.switchTo().alert();
		//Verify alert title
		Assert.assertEquals(alert.getText(), "User or Password is not valid");
		//Accept alert
		alert.accept();
		//Verify current url
		Assert.assertEquals(driver.getCurrentUrl(), "https://demo.guru99.com/v4/index.php");
	}
	
//	@Test
	public void TC_05_Authentication_Alert_1() {
		//Pass hẳn User/ password vào Url trước khi open nó
		// https://username:password@url
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		
		//Verify text
		Assert.assertTrue(driver.findElement(By.cssSelector(".example p")).getText().contains("Congratulations!"));
	}
	
	@Test
	public void TC_06_Authentication_Alert_2() {
		driver.get("http://the-internet.herokuapp.com/");
		String basicAuthenUrl = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		//Bắt Locator của text Basic Auth (dùng hàm splitUrl)
		driver.get(splitUrl(basicAuthenUrl, "admin", "admin"));
		//Verify text
		Assert.assertTrue(driver.findElement(By.cssSelector(".example p")).getText().contains("Congratulations!"));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public String splitUrl(String basicAuthenUrl, String username, String password) {
		//Dùng hàm split()
		String[] authenUrlArray = basicAuthenUrl.split("//");
		//Update biến mới cùng với username và password
		basicAuthenUrl = authenUrlArray[0] + "//" + username + ":" + password + "@" + authenUrlArray[1];
		//Trả giá trị về biến: return
		return basicAuthenUrl;
	}
	public void sleepInSecond (long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}