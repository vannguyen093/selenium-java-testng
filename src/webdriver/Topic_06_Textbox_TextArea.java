package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Textbox_TextArea {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String firstName, lastName, employeeID, editFirstName, editLastName;

	@BeforeClass
	public void beforeClass() {
		if(osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		firstName = "Automation";
		lastName = "FC";
		editFirstName = "Manual";
		editLastName = "FCB";
	}

	@Test
	public void TC_01_Textbox_TextArea() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
		driver.findElement(By.cssSelector(".orangehrm-login-button")).click();
		sleepInSecond(10);
		
		//Mở màn hình Add Employee
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/pim/addEmployee");
		sleepInSecond(10);
		
		driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys(lastName);
		
		//Lưu giá trị của Employee ID vào biến
		employeeID = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::input/following-sibling::input/input")).getAttribute("value");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		sleepInSecond(10);
		
		//Verify textbox enabled
		Assert.assertTrue(driver.findElement(By.xpath("//input[@name='firstName']")).isEnabled());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@name='lastName']")).isEnabled());
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::input/following-sibling::input/input")).isEnabled());
		
		//Verify actual value equal expected value
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='firstName']")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='lastName']")).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::input/following-sibling::input/input")).getAttribute("value"), employeeID);
		
//		//Edit dữ liệu mới
//		driver.findElement(By.xpath("//input[@name='firstName']")).clear();
//		driver.findElement(By.xpath("//input[@name='lastName']")).clear();
//		sleepInSecond(5);
//		driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys(editFirstName);
//		driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys(editLastName);
//		driver.findElement(By.xpath("(//button[@type='submit'])[1]")).click();
//		sleepInSecond(10);
//		
//		//Verify giá trị mới nhập
//		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='firstName']")).getAttribute("value"), editFirstName);
//		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='lastName']")).getAttribute("value"), editLastName);
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