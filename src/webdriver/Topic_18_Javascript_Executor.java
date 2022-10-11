package webdriver;

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

public class Topic_18_Javascript_Executor {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

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
	public void TC_01_Techpanda_JE() {
		navigateToUrlByJS("http://live.techpanda.org/");
		sleepInSecond(3);

		String getPageDomain = (String) executeForBrowser("return document.domain");
		sleepInSecond(2);
		Assert.assertEquals(getPageDomain, "live.techpanda.org");

		String getPageURL = (String) executeForBrowser("return document.URL");
		sleepInSecond(2);
		Assert.assertEquals(getPageURL, "http://live.techpanda.org/");

		hightlightElement("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Mobile']");
		sleepInSecond(3);

		hightlightElement("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		sleepInSecond(3);

		Assert.assertTrue(areExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));

		hightlightElement("//a[text()='Customer Service']");
		clickToElementByJS("//a[text()='Customer Service']");
		sleepInSecond(3);

		String getPageTitle = (String) executeForBrowser("return document.title");
		sleepInSecond(2);
		Assert.assertEquals(getPageTitle, "Customer Service");

		scrollToElementOnDown("//input[@id='newsletter']");
		sendkeyToElementByJS("//input[@id='newsletter']", "ioukhjiuyjhxcgv@gmail.com");
		sleepInSecond(3);
		hightlightElement("//button[@title='Subscribe']");
		clickToElementByJS("//button[@title='Subscribe']");
		sleepInSecond(3);
		Assert.assertTrue(areExpectedTextInInnerText("Thank you for your subscription."));

		navigateToUrlByJS("http://demo.guru99.com/v4/");
		sleepInSecond(3);
		String getNewDomainPage = (String) executeForBrowser("return document.domain");
		sleepInSecond(2);
		Assert.assertEquals(getNewDomainPage, "demo.guru99.com");
	}

	@Test
	public void TC_02_Ubuntu() {
		navigateToUrlByJS("https://login.ubuntu.com/");
		sleepInSecond(3);

		if (driver.findElement(By.cssSelector("dialog.cookie-policy")).isDisplayed()) {

			driver.findElement(By.cssSelector("dialog.cookie-policy button#cookie-policy-button-accept")).click();
			sleepInSecond(3);
		}

		hightlightElement("//button[@name='continue']");
		clickToElementByJS("//button[@name='continue']");
		sleepInSecond(3);

		Assert.assertEquals(getElementValidationMessage("//input[@id='id_email']"), "Please fill out this field.");
		sendkeyToElementByJS("//input[@id='id_email']", "ui@iuy@uy");
		sleepInSecond(3);
		hightlightElement("//button[@name='continue']");
		clickToElementByJS("//button[@name='continue']");
		sleepInSecond(3);
		Assert.assertEquals(getElementValidationMessage("//input[@id='id_email']"), "Please enter an email address.");

		driver.findElement(By.xpath("//input[@id='id_email']")).clear();
		sendkeyToElementByJS("//input[@id='id_email']", "ufghjghji@gmail.com");
		sleepInSecond(3);
		hightlightElement("//button[@name='continue']");
		clickToElementByJS("//button[@name='continue']");
		sleepInSecond(3);

		Assert.assertEquals(getElementValidationMessage("//input[@id='id_password']"), "Please fill out this field.");
		sendkeyToElementByJS("//input[@id='id_email']", "12456467");
		sleepInSecond(3);
		hightlightElement("//button[@name='continue']");
		clickToElementByJS("//button[@name='continue']");
		sleepInSecond(3);

//		Assert.assertTrue(areExpectedTextInInnerText(
//				"There were some problems with the information you gave us. Please check below and try again."));
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

	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element,
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getElement(locator));
		return status;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}
}