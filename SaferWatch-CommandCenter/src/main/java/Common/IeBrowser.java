package Common;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * This class defines all methods required to initialize IEDriver
 */
public class IeBrowser {
	private static Logger log = Logger.getLogger("IeBrowser");
	static String fileSeperator = System.getProperty("file.separator");
	static DesiredCapabilities capabilities;
	static WebDriver driver;

	/**
	 * 
	 * This method is used initiate IE browser
	 *
	 * @return , returns IE browser driver object
	 */
	public static WebDriver init() {
		try {
			System.setProperty("webdriver.ie.driver", getIEDriverPath());
			setCapabilities();
			driver = new InternetExplorerDriver(capabilities);
			log.info("Launching IE browser");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Unable to initialize Internet Explorer browser");
		}
		return driver;
	}

	/**
	 * Sets the required properties for IEdriver to initialize
	 * 
	 * @return IECapabilities with required properties set
	 */
	private static void setCapabilities() {
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		capabilities.setCapability("ignoreProtectedModeSettings", true);
		capabilities.setCapability("ignoreZoomSetting", true);
		capabilities.setCapability("nativeEvents", false);
		capabilities.setCapability("requireWindowFocus", true);
		capabilities.setCapability("IntroduceInstabilityByIgnoringProtectedModeSettings", true);
	}

	/**
	 * 
	 * This method is used to get IEDriverServer.exe file location
	 *
	 */
	public static String getIEDriverPath() {
		return CommonConstants.USER_DIR_PATH + CommonConstants.FILE_SEPARATOR + "Drivers"
				+ CommonConstants.FILE_SEPARATOR + "IEDriverServer.exe";
	}
}
