package PageObjects;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import Common.ConfigManager;
import Common.ActionMethods;

public class CreateUserPage extends ActionMethods {

	private String emailAddessField = "css:input[id='login_email']";
	private String passwordField = "css:input[id='login_password']";
	private String logInBtn = "css:.waves-effect.waves-light.m-r-10";
	private String userManagement = "xpath://span[text()='User Management']";
	private String selectOrg = "css:select#ddlOrganization";
	private String selectLocation = "xpath://select[@id='ddlLocation']";
	private String createUsertype = "xpath://u[text()=\"+ Customize/Create User Types\"]";
	private String createUserTypeName = "css:input#user-type-name";
	private String submitReportCheckBox = "css:div.checkbox input#cr-send-report";
	private String receiveIntelCheckBox = "css: div.checkbox input#cr-view-intel";
	private String publishAlertCheckBox = "css:div.checkbox input#cr-publish-alerts";
	private String receiveAlertsCheckBox = "css:div.checkbox input#cr-read-only";
	private String userManagementCheckBox = "css:div.checkbox input#cr-user-mgmt";
	private String userTypeSaveButton = "xpath://div[@class='form-group float-right']//button[text()='Save']";
	private String userTypeOkButtonPopUp = "xpath://button[text()='OK']";
	private String userAbilitiesSelect = "css:select#ddlUserType";
	private String expiryDate = "css:input#roleExpiryDate";
	private String manuallyAddUserLink = "css: button#addNewUsersBtn";

	private String mandatoryFieldsPopup = "css:div.sweet-alert.showSweetAlert.visible";
	private String sendAccessEmailCheckBox = "css:input#send-access-email";
	private String addUserButton = "xpath://button[text()='Add User']";

	private String createUserButton = "css:button#btnCreateUser";
	private String doneButton = "css:button#doneBtnAddUser";
	private String createdUser = "xpath://h4[text()='TestingOrgUser Testing']";
	private String createdUserGetText = "xpath://a[contains(text(),'yopmail.com')]";
	private String getPasswordYopmail = "css:td.wrapper p:nth-child(9) b";

	private String userTypeLHS = "xpath://a[text()='User Type']";

	private String userListLhs = "xpath://a[text()='User List']";
	private String uploadImageButton = "css:div.upload-dropzone";
	private String contactSupportLink = "css:a#contactSupport";

	private String locationNameErrorMessage = "xpath://small[text()='Location Name is required.']";
	private String userTypeErrorMessage = "xpath: //small[text()='User Type is required.']";

	// private String emailErrorMsg = "css:input[id='email'] +
	// span[class='invalid-feedback']";
	private String saferLogoImage = "css:div.text-center.mt-5";
	private String loginFromText = "css:form[name='loginForm'] h5";
	private String createUser = "xpath://a[text()='Create Users']";
	// private String errorMessage = "xpath://button[text()='Sign
	// In']//following-sibling::span";
	public String hrefAttribute;
	public String errorMsg;
	public String webPageTitle;
	public String loginFromTexts;
	public String currentURL;
	public String backgroundColor, color, fontFamily;

	static WebDriver driver;
	public Logger log = Logger.getLogger(CreateUserPage.class);
	ConfigManager config;
	Random randomGenerator = new Random();
	int randomInt = randomGenerator.nextInt(1000);

