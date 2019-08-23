/**************************************** PURPOSE **********************************

 - This class contains the code related to Basic setup of TestSuites such as Instantiating Browser,
 - Launching Browser from selected Configuration, perform Clean Up etc

USAGE
 - Inherit this BaseClass for any TestSuite class. You don't have to write any @BeforeMethod and @AfterMethod
 - actions in your TestSuite Classes
 
 - Example: 
 --import Com.Base
 --- public class <TestSuiteClassName> extends BaseClass
 */
package Common;

import java.io.File;
import java.lang.reflect.Method;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseSetup implements TimeOuts {

	protected static WebDriver driver;
	private Logger log = Logger.getLogger(BaseSetup.class);
	public static ExtentReports extentReport;
	public static ExtentTest extentTest;
	public ConfigManager config = new ConfigManager();

	/**
	 * Getter method for WebDriver
	 * 
	 * @return driver
	 */

	public WebDriver getDriver() {
		return driver;
	}

	public WebDriver getDriver1() {
		return driver;
	}
	/**
	 * 
	 * Setter method for WebDriver
	 *
	 * @param driver
	 */
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Initialize browser, Pass URL, Creates folder structure to store the
	 * automation reports, Create download file folder.
	 * 
	 */

	@BeforeSuite
	public void beforeSuite() {
		try {
			if (getDriver() == null) {
				initiateDriver(config.getProperty("Browser.Name"));
				driver.manage().deleteAllCookies();
				log.info(
						"---------------------------------Initiated Webdriver----------------------------------------------");
				System.out.println("URL :-"+config.getProperty("Url"));
				getDriver().get(config.getProperty("Url"));
				String reportName = "SaferWatch Command Center Automation Report" + ".html";
				ReportSetup.dateTime();
				File targetFile = new File(ReportSetup.setReportPath(), reportName);
				extentReport = new ExtentReports(targetFile.toString(), true);
				extentReport.loadConfig(new File("src\\main\\java\\Common\\extent-config.xml"));
				File file1 = new File(CommonConstants.DOWNLOAD_FILE_PATH);
				if (!file1.exists()) {
					file1.mkdir();
					log.info("Download folder create successfully");
				}

			}
			log.info("<h2>--------------------SuiteRunner Log-------------------------<h2>");
		} catch (Exception e) {
			log.error(e.getMessage() + "Before suite" + UtilityMethods.getStackTrace());

		}

	}

	/**
	 * Provide class name and method(Test case) name into extent report for every tests. 
	 * 
	 */
	@BeforeMethod()
	public void beforeMethod(Method method) {
		try {
			extentTest = extentReport.startTest((this.getClass().getSimpleName() + "::" + method.getName()),
					method.getName());
			extentTest.assignCategory(config.getProperty("TypeOfTesting"));
			getDriver().get(config.getProperty("Url"));
		} catch (Exception e) {
			log.error(e.getMessage() + "---" + UtilityMethods.getStackTrace());
		}

	}

	/**
	 * Attach screenshot into extent report for test case pass, fail and skip.
	 * 
	 */
	@AfterMethod()
	public void afterMethod(ITestResult result) throws Exception {
		try {

			System.out.println(result.getStatus());
			if (result.getStatus() == 1) {

				File Imagefile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				String PassImageFileName = "ScreenShot_" + result.getMethod().getMethodName()+".png";
				FileUtils.copyFile(Imagefile, new File(ReportSetup.getPassImagesPath(), PassImageFileName));
				String screenshotPath = ReportSetup.getPassImagesPath() + CommonConstants.FILE_SEPARATOR
						+ PassImageFileName;
				extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(screenshotPath));

			} else if (result.getStatus() == 2) {

				File Imagefile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				String FailureImageFileName = "ScreenShot_" + result.getMethod().getMethodName() + ".png";
				FileUtils.copyFile(Imagefile, new File(ReportSetup.getFailurImagesPath(), FailureImageFileName));
				String screenshotPath = ReportSetup.getFailurImagesPath() + CommonConstants.FILE_SEPARATOR
						+ FailureImageFileName;
				extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath));

			} else if (result.getStatus() == 3) {

				File Imagefile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				String FailureImageFileName = "ScreenShot_" + result.getMethod().getMethodName() + ".png";
				FileUtils.copyFile(Imagefile, new File(ReportSetup.getFailurImagesPath(), FailureImageFileName));
				String screenshotPath = ReportSetup.getFailurImagesPath() + CommonConstants.FILE_SEPARATOR
						+ FailureImageFileName;
				extentTest.log(LogStatus.SKIP, extentTest.addScreenCapture(screenshotPath));
			}
			extentReport.endTest(extentTest);

		} catch (Exception e) {
			log.error(e.getMessage() + "---" + UtilityMethods.getStackTrace());
		}
	}

	/**
	 * Purpose - To initiate driver based on the browser.
	 * 
	 * @return - driver
	 */

	public void initiateDriver(String browserType) {

		log.info("-----------------STARTED RUNNING SELENIUM TESTS------------------");
		setDriver(browserType);

	}


	/**
	 * Close extent report and quit browser.
	 * 
	 */
	@AfterSuite
	public void afterSuite() {
		try {
			extentReport.flush();
			extentReport.close();
			log.info("Extent report close successfully");
			driver.quit();
			log.info("Browser quit successfully");
		} catch (Exception e) {
			log.error("Extent report not closed successfully" + UtilityMethods.getStackTrace());
		}

	}

	/**
	 * Method used to add INFO logs into extent report.
	 */

	public void logInfo(String message) {
		extentTest.log(LogStatus.INFO, message);
		log.info("Message: " + message);
		Reporter.log(message);
	}

	/**
	 * Method used to add FATAL logs into extent report.
	 */

	public void logFatal(String message) {
		extentTest.log(LogStatus.FATAL, message);
		log.info("Message: " + message);
		Reporter.log(message);
	}
	
	/**
	 * Method used to add ERROR logs into extent report.
	 */

	public void logError(String message) {
		extentTest.log(LogStatus.ERROR, message);
		log.info("Message: " + message);
		Reporter.log(message);
	}

	/**
	 * Method used to add SKIP logs into extent report.
	 */
	public void logSkip(String message) {

		extentTest.log(LogStatus.SKIP, message);
		log.info("Message: " + message);
		Reporter.log(message);
	}

	/**
	 * Method used to add WARNING logs into extent report.
	 */
	public void logWarning(String message) {
		extentTest.log(LogStatus.WARNING, message);
		log.info("Message: " + message);
		Reporter.log(message);
	}

	/**
	 * 
	 * This method sets the driver object based on browser name. If invalid browser
	 * name is passed, by default it will set chrome browser
	 *
	 * @param browserType , Need to pass the browser type
	 */

	private void setDriver(String browserType) {
		switch (browserType) {
		case "chrome":
			driver = new ChromeBrowser().init();
			log.info("browser name: " + browserType + " Launch successfully...:)");
			break;

		case "firefox":
			driver = new FirefoxBrowser().init();
			log.info("browser name: " + browserType + " Launch successfully...:)");
			break;

		case "ie":
			driver = new IeBrowser().init();
			log.info("browser name: " + browserType + " Launch successfully...:)");
			break;

		case "edge":
			driver = new EdgeBrowser().init();
			log.info("browser name: " + browserType + " Launch successfully...:)");
			break;
			
		default:
			log.error("browser : " + browserType + " is invalid, Launching chrome as browser of choice.");
			driver = new ChromeBrowser().init();
			log.info("browser name: " + browserType + " Launch successfully...:)");

		}
	}

}
