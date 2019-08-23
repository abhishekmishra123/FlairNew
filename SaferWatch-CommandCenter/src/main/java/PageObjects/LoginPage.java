package PageObjects;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Common.ConfigManager;
import Common.ActionMethods;

public class LoginPage extends ActionMethods {

	private String emailAddessField = "css:input[id='login_email']";
	private String passwordField = "css:input[id='login_password']";
	private String logInBtn = "css:.waves-effect.waves-light.m-r-10";
	private String userProfile = "css:span.hidden-md-down";	
	
	//private String userProfile = "xpath://span[text()='Admin']";	
	private String logOut = "css:a.log";
    private String forgotPasswordLink = "xpath://a[text()='Forgot Password']";  
    private String resetPasswordHeading = "xpath: //h4[text()='Reset Password']";    
    private String resetPasswordMessage= "xpath://small[text()='Please Provide valid email address']";	
     private String termsOfService ="xpath://a[text()='Terms of Service']";
    private String termOfServiceParagraph="css:.post-292.page.type-page.status-publish.hentry p:nth-child(5)";
    private String submitRequest ="css:a.have-a-question-link";
    private String uploadImageButton="css:div.upload-dropzone";
    private String submitRequestbutton ="css:input[value=\"Submit\"]";
    private String faqlink ="css:a.blocks-item-link";
  	private String rememberMeCheckbox = "css:div.checkbox input[name='remember']";
	private String submitRequestLink = "xpath://a[text()='Click Here to Submit a Request or Inquiry']";
	private String contactSupportLink = "css:a#contactSupport";
	//private String emailErrorMsg = "css:input[id='email'] + span[class='invalid-feedback']";
	private String emailErrorMsg = "xpath://small[text()='The email is required']";
	private String wrongEmail = "xpath://small[text()='The value is not a valid email address']";
	private String passwordErrorMsg = "xpath://small[text()='The password is required']";
	private String publicUserErrorMsg = "xpath://p[text()='You do not have have enough rights to access this part of Application.']";
	
	
	private String saferLogoImage = "css:div.text-center.mt-5";
	private String loginFromText = "css:form[name='loginForm'] h5";
	//private String errorMessage = "xpath://button[text()='Sign In']//following-sibling::span";
	public String hrefAttribute;
	public String errorMsg;
	public String webPageTitle;
	public String loginFromTexts;
	public String currentURL;
	public String backgroundColor, color, fontFamily;

