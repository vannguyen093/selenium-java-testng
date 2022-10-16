package listener;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import testng.Topic_06_Listener;

public class ExtentReportListener implements ITestListener {


	@Override
	public void onTestFailure(ITestResult result) {
		TakesScreenshot t = (TakesScreenshot) Topic_06_Listener.driver;
		File scrFile = t.getScreenshotAs(OutputType.FILE);
		
		try {
			File destFile = new File ("./ScreenShot/"+result.getName()+".jpg");
			FileUtils.copyFile(scrFile, destFile);
			Reporter.log("<a href ='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "' height ='100' width ='100' /> </a>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

}
