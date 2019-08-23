package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.org.apache.bcel.internal.generic.Select;

import Common.BaseSetup;
import Common.UtilityMethods;
import PageObjects.LoginPage;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import org.openqa.selenium.By;

public class LoginTest extends BaseSetup {
	LoginPage loginPage;
	
	static Logger log = Logger.getLogger(LoginTest.class);

	@Test(priority = 0, description = "TC_Login_001 To verify login page title")
	public void Verify_login_page_title() {

		try {
			log.info("************************* TC-001*************************");
			extentTest.setDescription("TC_Login_001 To verify login page title");
			loginPage = new LoginPage(getDriver());
			String pageTitle = loginPage.getPageTitle();
			Assert.assertEquals(pageTitle, "SaferWatch - Command Center");
		} catch (Exception e) {
			e.printStackTrace();
			logError("Unable to verify login page title " + UtilityMethods.getStackTrace());
			Assert.fail("Unable to verify login page title");
		}
	}

	@Test(priority = 1, description = "TC_Login_002 To verify login page url")
	public void Verify_login_page_url() {

		try {
			log.info("************************* TC-002*************************");
			extentTest.setDescription("TC_Login_002 To verify login page url");
			loginPage = new LoginPage(getDriver());
			String pageUrl = loginPage.getCurrentWebpageURL();
			if (pageUrl.contains("saferwatchapp")) {
				Assert.assertEquals(1, 1);
			} else {
				Assert.assertEquals(1, 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logError("Unable to verify login page URL " + UtilityMethods.getStackTrace());
			Assert.fail("Unable to verify login page URL");
		}
	}

	@Test(priority = 2, description = "TC_Login_003 To verify login page UI fields")
	public void Verify_login_page_ui_fields() {

		try {
			log.info("************************* TC-003*************************");
			extentTest.setDescription("TC_Login_003 To verify login page UI fields");
			loginPage = new LoginPage(getDriver());
			Assert.assertTrue(loginPage.isSaferLogoImagePresent());
			Assert.assertTrue(loginPage.isEmailfieldPresent());
			Assert.assertTrue(loginPage.isPasswordFieldPresent());
			Assert.assertTrue(loginPage.isSignInButtonPresent());
			Assert.assertTrue(loginPage.isForgotPasswordLinkPresent());
			Assert.assertTrue(loginPage.isSubmitRequestLinkPresent());
			Assert.assertTrue(loginPage.isTermOfServiceLinkPresent());
			
			
		} catch (Exception e) {
			e.printStackTrace();
			logError("Unable to verify login page UI fields " + UtilityMethods.getStackTrace());
			Assert.fail("Unable to verify login page UI fields");
		}
	}

	@Test(priority = 3, description = " TC_Login_004  To verify validation message with valid 'email address' and empty 'password' field")
	public void Verify_validation_message_with_valid_emailAddress_and_empty_password_field() {

		try {
			log.info("************************* TC-004*************************");
			extentTest.setDescription(
					"TC_Login_004 To To verify validation message with valid 'email address' and empty 'password' field");
			loginPage = new LoginPage(getDriver());
			loginPage.enterEmailAddess(config.getProperty("superAdminEmail"));
			loginPage.clickOnLogInButton();
			String errorMsg = loginPage.getPasswordErrorMessage();
			Assert.assertEquals(errorMsg, "The password is required");
		} catch (Exception e) {
			e.printStackTrace();
			logError("Unable to verify validation message with valid 'email address' and empty 'password' field "
					+ UtilityMethods.getStackTrace());
			Assert.fail("Unable to verify validation message with valid 'email address' and empty 'password' field");
		}
	}

	@Test(priority = 4, description = "TC_Login_005 To verify validation message with valid 'Password' and empty 'email address' field")
	public void Verify_validation_message_with_valid_password_and_empty_emailAddress_field() {

		try {
			log.info("************************* TC-005*************************");
			extentTest.setDescription(
					"TC_Login_005 To verify validation message with valid 'password' and empty 'email address' field");
			loginPage = new LoginPage(getDriver());
			loginPage.enterPassword(config.getProperty("superAdminPassword"));
			loginPage.clickOnLogInButton();
			String errorMsg = loginPage.getEmailAddressErrorMessage();
			Assert.assertEquals(errorMsg, "The email is required");
		} catch (Exception e) {
			e.printStackTrace();
			logError("Unable to verify validation message with valid 'password' and empty 'email address' field "
					+ UtilityMethods.getStackTrace());
			Assert.fail("Unable to verify validation message with valid 'password' and empty 'email address' field");
		}
	}

	@Test(priority = 5, description = "TC_Login_006 To verify validation message with empty 'email address' and empty 'password' field")
	public void Verify_validation_message_with_empty_email_address_and_empty_password_field() {

		try {
			log.info("************************* TC-006*************************");
			extentTest.setDescription(
					"TC_Login_006 To verify validation message with empty 'email address' and empty 'password' field");
			loginPage = new LoginPage(getDriver());
			loginPage.enterEmailAddess("");
			loginPage.enterPassword("");
			loginPage.clickOnLogInButton();
			String emailAddressFieldErrorMsg = loginPage.getEmailAddressErrorMessage();
			String passwordFieldErrorMsg = loginPage.getPasswordErrorMessage();
			Assert.assertEquals(emailAddressFieldErrorMsg, "The email is required");
			Assert.assertEquals(passwordFieldErrorMsg, "The password is required");
		} catch (Exception e) {
			e.printStackTrace();
			logError("Unable to verify validation message with empty 'email address' and empty 'password' field "
					+ UtilityMethods.getStackTrace());
			Assert.fail("Unable to verify validation message with empty 'email address' and empty 'password' field");
		}
	}

	@Test(priority = 6, description = "TC_Login_007 To verify validation message with invalid 'Email address' and invalid 'password'")
	public void Verify_validation_message_with_invalid_email_address_and_invalid_password_field() {

		try {
			log.info("************************* TC-007*************************");
			extentTest.setDescription(
					"TC_Login_007 To verify validation message with invalid 'Email address' and invalid 'password' field");
			loginPage = new LoginPage(getDriver());
			loginPage.enterEmailAddess(config.getProperty("InvalidUsername"));
			loginPage.enterPassword(config.getProperty("InvalidPassword"));
			//loginPage.clickOnLogInButton();
			String errorMsg = loginPage.getInvalidCredentialsErrorMessage();
			System.out.println("errorMsg ->" + errorMsg);
			Assert.assertEquals(errorMsg,
					"The value is not a valid email address");
			
		} catch (Exception e) {
			e.printStackTrace();
			logError("Unable to verify validation message with invalid 'email address' and invalid 'password' field "
					+ UtilityMethods.getStackTrace());
			Assert.fail(
					"Unable to verify validation message with invalid 'email address' and invalid 'password' field");
		}
	}

	@Test(priority = 7, description = "TC_Login_08 To verify css value of login page fields")
	public void Verify_css_value_of_login_page_fields() {

		try {
			log.info("************************* TC-08*************************");
			extentTest.setDescription("TC_Login_08 To verify css value of login page fields");
			loginPage = new LoginPage(getDriver());
			String browserName = config.getProperty("Browser.Name");
			if (browserName.equalsIgnoreCase("chrome") || browserName.equalsIgnoreCase("ie")) {
				 Assert.assertEquals(loginPage.getlogInButtonBackgroundColor(), "rgba(54, 108, 185, 1)");
				 Assert.assertEquals(loginPage.getlogInButtonTextColor(), "rgba(255, 255, 255, 1)");
				 loginPage.clickOnLogInButton();
				 Assert.assertEquals(loginPage.getEmailAddressErrorMessage(), "The email is required");
				 Assert.assertEquals(loginPage.getPasswordErrorMessage(), "The password is required");
				 Assert.assertEquals(loginPage.getEmailAddresssErrorMsgTextColor(), "rgba(255, 92, 108, 1)");
				 Assert.assertEquals(loginPage.getPasswordErrorMsgTextColor(), "rgba(255, 92, 108, 1)");
			} else {
				 Assert.assertEquals(loginPage.getlogInButtonBackgroundColor(), "rgb(54, 108, 185, 1)");
				 Assert.assertEquals(loginPage.getlogInButtonTextColor(), "rgb(255, 255,255, 1)");		   
				 loginPage.clickOnLogInButton();
				 Assert.assertEquals(loginPage.getEmailAddressErrorMessage(), "The email is required");
				 Assert.assertEquals(loginPage.getPasswordErrorMessage(), "The password is required");
				 Assert.assertEquals(loginPage.getEmailAddresssErrorMsgTextColor(), "rgb(255, 92, 108, 1)");
				 Assert.assertEquals(loginPage.getPasswordErrorMsgTextColor(), "rgb(255, 92, 108, 1)");
			}

		} catch (Exception e) {
			e.printStackTrace();
			logError("Unable to verify css value of login page fields " + UtilityMethods.getStackTrace());
			Assert.fail("Unable to verify css value of login page fields");
		}
	}
	
	
	@Test(priority = 8, description = "TC_Login_09 To verify forgot password link ")
	public void Verify_forgot_password_link() {

		try {
			log.info("************************* TC-009*************************");
			extentTest.setDescription("TC_Login_002 To verify login page url");
			loginPage = new LoginPage(getDriver());
			loginPage.clickOnForgotPasswordLink();			
			String pageUrl = loginPage.getCurrentWebpageURL();
			if (pageUrl.contains("forgot-password")) {
				Assert.assertEquals(1, 1);
			} else {
				Assert.assertEquals(1, 0);
			}
			Assert.assertTrue(loginPage.isResetPasswordHeadingPresent());
			loginPage.clickOnLogInButton();
			 Assert.assertEquals(loginPage.isResetPasswordMessagePresent(), "Please Provide valid email address");
			
		} catch (Exception e) {
			e.printStackTrace();
			logError("Unable to verify forgot-password page URL " + UtilityMethods.getStackTrace());
			Assert.fail("Unable to verify forgot-password page URL");
		}
	}

	@Test(priority = 9, description = "TC_Login_010 To verify terms of service link ")
	public void Verify_termsOfService_link() {

		try {
			log.info("************************* TC-010*************************");
			extentTest.setDescription("TC_Login_009 To verify 'Terms of service' link");
			loginPage = new LoginPage(getDriver());
			loginPage.clickOnTermsOfServiceLink();	
			ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		    driver.switchTo().window(tabs2.get(1));
			String pageUrl = loginPage.getCurrentWebpageURL();
           if (pageUrl.contains("platformtermsofservice")) {
				Assert.assertEquals(1, 1);
			} else {
				Assert.assertEquals(1, 0);	
			}
			Assert.assertTrue(loginPage.isTermOfServiceParagraph());
			
		} catch (Exception e) {
			e.printStackTrace();
			logError("Unable to verify Term of service page URL " + UtilityMethods.getStackTrace());
			Assert.fail("Unable to verify term of service page URL");
		}
 	}
		
	@Test(priority = 10, description = "TC_Login_11 To verify login functionality with valid credentials of super admin ")
	public void Verify_login_functionality_with_valid_credentials_of_superAdmin() {
		try {
			log.info("************************* TC-011*************************");
			extentTest.setDescription(
					"TC_Login_014 To verify login functionality with valid credentials of administrator");
			loginPage = new LoginPage(getDriver());
			loginPage.enterEmailAddess(config.getProperty("superAdminEmail"));
			loginPage.enterPassword(config.getProperty("superAdminPassword"));
			loginPage.clickOnLogInButton();
			String currentUrl = loginPage.getCurrentWebpageURL();
			Assert.assertTrue(currentUrl.contains("intel"));
		    loginPage.clickOnUserProfile();
			loginPage.clickOnLogOutButton();	
		} catch (Exception e) {
			e.printStackTrace();
			logError("Unable to verify login functionality with valid credentials of super admin "
					+ UtilityMethods.getStackTrace());
			Assert.fail("Unable to verify login functionality with valid credentials of super admin");
		}
	}
	
	@Test(priority = 11, description = "TC_Login_12 To verify public user login functionality with sign up via app")
	public void Verify_login_functionality_with_public_user() {
		try {
			log.info("************************* TC-011*************************");
			extentTest.setDescription(
					"TC_Login_014 To verify login functionality with sign up public user");
			loginPage = new LoginPage(getDriver());
			loginPage.enterEmailAddess(config.getProperty("appPublicUserEmail"));
			loginPage.enterPassword(config.getProperty("appPublicUserPassword"));
			loginPage.clickOnLogInButton();
			Assert.assertEquals(loginPage.isPublicUserErrorMessagePresent(), "You do not have have enough rights to access this part of Application.");
			
				
		} catch (Exception e) {
			e.printStackTrace();
			logError("Unable to verify login functionality with sign up public user"
					+ UtilityMethods.getStackTrace());
			Assert.fail("Unable verify login functionality with sign up public user");
		}
	}
	
	@Test(priority = 12, description = "TC_Login_013 To verify submit request link and FAQ link")
	public void Verify_submitRequest_link() {

		try {
			log.info("************************* TC-012*************************");
			extentTest.setDescription("TC_Login_011 To Verify submit request link");
			loginPage = new LoginPage(getDriver());
			loginPage.clickOnSubmitRequestLink();	
			ArrayList<String> tabs3 = new ArrayList<String> (driver.getWindowHandles());
		    driver.switchTo().window(tabs3.get(2));
			String pageUrl = loginPage.getCurrentWebpageURL();
           if (pageUrl.contains("requests")) {
				Assert.assertEquals(1, 1);
			} else {
				Assert.assertEquals(1, 0);	
			}
           driver.findElement(By.cssSelector("input[id='request_anonymous_requester_email']")).sendKeys(loginPage.generateEmail(20));
           driver.findElement(By.cssSelector("input[id='request_subject']")).sendKeys(loginPage.generateRandomString(10));
           driver.findElement(By.cssSelector("textarea[id='request_description']")).sendKeys(loginPage.generateRandomString(50));
           loginPage.clickOnUploadImageButton();
           
           //put path to your image in a clipboard
		    StringSelection ss = new StringSelection("Downloads\\download.jpg");
		    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		    
		    //imitate mouse events like ENTER, CTRL+C, CTRL+V
		    Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
		    robot.keyRelease(KeyEvent.VK_ENTER);
		    robot.keyPress(KeyEvent.VK_CONTROL);
		    robot.keyPress(KeyEvent.VK_V);
		    robot.keyRelease(KeyEvent.VK_V);
		    robot.keyRelease(KeyEvent.VK_CONTROL);
		    robot.keyPress(KeyEvent.VK_ENTER);
		    robot.keyRelease(KeyEvent.VK_ENTER);
		    
		    loginPage.clickOnSubmitRequestButton(); 
		    loginPage.clickOnFaqlinkButton();
		    ArrayList<String> tabs4 = new ArrayList<String> (driver.getWindowHandles());
		    driver.switchTo().window(tabs4.get(2));
		    String pageUrl3 = loginPage.getCurrentWebpageURL();
			if (pageUrl3.contains("FAQ")) {
				Assert.assertEquals(1, 1);
			} else {
				Assert.assertEquals(1, 0);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logError("Unable to verify submit request link " + UtilityMethods.getStackTrace());
			Assert.fail("Unable to verify submit request link");
		}
 	}


}
