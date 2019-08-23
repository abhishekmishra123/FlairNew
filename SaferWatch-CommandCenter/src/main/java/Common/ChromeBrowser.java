
package Common;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;


public class ChromeBrowser {
	static ConfigManager Sys = new ConfigManager();
	private static Logger log = Logger.getLogger(ChromeBrowser.class);
	private static WebDriver driver;
	
	
	/**
	 * 
	 * This method initiates Chrome browser and returns the driver object
	 *
	 * @return , returns the driver object after initiating Chrome browser
	 */
	public static WebDriver init() {
		try {
//			File file1 = new File(CommonConstants.DOWNLOAD_FILE_PATH);
//			if (!file1.exists()) {
//				file1.mkdir();
//				log.info("Download folder create successfully");
//			}
			driver = initChromeDriver();
			log.info("Return driver successfully" + driver);

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Unable to return driver");
		}
		return driver;

	}

	/**
	 * 
	 * This method initiates Chrome browser with default profile and returns the
	 * driver object
	 *
	 * @return , returns the driver object after initiating Chrome browser
	 */
	private static WebDriver initChromeDriver() {
		DesiredCapabilities cap = null;
		try {

			log.info("Launching google chrome...");
		    System.setProperty("webdriver.chrome.driver", getDriverPath());
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			prefs.put("download.default_directory", getDownloadLocation());
			log.info("Set download file path is " + getDownloadLocation());
			prefs.put("download.prompt_for_download", false);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("chrome.switches", "--disable-extensions");
			cap = DesiredCapabilities.chrome();
			cap.setCapability(ChromeOptions.CAPABILITY, options);
			LoggingPreferences logPrefs = new LoggingPreferences();
			logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
			cap.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

		} catch (Exception e) {
			log.info("Unable to initialize chrome browser successfully");
			e.printStackTrace();
		}
		return new ChromeDriver(cap);

	}

	/**
	 * Method to get file download path location
	 * @return - returns file download path
	 */
	public static String getDownloadLocation() {
			return CommonConstants.DOWNLOAD_FILE_PATH;
	}

	/**
	 * 
	 * This method returns the location of chromedriver.exe file
	 * @return, returns chromedriver.exe file path
	 */
	public static String getDriverPath() {
		return CommonConstants.USER_DIR_PATH + CommonConstants.FILE_SEPARATOR + "Drivers"
				+ CommonConstants.FILE_SEPARATOR + "chromedriver.exe";
	}

}
