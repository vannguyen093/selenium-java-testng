package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser_B3 {
	WebDriver driver;
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Is_Displayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// Email Textbox
		WebElement emailTextbox = driver.findElement(By.cssSelector("#mail"));
		if (emailTextbox.isDisplayed()) {
			emailTextbox.sendKeys("Automation Testing");
			System.out.println("Email Textbox is displayed");
		} else {
			System.out.println("Email Textbox is not displayed");
		}

		// Radio Button
		WebElement ageUnder18Radio = driver.findElement(By.cssSelector("#under_18"));
		if (ageUnder18Radio.isDisplayed()) {
			ageUnder18Radio.click();
			System.out.println("Age Under 18 radio is displayed");
		} else {
			System.out.println("Age Under 18 radio is not displayed");
		}

		// Education Textarea
		WebElement educationTextarea = driver.findElement(By.cssSelector("#edu"));
		if (educationTextarea.isDisplayed()) {
			educationTextarea.sendKeys("Automation Testing");
			System.out.println("Education Textarea is displayed");
		} else {
			System.out.println("Education Textarea is not displayed");
		}

		// Image 5
		WebElement image5 = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
		if (image5.isDisplayed()) {
			System.out.println("Image 5 is displayed");
		} else {
			System.out.println("Image 5 is not displayed");
		}
	}

	@Test
	public void TC_02_Is_Enabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// Email Textbox
		WebElement emailTextbox = driver.findElement(By.cssSelector("#mail"));
		if (emailTextbox.isEnabled()) {
			System.out.println("Email Textbox is enabled");
		} else {
			System.out.println("Email Textbox is disabled");
		}

		// Password Textbox
		WebElement passwordTextbox = driver.findElement(By.cssSelector("#disable_password"));
		if (passwordTextbox.isEnabled()) {
			System.out.println("Password Textbox is enabled");
		} else {
			System.out.println("Password Textbox is disabled");
		}

		// Age Under 18 Radio
		WebElement ageUnder18Radio = driver.findElement(By.cssSelector("#under_18"));
		if (ageUnder18Radio.isEnabled()) {
			System.out.println("Age Under 18 Radio is enabled");
		} else {
			System.out.println("Age Under 18 Radio is disabled");
		}

		// Disabled Radio
		WebElement disabledRadio = driver.findElement(By.cssSelector("#radio-disabled"));
		if (disabledRadio.isEnabled()) {
			System.out.println("Radio is enabled");
		} else {
			System.out.println("Radio is disabled");
		}

		// Education Textarea
		WebElement educationTextarea = driver.findElement(By.cssSelector("#edu"));
		if (educationTextarea.isEnabled()) {
			System.out.println("Education Textarea is enabled");
		} else {
			System.out.println("Education Textarea is disabled");
		}

		// Biography Textarea
		WebElement biographyTextarea = driver.findElement(By.cssSelector("#bio"));
		if (biographyTextarea.isEnabled()) {
			System.out.println("Biography Textarea is enabled");
		} else {
			System.out.println("Biography Textarea is disabled");
		}

		// Job Role 1 Dropdown
		WebElement jobRole1Dropdown = driver.findElement(By.cssSelector("#job1"));
		if (jobRole1Dropdown.isEnabled()) {
			System.out.println("Job Role 1 Dropdown is enabled");
		} else {
			System.out.println("Job Role 1 Dropdown is disabled");
		}

		// Job Role 3 Dropdown
		WebElement jobRole3Dropdown = driver.findElement(By.cssSelector("#job3"));
		if (jobRole3Dropdown.isEnabled()) {
			System.out.println("Job Role 3 Dropdown is enabled");
		} else {
			System.out.println("Job Role 3 Dropdown is disabled");
		}

		// Interest Development Checkbox
		WebElement interestDevCheckbox = driver.findElement(By.cssSelector("#development"));
		if (interestDevCheckbox.isEnabled()) {
			System.out.println("Interest Development is enabled");
		} else {
			System.out.println("Interest Development is disabled");
		}

		// Disabled Checkbox
		WebElement disabledCheckbox = driver.findElement(By.cssSelector("#check-disbaled"));
		if (disabledCheckbox.isEnabled()) {
			System.out.println("Checkbox is enabled");
		} else {
			System.out.println("Checkbox is disabled");
		}

		// Slider 1
		WebElement slider1 = driver.findElement(By.cssSelector("#slider-1"));
		if (slider1.isEnabled()) {
			System.out.println("Slider 1 is enabled");
		} else {
			System.out.println("Slider 1 is disabled");
		}

		// Slider 2
		WebElement slider2 = driver.findElement(By.cssSelector("#slider-2"));
		if (slider2.isEnabled()) {
			System.out.println("Slider 2 is enabled");
		} else {
			System.out.println("Slider 2 is disabled");
		}
	}

	@Test
	public void TC_03_Is_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// Age Under 18 Radio
		WebElement ageUnder18Radio = driver.findElement(By.cssSelector("#under_18"));
		ageUnder18Radio.click();
		if (ageUnder18Radio.isSelected()) {
			System.out.println("Age Under 18 Radio is selected");
		} else {
			System.out.println("Age Under 18 Radio is unselected");
		}

		// Java Checkbox
		WebElement javaCheckbox = driver.findElement(By.cssSelector("#java"));
		javaCheckbox.click();
		if (javaCheckbox.isSelected()) {
			System.out.println("Java Checkbox is selected");
		} else {
			System.out.println("Java Checkbox is unselected");
		}
	}

	@Test
	public void TC_04_MailChimp() {
		driver.get("https://login.mailchimp.com/signup/");

		driver.findElement(By.cssSelector("#email")).sendKeys("vannguyen093@gmail.com");
		sleepinSecond(3);

		WebElement passwordTextbox = driver.findElement(By.cssSelector("#new_password"));

		// Check lowercase
		passwordTextbox.sendKeys("aaa");
		sleepinSecond(2);
		Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());

		// Check uppercase
		passwordTextbox.clear();
		passwordTextbox.sendKeys("AAA");
		sleepinSecond(2);
		Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());

		// Check number char
		passwordTextbox.clear();
		passwordTextbox.sendKeys("123");
		sleepinSecond(2);
		Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());

		// Check special char
		passwordTextbox.clear();
		passwordTextbox.sendKeys("!@#$");
		sleepinSecond(2);
		Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());

		// Check 8 char
		passwordTextbox.clear();
		passwordTextbox.sendKeys("abch123$");
		sleepinSecond(2);
		Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());

		// Full
		passwordTextbox.clear();
		passwordTextbox.sendKeys("abCH123$");
		sleepinSecond(2);
		Assert.assertTrue(driver.findElement(By.cssSelector(".av-password.success-check")).isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public void sleepinSecond (long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}