	public CreateUserPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		config = new ConfigManager();
	}

	
	public void enterEmailAddess(String strEmailAdd) {
		waitForPageToLoad();
		waitForElementPresent(emailAddessField, SHORTWAIT);
		safeClearAndType(emailAddessField, strEmailAdd, SHORTWAIT);
		log.info("Enter Username successfully");

	}

	public void enterPassword(String strPass) {
		waitForPageToLoad();
		waitForElementPresent(passwordField, SHORTWAIT);
		safeClearAndType(passwordField, strPass, SHORTWAIT);
		log.info("Enter password successfully");
		needToWait(3);
	}

	public void enterCredentials(String strEmailAdd, String strPass) {
		waitForPageToLoad();
		waitForElementPresent(emailAddessField, SHORTWAIT);
		safeClearAndType(emailAddessField, strEmailAdd, SHORTWAIT);
		safeClearAndType(passwordField, strPass, SHORTWAIT);
		log.info("UserName and Password enter successfully");
	}

	
	
	//span[text()='Configuration']

	public void clickOnUserManagementDropDownButton() {
		waitForElementClickable(userManagement, SHORTWAIT);
		waitForElementVisible(userManagement, SHORTWAIT);
		String browserName = config.getProperty("Browser.Name");
		needToWait(2);
		if (browserName.equalsIgnoreCase("ie")) {
			// WebElement element = driver.findElement(By.xpath("//button[text()='Sign
			// In']"));
			WebElement element = driver.findElement(findLocator(userManagement));
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
		} else {
			safeJavaScriptClick(userManagement);
		}
		needToWait(3);
		log.info("Click on User Management  Drop Down button successfully");
	}

	public void clickOnCreateUser() {
		waitForElementClickable(createUser, SHORTWAIT);
		String browserName = config.getProperty("Browser.Name");
		needToWait(2);
		if (browserName.equalsIgnoreCase("ie")) {
			WebElement element = driver.findElement(findLocator(createUser));
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
		} else {
			safeClick(createUser, SHORTWAIT);
			needToWait(4);
		}
		log.info("Click on Create user Link successfully");
	}

	public void clickOnUserAbilitiesSelect() {
		waitForElementClickable(userAbilitiesSelect, SHORTWAIT);
		String browserName = config.getProperty("Browser.Name");
		needToWait(2);
		if (browserName.equalsIgnoreCase("ie")) {
			WebElement element = driver.findElement(findLocator(userAbilitiesSelect));
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
		} else {
			safeClick(userAbilitiesSelect, SHORTWAIT);
			needToWait(4);
		}
		log.info("Click on User Abilities Select dropdown successfully");
	}

	public void selectUsertype() {
		Select Location = new Select(driver.findElement(By.id("ddlUserType")));
		Location.selectByVisibleText("OrgUserType1");
	}
	
	public void selectUsertypeForSumitReport() {
		Select Location = new Select(driver.findElement(By.id("ddlUserType")));
		Location.selectByVisibleText("OrgUserType2");
	}
	
	public void selectUsertypeForReceiveIntel() {
		Select Location = new Select(driver.findElement(By.id("ddlUserType")));
		Location.selectByVisibleText("OrgUserType3");
	}
	
	public void selectUsertypeForPublishAlerts() {
		Select Location = new Select(driver.findElement(By.id("ddlUserType")));
		Location.selectByVisibleText("OrgUserType4");
	}
	
	public void selectUsertypeForReceiveAlerts() {
		Select Location = new Select(driver.findElement(By.id("ddlUserType")));
		Location.selectByVisibleText("OrgUserType5");
	}

	public boolean isExpiryDatePresent() {
		return isElementVisible(expiryDate, SHORTWAIT);
	}

	public boolean isTopHeaderPresent() {
		return isElementVisible(selectOrg, SHORTWAIT);
	}

	public void clickOnManuallyAddUserLink() {
		waitForElementClickable(manuallyAddUserLink, SHORTWAIT);
		String browserName = config.getProperty("Browser.Name");
		needToWait(2);
		if (browserName.equalsIgnoreCase("ie")) {
			WebElement element = driver.findElement(findLocator(manuallyAddUserLink));
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
		} else {
			safeClick(manuallyAddUserLink, SHORTWAIT);
			needToWait(4);
		}
		log.info("Click on User Abilities Select dropdown successfully");
	}

	public boolean isMandatoryFieldsPopupPresent() {
		return isElementVisible(mandatoryFieldsPopup, SHORTWAIT);
	}

	public void EnterDataSaferWatchUsers() {
		driver.findElement(By.id("txtFirstName")).sendKeys("TestingOrgUser");
		driver.findElement(By.id("txtLastName")).sendKeys("Testing");
		driver.findElement(By.id("txtEmail")).sendKeys("testing" + randomInt + "@yopmail.com");
		// driver.findElement(By.id("txtEmail")).sendKeys("testOrgUser@mailinator.com");
		waitForElementClickable(addUserButton, SHORTWAIT);
		safeJavaScriptClick(addUserButton);
		waitForElementClickable(doneButton, SHORTWAIT);
		safeClick(doneButton, SHORTWAIT);
	}

	public void clickOnSendAccessEmailCheckBox() {
		waitForElementClickable(sendAccessEmailCheckBox, SHORTWAIT);
		safeClick(sendAccessEmailCheckBox, SHORTWAIT);
		waitForElementClickable(createUserButton, SHORTWAIT);
		safeClick(createUserButton, SHORTWAIT);
	}

	public void clickOnAddUserButton() {
		waitForElementClickable(createUserButton, SHORTWAIT);
		safeClick(createUserButton, SHORTWAIT);
	}

	public String getLocationMessageText() {
		waitForPageToLoad();
		waitForElementPresent(locationNameErrorMessage, SHORTWAIT);
		locationNameErrorMessage = safeGetText(locationNameErrorMessage, SHORTWAIT);
		return locationNameErrorMessage;
	}

	public String getUserTypeErrorMessageText() {
		waitForPageToLoad();
		waitForElementPresent(userTypeErrorMessage, SHORTWAIT);
		locationNameErrorMessage = safeGetText(userTypeErrorMessage, SHORTWAIT);
		return locationNameErrorMessage;
	}

	public void clickOnUserListLhs() {
		waitForElementClickable(userListLhs, SHORTWAIT);
		safeJavaScriptClick(userListLhs);

	}

	public boolean isCreatedUserPresent() {
		return isElementVisible(createdUser, SHORTWAIT);
	}

	public String getCreatedUserText() {
		waitForPageToLoad();
		waitForElementPresent(createdUserGetText, SHORTWAIT);
		createdUserGetText = safeGetText(createdUserGetText, SHORTWAIT);
		return createdUserGetText;
	}

	public String getYopmailPasswordText() {
		waitForPageToLoad();
		waitForElementPresent(getPasswordYopmail, SHORTWAIT);
		getPasswordYopmail = safeGetText(getPasswordYopmail, SHORTWAIT);
		return getPasswordYopmail;
	}

	public void clickOnUserType() {
		waitForElementClickable(userTypeLHS, SHORTWAIT);
		safeJavaScriptClick(userTypeLHS);
	}

	public void clickOnTopHeaderOrg() {
		waitForElementClickable(selectOrg, SHORTWAIT);
		waitForElementVisible(selectOrg, SHORTWAIT);
		String browserName = config.getProperty("Browser.Name");
		needToWait(2);
		if (browserName.equalsIgnoreCase("ie")) {
			WebElement element = driver.findElement(findLocator(selectOrg));
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
		} else {
			safeClick(selectOrg, SHORTWAIT);
		}
		needToWait(3);
		log.info("Click on role  button successfully");
	}

	
	public String getCurrentWebpageURL() {
		waitForPageToLoad();
		needToWait(3);
		currentURL = getCurrentURL();
		return currentURL;
	}


	public String generateRandomString(int length) {
		return RandomStringUtils.randomAlphabetic(length);
	}

	
	public void selectORG() {
		Select Organization = new Select(driver.findElement(By.id("ddlOrganization")));
		Organization.selectByVisibleText("Automation Testing Organization");
	}

	public void clickOnSelectLocation() {
		scrollIntoElementView(selectLocation);
		waitForElementClickable(selectLocation, SHORTWAIT);

		waitForElementVisible(selectLocation, SHORTWAIT);
		String browserName = config.getProperty("Browser.Name");
		needToWait(3);
		if (browserName.equalsIgnoreCase("ie")) {
			WebElement element = driver.findElement(findLocator(selectLocation));
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
		} else {
			// safeClick(selectLocation, SHORTWAIT);
			safeJavaScriptClick(selectLocation);
		}
		needToWait(3);
		log.info("Click on select location dropdown successfully");
	}

	public void selectLocation() {
		scrollIntoElementView(selectLocation);
		Select Location = new Select(driver.findElement(By.id("ddlLocation")));
		Location.selectByVisibleText("Automation Testing Organization");
	}

	public void clickOnCreateUsertype() {
		waitForElementClickable(createUsertype, SHORTWAIT);
		waitForElementVisible(createUsertype, SHORTWAIT);
		String browserName = config.getProperty("Browser.Name");
		needToWait(2);
		if (browserName.equalsIgnoreCase("ie")) {
			WebElement element = driver.findElement(findLocator(createUsertype));
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
		} else {
			safeClick(createUsertype, SHORTWAIT);
		}
		needToWait(3);
		log.info("Click on create user type link successfully");
	}

	public void createUserName() {
		waitForElementVisible(createUserTypeName, SHORTWAIT);
		//driver.findElement(By.id("user-type-name")).sendKeys(generateRandomString(5));
		driver.findElement(By.id("user-type-name")).sendKeys("OrgUserType1");
	}
	
	public void createUserNameForSubmitReport() {
		waitForElementVisible(createUserTypeName, SHORTWAIT);
		driver.findElement(By.id("user-type-name")).sendKeys("OrgUserType2");
	}

	public void createUserNameForReceiveIntel() {
		waitForElementVisible(createUserTypeName, SHORTWAIT);
		driver.findElement(By.id("user-type-name")).sendKeys("OrgUserType3");
	}
	
	public void createUserNameForPublishAlerts() {
		waitForElementVisible(createUserTypeName, SHORTWAIT);
		driver.findElement(By.id("user-type-name")).sendKeys("OrgUserType4");
	}
	
	public void createUserNameForReceiveAlerts() {
		waitForElementVisible(createUserTypeName, SHORTWAIT);
		driver.findElement(By.id("user-type-name")).sendKeys("OrgUserType5");
	}
	
	public void clickOnUserTypeCriteriaCheckBoxsWithoutPublishCheckbox() {
		waitForElementClickable(submitReportCheckBox, SHORTWAIT);
		safeJavaScriptClick(submitReportCheckBox);
		waitForElementClickable(receiveIntelCheckBox, SHORTWAIT);
		safeJavaScriptClick(receiveIntelCheckBox);
		waitForElementClickable(receiveAlertsCheckBox, SHORTWAIT);
		waitForElementPresent(receiveAlertsCheckBox, SHORTWAIT);
		safeJavaScriptClick(receiveAlertsCheckBox);
		waitForElementClickable(userManagementCheckBox, SHORTWAIT);
		safeJavaScriptClick(userManagementCheckBox);
	}

	public void clickOnUserTypeCriteriaCheckBoxsWithAllRightsIncludePublishAlerts() {
		waitForElementClickable(submitReportCheckBox, SHORTWAIT);
		safeJavaScriptClick(submitReportCheckBox);
		waitForElementClickable(receiveIntelCheckBox, SHORTWAIT);
		safeJavaScriptClick(receiveIntelCheckBox);
		waitForElementClickable(publishAlertCheckBox, SHORTWAIT);
		safeJavaScriptClick(publishAlertCheckBox);
		waitForElementClickable(userManagementCheckBox, SHORTWAIT);
		safeJavaScriptClick(userManagementCheckBox);
	}
	
	public void clickOnUserTypeCriteriaCheckBoxsWithSubmitReports() {
		waitForElementClickable(submitReportCheckBox, SHORTWAIT);
		safeJavaScriptClick(submitReportCheckBox);		
	}
	
	public void clickOnUserTypeCriteriaCheckBoxsWithReceiveIntel() {
		waitForElementClickable(receiveIntelCheckBox, SHORTWAIT);
		safeJavaScriptClick(receiveIntelCheckBox);		
	}
	
	public void clickOnUserTypeCriteriaCheckBoxsWithPublishAlerts() {
		waitForElementClickable(publishAlertCheckBox, SHORTWAIT);
		safeJavaScriptClick(publishAlertCheckBox);		
	}
	
	public void clickOnUserTypeCriteriaCheckBoxsWithReceiveAlertsCheckBox() {
		waitForElementClickable(receiveAlertsCheckBox, SHORTWAIT);
		safeJavaScriptClick(receiveAlertsCheckBox);		
	}

	
	
	public void clickOnUserTypeSaveButton() {
		waitForElementClickable(userTypeSaveButton, SHORTWAIT);
		waitForElementVisible(userTypeSaveButton, SHORTWAIT);
		String browserName = config.getProperty("Browser.Name");
		needToWait(2);
		if (browserName.equalsIgnoreCase("ie")) {
			WebElement element = driver.findElement(findLocator(userTypeSaveButton));
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
		} else {
			safeJavaScriptClick(userTypeSaveButton);
		}
		needToWait(3);
		log.info("Click on create user type Save button successfully");
	}

	public void clickOnUserTypeConfirmButtonPopUp() {
		waitForElementClickable(userTypeOkButtonPopUp, SHORTWAIT);
		waitForElementVisible(userTypeOkButtonPopUp, SHORTWAIT);
		String browserName = config.getProperty("Browser.Name");
		needToWait(2);
		if (browserName.equalsIgnoreCase("ie")) {
			WebElement element = driver.findElement(findLocator(userTypeOkButtonPopUp));
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
		} else {
			safeClick(userTypeOkButtonPopUp, SHORTWAIT);
		}
		needToWait(3);
		log.info("Click on create user type Confirm (Ok) button successfully");
	}

}
