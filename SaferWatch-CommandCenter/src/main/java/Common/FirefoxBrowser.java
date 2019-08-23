package Common;


import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

/**
 * This class defines all methods required to initialize firefox driver.
 */
public class FirefoxBrowser {
	static ConfigManager Sys = new ConfigManager();
	static WebDriver driver;
	static FirefoxProfile firefoxProfile;
	private static Logger log = Logger.getLogger("FirefoxBrowser");
	static String fileSeperator = System.getProperty("file.separator");

	/**
	 * 
	 * This method is used to initiate firefox browser
	 * @return , Returns firefox browser driver object
	 */
	public static WebDriver init() {
		try {
		System.setProperty("webdriver.gecko.driver", getFirefoxDriverPath());
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Unable to initialize firefox browser");
		}
		return driver;

	}
	
	/**
	 * 
	 * This method is used to get geckodriver.exe file location
	 * @return , returns geckodriver.exe file path
	 */

	public static String getFirefoxDriverPath() {
		return CommonConstants.USER_DIR_PATH + CommonConstants.FILE_SEPARATOR + "Drivers"
				+ CommonConstants.FILE_SEPARATOR + "geckodriver.exe";
	}

}
