package webdriver;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_22_Wait_ExplicitWait {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait explicit;
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
	}

//	@Test
	public void TC_01_Ajax_Loading() {
		driver.get(
				"https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		explicit = new WebDriverWait(driver, 15);

		Assert.assertTrue(explicit
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.RadCalendar"))).isDisplayed());

		explicit.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("span[id='ctl00_ContentPlaceholder1_Label1']")));
		Assert.assertEquals(driver.findElement(By.cssSelector("span[id='ctl00_ContentPlaceholder1_Label1']")).getText(),
				"No Selected Dates to display.");

		driver.findElement(By.xpath("//table[@class='rcMainTable']//a[text()='11']")).click();
		explicit.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//table[@class='rcMainTable']//a[text()='11']")));
		explicit.until(
				ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar1'] div.raDiv")));

		explicit.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("span[id='ctl00_ContentPlaceholder1_Label1']")));
		Assert.assertEquals(driver.findElement(By.cssSelector("span[id='ctl00_ContentPlaceholder1_Label1']")).getText(),
				"Tuesday, October 11, 2022");
	}

	@Test
	public void TC_02_Gofile() {
		driver.get("https://gofile.io/?t=uploadFiles");
		explicit = new WebDriverWait(driver, 100);
		
		explicit.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#rowLoading i")));

		explicit.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("div#rowUploadButton button.uploadButton")));
		
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(pathImg1 + "\n" + pathImg2 + "\n" + pathImg3);
		
		explicit.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress-bar"))));
		
		Assert.assertTrue(explicit.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.callout h5"))).isDisplayed());
		
		explicit.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button#rowUploadSuccess-showFiles"))).click();
		
		Assert.assertTrue(explicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='"+ img1 +"']/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']"))).isDisplayed());
		Assert.assertTrue(explicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='"+ img2 +"']/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']"))).isDisplayed());
		Assert.assertTrue(explicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='"+ img3 +"']/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']"))).isDisplayed());
		
		Assert.assertTrue(explicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='"+ img1 +"']/parent::a/parent::div/following-sibling::div//button[contains(@class,'contentPlay')]"))).isDisplayed());
		Assert.assertTrue(explicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='"+ img2 +"']/parent::a/parent::div/following-sibling::div//button[contains(@class,'contentPlay')]"))).isDisplayed());
		Assert.assertTrue(explicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='"+ img3 +"']/parent::a/parent::div/following-sibling::div//button[contains(@class,'contentPlay')]"))).isDisplayed());

	}

//	@Test
	public void TC_03_() {

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