package Common;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Sync implements TimeOuts {
	private WebDriver driver;
	static Logger log = Logger.getLogger(Sync.class);

	public Sync(WebDriver driver) {
		this.driver = driver;
	}

	public void nullifyImplicitWait() {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); 
	}

	/**
	 * Method - Waits for an element till the element is visible
	 * 
	 * @param driver
	 *            (WebDriver) The driver object to be used to wait and find the
	 *            element
	 * @param bylocator
	 *            (By) locator object of the element to be found
	 * @param waitTime
	 *            (int) The time in seconds to wait until returning a failure
	 * @return - True (Boolean) if element is located and is visible on screen
	 *         within timeout period else false
	 * @throws Exception
	 */
	public boolean isElementVisible(String locatorString, int... optionWaitTime) {
		int waitTime = getWaitTime(optionWaitTime);
		boolean bFlag = false;
		nullifyImplicitWait();
		log.info("Waiting until element " + locatorString + " is visible");
		try {
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(findLocator(locatorString)));
			setImplicitWait(IMPLICITWAIT);
			if (findElement((locatorString)).isDisplayed()) {
				bFlag = true;
				log.info("Element " + locatorString + " is displayed");
			}
		} catch (NoSuchElementException e) {
			log.info("Element " + locatorString + " was not displayed"
					+ UtilityMethods.getStackTrace());
		} catch (TimeoutException e) {
			log.info("Element " + locatorString + " was not visible in time - "
					+ waitTime + UtilityMethods.getStackTrace());
		}

		catch (Exception e) {
			log.error("Element " + locatorString + " was not displayed"
					+ UtilityMethods.getStackTrace());
			Assert.fail("Element " + locatorString + " was not displayed."
					+ UtilityMethods.getStackTrace());
		}
		return bFlag;
	}

	/**
	 * Waits for the completion of Ajax jQuery processing by checking
	 * "return jQuery.active == 0" condition.
	 *
	 * @param driver
	 *            - The driver object to be used to wait and find the element
	 * @param timeOutInSeconds
	 *            - The time in seconds to wait until returning a failure
	 * @return True (Boolean) if jquery/ ajax is loaded within specified
	 *         timeout.
	 * @throws Exception
	 * */
	public boolean waitForJQueryProcessing(int timeOutInSeconds) {
		log.info("Waiting ajax processing to complete..");
		boolean jQcondition = false;
		try {
			Thread.sleep(500);
			new WebDriverWait(driver, timeOutInSeconds)
					.until(new ExpectedCondition<Boolean>() {
						@Override
						public Boolean apply(WebDriver driverObject) {
							return (Boolean) ((JavascriptExecutor) driverObject)
									.executeScript("return jQuery.active == 0");
						}
					});
			jQcondition = (Boolean) ((JavascriptExecutor) driver)
					.executeScript("return window.jQuery != undefined && jQuery.active === 0");
			System.out.println(jQcondition);
			return jQcondition;
		} catch (Exception e) {
			log.error("Page Loading is not completed"
					+ UtilityMethods.getStackTrace());
			Assert.fail("Page Loading is not completed"
					+ UtilityMethods.getStackTrace());
		}
		return jQcondition;
	}

	/**
	 * Set driver's implicitlyWait() time according given waitTime.
	 * 
	 * @param driver
	 *            (WebDriver) The driver object to be used
	 * @param waitTimeInSeconds
	 *            (int) The time in seconds to specify implicit wait
	 * @return void
	 * @throws Exception
	 */
	public void setImplicitWait(int waitTimeInSeconds) {
		driver.manage().timeouts()
				.implicitlyWait(waitTimeInSeconds, TimeUnit.SECONDS);
	}

	/**
	 * Waits for an element till the timeout expires
	 * 
	 * @param driver
	 *            (WebDriver) The driver object to be used to wait and find the
	 *            element
	 * @param bylocator
	 *            (By) locator object of the element to be found
	 * @param waitTime
	 *            (int) The time in seconds to wait until returning a failure
	 * @return - True (Boolean) if element is located within timeout period else
	 *         false
	 */
	public boolean isElementPresent(String locatorString, int waitTime) {
		boolean bFlag = false;
		nullifyImplicitWait();
		log.info("Waiting for presence of element " + locatorString);
		try {
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions
					.presenceOfElementLocated(findLocator(locatorString)));
			if (findElement(locatorString).isDisplayed()
					|| findElement(locatorString).isEnabled()) {
				bFlag = true;
				log.info("Element " + locatorString + " is displayed");
			}
		} catch (NoSuchElementException e) {
			log.info("Element " + locatorString + " was not found in DOM"
					+ UtilityMethods.getStackTrace());
		} catch (TimeoutException e) {
			log.info("Element " + locatorString
					+ " was not displayed in time - " + waitTime
					+ UtilityMethods.getStackTrace());
		} catch (Exception e) {
			log.error("Element " + locatorString + " is not displayed"
					+ UtilityMethods.getStackTrace());
			Assert.fail("Element " + locatorString + " is not displayed"
					+ UtilityMethods.getStackTrace());
		}
		setImplicitWait(IMPLICITWAIT);
		return bFlag;
	}

	/**
	 * Method - Waits for an element till the element is clickable
	 * 
	 * @param driver
	 *            (WebDriver) The driver object to be used to wait and find the
	 *            element
	 * @param bylocator
	 *            (By) locator object of the element to be found
	 * @param waitTime
	 *            (int) The time in seconds to wait until returning a failure
	 * @return - True (Boolean) if element is located and is clickable within
	 *         timeout period else false
	 * @throws Exception
	 */
	public boolean waitUntilClickable(String locatorString,
			int... optionWaitTime) {
		int waitTime = getWaitTime(optionWaitTime);
		boolean bFlag = false;
		nullifyImplicitWait();
		try {
			log.info("Waiting until element " + locatorString + " is clickable");
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions
					.elementToBeClickable(findLocator(locatorString)));

			if (findElement((locatorString)).isDisplayed()) {
				bFlag = true;
				log.info("Element " + locatorString
						+ " is displayed and is clickable");
			}
		}

		catch (NoSuchElementException e) {
			log.error("Element " + locatorString + " was not displayed"
					+ UtilityMethods.getStackTrace());
			Assert.fail("Element " + locatorString + " was not displayed"
					+ UtilityMethods.getStackTrace());
		} catch (TimeoutException e) {
			log.error("Element " + locatorString
					+ " was not clickable in time - " + waitTime
					+ UtilityMethods.getStackTrace());
			Assert.fail("Element " + locatorString
					+ " was not clickable in time - " + waitTime
					+ UtilityMethods.getStackTrace());
		} catch (Exception e) {
			log.error("Element " + locatorString + " was not clickable"
					+ UtilityMethods.getStackTrace());
			Assert.fail("Element " + locatorString + " was not clickable"
					+ UtilityMethods.getStackTrace());
		}
		setImplicitWait(IMPLICITWAIT);
		return bFlag;
	}

	/**
	 * 
	 * Method - Waits for an element till the element is clickable
	 *
	 * @param locator
	 *            (By) locator object of the element to be found
	 * @param optionWaitTime
	 *            (int) The time in seconds to wait until returning a failure
	 * @return True (Boolean) if element is located and is clickable on screen
	 *         within timeout period else false
	 */
	public boolean isElementClickable(String locatorString,
			int... optionWaitTime) {
		int waitTime = getWaitTime(optionWaitTime);
		boolean bFlag = false;
		nullifyImplicitWait();
		try {
			log.info("Waiting until element " + locatorString + " is clickable");
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions
					.elementToBeClickable(findLocator(locatorString)));

			if (findElement((locatorString)).isDisplayed()) {
				bFlag = true;
				log.info("Element " + locatorString
						+ " is displayed and is clickable");
			}
		}

		catch (NoSuchElementException e) {
			log.error("Element " + locatorString + " was not displayed"
					+ UtilityMethods.getStackTrace());
		} catch (TimeoutException e) {
			log.error("Element " + locatorString
					+ " was not clickable in time - " + waitTime
					+ UtilityMethods.getStackTrace());
		} catch (Exception e) {
			log.error("Element " + locatorString + " was not clickable"
					+ UtilityMethods.getStackTrace());
			Assert.fail("Element " + locatorString + " was not clickable"
					+ UtilityMethods.getStackTrace());
		}
		setImplicitWait(IMPLICITWAIT);
		return bFlag;
	}

	public boolean isElementSelected(String locatorString,
			int... optionWaitTime) {
		int waitTime = getWaitTime(optionWaitTime);
		boolean bFlag = false;
		nullifyImplicitWait();
		try {
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions
					.elementToBeSelected(findLocator(locatorString)));
			if (findElement((locatorString)).isSelected()) {
				bFlag = true;
			}

		}

		catch (NoSuchElementException e) {
			log.error("Element " + locatorString + " was not Selected"
					+ UtilityMethods.getStackTrace());
		} catch (TimeoutException e) {
			log.error("Element " + locatorString
					+ " was not Selected in time - " + waitTime
					+ UtilityMethods.getStackTrace());
		} catch (Exception e) {
			log.error("Element " + locatorString + " was not Selected"
					+ UtilityMethods.getStackTrace());
			Assert.fail("Element " + locatorString + " was not Selected"
					+ UtilityMethods.getStackTrace());
		}
		setImplicitWait(IMPLICITWAIT);
		return bFlag;
	}

	/**
	 * Method - Waits for an element till the element is visible
	 * 
	 * @param driver
	 *            (WebDriver) The driver object to be used to wait and find the
	 *            element
	 * @param bylocator
	 *            (By) locator object of the element to be found
	 * @param waitTime
	 *            (int) The time in seconds to wait until returning a failure
	 * @return - True (Boolean) if element is located and is visible on screen
	 *         within timeout period else false
	 * @throws Exception
	 */
	public boolean isElementEnabled(String locatorString, int... optionWaitTime) {
		int waitTime = getWaitTime(optionWaitTime);
		boolean bFlag = false;
		nullifyImplicitWait();
		log.info("Waiting until element " + locatorString + " is visible");
		try {
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(findLocator(locatorString)));
			if (findElement((locatorString)).isEnabled()) {
				bFlag = true;
				log.info("Element " + locatorString + " is displayed");
			}
		} catch (NoSuchElementException e) {
			log.info("Element " + locatorString + " was not displayed"
					+ UtilityMethods.getStackTrace());
		} catch (TimeoutException e) {
			log.info("Element " + locatorString + " was not visible in time - "
					+ waitTime + UtilityMethods.getStackTrace());
		}

		catch (Exception e) {
			log.error("Element " + locatorString + " was not displayed"
					+ UtilityMethods.getStackTrace());
			Assert.fail("Element " + locatorString + " was not displayed."
					+ UtilityMethods.getStackTrace());
		}
		return bFlag;
	}

	/**
	 * Method - Wait until a particular text appears on the screen
	 * 
	 * @param driver
	 *            (WebDriver) The driver object to be used to wait and find the
	 *            element
	 * @param text
	 *            (String) - text until which the WebDriver should wait.
	 * @return void
	 * @throws Exception
	 */
	public void waitForPageToLoad(final String text) throws Exception {
		log.info("Waiting for page to be loaded...");
		nullifyImplicitWait();
		(new WebDriverWait(driver, 20))
				.until(new ExpectedCondition<WebElement>() {
					public WebElement apply(WebDriver d) {
						return d.findElement(By.partialLinkText(text));
					}
				});
		setImplicitWait(IMPLICITWAIT);
	}

	/**
	 * Waits until page is loaded.
	 *
	 * @param driver
	 *            - The driver object to use to perform this element search
	 * @param int - timeout in seconds
	 * @return True (boolean)
	 * @throws Exception
	 */
	public boolean waitForPageToLoad(int timeOutInSeconds) {
		log.info("Waiting for page to be loaded...");
		boolean isPageLoadComplete = false;
		nullifyImplicitWait(); // nullify implicitlyWait()
		try {
			new WebDriverWait(driver, timeOutInSeconds)
					.until(new ExpectedCondition<Boolean>() {
						@Override
						public Boolean apply(WebDriver driverObject) {
							return (Boolean) ((String) ((JavascriptExecutor) driver)
									.executeScript("return document.readyState"))
									.equals("complete");
						}
					});
			isPageLoadComplete = (Boolean) ((String) ((JavascriptExecutor) driver)
					.executeScript("return document.readyState"))
					.equals("complete");
		} catch (Exception e) {
			log.error("Unable to load web page"
					+ UtilityMethods.getStackTrace());
			Assert.fail("unable to load webpage" + "\n"
					+ UtilityMethods.getStackTrace());
		}
		setImplicitWait(IMPLICITWAIT);
		return isPageLoadComplete;
	}

	/**
	 * Waits until page is loaded. Default timeout is 250 seconds. Poll time is
	 * every 500 milliseconds.
	 *
	 * @param driver
	 *            - The driver object to use to perform this element search
	 * @return void
	 * @throws Exception
	 */

	public void waitForPageToLoad() {
		log.info("Waiting for page to be loaded...");
		try {
			int waitTime = 0;
			boolean isPageLoadComplete = false;
			do {

				isPageLoadComplete = ((String) ((JavascriptExecutor) driver)
						.executeScript("return document.readyState"))
						.equals("complete");
				System.out.print(".");
				Thread.sleep(500);
				waitTime++;
				if (waitTime > 500) {
					break;
				}
			} while (!isPageLoadComplete);

			if (!isPageLoadComplete) {

				log.error("Unable to load webpage with in default timeout 250 seconds");
				Assert.fail("unable to load webpage with in default timeout 250 seconds");
			}
		} catch (Exception e) {
			log.error("Unable to load web page"
					+ UtilityMethods.getStackTrace());
			Assert.fail("unable to load webpage" + "\n" + e.getMessage());
		}

	}

	public boolean isElementPresent(By locator) {
		log.info("Waiting for presence of element " + locator);

		setImplicitWait(IMPLICITWAIT);
		return driver.findElements(locator).size() > 0;
	}

	public boolean isElementDisplayed(By locator) {
		log.info("Verifying if element " + locator + " is displayed");
		boolean isDisplayed = false;
		setImplicitWait(IMPLICITWAIT);

		try {
			if (isElementPresent(locator)) {
				isDisplayed = driver.findElement(locator).isDisplayed();
			}
		} catch (Exception e) {
			log.error("Exception occured while determining state of " + locator
					+ UtilityMethods.getStackTrace());
		}
		return isDisplayed;
	}

	public boolean isElementNotDisplayed(String locatorString) {
		log.info("Verifying if element " + locatorString + " is not displayed");
		boolean isDisplayed = false;
		setImplicitWait(0);

		try {
			if (isElementPresent(locatorString, VERYSHORTWAIT)) {
				isDisplayed = findElement(locatorString).isDisplayed();
			}
		} catch (Exception e) {
			log.error("Exception occured while determining state of "
					+ locatorString + UtilityMethods.getStackTrace());
		}
		return !(isDisplayed);
	}

	public boolean isElementEnabled(By locator) {
		log.info("Verifying if element " + locator + " is enabled");
		boolean isEnabled = false;
		setImplicitWait(IMPLICITWAIT);
		try {
			if (isElementPresent(locator)) {
				isEnabled = driver.findElement(locator).isEnabled();
			}
		} catch (Exception e) {
			log.error("Exception occured while determining state of " + locator
					+ UtilityMethods.getStackTrace());
		}
		return isEnabled;
	}

	public boolean isElementSelected(By locator) {
		log.info("Verifying if element " + locator + " is selected");
		boolean isSelected = false;
		setImplicitWait(IMPLICITWAIT);
		try {
			if (isElementPresent(locator)) {
				isSelected = driver.findElement(locator).isSelected();
			}
		} catch (Exception e) {
			log.error("Exception occured while determining state of " + locator
					+ UtilityMethods.getStackTrace());
		}
		return isSelected;
	}

	public int getWaitTime(int[] optionalWaitArray) {
		if (optionalWaitArray.length <= 0) {
			return MEDIUMWAIT;
		} else {
			return optionalWaitArray[0];
		}
	}

	public void needToWait(int waitInSec) {
		try {
			Thread.sleep(waitInSec * 1000);
		} catch (Exception e) {
		}
	}

	public WebElement findElement(String locatorString) {
		return driver.findElement(findLocator(locatorString));
	}

	public By findLocator(String locatorString) {
		String string = locatorString;
		String[] parts = string.split(":");
		String type = parts[0]; // 004
		String object = parts[1];
		if (parts.length > 2) {
			for (int i = 2; i < parts.length; i++) {
				object = object + ":" + parts[i];
			}
		}
		By by = null;
		if (type.equals("id")) {
			by = By.id(object);
		} else if (type.equals("name")) {
			by = By.name(object);
		} else if (type.equals("class")) {
			by = By.className(object);
		} else if (type.equals("link")) {
			by = By.linkText(object);
		} else if (type.equals("partiallink")) {
			by = By.partialLinkText(object);
		} else if (type.equals("css")) {
			by = By.cssSelector(object);
		} else if (type.equals("xpath")) {
			by = By.xpath(object);
		} else {
			throw new RuntimeException(
					"Please provide correct element locating strategy");
		}
		return by;
	}

}