	static WebDriver driver;
	public Logger log = Logger.getLogger(LoginPage.class);
	ConfigManager config;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		config = new ConfigManager();
	}

	public String getContactSupportHrefAttribute() {
        waitForPageToLoad();
		waitForElementPresent(contactSupportLink, SHORTWAIT);
		hrefAttribute = safeGetAttribute(contactSupportLink, "href", SHORTWAIT);
		log.info("Get href attribute of 'Contact Support' link");
		return hrefAttribute;
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

	public void clickOnLogInButton() {
		waitForElementClickable(logInBtn, SHORTWAIT);
		waitForElementVisible(logInBtn, SHORTWAIT);
		String browserName = config.getProperty("Browser.Name");
		needToWait(2);
		if (browserName.equalsIgnoreCase("ie")) {
			WebElement element = driver.findElement(findLocator(logInBtn));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", element);
		}else {
			safeClick(logInBtn, SHORTWAIT);
		}
		needToWait(3);
		log.info("Click on Login  button successfully");
	}
	
	
	public void clickOnUserProfile() {
		waitForElementClickable(userProfile, SHORTWAIT);
		waitForElementVisible(userProfile, SHORTWAIT);
		String browserName = config.getProperty("Browser.Name");
		needToWait(2);
		if (browserName.equalsIgnoreCase("ie")) {
			//WebElement element = driver.findElement(By.xpath("//button[text()='Sign In']"));
			WebElement element = driver.findElement(findLocator(userProfile));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", element);
		}else {
			safeJavaScriptClick(userProfile);
		}
		needToWait(3);
		log.info("Click on user profile button successfully");
	}
	
	public void clickOnLogOutButton() {
		waitForElementClickable(logOut, SHORTWAIT);
		String browserName = config.getProperty("Browser.Name");
		needToWait(2);
		if (browserName.equalsIgnoreCase("ie")) {
			WebElement element = driver.findElement(findLocator(logOut));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", element);
		}else {
			safeClick(logOut, SHORTWAIT);
			needToWait(4);
		}
		log.info("Click on logout button successfully");
	}

	public void clickOnForgotPasswordLink() {
		waitForElementClickable(forgotPasswordLink, SHORTWAIT);
		String browserName = config.getProperty("Browser.Name");
		needToWait(2);
		if (browserName.equalsIgnoreCase("ie")) {
			WebElement element = driver.findElement(findLocator(forgotPasswordLink));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", element);
		}else {
			safeClick(forgotPasswordLink, SHORTWAIT);
			needToWait(4);
		}
		log.info("Click on forgot Password Link successfully");
	}
	
	public boolean isResetPasswordHeadingPresent() {
		return isElementVisible(resetPasswordHeading, SHORTWAIT);		
	} 
	
	public boolean isTermOfServiceParagraph() {
		return isElementVisible(termOfServiceParagraph, SHORTWAIT);
	}
public String isResetPasswordMessagePresent() {
		waitForPageToLoad();
		waitForElementPresent(resetPasswordMessage, SHORTWAIT);
		errorMsg = safeGetText(resetPasswordMessage, SHORTWAIT);
		log.info("Get error message of 'Email address' field");
		return errorMsg;
	}
	

public String isPublicUserErrorMessagePresent() {
	waitForPageToLoad();
	waitForElementPresent(publicUserErrorMsg, SHORTWAIT);
	errorMsg = safeGetText(publicUserErrorMsg, SHORTWAIT);
	log.info("You do not have have enough rights to access this part of Application.");
	return errorMsg;
}
	
	public void clickOnTermsOfServiceLink() {
		waitForElementClickable(termsOfService, SHORTWAIT);
		String browserName = config.getProperty("Browser.Name");
		needToWait(2);
		if (browserName.equalsIgnoreCase("ie")) {
			WebElement element = driver.findElement(findLocator(termsOfService));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", element);
		}else {
			safeClick(termsOfService, SHORTWAIT);
			needToWait(4);
		}
		log.info("Click on terms Of Service Link successfully");
	}
	
	public void clickOnSubmitRequestLink() {
		waitForElementClickable(submitRequest, SHORTWAIT);
		String browserName = config.getProperty("Browser.Name");
		needToWait(2);
		if (browserName.equalsIgnoreCase("ie")) {
			WebElement element = driver.findElement(findLocator(submitRequest));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", element);
		}else {
			safeClick(submitRequest, SHORTWAIT);
			needToWait(4);
		}
		log.info("Click on Submit Request Link successfully");
	}
	
   public void authLogin(String authname, String authpwd) {
		enterCredentials(config.getProperty(authname), config.getProperty(authpwd));
		clickOnLogInButton();

	}

	public String getEmailAddressErrorMessage() {
		waitForPageToLoad();
		waitForElementPresent(emailErrorMsg, SHORTWAIT);
		errorMsg = safeGetText(emailErrorMsg, SHORTWAIT);
		log.info("Get error message of 'Username' field");
		return errorMsg;
	}

	public String getPasswordErrorMessage() {
		waitForPageToLoad();
		waitForElementPresent(passwordErrorMsg, SHORTWAIT);
		errorMsg = safeGetText(passwordErrorMsg, SHORTWAIT);
		log.info("Get error message of 'Password' field");
		return errorMsg;
	}

	public String getInvalidCredentialsErrorMessage() {
		waitForPageToLoad();
		waitForElementPresent(wrongEmail, SHORTWAIT);
		errorMsg = safeGetText(wrongEmail, SHORTWAIT);
		log.info("Get error message for invalid credentials.");
		return errorMsg;
	}


	public boolean isSaferLogoImagePresent() {
		return isElementVisible(saferLogoImage, SHORTWAIT);
	}

	public boolean isEmailfieldPresent() {
		return isElementVisible(emailAddessField, SHORTWAIT);
	}

	public boolean isPasswordFieldPresent() {
		return isElementVisible(passwordField, SHORTWAIT);
	}

	public boolean isSignInButtonPresent() {
		return isElementVisible(logInBtn, SHORTWAIT);
	}

	public boolean isForgotPasswordLinkPresent() {
		return isElementVisible(forgotPasswordLink, SHORTWAIT);
	}
	
	public boolean isRememberMeCheckboxPresent() {
		return isElementVisible(rememberMeCheckbox, SHORTWAIT);
	}
	
	public boolean isTermOfServiceLinkPresent() {
		return isElementVisible(submitRequestLink, SHORTWAIT);
	}
	
	public boolean isSubmitRequestLinkPresent() {
		return isElementVisible(submitRequestLink, SHORTWAIT);
	}

	public boolean isContactRequestLinkPresent() {
		return isElementVisible(contactSupportLink, SHORTWAIT);
	}

	public String getPageTitle() {
		waitForPageToLoad();
		webPageTitle = getTitle();
		return webPageTitle;
	}

	public String getCurrentWebpageURL() {
		waitForPageToLoad();
		needToWait(3);
		currentURL = getCurrentURL();
		return currentURL;
	}

	public String getLoginFormText() {
		waitForPageToLoad();
		waitForElementPresent(loginFromText, SHORTWAIT);
		loginFromTexts = safeGetText(loginFromText, SHORTWAIT);
		return loginFromTexts;
	}

	public String getlogInButtonBackgroundColor() {
		waitForPageToLoad();
		waitForElementPresent(logInBtn, SHORTWAIT);
		backgroundColor = getCssValue(logInBtn, "background-color", SHORTWAIT);
		return backgroundColor;
	}

	public String getlogInButtonTextColor() {
		color = getCssValue(logInBtn, "color", SHORTWAIT);
		return color;
	}
	
	public String getContactSupportLinkColor() {
		color = getCssValue(contactSupportLink, "color", SHORTWAIT);
		return color;
	}

	public String getEmailAddresssErrorMsgTextColor() {
		color = getCssValue(emailErrorMsg, "color", SHORTWAIT);
		return color;
	}

	public String getPasswordErrorMsgTextColor() {
		color = getCssValue(passwordErrorMsg, "color", SHORTWAIT);
		return color;
	}

	public String getLoginFormTextColor() {
		color = getCssValue(loginFromText, "color", SHORTWAIT);
		return color;
	}

	public String getMapfreLogoBackgroundColor() {
		waitForPageToLoad();
		waitForElementPresent(saferLogoImage, SHORTWAIT);
		backgroundColor = getCssValue(saferLogoImage.replace(" a img", ""), "background-color", SHORTWAIT);
		return backgroundColor;
	}
	
	 public String generateEmail(int length) {
		  String allowedChars="abcdefghijklmnopqrstuvwxyz" +   //alphabets
		    "1234567890" +   //numbers
		    "_-.";   //special characters
		  String email="";
		  String temp=RandomStringUtils.random(length,allowedChars);
		  email=temp.substring(0,temp.length()-9)+"@test.org";
		  return email;
		 }
	
	  public String generateRandomString(int length){
		  return RandomStringUtils.randomAlphabetic(length);
		  
		 }
	  
	  
	  public void clickOnUploadImageButton() {
			waitForElementClickable(uploadImageButton, SHORTWAIT);
			String browserName = config.getProperty("Browser.Name");
			needToWait(2);
			if (browserName.equalsIgnoreCase("ie")) {
				WebElement element = driver.findElement(findLocator(uploadImageButton));
				JavascriptExecutor executor = (JavascriptExecutor)driver;
				executor.executeScript("arguments[0].click();", element);
			}else {
				safeClick(uploadImageButton, SHORTWAIT);
				needToWait(4);
			}
			log.info("Click on upload image button successfully");
		}
	  public void clickOnSubmitRequestButton() {
			waitForElementClickable(submitRequestbutton, SHORTWAIT);
			String browserName = config.getProperty("Browser.Name");
			needToWait(2);
			if (browserName.equalsIgnoreCase("ie")) {
				WebElement element = driver.findElement(findLocator(submitRequestbutton));
				JavascriptExecutor executor = (JavascriptExecutor)driver;
				executor.executeScript("arguments[0].click();", element);
			}else {
				safeClick(submitRequestbutton, SHORTWAIT);
				needToWait(4);
			}
			log.info("Click on Submit Request button successfully");
		}
	  
	  public void clickOnFaqlinkButton() {
			waitForElementClickable(faqlink, SHORTWAIT);
			String browserName = config.getProperty("Browser.Name");
			needToWait(2);
			if (browserName.equalsIgnoreCase("ie")) {
				WebElement element = driver.findElement(findLocator(faqlink));
				JavascriptExecutor executor = (JavascriptExecutor)driver;
				executor.executeScript("arguments[0].click();", element);
			}else {
				safeClick(faqlink, SHORTWAIT);
				needToWait(4);
			}
			log.info("Click on FAQ Link successfully");
		}
	  
	 
	  
}
