package Common;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ActionMethods extends Sync {
	WebDriver driver;
	static Logger log = Logger.getLogger(ActionMethods.class);

	/**
	 * Constructor to initialize the local WebDriver variable with the WebDriver
	 * Variable that has been passed from each PageParts Java class
	 */

	public ActionMethods(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	/**
	 * Method - Safe Method for User Clear and Type, waits until the element is
	 * loaded and then enters some text
	 * 
	 * @param locatorString
	 * @param sText
	 * @param waitTime
	 */
	public void safeClearAndType(String locatorString, String text, int... optionWaitTime) {
		try {
			int waitTime = getWaitTime(optionWaitTime);
			if (isElementPresent(locatorString, waitTime)) {
				scrollIntoElementView(locatorString);
				WebElement element = findElement(locatorString);
				setHighlight(element);
				element.clear();
				element.sendKeys(text);
				log.info("Cleared the field and entered - '" + text + " in the element - " + locatorString);
			} else {
				log.error("Unable to clear and enter " + text + " in field " + locatorString
						+ UtilityMethods.getStackTrace());
				Assert.fail("Unable to clear and enter " + text + " in field " + locatorString
						+ UtilityMethods.getStackTrace());
			}
		} catch (StaleElementReferenceException e) {
			log.error("Element with " + locatorString + " is not attached to the page document"
					+ UtilityMethods.getStackTrace());
			Assert.fail("Element with " + locatorString + " is not attached to the page document"
					+ UtilityMethods.getStackTrace());
		} catch (NoSuchElementException e) {
			log.error("Element " + locatorString + " was not found in DOM" + UtilityMethods.getStackTrace());
			Assert.fail("Element " + locatorString + " was not found in DOM" + UtilityMethods.getStackTrace());
		} catch (Exception e) {
			log.error("Unable to clear and enter '" + text + "' text in field with locatorString -" + locatorString
					+ UtilityMethods.getStackTrace());
			Assert.fail("Unable to clear and enter '" + text + "' text in field with locatorString -" + locatorString
					+ UtilityMethods.getStackTrace());
		}
	}

	/**
	 * Method - Safe Method for User Type, waits until the element is loaded and
	 * then enters some text
	 * 
	 * @param locatorString
	 * @param sText
	 * @param waitTime
	 */
	public void safeType(String locatorString, String text, int... optionWaitTime) {
		try {
			int waitTime = getWaitTime(optionWaitTime);
			if (isElementPresent(locatorString, waitTime)) {
				scrollIntoElementView(locatorString);
				WebElement element = findElement(locatorString);
				setHighlight(element);
				element.sendKeys(text);
			} else {
				log.error("Unable to enter " + text + " in field " + locatorString + UtilityMethods.getStackTrace());
				Assert.fail("Unable to enter " + text + " in field " + locatorString + UtilityMethods.getStackTrace());
			}
		} catch (StaleElementReferenceException e) {
			log.error("Element with " + locatorString + " is not attached to the page document"
					+ UtilityMethods.getStackTrace());
			Assert.fail("Element with " + locatorString + " is not attached to the page document"
					+ UtilityMethods.getStackTrace());
		} catch (NoSuchElementException e) {
			log.error("Element " + locatorString + " was not found in DOM" + UtilityMethods.getStackTrace());
			Assert.fail("Element " + locatorString + " was not found in DOM" + UtilityMethods.getStackTrace());
		} catch (Exception e) {
			log.error("Unable to enter '" + text + "' text in field with locatorString -" + locatorString
					+ UtilityMethods.getStackTrace());
			Assert.fail("Unable to enter '" + text + "' text in field with locatorString -" + locatorString
					+ UtilityMethods.getStackTrace());
		}
	}

	/**
	 * Method - Safe Method for Radio button selection, waits until the element is
	 * loaded and then selects Radio button
	 * 
	 * @param locatorString
	 * @param waitTime
	 * @return - boolean (returns True when the Radio button is selected else
	 *         returns false)
	 * @throws Exception
	 */
	public void safeSelectRadioButton(String locatorString, int... optionWaitTime) {
		try {
			int waitTime = getWaitTime(optionWaitTime);
			if (waitUntilClickable(locatorString, waitTime)) {
				scrollIntoElementView(locatorString);
				WebElement element = findElement(locatorString);
				setHighlight(element);
				element.click();
				log.info("Clicked on element " + locatorString);
			} else {
				log.error("Unable to select Radio button " + locatorString + UtilityMethods.getStackTrace());
				Assert.fail("Unable to select Radio button " + locatorString + UtilityMethods.getStackTrace());
			}
		} catch (StaleElementReferenceException e) {
			log.error("Element with " + locatorString + " is not attached to the page document"
					+ UtilityMethods.getStackTrace());
			Assert.fail("Element with " + locatorString + " is not attached to the page document"
					+ UtilityMethods.getStackTrace());
		} catch (NoSuchElementException e) {
			log.error("Element " + locatorString + " was not found in DOM" + UtilityMethods.getStackTrace());
			Assert.fail("Element " + locatorString + " was not found in DOM" + UtilityMethods.getStackTrace());
		} catch (Exception e) {
			log.error("Unable to click on radio button with locatorString '" + locatorString + "'' "
					+ UtilityMethods.getStackTrace());
			Assert.fail("Unable to click on radio button with locatorString '" + locatorString + "'' "
					+ UtilityMethods.getStackTrace());
		}
	}

	/**
	 * Method - Safe Method for checkbox selection, waits until the element is
	 * loaded and then selects checkbox
	 * 
	 * @param locatorString
	 * @param waitTime
	 * @return - boolean (returns True when the checkbox is selected else returns
	 *         false)
	 * @throws Exception
	 */
	public void safeCheck(String locatorString, int... optionWaitTime) {

		try {
			int waitTime = getWaitTime(optionWaitTime);
			if (isElementPresent(locatorString, waitTime)) {
				scrollIntoElementView(locatorString);
				WebElement checkBox = findElement(locatorString);
				setHighlight(checkBox);
				if (checkBox.isSelected()) {
					log.info("CheckBox " + locatorString + "is already selected");
				} else
					checkBox.click();
			} else {
				log.error("Unable to select checkbox " + locatorString + UtilityMethods.getStackTrace());
				Assert.fail("Unable to select checkbox " + locatorString + UtilityMethods.getStackTrace());
			}
		} catch (StaleElementReferenceException e) {
			log.error("Element with " + locatorString + " is not attached to the page document"
					+ UtilityMethods.getStackTrace());
			Assert.fail("Element with " + locatorString + " is not attached to the page document"
					+ UtilityMethods.getStackTrace());
		} catch (NoSuchElementException e) {
			log.error("Element " + locatorString + " was not found in DOM" + UtilityMethods.getStackTrace());
			Assert.fail("Element " + locatorString + " was not found in DOM" + UtilityMethods.getStackTrace());
		} catch (Exception e) {
			log.error("Unable to check the checkbox with locatorString '" + locatorString + "'' "
					+ UtilityMethods.getStackTrace());
			Assert.fail("Unable to check the checkbox with locatorString '" + locatorString + "'' "
					+ UtilityMethods.getStackTrace());
		}
	}

	/**
	 * Method - Safe Method for checkbox deselection, waits until the element is
	 * loaded and then deselects checkbox
	 * 
	 * @param locatorString
	 * @param waitTime
	 * @return - boolean (returns True when the checkbox is deselected else returns
	 *         false)
	 * @throws Exception
	 */
	public void safeUnCheck(String locatorString, int... optionWaitTime) {
		try {
			int waitTime = getWaitTime(optionWaitTime);
			if (isElementPresent(locatorString, waitTime)) {
				scrollIntoElementView(locatorString);
				WebElement checkBox = findElement(locatorString);
				setHighlight(checkBox);
				if (checkBox.isSelected())
					checkBox.click();
				else {
					log.info("CheckBox " + locatorString + "is already deselected");
				}
			} else {
				log.error("Unable to uncheck the checkbox " + locatorString + UtilityMethods.getStackTrace());
				Assert.fail("Unable to uncheck the checkbox " + locatorString + UtilityMethods.getStackTrace());
			}
		} catch (StaleElementReferenceException e) {
			log.error("Element with " + locatorString + " is not attached to the page document"
					+ UtilityMethods.getStackTrace());
			Assert.fail("Element with " + locatorString + " is not attached to the page document"
					+ UtilityMethods.getStackTrace());
		} catch (NoSuchElementException e) {
			log.error("Element " + locatorString + " was not found in DOM" + UtilityMethods.getStackTrace());
			Assert.fail("Element " + locatorString + " was not found in DOM" + UtilityMethods.getStackTrace());
		} catch (Exception e) {
			log.error("Unable to uncheck the checkbox with locatorString '" + locatorString + "'' "
					+ UtilityMethods.getStackTrace());
			Assert.fail("Unable to uncheck the checkbox with locatorString '" + locatorString + "'' "
					+ UtilityMethods.getStackTrace());
		}
	}

	/**
	 * Method - Safe Method for checkbox Selection or Deselection based on user
	 * input, waits until the element is loaded and then deselects/selects checkbox
	 * 
	 * @param locatorString
	 * @param checkOption
	 * @param waitTime
	 * @return - boolean (returns True when the checkbox is status is toggled else
	 *         returns false)
	 * @throws Exception
	 */
	public void safeCheckByOption(String locatorString, boolean checkOption, int... optionWaitTime) {
		try {
			int waitTime = getWaitTime(optionWaitTime);
			if (isElementPresent(locatorString, waitTime)) {
				scrollIntoElementView(locatorString);
				WebElement checkBox = findElement(locatorString);
				setHighlight(checkBox);
				if ((checkBox.isSelected() == true && checkOption == false)
						|| (checkBox.isSelected() == false && checkOption == true))
					checkBox.click();
				else {
					log.info("CheckBox " + locatorString + "is already deselected");
				}
			} else {
				log.error("Unable to Select or Deselect checkbox " + locatorString + UtilityMethods.getStackTrace());
				Assert.fail("Unable to Select or Deselect checkbox " + locatorString + UtilityMethods.getStackTrace());
			}
		} catch (StaleElementReferenceException e) {
			log.error("Element with " + locatorString + " is not attached to the page document"
					+ UtilityMethods.getStackTrace());
			Assert.fail("Element with " + locatorString + " is not attached to the page document"
					+ UtilityMethods.getStackTrace());
		} catch (NoSuchElementException e) {
			log.error("Element " + locatorString + " was not found in DOM" + UtilityMethods.getStackTrace());
			Assert.fail("Element " + locatorString + " was not found in DOM" + UtilityMethods.getStackTrace());
		} catch (Exception e) {
			log.error("Unable to check or uncheck the checkbox with locatorString '" + locatorString + "'' "
					+ UtilityMethods.getStackTrace());
			Assert.fail("Unable to check or uncheck the checkbox with locatorString '" + locatorString + "'' "
					+ UtilityMethods.getStackTrace());
		}
	}


	/**
	 * Method - Safe Method for User Select option from Drop down by option name,
	 * waits until the element is loaded and then selects an option from drop down
	 * 
	 * @param locatorString
	 * @param sOptionToSelect
	 * @param waitTime
	 * @return - boolean (returns True when option is selected from the drop down
	 *         else returns false)
	 * @throws Exception
	 */
	public void safeSelectOptionInDropDown(String locatorString, String optionToSelect, int... optionWaitTime) {
		try {
			List<WebElement> options = Collections.<WebElement>emptyList();
			int waitTime = getWaitTime(optionWaitTime);
			if (isElementPresent(locatorString, waitTime)) {
				scrollIntoElementView(locatorString);
				WebElement selectElement = findElement(locatorString);
				setHighlight(selectElement);
				Select select = new Select(selectElement);
				// Get a list of the options
				options = select.getOptions();
				if (!options.isEmpty()) {
					for (WebElement option : options) {
						if (option.getText().contains(optionToSelect)) {
							option.click();
							log.info("Selected " + option + " from " + locatorString + " dropdown");
							break;
						}
					}
				}
			} else {
				log.error("Unable to select " + optionToSelect + " from " + locatorString + "\n"
						+ UtilityMethods.getStackTrace());
				Assert.fail("Unable to select " + optionToSelect + " from " + locatorString + "\n"
						+ UtilityMethods.getStackTrace());
			}
		} catch (StaleElementReferenceException e) {
			log.error("Element with dropdown locatorString- " + locatorString + " or option to be selected -'"
					+ optionToSelect + "' webelement is not attached to the page document"
					+ UtilityMethods.getStackTrace());
			Assert.fail("Element with dropdown locatorString- " + locatorString + " or option to be selected -'"
					+ optionToSelect + "' webelement is not attached to the page document"
					+ UtilityMethods.getStackTrace());
		} catch (NoSuchElementException e) {
			log.error("Element with dropdown locatorString- " + locatorString + " or option to be selected -'"
					+ optionToSelect + "' webelement was not found in DOM" + UtilityMethods.getStackTrace());
			Assert.fail("Element with dropdown locatorString- " + locatorString + " or option to be selected -'"
					+ optionToSelect + "' webelement was not found in DOM" + UtilityMethods.getStackTrace());
		} catch (Exception e) {
			log.error("Unable to select " + optionToSelect + " from " + locatorString + "\n"
					+ UtilityMethods.getStackTrace());
			Assert.fail("Unable to select " + optionToSelect + " from " + locatorString + "\n"
					+ UtilityMethods.getStackTrace());
		}
	}

	/**
	 * Method - Safe Method for User Select option from Drop down by option index,
	 * waits until the element is loaded and then selects an option from drop down
	 * 
	 * @param locatorString
	 * @param iIndexofOptionToSelect
	 * @param waitTime
	 * @return - boolean (returns True when option is selected from the drop down
	 *         else returns false)
	 * @throws Exception
	 */
	public void safeSelectOptionInDropDownByIndexValue(String locatorString, int iIndexofOptionToSelect,
			int... optionWaitTime) {
		try {
			int waitTime = getWaitTime(optionWaitTime);
			if (isElementPresent(locatorString, waitTime)) {
				scrollIntoElementView(locatorString);
				WebElement selectElement = findElement(locatorString);
				setHighlight(selectElement);
				Select select = new Select(selectElement);
				select.selectByIndex(iIndexofOptionToSelect);
				log.info("Selected option from " + locatorString + " dropdown");
			} else {
				log.error("Unable to select option from " + locatorString + "\n" + UtilityMethods.getStackTrace());
				Assert.fail("Unable to select option from " + locatorString + "\n" + UtilityMethods.getStackTrace());
			}
		} catch (StaleElementReferenceException e) {
			log.error("Element with " + locatorString + " is not attached to the page document"
					+ UtilityMethods.getStackTrace());
			Assert.fail("Element with " + locatorString + " is not attached to the page document"
					+ UtilityMethods.getStackTrace());
		} catch (NoSuchElementException e) {
			log.error("Element " + locatorString + " was not found in DOM" + UtilityMethods.getStackTrace());
			Assert.fail("Element " + locatorString + " was not found in DOM" + UtilityMethods.getStackTrace());
		} catch (Exception e) {
			log.error("Unable to select option from " + locatorString + " dropdown" + "\n"
					+ UtilityMethods.getStackTrace());
			Assert.fail("Unable to select option from " + locatorString + " dropdown" + "\n"
					+ UtilityMethods.getStackTrace());
		}
	}

	/**
	 * Method - Safe Method for User Select option from Drop down by option value,
	 * waits until the element is loaded and then selects an option from drop down
	 * 
	 * @param locatorString
	 * @param sValueOfOptionToSelect
	 * @param waitTime
	 * @return - boolean (returns True when option is selected from the drop down
	 *         else returns false)
	 * @throws Exception
	 */
	public void safeSelectOptionInDropDownByValue(String locatorString, String sValuefOptionToSelect,
			int... optionWaitTime) {
		try {
			int waitTime = getWaitTime(optionWaitTime);
			if (isElementPresent(locatorString, waitTime)) {
				scrollIntoElementView(locatorString);
				WebElement selectElement = findElement(locatorString);
				setHighlight(selectElement);
				Select select = new Select(selectElement);
				select.selectByValue(sValuefOptionToSelect);
				log.info("Selected option from " + locatorString + " dropdown");
			} else {
				log.error("Unable to select option from " + locatorString + "\n" + UtilityMethods.getStackTrace());
				Assert.fail("Unable to select option from " + locatorString + "\n" + UtilityMethods.getStackTrace());
			}
		} catch (StaleElementReferenceException e) {
			log.error("Element with " + locatorString + " is not attached to the page document"
					+ UtilityMethods.getStackTrace());
			Assert.fail("Element with " + locatorString + " is not attached to the page document"
					+ UtilityMethods.getStackTrace());
		} catch (NoSuchElementException e) {
			log.error("Element " + locatorString + " was not found in DOM" + UtilityMethods.getStackTrace());
			Assert.fail("Element " + locatorString + " was not found in DOM" + UtilityMethods.getStackTrace());
		} catch (Exception e) {
			log.error("Unable to select option from " + locatorString + " dropdown" + "\n"
					+ UtilityMethods.getStackTrace());
			Assert.fail("Unable to select option from " + locatorString + " dropdown" + "\n"
					+ UtilityMethods.getStackTrace());
		}
	}

	/**
	 * Method - Safe Method for User Select option from Drop down by option lable,
	 * waits until the element is loaded and then selects an option from drop down
	 * 
	 * @param locatorString
	 * @param sVisibleTextOptionToSelect
	 * @param waitTime
	 * @return - boolean (returns True when option is selected from the drop down
	 *         else returns false)
	 * @throws Exception
	 */
	public void safeSelectOptionInDropDownByVisibleText(String locatorString, String sVisibleTextOptionToSelect,
			int... optionWaitTime) {
		try {
			int waitTime = getWaitTime(optionWaitTime);
			if (isElementPresent(locatorString, waitTime)) {
				scrollIntoElementView(locatorString);
				WebElement selectElement = findElement(locatorString);
				setHighlight(selectElement);
				Select select = new Select(selectElement);
				select.selectByVisibleText(sVisibleTextOptionToSelect);
				log.info("Selected option from " + locatorString + " dropdown");
			} else {
				log.error("Unable to select option from " + locatorString + "\n" + UtilityMethods.getStackTrace());
				Assert.fail("Unable to select option from " + locatorString + "\n" + UtilityMethods.getStackTrace());
			}
		} catch (StaleElementReferenceException e) {
			log.error("Element with " + locatorString + " is not attached to the page document"
					+ UtilityMethods.getStackTrace());
			Assert.fail("Element with " + locatorString + " is not attached to the page document"
					+ UtilityMethods.getStackTrace());
		}catch (NoSuchElementException e) {
			log.error("Element " + locatorString + " was not found in DOM" + UtilityMethods.getStackTrace());
			Assert.fail("Element " + locatorString + " was not found in DOM" + UtilityMethods.getStackTrace());
		} catch (Exception e) {
			log.error("Unable to select option from " + locatorString + " dropdown" + "\n"
					+ UtilityMethods.getStackTrace());
			Assert.fail("Unable to select option from " + locatorString + " dropdown" + "\n"
					+ UtilityMethods.getStackTrace());
		}
	}

	/**
	 * Method - Method to hover on an element based on locatorString using
	 * Actions,it waits until the element is loaded and then hovers on the element
	 * 
	 * @param locatorString
	 * @param waitTime
	 * @throws Exception
	 */
	public void mouseHover(String locatorString, int waitTime) {
		try {
			if (isElementVisible(locatorString, waitTime)) {
				Actions builder = new Actions(driver);

				WebElement HoverElement = findElement(locatorString);
				builder.moveToElement(HoverElement).build().perform();

				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					log.error("Exception occurred while waiting" + UtilityMethods.getStackTrace());
				}
				log.info("Hovered on element " + locatorString);
			} else {
				log.error("Element was not visible to hover " + "\n" + UtilityMethods.getStackTrace());
				Assert.fail("Element was not visible to hover " + "\n" + UtilityMethods.getStackTrace());
			}
		} catch (StaleElementReferenceException e) {
			log.error("Element with " + locatorString + " is not attached to the page document"
					+ UtilityMethods.getStackTrace());
			Assert.fail("Element with " + locatorString + " is not attached to the page document"
					+ UtilityMethods.getStackTrace());
		} catch (NoSuchElementException e) {
			log.error("Element " + locatorString + " was not found in DOM" + UtilityMethods.getStackTrace());
			Assert.fail("Element " + locatorString + " was not found in DOM" + UtilityMethods.getStackTrace());
		} catch (Exception e) {
			log.error("Unable to hover the cursor on " + locatorString + "\n" + UtilityMethods.getStackTrace());
			Assert.fail("Unable to hover the cursor on " + locatorString + "\n" + UtilityMethods.getStackTrace());
		}
	}

	/**
	 * Method - Method to hover on an element based on locatorString using Actions
	 * and click on given option,it waits until the element is loaded and then
	 * hovers on the element
	 * 
	 * @param locatorString
	 * @param waitTime
	 * @throws Exception
	 */
	public void mouseHoverAndSelectOption(String locatorString, By byOptionlocatorString, int waitTime) {
		try {
			if (isElementPresent(locatorString, waitTime)) {
				Actions builder = new Actions(driver);
				WebElement HoverElement = findElement(locatorString);
				builder.moveToElement(HoverElement).build().perform();
				try {
					builder.wait(4000);
				} catch (InterruptedException e) {
					log.error("Exception occurred while waiting" + UtilityMethods.getStackTrace());
				}
				WebElement element = driver.findElement(byOptionlocatorString);
				element.click();
				log.info("Hovered on element and select the Option" + locatorString);
			} else {
				log.error("Element was not visible to hover " + "\n" + UtilityMethods.getStackTrace());
				Assert.fail("Element was not visible to hover " + "\n" + UtilityMethods.getStackTrace());
			}
		} catch (StaleElementReferenceException e) {
			log.error("Element with " + locatorString + "or" + byOptionlocatorString
					+ "is not attached to the page document" + UtilityMethods.getStackTrace());
			Assert.fail("Element with " + locatorString + "or" + byOptionlocatorString
					+ "is not attached to the page document" + UtilityMethods.getStackTrace());
		} catch (NoSuchElementException e) {
			log.error("Element " + locatorString + "or" + byOptionlocatorString + " was not found in DOM"
					+ UtilityMethods.getStackTrace());
			Assert.fail("Element " + locatorString + "or" + byOptionlocatorString + " was not found in DOM"
					+ UtilityMethods.getStackTrace());
		} catch (Exception e) {
			log.error("Unable to hover the cursor on " + locatorString + "or unable to select " + byOptionlocatorString
					+ "option" + "\n" + UtilityMethods.getStackTrace());
			Assert.fail("Unable to hover the cursor on " + locatorString + "or unable to select "
					+ byOptionlocatorString + "option" + "\n" + UtilityMethods.getStackTrace());
		}
	}

	/**
	 * Method - Method to hover on an element based on locatorString using
	 * JavaScript snippet,it waits until the element is loaded and then hovers on
	 * the element
	 * 
	 * @param locatorString
	 * @param Choice
	 * @param waitTime
	 * @throws Exception
	 */
	public void mouseHoverJScript(String locatorString, String Choice, int waitTime) {
		try {
			if (isElementPresent(locatorString, waitTime)) {
				WebElement HoverElement = findElement(locatorString);
				String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
				((JavascriptExecutor) driver).executeScript(mouseOverScript, HoverElement);
				Thread.sleep(4000);
				log.info("Hovered on element " + locatorString);
			} else {
				log.error("Element was not visible to hover " + "\n" + UtilityMethods.getStackTrace());
				Assert.fail("Element was not visible to hover " + "\n" + UtilityMethods.getStackTrace());
			}
		} catch (StaleElementReferenceException e) {
			log.error("Element with " + locatorString + "is not attached to the page document"
					+ UtilityMethods.getStackTrace());
			Assert.fail("Element with " + locatorString + "is not attached to the page document"
					+ UtilityMethods.getStackTrace());
		} catch (NoSuchElementException e) {
			log.error("Element " + locatorString + " was not found in DOM" + UtilityMethods.getStackTrace());
			Assert.fail("Element " + locatorString + " was not found in DOM" + UtilityMethods.getStackTrace());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Some exception occurred while hovering" + UtilityMethods.getStackTrace());
			Assert.fail("Some exception occurred while hovering" + UtilityMethods.getStackTrace());
		}
	}

	/**
	 * Method - Safe Method for User Click, waits until the element is loaded and
	 * then performs a click action
	 * 
	 * @param locatorStringToClick
	 * @param locatorStringToCheck
	 * @param waitTime
	 * @return - boolean (returns True when click action is performed else returns
	 *         false)
	 * @throws Exception
	 */
	public void safeClick(String locatorStringToClick, String locatorStringToCheck, int waitElementToClick,
			int waitElementToCheck) throws Exception {
		boolean bResult = false;
		int iAttempts = 0;
		nullifyImplicitWait();
		WebDriverWait wait = new WebDriverWait(driver, waitElementToClick);
		WebDriverWait wait2 = new WebDriverWait(driver, waitElementToCheck);
		while (iAttempts < 3) {

			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(findLocator(locatorStringToClick)));
				wait.until(ExpectedConditions.elementToBeClickable(findLocator(locatorStringToClick)));
				WebElement element = findElement(locatorStringToClick);

				if (element.isDisplayed()) {
					setHighlight(element);
					element.click();
					waitForPageToLoad();
					wait2.until(ExpectedConditions.visibilityOfElementLocated(findLocator(locatorStringToCheck)));
					WebElement elementToCheck = findElement(locatorStringToCheck);
					if (elementToCheck.isDisplayed()) {
						log.info("Clicked on element " + locatorStringToClick);
						break;
					} else {
						Thread.sleep(1000);
						continue;
					}
				}
			} catch (Exception e) {
				log.info("Attempt: " + iAttempts + "\n Unable to click on element " + locatorStringToClick);
			}
			iAttempts++;
		}
		if (!bResult) {
			Assert.fail("Unable to click on element " + locatorStringToClick + UtilityMethods.getStackTrace());
		}
	}

	/**
	 * Purpose- Method For performing drag and drop operations
	 * 
	 * @param SourcelocatorString ,DestinationlocatorString
	 * @param waitTime
	 * @throws Exception
	 * @function_call - eg: DragAndDrop(By.id(SourcelocatorString),
	 *                By.xpath(DestinationlocatorString), "MEDIUMWAIT");
	 */
	public void dragAndDrop(String SourcelocatorString, String DestinationlocatorString, int waitTime) {
		try {
			if (isElementPresent(SourcelocatorString, waitTime)) {
				WebElement source = findElement(SourcelocatorString);
				if (isElementPresent(DestinationlocatorString, waitTime)) {
					WebElement destination = findElement(DestinationlocatorString);
					Actions builder = new Actions(driver);
					builder.dragAndDrop(source, destination).build().perform();
					log.info("Dragged the element " + SourcelocatorString + " and dropped in to "
							+ DestinationlocatorString);
				} else {
					log.error("Destination Element with locatorString " + DestinationlocatorString
							+ " was not displayed to drop" + "\n" + UtilityMethods.getStackTrace());
					Assert.fail("Destination Element with locatorString " + DestinationlocatorString
							+ " was not displayed to drop" + "\n" + UtilityMethods.getStackTrace());
				}
			} else {
				log.error("Source Element with locatorString " + SourcelocatorString + " was not displayed to drag"
						+ "\n" + UtilityMethods.getStackTrace());
				Assert.fail("Source Element with locatorString " + SourcelocatorString + " was not displayed to drag"
						+ "\n" + UtilityMethods.getStackTrace());
			}
		} catch (StaleElementReferenceException e) {
			log.error("Element with " + SourcelocatorString + "or" + DestinationlocatorString
					+ "is not attached to the page document" + UtilityMethods.getStackTrace());
			Assert.fail("Element with " + SourcelocatorString + "or" + DestinationlocatorString
					+ "is not attached to the page document" + UtilityMethods.getStackTrace());
		} catch (NoSuchElementException e) {
			log.error("Element " + SourcelocatorString + "or" + DestinationlocatorString + " was not found in DOM"
					+ UtilityMethods.getStackTrace());
			Assert.fail("Element " + SourcelocatorString + "or" + DestinationlocatorString + " was not found in DOM"
					+ UtilityMethods.getStackTrace());
		} catch (Exception e) {
			log.error("Some exception occurred while performing drag and drop operation "
					+ UtilityMethods.getStackTrace());
			Assert.fail("Some exception occurred while performing drag and drop operation "
					+ UtilityMethods.getStackTrace());
		}
	}

	/**
	 * 
	 * Purpose- Method For waiting for an alert and acceptng it
	 *
	 * @param waitTime
	 * @return returns true if alert is displayed and accepted, else returns false
	 */
	public boolean AlertExistsAndAccepted(int waitTime) {
		boolean bAlert = false;
		int i = 0;
		while (i++ < waitTime) {
			try {
				Alert alert = driver.switchTo().alert();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException exception) {
					log.error("Exception occured while waiting for an alert using thread sleep method "
							+ UtilityMethods.getStackTrace());
				}
				alert.accept();
				log.info("Alert is displayed and accepted successfully");
				bAlert = true;
				break;
			} catch (NoAlertPresentException e) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException exception) {
					log.error("Exception occured while waiting for an alert using thread sleep method "
							+ UtilityMethods.getStackTrace());
				}
				bAlert = false;
				log.error("Waiting for alert to appear... ");
				continue;
			}
		}
		return bAlert;
	}

	/**
	 * Method: for verifying if accept exists and accepting the alert
	 * 
	 * @return - boolean (returns True when accept action is performed else returns
	 *         false)
	 * @throws Exception
	 */
	public void acceptAlert() {
		try {
			Alert alert = driver.switchTo().alert();
			String sText = alert.getText();
			alert.accept();
			log.info("Accepted the alert:" + sText);
		} catch (NoAlertPresentException e) {
			log.error("Alert is not displayed to accept." + e + "\n" + UtilityMethods.getStackTrace());
			Assert.fail("Alert is not displayed to accept." + e + "\n" + UtilityMethods.getStackTrace());
		} catch (Exception e) {
			log.error("Unable to accept the alert." + e + "\n" + UtilityMethods.getStackTrace());
			Assert.fail("Unable to accept the alert." + UtilityMethods.getStackTrace());
		}
	}

	/**
	 * Method: for verifying if accept exists and rejecting/dismissing the alert
	 * 
	 * @return - boolean (returns True when dismiss action is performed else returns
	 *         false)
	 * @throws Exception
	 */
	public void dismissAlert() throws Exception {
		try {
			Alert alert = driver.switchTo().alert();
			String sText = alert.getText();
			alert.dismiss();
			log.info("Dismissed the alert:" + sText);
		} catch (NoAlertPresentException e) {
			log.error("Alert is not displayed to dismiss." + e + "\n" + UtilityMethods.getStackTrace());
			Assert.fail("Alert is not displayed to dismiss." + e + "\n" + UtilityMethods.getStackTrace());
		} catch (Exception e) {
			log.error("Unable to dismiss the alert." + UtilityMethods.getStackTrace());
			Assert.fail("Unable to accept the alert." + UtilityMethods.getStackTrace());
		}

	}

	public String getAlertText() {
		String sText = null;
		try {
			Alert alert = driver.switchTo().alert();
			sText = alert.getText();
			// alert.accept();
			log.info("Accepted the alert:" + sText);
		} catch (NoAlertPresentException e) {
			log.error("Alert Text is not displayed to get text." + e + "\n" + UtilityMethods.getStackTrace());
			Assert.fail("Alert Text is not displayed to get text.." + e + "\n" + UtilityMethods.getStackTrace());
		} catch (Exception e) {
			log.error("Unable to accept the alert." + e + "\n" + UtilityMethods.getStackTrace());
			Assert.fail("Unable to accept the alert." + UtilityMethods.getStackTrace());
		}
		return sText;
	}

	/**
	 * Purpose - To select the context menu option for the given element
	 * 
	 * @param locatorString
	 * @param OptionIndex
	 * @param waitTime
	 * @return
	 * @throws Exception
	 */
	public void safeSelectContextMenuOption(String locatorString, int iOptionIndex, int waitTime) {
		try {
			if (isElementPresent(locatorString, waitTime)) {
				selectContextMenuOption(locatorString, iOptionIndex);
			} else {
				log.error("Element with locatorString " + locatorString
						+ "is not displayed to perform content menu operation" + UtilityMethods.getStackTrace());
				Assert.fail("Element with locatorString " + locatorString
						+ "is not displayed to perform content menu operation" + UtilityMethods.getStackTrace());
			}
		} catch (StaleElementReferenceException e) {
			log.error("Element with " + locatorString + "is not attached to the page document"
					+ UtilityMethods.getStackTrace());
			Assert.fail("Element with " + locatorString + "is not attached to the page document"
					+ UtilityMethods.getStackTrace());
		} catch (NoSuchElementException e) {
			log.error("Element " + locatorString + " was not found in DOM" + UtilityMethods.getStackTrace());
			Assert.fail("Element " + locatorString + " was not found in DOM" + UtilityMethods.getStackTrace());
		} catch (Exception e) {
			log.error("Unable to select context menu option" + UtilityMethods.getStackTrace());
			Assert.fail("Unable to select context menu option" + UtilityMethods.getStackTrace());
		}
	}

	private void selectContextMenuOption(String locatorString, int iOptionIndex) {
		WebElement Element = findElement(locatorString);
		Actions builder = new Actions(driver);
		for (int count = 1; count <= iOptionIndex; count++) {
			builder.contextClick(Element).sendKeys(Keys.ARROW_DOWN);
		}
		builder.contextClick(Element).sendKeys(Keys.RETURN).build().perform();
	}

	/**
	 * Method: for uploading file
	 * 
	 * @return - boolean (returns True when upload is successful else returns false)
	 * @throws Exception
	 */
	public boolean uploadFile(String locatorString, String filePath, int... optionWaitTime) throws Exception {
		boolean hasTyped = false;
		try {
			int waitTime = getWaitTime(optionWaitTime);
			if (isElementPresent(locatorString, waitTime)) {
				scrollIntoElementView(locatorString);
				WebElement element = findElement(locatorString);
				setHighlight(element);
				element.sendKeys(filePath);
				log.info("Entered - '" + filePath + " in the element - " + locatorString);
				hasTyped = true;
			} else {
				log.error("Unable to upload file - " + filePath + " using upload field - " + locatorString);
				Assert.fail("Unable to upload file - " + filePath + " using upload field - " + locatorString);

			}
		} catch (StaleElementReferenceException e) {
			log.error("Element with " + locatorString + "is not attached to the page document"
					+ UtilityMethods.getStackTrace());
			Assert.fail("Element with " + locatorString + "is not attached to the page document"
					+ UtilityMethods.getStackTrace());
		} catch (NoSuchElementException e) {
			log.error("Element " + locatorString + " was not found in DOM" + UtilityMethods.getStackTrace());
			Assert.fail("Element " + locatorString + " was not found in DOM" + UtilityMethods.getStackTrace());
		} catch (Exception e) {
			log.error("Unable to upload file - " + filePath + " using upload field - " + locatorString);
			Assert.fail("Unable to upload file - " + filePath + " using upload field - " + locatorString);
		}
		return hasTyped;
	}


	/**
	 * 
	 * TODO JavaScript method for entering a text in a field
	 *
	 * @param locatorString - locatorString value by which text field is recognized
	 * @param sText         - Text to be entered in a field
	 * @param waitTime      - Time to wait for an element
	 * @return
	 * @throws Exception
	 */
	public void safeJavaScriptType(String locatorString, String sText, int waitTime) throws Exception {
		try {
			if (isElementPresent(locatorString, waitTime)) {
				scrollIntoElementView(locatorString);
				WebElement element = findElement(locatorString);
				setHighlight(element);
				((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + sText + "');",
						element);
			} else {
				log.error("Unable to enter " + sText + " in field " + locatorString + UtilityMethods.getStackTrace());
				Assert.fail("Unable to enter " + sText + " in field " + locatorString + UtilityMethods.getStackTrace());
			}
		} catch (StaleElementReferenceException e) {
			log.error("Element with " + locatorString + "is not attached to the page document"
					+ UtilityMethods.getStackTrace());
			Assert.fail("Element with " + locatorString + "is not attached to the page document"
					+ UtilityMethods.getStackTrace());
		} catch (NoSuchElementException e) {
			log.error("Element " + locatorString + " was not found in DOM" + UtilityMethods.getStackTrace());
			Assert.fail("Element " + locatorString + " was not found in DOM" + UtilityMethods.getStackTrace());
		} catch (Exception e) {
			log.error("Unable to enter " + sText + " in field " + locatorString + UtilityMethods.getStackTrace());
			Assert.fail("Unable to enter " + sText + " in field " + locatorString + UtilityMethods.getStackTrace());
		}
	}

	/**
	 * 
	 * TODO JavaScript Safe Method for Clear and Type
	 *
	 * @param locatorString - locatorString value by which text field is recognized
	 * @param sText         - Text to be entered in a field
	 * @param waitTime      - Time to wait for an element
	 * @return
	 * @throws Exception
	 */
	public void safeJavaScriptClearType(String locatorString, String sText, int waitTime) {
		try {
			if (isElementPresent(locatorString, waitTime)) {
				scrollIntoElementView(locatorString);
				WebElement element = findElement(locatorString);
				setHighlight(element);
				((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '');", element);
				((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + sText + "');",
						element);
			} else {
				log.error("Unable to enter " + sText + " in field " + locatorString + UtilityMethods.getStackTrace());
				Assert.fail("Unable to enter " + sText + " in field " + locatorString + "\n"
						+ UtilityMethods.getStackTrace());
			}
		} catch (StaleElementReferenceException e) {
			log.error("Element with " + locatorString + "is not attached to the page document"
					+ UtilityMethods.getStackTrace());
			Assert.fail("Element with " + locatorString + "is not attached to the page document"
					+ UtilityMethods.getStackTrace());
		} catch (NoSuchElementException e) {
			log.error("Element " + locatorString + " was not found in DOM" + UtilityMethods.getStackTrace());
			Assert.fail("Element " + locatorString + " was not found in DOM" + UtilityMethods.getStackTrace());
		} catch (Exception e) {
			log.error("Unable to enter " + sText + " in field " + locatorString + UtilityMethods.getStackTrace());
			Assert.fail(
					"Unable to enter " + sText + " in field " + locatorString + "\n" + UtilityMethods.getStackTrace());
		}
	}

	/**
	 * 
	 * TODO Safe method to get the attribute value
	 *
	 * @param locatorString   - locatorString value by which an element is located
	 * @param sAttributeValue - attribute type
	 * @param waitTime        - Time to wait for an element
	 * @return - returns the attribute value of type string
	 */
	public String safeGetAttribute(String locatorString, String sAttributeValue, int waitTime) {
		String sValue = null;
		try {
		//	scrollIntoViewThroughJavaScriptExecuter(locatorString);
			if (isElementPresent(locatorString, waitTime)) {
				sValue = findElement(locatorString).getAttribute(sAttributeValue);
			} else {
				log.error("Unable to get attribute from locatorString " + locatorString);
				Assert.fail("Unable to get attribute from locatorString " + locatorString);
			}
		} catch (StaleElementReferenceException e) {
			log.error("Element with " + locatorString + "is not attached to the page document"
					+ UtilityMethods.getStackTrace());
			Assert.fail("Element with " + locatorString + "is not attached to the page document"
					+ UtilityMethods.getStackTrace());
		} catch (NoSuchElementException e) {
			log.error("Element " + locatorString + " was not found in DOM" + UtilityMethods.getStackTrace());
			Assert.fail("Element " + locatorString + " was not found in DOM" + UtilityMethods.getStackTrace());
		} catch (Exception e) {
			log.error("Unable to get attribute value of type " + sAttributeValue + " from the element " + locatorString
					+ "\n" + UtilityMethods.getStackTrace());
			Assert.fail("Unable to get attribute value of type " + sAttributeValue + " from the element "
					+ locatorString + "\n" + UtilityMethods.getStackTrace());
		}
		return sValue;
	}

	/**
	 * 
	 * TODO Safe method to get text from an element
	 *
	 * @param locatorString - locatorString value by which an element is located
	 * @param waitTime      - Time to wait for an element
	 * @return - returns the text value from element
	 */
	public String safeGetText(String locatorString, int waitTime) {
		String sValue = null;
		try {
			if (isElementPresent(locatorString, waitTime)) {
				scrollIntoViewThroughJavaScriptExecuter(locatorString);
				sValue = findElement(locatorString).getText();
			} else {
				Assert.fail("Unable to get the text from the element " + locatorString);
			}

		} catch (StaleElementReferenceException e) {
			log.error("Element with " + locatorString + "is not attached to the page document"
					+ UtilityMethods.getStackTrace());
			Assert.fail("Element with " + locatorString + "is not attached to the page document"
					+ UtilityMethods.getStackTrace());
		} catch (NoSuchElementException e) {
			log.error("Element " + locatorString + " was not found in DOM" + UtilityMethods.getStackTrace());
			Assert.fail("Element " + locatorString + " was not found in DOM" + UtilityMethods.getStackTrace());
		} catch (Exception e) {
			log.error(
					"Unable to get the text from the element " + locatorString + "\n" + UtilityMethods.getStackTrace());
			Assert.fail(
					"Unable to get the text from the element " + locatorString + "\n" + UtilityMethods.getStackTrace());
		}
		return sValue;
	}
	
	/**
	 * 
	 * TODO Method to get css value of an element
	 *
	 * @param locatorString - locatorString value by which an element is located
	 * @param waitTime      - Time to wait for an element
	 * @return - returns the css value from element
	 */
	public String getCssValue(String locatorString, String cssValue, int waitTime) {
		String sValue = null;
		try {
			if (isElementPresent(locatorString, waitTime)) {
				scrollIntoViewThroughJavaScriptExecuter(locatorString);
				sValue = findElement(locatorString).getCssValue(cssValue);
			} else {
				Assert.fail("Unable to get the+ "+cssValue+" of element " + locatorString);
			}

		} catch (StaleElementReferenceException e) {
			log.error("Element with " + locatorString + "is not attached to the page document"
					+ UtilityMethods.getStackTrace());
			Assert.fail("Element with " + locatorString + "is not attached to the page document"
					+ UtilityMethods.getStackTrace());
		} catch (NoSuchElementException e) {
			log.error("Element " + locatorString + " was not found in DOM" + UtilityMethods.getStackTrace());
			Assert.fail("Element " + locatorString + " was not found in DOM" + UtilityMethods.getStackTrace());
		} catch (Exception e) {
			log.error(
					"Unable to get the "+cssValue+" element " + locatorString + "\n" + UtilityMethods.getStackTrace());
			Assert.fail(
					"Unable to get the "+cssValue+" element " + locatorString + "\n" + UtilityMethods.getStackTrace());
		}
		return sValue;
	}


	/**
	 * 
	 * TODO scroll method to scroll the page down until expected element is visible
	 * *
	 * 
	 * @param locatorString - locatorString value by which an element is located
	 * @param waitTime      - Time to wait for an element
	 * @return - returns the text value from element
	 */
	public void scrollIntoElementView(String locatorString) {
		try {
			WebElement element = findElement(locatorString);
			/*if (new ConfigManager().getProperty("Browser.Name").equalsIgnoreCase("ff")
					|| new ConfigManager().getProperty("Browser.Name").equalsIgnoreCase("ie")) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
			}*/
	
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
		
		} catch (StaleElementReferenceException e) {
			log.error("Element with " + locatorString + "is not attached to the page document"
					+ UtilityMethods.getStackTrace());
			Assert.fail("Element with " + locatorString + "is not attached to the page document"
					+ UtilityMethods.getStackTrace());
		} catch (NoSuchElementException e) {
			log.error("Element " + locatorString + " was not found in DOM" + UtilityMethods.getStackTrace());
			Assert.fail("Element " + locatorString + " was not found in DOM" + UtilityMethods.getStackTrace());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Unable to scroll the page to find " + locatorString + "\n" + UtilityMethods.getStackTrace());
			Assert.fail("Unable to scroll the page to find " + locatorString + "\n" + UtilityMethods.getStackTrace());
		}
	}


	/**
	 * Method for switching to frame using frame id
	 * 
	 * @param driver
	 * @param frame
	 */
	public void selectFrame(String frame) {
		try {
			driver.switchTo().frame(frame);
			log.info("Navigated to frame with id " + frame);
		} catch (NoSuchFrameException e) {
			log.error("Unable to locate frame with id " + frame + UtilityMethods.getStackTrace());
			Assert.fail("Unable to locate frame with id " + frame + UtilityMethods.getStackTrace());
		} catch (Exception e) {
			log.error("Unable to navigate to frame with id " + frame + UtilityMethods.getStackTrace());
			Assert.fail("Unable to navigate to frame with id " + frame + UtilityMethods.getStackTrace());
		}
	}

	/**
	 * Method - Method for switching to frame in a frame
	 * 
	 * @param driver
	 * @param ParentFrame
	 * @param ChildFrame
	 */
	public void selectFrame(String ParentFrame, String ChildFrame) {
		try {
			driver.switchTo().frame(ParentFrame).switchTo().frame(ChildFrame);
			log.info("Navigated to innerframe with id " + ChildFrame + "which is present on frame with id"
					+ ParentFrame);
		} catch (NoSuchFrameException e) {
			log.error("Unable to locate frame with id " + ParentFrame + " or " + ChildFrame
					+ UtilityMethods.getStackTrace());
			Assert.fail("Unable to locate frame with id " + ParentFrame + " or " + ChildFrame
					+ UtilityMethods.getStackTrace());
		} catch (Exception e) {
			log.error("Unable to navigate to innerframe with id " + ChildFrame + "which is present on frame with id"
					+ ParentFrame + UtilityMethods.getStackTrace());
			Assert.fail("Unable to navigate to innerframe with id " + ChildFrame + "which is present on frame with id"
					+ ParentFrame + UtilityMethods.getStackTrace());
		}
	}

	/**
	 * Switch out from frame , or Navigate driver to main document when a page
	 * contains iframes.
	 *
	 */

	public void switchToDefaultContent() {
		try {
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			log.error("Unable to navigate Default content " + UtilityMethods.getStackTrace());
			Assert.fail("Unable to navigate to frame with locatorString " + UtilityMethods.getStackTrace());
		}
	}

	/**
	 * Method - Method for switching to frame using any locatorString of the frame
	 * 
	 * @param drive
	 * @param ParentFrame
	 * @param ChildFrame
	 */
	public void selectFrame(String FramelocatorString, int waitTime) {
		try {
			if (isElementPresent(FramelocatorString, waitTime)) {
				WebElement Frame = findElement(FramelocatorString);
				driver.switchTo().frame(Frame);
				log.info("Navigated to frame with locatorString " + FramelocatorString);
			} else {
				log.error("Unable to navigate to frame with locatorString " + FramelocatorString
						+ UtilityMethods.getStackTrace());
				Assert.fail("Unable to navigate to frame with locatorString " + FramelocatorString
						+ UtilityMethods.getStackTrace());
			}
		} catch (NoSuchFrameException e) {
			log.error(
					"Unable to locate frame with locatorString " + FramelocatorString + UtilityMethods.getStackTrace());
			Assert.fail(
					"Unable to locate frame with locatorString " + FramelocatorString + UtilityMethods.getStackTrace());
		} catch (Exception e) {
			log.error("Unable to navigate to frame with locatorString " + FramelocatorString
					+ UtilityMethods.getStackTrace());
			Assert.fail("Unable to navigate to frame with locatorString " + FramelocatorString
					+ UtilityMethods.getStackTrace());
		}
	}

	/**
	 * Method - Method for switching back to webpage from frame
	 * 
	 * @param driver
	 */
	public void defaultFrame() {
		try {
			driver.switchTo().defaultContent();
			log.info("Navigated to back to webpage from frame");
		} catch (Exception e) {
			log.error("unable to navigate back to main webpage from frame" + UtilityMethods.getStackTrace());
			Assert.fail("unable to navigate back to main webpage from frame" + UtilityMethods.getStackTrace());
		}
	}

	/**
	 * Method: Gets a UI element attribute value and compares with expected value
	 * 
	 * @param driver
	 * @param locatorString
	 * @param attributeName
	 * @param expected
	 * @param contains
	 * @return - Boolean (true if matches)
	 */
	public boolean getAttributeValue(WebDriver driver, String locatorString, String attributeName, String expected,
			boolean contains) {
		boolean bvalue = false;
		try {
			String sTemp = findElement(locatorString).getAttribute(attributeName);
			if (contains) {
				if (sTemp.contains(expected))
					bvalue = true;
				else
					bvalue = false;
			} else {
				if (sTemp == expected)
					bvalue = true;
				else
					bvalue = false;
			}
		} catch (StaleElementReferenceException e) {
			log.error("Element with " + locatorString + "is not attached to the page document"
					+ UtilityMethods.getStackTrace());
			Assert.fail("Element with " + locatorString + "is not attached to the page document"
					+ UtilityMethods.getStackTrace());
		} catch (NoSuchElementException e) {
			log.error("Element " + locatorString + " was not found in DOM" + UtilityMethods.getStackTrace());
			Assert.fail("Element " + locatorString + " was not found in DOM" + UtilityMethods.getStackTrace());
		} catch (Exception e) {
			log.error("Unable to get attribute value of type " + attributeName + " from the element " + locatorString
					+ "\n" + UtilityMethods.getStackTrace());
			Assert.fail("Unable to get attribute value of type " + attributeName + " from the element " + locatorString
					+ "\n" + UtilityMethods.getStackTrace());
		}
		return bvalue;
	}

	/**
	 * @Method Highlights on current working element or locatorString
	 * @param Webdriver  instance
	 * @param WebElement
	 * @return void (nothing)
	 */
	
	public void setHighlight(WebElement element) {
		if (false) {
			String attributevalue = "border:3px solid red;";
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			String getattrib = element.getAttribute("style");
			executor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, attributevalue);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				log.error("Sleep interrupted - " + UtilityMethods.getStackTrace());
			}
			executor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, getattrib);
		}
	}

	/**
	 * Method: Highlights on current working element or locatorString
	 * 
	 * @param driver
	 * @param locatorString
	 * @throws Exception
	 */
	public void highlightElement(WebElement element) throws Exception {
		if (false) {
			// change borderwidth andcolor values if required
			String attributevalue = "border:3px solid green;";
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			String getattrib = element.getAttribute("style");
			executor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, attributevalue);
			Thread.sleep(100);
			executor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, getattrib);
		}
	}

	public void jsClickOnElement(String cssSelector) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("var x = $(\'" + cssSelector + "\');");
			stringBuilder.append("x.click();");
			js.executeScript(stringBuilder.toString());
		} catch (Exception e) {
			log.error("some exception occured while trying to click on locatorString with css:" + cssSelector
					+ " using Jsclick" + "\n" + e.getMessage());
			Assert.fail("some exception occured while trying to click on locatorString with css:" + cssSelector
					+ " using Jsclick" + "\n" + e.getMessage());
		}
	}

	/**
	 * 
	 * This method is used to switch to windows based on provided number.
	 *
	 * @param num , Window number starting at 0
	 */
	public void switchToWindow(int num) {
		try {
			int numWindow = driver.getWindowHandles().size();
			String[] window = (String[]) driver.getWindowHandles().toArray(new String[numWindow]);
			driver.switchTo().window(window[num]);
			log.info("Navigated succesfsully to window with sepcified number: " + num);
		} catch (NoSuchWindowException e) {
			log.error("Window with sepcified number " + num
					+ " doesn't exists. Please check the window number or wait until the new window appears" + "\n"
					+ UtilityMethods.getStackTrace());
			Assert.fail("Window with sepcified number  " + num
					+ "doesn't exists. Please check the window number or wait until the new window appears"
					+ e.getMessage());
		} catch (Exception e) {
			log.error("Some exception occured while switching to new window with number: " + num + "\n"
					+ UtilityMethods.getStackTrace());
			Assert.fail("Some exception occured while switching to new window with number: " + num + e.getMessage());
		}
	}

	/**
	 * 
	 * This method is used to switch to new window.
	 *
	 * @param num , Window number starting at 0
	 */
	public void switchToNewWindow() {
		try {
			Set<String> windowHandles = driver.getWindowHandles();
			int numWindow = windowHandles.size();
			if (numWindow < 2) {
				log.error("new window is not available window count : " + numWindow);
				Assert.fail("new window is not available window count : " + numWindow
						+ " Please check the window count or wait until the new window appears");
			}
			String window = driver.getWindowHandle();
			for (String windowHandle : windowHandles) {
				if (!windowHandle.equals(window)) {
					driver.switchTo().window(windowHandle);
					log.info("Navigated succesfsully to new window");
				}
			}
		} catch (Exception e) {
			log.error(
					"new window is may not available Please check the window count or wait until the new window appears");
			Assert.fail(
					"new window is not available Please check the window count or wait until the new window appears");
		}
	}

	/**
	 * 
	 * This method is used to refresh the web page
	 *
	 */
	public void refresh() {
		try {
			driver.navigate().refresh();
		} catch (Exception e) {
			log.error("Some exception occured while refreshing the page, exception message: " + e.getMessage());
			Assert.fail("Some exception occured while refreshing the page, exception message: " + e.getMessage());
		}
	}

	/**
	 * 
	 * This method is used to navigate to back page
	 *
	 */
	public void navigateToBackPage() {
		try {
			driver.navigate().back();
		} catch (Exception e) {
			log.error("Some exception occured while navigating to back page, exception message: " + e.getMessage());
			Assert.fail("Some exception occured while navigating to back page, exception message: " + e.getMessage());
		}
	}

	/**
	 * 
	 * This method is used to perform web page forward navigation
	 *
	 */
	public void navigateToForwardPage() {
		try {
			driver.navigate().forward();
		} catch (Exception e) {
			log.error("Some exception occured while forwarding to a page, exception message: " + e.getMessage());
			Assert.fail("Some exception occured while forwarding to a page, exception message: " + e.getMessage());
		}
	}

	/**
	 * 
	 * This method is used to retrieve current url
	 *
	 * @return, returns current url
	 */
	public String getCurrentURL() {
		String sUrl = null;
		try {
			int i = 0;
			while (sUrl == null || sUrl.length() == 00) {
				sUrl = driver.getCurrentUrl();
				i++;
				if (i > 15)
					break;
			}
			log.info("url : " + sUrl);
		} catch (Exception e) {
			log.error("Some exception occured while retriving current url, exception message: " + e.getMessage());
			Assert.fail("Some exception occured while retriving current url, exception message: " + e.getMessage());
		}

		return sUrl;
	}

	/**
	 * 
	 * This method is used to retrieve current web page title
	 *
	 * @return , returns current web page title
	 */
	public String getTitle() {
		String sTitle = null;
		try {
			sTitle = driver.getTitle();
		} catch (Exception e) {
			log.error("Some exception occured while retriving title of the web page, exception message: "
					+ e.getMessage());
			Assert.fail("Some exception occured while retriving title of the web page, exception message: "
					+ e.getMessage());
		}
		return sTitle;
	}

	/**
	 * This method is used to Delete all cookies
	 */
	public void deleteAllCookies() {
		try {
			driver.manage().deleteAllCookies();
		} catch (Exception e) {
			log.error("Some exception occured while deleting all cookies, exception message: " + e.getMessage());
			Assert.fail("Some exception occured while deleting all cookies, exception message: " + e.getMessage());
		}
	}

	/**
	 * 
	 * This method is used to retrieve page source of the web page
	 *
	 * @return , returns page source of the web page
	 */
	public String getPageSource() {
		String sPageSource = null;
		try {
			sPageSource = driver.getPageSource();
		} catch (Exception e) {
			log.error("Some exception occured while retriving page source, exception message: " + e.getMessage());

			Assert.fail("Some exception occured while retriving page source, exception message: " + e.getMessage());
		}
		return sPageSource;
	}

	/**
	 * 
	 * This method is used to return number of locatorStrings found
	 *
	 * @param locatorString
	 * @return , returns number of locatorStrings
	 */
	public int getlocatorStringCount(String locatorString) {
		int iCount = 0;
		try {
			iCount = driver.findElements(findLocator(locatorString)).size();
		} catch (Exception e) {
			log.error(
					"Some exception occured while retriving locatorString count, exception message: " + e.getMessage());
			Assert.fail(
					"Some exception occured while retriving locatorString count, exception message: " + e.getMessage());
		}
		return iCount;
	}

	/**
	 * 
	 * This method is used to return list of WebElements matched String
	 * locatorString
	 *
	 * @param locatorString
	 * @return
	 */
	public List<WebElement> locatorStringWebElements(String locatorString) {
		List<WebElement> list = null;
		try {
			list = driver.findElements(findLocator(locatorString));
		} catch (Exception e) {
			log.error("Some exception occured while retriving web elements of a locatorString, exception message: "
					+ e.getMessage());
			Assert.fail("Some exception occured while retriving web elements of a locatorString, exception message: "
					+ e.getMessage());
		}
		return list;
	}

	public String waitForText(String locatorString, int wait) {
		String s = "";
		for (int i = 0; i < 10; i++) {
			s = safeGetText(locatorString, MEDIUMWAIT);
			if (s.length() > 0)
				break;
			else
				try {
					Thread.sleep(50);
				} catch (Exception e) {
				}
		}
		return s;
	}

	public void scrollIntoViewThroughJavaScriptExecuter(String locatorString) {
		WebElement element = findElement(locatorString);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		try {
			Thread.sleep(500);
		} catch (Exception e) {
		}
	}

	public String safeGetTextThroughJS(String locatorString) {
		scrollIntoViewThroughJavaScriptExecuter(locatorString);
		final String scriptGetValue = "return arguments[0].innerHTML";
		WebElement element = findElement(locatorString);
		String sValue = (String) ((JavascriptExecutor) driver).executeScript(scriptGetValue, element);
		return sValue;
	}

	public void sendKeysThroughAction(String locatorString, String value) {

		Actions builder = new Actions(driver);
		Action type = builder.click(findElement(locatorString)).sendKeys(value).build();
		type.perform();
	}

	public void waitForElementVisible(String locatorString, long sec) {
		WebDriverWait wait = new WebDriverWait(driver, sec);
		wait.until(ExpectedConditions.visibilityOfElementLocated(findLocator(locatorString)));
	}

	public void waitForElementClickable(String locatorString, long sec) {
		WebDriverWait wait = new WebDriverWait(driver, sec);
		wait.until(ExpectedConditions.elementToBeClickable(findLocator(locatorString)));
	}

	public void waitForElementPresent(String locatorString, long sec) {
		WebDriverWait wait = new WebDriverWait(driver, sec);
		wait.until(ExpectedConditions.presenceOfElementLocated(findLocator(locatorString)));
	}

	public void needToWait(int waitInSec) {
		try {
			Thread.sleep(waitInSec * 1000);
		} catch (Exception e) {
		}
	}

	/**
	 * Method - Safe Method for User Click, waits until the element is loaded and
	 * then performs a click action
	 * 
	 * @param locatorString
	 * @param waitTime
	 */
	public void directClick(String locatorString) {

		findElement(locatorString).click();

	}

	/**
	 * 
	 * TODO JavaScript method for clicking on an element
	 *
	 * @param locatorString - locatorString value by which element is recognized
	 * @param waitTime      - Time to wait for an element
	 * @return
	 * @throws Exception
	 */
	public void safeJavaScriptClick(String locatorString) {

		try {
			WebElement element = findElement(locatorString);
			scrollIntoViewThroughJSExecuter(element);
			// setHighlight(element);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		} catch (Exception e) {

		}
	}

	public void scrollIntoViewThroughJSExecuter(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		try {
			Thread.sleep(500);
		} catch (Exception e) {
		}
	}

	/**
	 * This method is used for
	 */

	public String getToolTipValue(String locatorString) {
		String tooltip_msg = null;
		try {
			WebElement element = findElement(locatorString);
			Actions builder = new Actions(driver);
			builder.moveToElement(element).perform();
			tooltip_msg = element.getText();
			log.info("Tooltip message is " + tooltip_msg);

		} catch (Exception e) {
			log.error("Unable to fatch tool-tip value");
		}
		return tooltip_msg;

	}

	/**
	 * Purpose- Method For draw polygon on map Need to modify in future
	 * 
	 * @param SourcelocatorString
	 */

	public void drawPolygon(String SourcelocatorString, int waitTime) {
		try {
			if (isElementPresent(SourcelocatorString, waitTime)) {
				WebElement element = findElement(SourcelocatorString);
				Actions builder = new Actions(driver);
				Action drawAction = builder.clickAndHold(element).moveByOffset(20, 80).moveByOffset(80, 20)
						.moveByOffset(-20, -80).moveByOffset(-80, -20).release(element).build();
				drawAction.perform();
				log.info("Dragged the element");

			} else {
				log.error("Source Element with locatorString " + SourcelocatorString + " was not displayed to drag"
						+ "\n" + UtilityMethods.getStackTrace());
				Assert.fail("Source Element with locatorString " + SourcelocatorString + " was not displayed to drag"
						+ "\n" + UtilityMethods.getStackTrace());
			}
		} catch (StaleElementReferenceException e) {
			log.error("Element with " + SourcelocatorString + "is not attached to the page document"
					+ UtilityMethods.getStackTrace());
			Assert.fail("Element with " + SourcelocatorString + "is not attached to the page document"
					+ UtilityMethods.getStackTrace());
		} catch (NoSuchElementException e) {
			log.error("Element " + SourcelocatorString + " was not found in DOM" + UtilityMethods.getStackTrace());
			Assert.fail("Element " + SourcelocatorString + " was not found in DOM" + UtilityMethods.getStackTrace());
		} catch (Exception e) {
			log.error("Some exception occurred while performing drag operation " + UtilityMethods.getStackTrace());
			Assert.fail("Some exception occurred while performing drag operation " + UtilityMethods.getStackTrace());
		}
	}

	/**
	 * Method - Safe Method for User Click, waits until the element is loaded and
	 * then performs a click action
	 * 
	 * @param locatorString
	 * @param waitTime
	 */
	public void safeClick(String locatorString, int waitTime) {
		try {
			if (isElementPresent(locatorString, waitTime)) {
				WebElement element = findElement(locatorString);
				scrollIntoViewThroughJSExecuter(element);
				element.click();
			}
		} catch (StaleElementReferenceException e) {
			log.error("Element with " + locatorString + "is not attached to the page document"
					+ UtilityMethods.getStackTrace());
			Assert.fail("Element with " + locatorString + "is not attached to the page document"
					+ UtilityMethods.getStackTrace());
		} catch (NoSuchElementException e) {
			log.error("Element " + locatorString + " was not found in DOM" + UtilityMethods.getStackTrace());
			Assert.fail("Element " + locatorString + " was not found in DOM" + UtilityMethods.getStackTrace());
		} catch (Exception e) {
			log.error("Some exception occurred while performing click operation " + UtilityMethods.getStackTrace());
			Assert.fail("Some exception occurred while performing click operation " + UtilityMethods.getStackTrace());
		}
	}

	/**
	 * Used to click on Tab
	 */
	public void ClickOnKeyboardTabButton() {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.TAB).perform();
		action.release().perform();
	}

	/**
	 * Used to click on Enter
	 */
	public void ClickOnKeyboardEnterButton() {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).perform();
		action.release().perform();
	}

	/**
	 * This method is used for Toast message
	 */

	public String getToastMessage() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='md-toast-content']")));
		return element.getText();
	}

	/**
	 * purpose-Safe Method for Used to Click, waits until the element is loaded
	 * 
	 * @param locatorString1
	 * @param locatorString2
	 * @param waitTime
	 */
	public void safeSelectDropDown(String locatorString1, String locatorString2, int waitTime) {
		try {
			if (isElementPresent(locatorString1, waitTime)) {
				directClick(locatorString1);

				if (isElementPresent(locatorString2, waitTime)) {
					needToWait(1);
					directClick(locatorString2);
				} else {
					log.error("Unable to select from " + locatorString2 + "\n" + UtilityMethods.getStackTrace());
					Assert.fail("Unable to select from " + locatorString2 + "\n" + UtilityMethods.getStackTrace());

				}
			} else {
				log.error("Unable to select from " + locatorString1 + "\n" + UtilityMethods.getStackTrace());
				Assert.fail("Unable to select from " + locatorString1 + "\n" + UtilityMethods.getStackTrace());
			}
		} catch (StaleElementReferenceException e) {
			log.error("Element with locatorString- " + locatorString1
					+ "webelement is not attached to the page document" + UtilityMethods.getStackTrace());
			Assert.fail("Element with locatorString- " + locatorString1
					+ " webelement is not attached to the page document" + UtilityMethods.getStackTrace());
		} catch (NoSuchElementException e) {
			log.error("Element with locatorString- " + locatorString1 + "webelement was not found in DOM"
					+ UtilityMethods.getStackTrace());
			Assert.fail("Element with locatorString- " + locatorString1 + "webelement was not found in DOM"
					+ UtilityMethods.getStackTrace());
		} catch (Exception e) {
			log.error("Unable to select from " + locatorString1 + "\n" + UtilityMethods.getStackTrace());
			Assert.fail("Unable to select from " + locatorString1 + "\n" + UtilityMethods.getStackTrace());
		}

	}

	public void safeSelectDropDownThroughJS(String locatorString1, String locatorString2, int waitTime) {
		try {
			if (isElementPresent(locatorString1, waitTime)) {
				WebElement element = findElement(locatorString1);
				scrollIntoViewThroughJSExecuter(element);
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
				needToWait(2);
				if (isElementPresent(locatorString2, waitTime)) {
					WebElement element2 = findElement(locatorString2);
					scrollIntoViewThroughJSExecuter(element);
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", element2);
				} else {
					log.error("Unable to select from " + locatorString2 + "\n" + UtilityMethods.getStackTrace());
					Assert.fail("Unable to select from " + locatorString2 + "\n" + UtilityMethods.getStackTrace());

				}
			} else {
				log.error("Unable to select from " + locatorString1 + "\n" + UtilityMethods.getStackTrace());
				Assert.fail("Unable to select from " + locatorString1 + "\n" + UtilityMethods.getStackTrace());
			}
		} catch (StaleElementReferenceException e) {
			log.error("Element with locatorString- " + locatorString1
					+ "webelement is not attached to the page document" + UtilityMethods.getStackTrace());
			Assert.fail("Element with locatorString- " + locatorString1
					+ " webelement is not attached to the page document" + UtilityMethods.getStackTrace());
		} catch (NoSuchElementException e) {
			log.error("Element with locatorString- " + locatorString1 + "webelement was not found in DOM"
					+ UtilityMethods.getStackTrace());
			Assert.fail("Element with locatorString- " + locatorString1 + "webelement was not found in DOM"
					+ UtilityMethods.getStackTrace());
		} catch (Exception e) {
			log.error("Unable to select from " + locatorString1 + "\n" + UtilityMethods.getStackTrace());
			Assert.fail("Unable to select from " + locatorString1 + "\n" + UtilityMethods.getStackTrace());
		}

	}

	/**
	 * Method - Safe Method for User Double Click, waits until the element is loaded
	 * and then performs a double click action
	 * 
	 * @param locatorString
	 * @param waitTime
	 */
	public void safeDblClick(String locatorString, int... optionWaitTime) {
		try {
			int waitTime = getWaitTime(optionWaitTime);
			if (waitUntilClickable(locatorString, waitTime)) {
				scrollIntoElementView(locatorString);// Need to implement
				WebElement element = findElement(locatorString);
				// setHighlight(element);
				Actions userAction = new Actions(driver).doubleClick(element);
				userAction.build().perform();
				log.info("Double clicked the element " + locatorString);
			} else {
				log.error("Unable to double click the element " + locatorString + UtilityMethods.getStackTrace());
				Assert.fail("Unable to double click the element " + locatorString + UtilityMethods.getStackTrace());
			}
		} catch (StaleElementReferenceException e) {
			log.error("Element with " + locatorString + " is not attached to the page document"
					+ UtilityMethods.getStackTrace());
			Assert.fail("Element with " + locatorString + " is not attached to the page document"
					+ UtilityMethods.getStackTrace());
		} catch (NoSuchElementException e) {
			log.error("Element " + locatorString + " was not found in DOM" + UtilityMethods.getStackTrace());
			Assert.fail("Element " + locatorString + " was not found in DOM" + UtilityMethods.getStackTrace());
		} catch (Exception e) {
			log.error("Element " + locatorString + " was not clickable" + UtilityMethods.getStackTrace());
			Assert.fail("Element " + locatorString + " was not clickable" + UtilityMethods.getStackTrace());
		}
	}

}
