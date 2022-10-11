package webdriver;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_Upload_FIle {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	// Get Image Name
	String img1 = "david-marcu-78A265wPiO4-unsplash.jpg";
	String img2 = "qingbao-meng-01_igFr7hd4-unsplash.jpg";
	String img3 = "tron-le-wUk2U5Wirxg-unsplash.jpg";

	// Get Path
	String uploadFilePath = projectPath + File.separator + "uploadfile" + File.separator;

	// Get Image Path
	String pathImg1 = uploadFilePath + img1;
	String pathImg2 = uploadFilePath + img2;
	String pathImg3 = uploadFilePath + img3;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

//	@Test
	public void TC_01_Upload_One_File_Per_Time() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		// Upload file
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(pathImg1);
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(pathImg2);
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(pathImg3);
		sleepInSecond(3);

		// Verify upload thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + img1 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + img2 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + img3 + "']")).isDisplayed());

		// Click Start btn
		List<WebElement> startBtn = driver.findElements(By.cssSelector("table.table button.start"));

		for (WebElement button : startBtn) {
			button.click();
			sleepInSecond(2);
		}

		// Verify link img uploaded
		Assert.assertTrue(driver.findElement(By.cssSelector("p a[download='" + img1 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("p a[download='" + img2 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("p a[download='" + img3 + "']")).isDisplayed());
	}

	@Test
	public void TC_02_Upload_Multiple_Files_Per_Time() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		// Upload file
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(pathImg1 + "\n" + pathImg2 + "\n" + pathImg3);
		sleepInSecond(3);

		// Verify upload thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + img1 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + img2 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + img3 + "']")).isDisplayed());

		// Click Start btn
		List<WebElement> startBtn = driver.findElements(By.cssSelector("table.table button.start"));

		for (WebElement button : startBtn) {
			button.click();
			sleepInSecond(2);
		}

		// Verify link img uploaded
		Assert.assertTrue(driver.findElement(By.cssSelector("p a[download='" + img1 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("p a[download='" + img2 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("p a[download='" + img3 + "']")).isDisplayed());
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