package Common;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * This class defines all methods required to initialize EdgeBrowser
 */
public class EdgeBrowser {
	private static Logger log = Logger.getLogger("EdgeBrowser");
	static String fileSeperator = System.getProperty("file.separator");
	static DesiredCapabilities capabilities;
	static WebDriver driver;

	/**
	 * 
	 * This method is used initiate Microsoft Edge browser
	 *
	 * @return , returns Microsoft Edge browser driver object
	 */
	public static WebDriver init() {
		try {
//		setCapabilities();
			System.setProperty("webdriver.edge.driver", getEdgeDriverPath());
			WebDriver driver = new EdgeDriver();
			driver.manage().window().maximize();
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Unable to initialize edge browser");
		}
		return driver;
	}

	/**
	 * Sets the required properties for edge driver to initialize
	 * 
	 * @return MicrosoftEdgeCapabilities with required properties set
	 */
//	private static void setCapabilities() {
//	}

	/**
	 * 
	 * This method is used to get MicrosoftWebDriver.exe file location
	 *
	 * @return , returns MicrosoftWebDriver.exe file path
	 */

	public static String getEdgeDriverPath() {
		return CommonConstants.USER_DIR_PATH + CommonConstants.FILE_SEPARATOR + "Drivers"
				+ CommonConstants.FILE_SEPARATOR + "MicrosoftWebDriver.exe";
	}
}
