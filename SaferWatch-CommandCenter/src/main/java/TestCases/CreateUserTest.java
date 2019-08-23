package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.javascript.host.file.File;

import Common.BaseSetup;
import Common.UtilityMethods;
import PageObjects.AlertsPage;
import PageObjects.CreateUserPage;
import PageObjects.IntelPage;
import PageObjects.LoginPage;
import PageObjects.OrganizationsPage;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

public class CreateUserTest extends BaseSetup {
	LoginPage loginPage;
	CreateUserPage createUserPage;
	OrganizationsPage organizationsPage;
	IntelPage intelPage;
	AlertsPage alertsPage;
	
	
	static Logger log = Logger.getLogger(CreateUserTest.class);
	
	
	


//	 @Test(priority = 2, description = "TC_CreateOrgUser_1 To Verify validation messages without select organization/location & user abilities")
//	 public void
//	 Verify_validation_message_without_select_organization_location_and_user_abilities()
//	 {
//	 try {
//	 log.info("************************* TC-01*************************");
//	 extentTest.setDescription("TC_CreateOrgUser_1 To Verify validation messages without select Organization/location & user abilities");
//	 loginPage = new LoginPage(getDriver());
//	 createUserPage = new CreateUserPage(getDriver());
//	// loginPage.enterEmailAddess(config.getProperty("superAdminEmail"));
//	// loginPage.enterPassword(config.getProperty("superAdminPassword"));
//	// loginPage.clickOnLogInButton();
//	 createUserPage.clickOnUserManagementDropDownButton();
//	 createUserPage.clickOnCreateUser();
//	 createUserPage.clickOnAddUserButton();
//	 Assert.assertEquals(createUserPage.getLocationMessageText(), "Location Name is required.");
//	 Assert.assertEquals(createUserPage.getUserTypeErrorMessageText(), "User Type is required.");
//	
//	 } catch (Exception e) {
//	 e.printStackTrace();
//	 logError("Unable To Verify validation messages without select Organization/location & user abilities"
//	 + UtilityMethods.getStackTrace());
//	 Assert.fail("Verify To Verify validation messages without select Organization/location & user abilities");
//	 }
//	 }
//	
//	 @Test(priority = 3, description = "TC_createOrgUser_2 To Verify validation message without select organization/location")
//	 public void Verify_validation_message_without_select_Organization_location()
//	 {
//	 try {
//	 log.info("************************* TC-02*************************");
//	 extentTest.setDescription(
//	 "TC_createUser_2 To Verify validation message without select organization/location");
//	 loginPage = new LoginPage(getDriver());
//	 createUserPage = new CreateUserPage(getDriver());
//	// loginPage.enterEmailAddess(config.getProperty("superAdminEmail"));
//	// loginPage.enterPassword(config.getProperty("superAdminPassword"));
//	// loginPage.clickOnLogInButton();
//	 createUserPage.clickOnUserManagementDropDownButton();
//	 createUserPage.clickOnCreateUser();
//	 createUserPage.clickOnTopHeaderOrg();
//	 createUserPage.selectORG();
//	 createUserPage.clickOnCreateUsertype();
//	
//	 //User type Creation
//	 createUserPage.createUserName();
//	 createUserPage.clickOnUserTypeCriteriaCheckBoxsWithAllRightsIncludePublishAlerts();
//	 createUserPage.clickOnUserTypeSaveButton();
//	 createUserPage.clickOnUserTypeConfirmButtonPopUp();
//	
//	 //Verify location drop-down validation message
//	 createUserPage.clickOnCreateUser();
//	 createUserPage.clickOnUserAbilitiesSelect();
//	 createUserPage.selectUsertype();
//	 createUserPage.clickOnAddUserButton();
//	 Assert.assertEquals(createUserPage.getLocationMessageText(), "Location Name is required.");
//	
//	
//	 } catch (Exception e) {
//	 e.printStackTrace();
//	 logError("Unable To Verify validation message without select User Abilities"
//	 + UtilityMethods.getStackTrace());
//	 Assert.fail("Verify validation message without select User Abilities");
//	 }
//	 }
//	
//	 @Test(priority = 4, description = "TC_createOrgUser_3 To Verify validation message without select User Abilities")
//	 public void Verify_validation_message_without_select_User_Abilities() {
//	 try {
//	 log.info("************************* TC-03*************************");
//	 extentTest.setDescription("TC_createOrgUser_3 To Verify validation message without select User Abilities");
//	 loginPage = new LoginPage(getDriver());
//	 createUserPage = new CreateUserPage(getDriver());
//	// loginPage.enterEmailAddess(config.getProperty("superAdminEmail"));
//	// loginPage.enterPassword(config.getProperty("superAdminPassword"));
//	// loginPage.clickOnLogInButton();
//	 createUserPage.clickOnUserManagementDropDownButton();
//	 createUserPage.clickOnCreateUser();
//	 createUserPage.clickOnTopHeaderOrg();
//	 createUserPage.selectORG();
//	
//	 //verify the user type validation message
//	 createUserPage.clickOnCreateUser();
//	 createUserPage.clickOnSelectLocation();
//	 createUserPage.selectLocation();
//	 createUserPage.clickOnAddUserButton();
//	 Assert.assertEquals(createUserPage.getUserTypeErrorMessageText(), "User Type is required.");
//	
//	
//	 } catch (Exception e) {
//	 e.printStackTrace();
//	 logError("Unable To Verify validation message without select User Abilities"
//	 + UtilityMethods.getStackTrace());
//	 Assert.fail("Verify To Verify validation message without select User Abilities");
//	 }
//	 }
//	
	
//	 @Test(priority = 5, description = "TC_createOrgUser_4 To Verify the mandatory fields pop-up on select users section")
//	 public void Verify_the_mandatory_fields_popup_on_select_users_section() {
//	 try {
//	 log.info("************************* TC-04*************************");
//	 extentTest.setDescription( "TC_createOrgUser_4 To Verify the mandatory fields pop-up on select users section");
//	 loginPage = new LoginPage(getDriver());
//	 createUserPage = new CreateUserPage(getDriver());
//	// loginPage.enterEmailAddess(config.getProperty("superAdminEmail"));
//	// loginPage.enterPassword(config.getProperty("superAdminPassword"));
//	// loginPage.clickOnLogInButton();
//	 createUserPage.clickOnUserManagementDropDownButton();
//	 createUserPage.clickOnCreateUser();
//	
//	 //Verify the mandatory fields pop-up
//	 createUserPage.clickOnManuallyAddUserLink();
//	 Assert.assertTrue(createUserPage.isMandatoryFieldsPopupPresent());
//	 createUserPage.clickOnUserTypeConfirmButtonPopUp();
//	 loginPage.clickOnUserProfile();
//	 loginPage.clickOnLogOutButton();
//	
//	 } catch (Exception e) {
//	 e.printStackTrace();
//	 logError("Unable To Verify validation message without select User Abilities"
//	 + UtilityMethods.getStackTrace());
//	 Assert.fail("Verify To Verify validation message without select User Abilities");
//	 }
//	 }
//	
//	
//	 @Test(priority = 0, description = "TC_CreateOrgUser_5 A) To Verify creation of org user functionality with all_permission, 'maually add user', with new '+ customize/create user types' and with all rights")
//	 public void
//	 A_Verify_org_user_login_functionality_with_all_permission_and_create_new_customize_create_user_types
//	 () {
//	 try {
//	 log.info("************************* TC-05*************************");
//	 extentTest.setDescription( "TC_CreateOrgUser_5 A) To Verify creation of org user functionality with all_permission, 'maually add user', with new '+ customize/create user types' and with all rights");
//	 loginPage = new LoginPage(getDriver());
//	 createUserPage = new CreateUserPage(getDriver());
//	 organizationsPage = new OrganizationsPage(getDriver());
//	 ((JavascriptExecutor)driver).executeScript("window.open()");
//	 ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
//	 driver.switchTo().window(tabs.get(1));
//	 driver.get("http://www.yopmail.com/en/");
//	 driver.switchTo().window(tabs.get(0));
//	 loginPage.enterEmailAddess(config.getProperty("superAdminEmail"));
//	 loginPage.enterPassword(config.getProperty("superAdminPassword"));
//	 loginPage.clickOnLogInButton();
//	 String currentUrl = loginPage.getCurrentWebpageURL();
//	 Assert.assertTrue(currentUrl.contains("intel"));
//	 createUserPage.clickOnUserManagementDropDownButton();
//	 createUserPage.clickOnCreateUser();
//	 createUserPage.clickOnTopHeaderOrg();
//	 createUserPage.selectORG();
//	 createUserPage.clickOnCreateUsertype();
//	
//	 //User type Creation
//	 createUserPage.createUserName();
//	 createUserPage.clickOnUserTypeCriteriaCheckBoxsWithAllRightsIncludePublishAlerts();
//	 createUserPage.clickOnUserTypeSaveButton();
//	 createUserPage.clickOnUserTypeConfirmButtonPopUp();
//	
//	 //User creation
//	 createUserPage.clickOnCreateUser();
//	 createUserPage.clickOnSelectLocation();
//	 createUserPage.selectLocation();
//	 Assert.assertFalse(createUserPage.isExpiryDatePresent());
//	 createUserPage.clickOnUserAbilitiesSelect();
//	 createUserPage.selectUsertype();
//	 Assert.assertTrue(createUserPage.isExpiryDatePresent());
//	 createUserPage.clickOnManuallyAddUserLink();
//	
//	 //Enter data for new user
//	 createUserPage.EnterDataSaferWatchUsers();
//	 createUserPage.clickOnSendAccessEmailCheckBox();
//	 createUserPage.clickOnUserListLhs();
//	 Assert.assertTrue(createUserPage.isCreatedUserPresent());
//	 String strEmail = createUserPage.getCreatedUserText();
//	
//	 //Get Password from yopmail
//	 driver.switchTo().window(tabs.get(1));
//	 driver.findElement(By.cssSelector("input.scpt")).sendKeys(strEmail);
//	 driver.findElement(By.cssSelector("input.scpt")).sendKeys(Keys.ENTER);
//	 driver.switchTo().frame("ifmail");
//	 String strPassword = createUserPage.getYopmailPasswordText();
//	 driver.manage().deleteAllCookies();
//	 driver.close();
//	
//	 //Verify the Org user with all rights
//	 driver.switchTo().window(tabs.get(0));
//	 loginPage.clickOnUserProfile();
//	 loginPage.clickOnLogOutButton();
//	 loginPage.enterEmailAddess(strEmail);
//	 loginPage.enterPassword(strPassword);
//	 loginPage.clickOnLogInButton();
//	 String currentUrl1 = loginPage.getCurrentWebpageURL();
//	 Assert.assertTrue(currentUrl1.contains("intel"));
//	 Assert.assertFalse(createUserPage.isTopHeaderPresent());
//	 organizationsPage.clickOnConfigurationLHSMenu();
//	 organizationsPage.clickOnConfigurationLHSMenu();
//	 Assert.assertFalse(organizationsPage.isAddOrganizationButtonPresent());
//	 loginPage.clickOnUserProfile();
//	 loginPage.clickOnLogOutButton();
//	
//	
//	 } catch (Exception e) {
//	 e.printStackTrace();
//	 logError("Unable To Verify creation of org user functionality with all_permission, new '+ customize/create user types'and 'maually add user'"
//	 + UtilityMethods.getStackTrace());
//	 Assert.fail("Verify creation of org user functionality with all_permission, new '+ customize/create user types'and 'maually add user");
//	 }
//	 }
//	
//	
//	 @Test(priority = 1, description = "TC_createOrgUser_5 B) To Verify creation of org user functionality with all_permission, 'maually add user' \r\n" +
//	 "and with existing 'create user type'")
//	 public void
//	 B_Verify_org_user_login_functionality_with_all_permission_and_existing_User_Types()
//	 {
//	 try {
//	 log.info("************************* TC-05*************************");
//	 extentTest.setDescription("TC_CreateUser_5 To Verify creation of org user functionality with all_permission, 'maually add user' \\r\\n\" + \r\n" +
//	 " \"and with existing 'create user type'");
//	 loginPage = new LoginPage(getDriver());
//	 createUserPage = new CreateUserPage(getDriver());
//	 loginPage.enterEmailAddess(config.getProperty("superAdminEmail"));
//	 loginPage.enterPassword(config.getProperty("superAdminPassword"));
//	 loginPage.clickOnLogInButton();
//	 createUserPage.clickOnUserManagementDropDownButton();
//	 createUserPage.clickOnCreateUser();
//	 createUserPage.clickOnTopHeaderOrg();
//	 createUserPage.selectORG();
//	 createUserPage.clickOnSelectLocation();
//	 createUserPage.selectLocation();
//	 Assert.assertFalse(createUserPage.isExpiryDatePresent());
//	 createUserPage.clickOnUserAbilitiesSelect();
//	 createUserPage.selectUsertype();
//	 Assert.assertTrue(createUserPage.isExpiryDatePresent());
//	 createUserPage.clickOnManuallyAddUserLink();
//	 createUserPage.EnterDataSaferWatchUsers();
//	 createUserPage.clickOnSendAccessEmailCheckBox();
//	 createUserPage.clickOnUserListLhs();
//	 Assert.assertTrue(createUserPage.isCreatedUserPresent());
//	
//	 } catch (Exception e) {
//	 e.printStackTrace();
//	 logError("Unable To Verify creation of org user functionality with all_permission, 'maually add user' and with existing 'create user type"
//	 + UtilityMethods.getStackTrace());
//	 Assert.fail("Unable To Verify creation of org user functionality with all_permission, 'maually add user' and with existing 'create user type");
//	 }
//	 }
//
//	@Test(priority = 6, description = "TC_CreateOrgUser_6 To Verify org user login functionality with 'submit a report' permission")
//	public void Verify_org_user_login_functionality_with_submit_a_report_permission() {
//		try {
//			log.info("************************* TC-06*************************");
//			extentTest.setDescription(
//					"TC_CreateOrgUser_6 To Verify org user login functionality with 'submit a report' permission ");
//			loginPage = new LoginPage(getDriver());
//			createUserPage = new CreateUserPage(getDriver());
//			((JavascriptExecutor) driver).executeScript("window.open()");
//			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
//			driver.switchTo().window(tabs.get(1));
//			driver.get("http://www.yopmail.com/en/");
//			driver.switchTo().window(tabs.get(0));
//			loginPage.enterEmailAddess(config.getProperty("superAdminEmail"));
//			loginPage.enterPassword(config.getProperty("superAdminPassword"));
//			loginPage.clickOnLogInButton();
//			createUserPage.clickOnUserManagementDropDownButton();
//			createUserPage.clickOnCreateUser();
//			createUserPage.clickOnTopHeaderOrg();
//			createUserPage.selectORG();
//			createUserPage.clickOnCreateUsertype();
//
//			// User type Creation
//			createUserPage.createUserNameForSubmitReport();
//			createUserPage.clickOnUserTypeCriteriaCheckBoxsWithSubmitReports();
//			createUserPage.clickOnUserTypeSaveButton();
//			createUserPage.clickOnUserTypeConfirmButtonPopUp();
//
//			// User creation
//			createUserPage.clickOnCreateUser();
//			createUserPage.clickOnSelectLocation();
//			createUserPage.selectLocation();
//			Assert.assertFalse(createUserPage.isExpiryDatePresent());
//			createUserPage.clickOnUserAbilitiesSelect();
//			createUserPage.selectUsertypeForSumitReport();
//			Assert.assertTrue(createUserPage.isExpiryDatePresent());
//			createUserPage.clickOnManuallyAddUserLink();
//
//			// Enter data for new user
//			createUserPage.EnterDataSaferWatchUsers();
//			createUserPage.clickOnSendAccessEmailCheckBox();
//			createUserPage.clickOnUserListLhs();
//			Assert.assertTrue(createUserPage.isCreatedUserPresent());
//			String strEmail = createUserPage.getCreatedUserText();
//
//			// Get Password from yopmail
//			driver.switchTo().window(tabs.get(1));
//			driver.findElement(By.cssSelector("input.scpt")).sendKeys(strEmail);
//			driver.findElement(By.cssSelector("input.scpt")).sendKeys(Keys.ENTER);
//			driver.switchTo().frame("ifmail");
//			String strPassword = createUserPage.getYopmailPasswordText();
//			driver.manage().deleteAllCookies();
//			driver.close();
//			
//			
//			// Verify the Org user with submit reports rights
//			driver.switchTo().window(tabs.get(0));
//			loginPage.clickOnUserProfile();
//			loginPage.clickOnLogOutButton();
//			loginPage.enterEmailAddess(strEmail);
//			loginPage.enterPassword(strPassword);
//			loginPage.clickOnLogInButton();
//			Assert.assertEquals(loginPage.isPublicUserErrorMessagePresent(),
//					"You do not have have enough rights to access this part of Application.");
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			logError("Unable to Verify org user login functionality with 'submit a report' permission"
//					+ UtilityMethods.getStackTrace());
//			Assert.fail("Verify org user login functionality with 'submit a report' permission");
//		}
//	}
//	
//	@Test(priority = 7, description = "TC_CreateOrgUser_7 To Verify org user login functionality with 'receive intel' permission")
//	public void  Verify_org_user_login_functionality_with_receive_intel_permission() {
//		try {
//			log.info("************************* TC-07*************************");
//			extentTest.setDescription(
//					"TC_CreateOrgUser_7 To Verify org user login functionality with 'receive intel' permission");
//			loginPage = new LoginPage(getDriver());
//			createUserPage = new CreateUserPage(getDriver());
//			intelPage = new IntelPage(getDriver());
//			((JavascriptExecutor) driver).executeScript("window.open()");
//			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
//			driver.switchTo().window(tabs.get(1));
//			driver.get("http://www.yopmail.com/en/");
//			driver.switchTo().window(tabs.get(0));
//			loginPage.enterEmailAddess(config.getProperty("superAdminEmail"));
//			loginPage.enterPassword(config.getProperty("superAdminPassword"));
//			loginPage.clickOnLogInButton();
//			createUserPage.clickOnUserManagementDropDownButton();
//			createUserPage.clickOnCreateUser();
//			createUserPage.clickOnTopHeaderOrg();
//			createUserPage.selectORG();
//			createUserPage.clickOnCreateUsertype();
//
//			// User type Creation
//			createUserPage.createUserNameForReceiveIntel();
//			createUserPage.clickOnUserTypeCriteriaCheckBoxsWithReceiveIntel();
//			createUserPage.clickOnUserTypeSaveButton();
//			createUserPage.clickOnUserTypeConfirmButtonPopUp();
//
//			// User creation
//			createUserPage.clickOnCreateUser();
//			createUserPage.clickOnSelectLocation();
//			createUserPage.selectLocation();
//			Assert.assertFalse(createUserPage.isExpiryDatePresent());
//			createUserPage.clickOnUserAbilitiesSelect();
//			createUserPage.selectUsertypeForReceiveIntel();
//			Assert.assertTrue(createUserPage.isExpiryDatePresent());
//			createUserPage.clickOnManuallyAddUserLink();
//
//			// Enter data for new user
//			createUserPage.EnterDataSaferWatchUsers();
//			createUserPage.clickOnSendAccessEmailCheckBox();
//			createUserPage.clickOnUserListLhs();
//			Assert.assertTrue(createUserPage.isCreatedUserPresent());
//			String strEmail = createUserPage.getCreatedUserText();
//
//			// Get Password from yopmail
//			driver.switchTo().window(tabs.get(1));
//			driver.findElement(By.cssSelector("input.scpt")).sendKeys(strEmail);
//			driver.findElement(By.cssSelector("input.scpt")).sendKeys(Keys.ENTER);
//			driver.switchTo().frame("ifmail");
//			String strPassword = createUserPage.getYopmailPasswordText();
//			driver.manage().deleteAllCookies();
//			driver.close();
//
//			// Verify the Org user with Receive Intel rights
//			driver.switchTo().window(tabs.get(0));
//			loginPage.clickOnUserProfile();
//			loginPage.clickOnLogOutButton();
//			loginPage.enterEmailAddess(strEmail);
//			loginPage.enterPassword(strPassword);
//			loginPage.clickOnLogInButton();
//			String currentUrl = loginPage.getCurrentWebpageURL();
//			Assert.assertTrue(currentUrl.contains("intel"));
//			Assert.assertTrue(intelPage.isIntelLhsMenuPresent());
//			loginPage.clickOnUserProfile();
//			loginPage.clickOnLogOutButton();
//			
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			logError("Unable Verify org user login functionality with 'receive intel' permission"
//					+ UtilityMethods.getStackTrace());
//			Assert.fail(" Verify org user login functionality with 'receive intel' permission");
//		}
//	}

	@Test(priority = 8, description = "TC_CreateOrgUser_8 To Verify org user login functionality with 'publish alerts & receive alerts' permission ")
	public void  Verify_org_user_login_functionality_with_publish_alerts_and_receive_alerts_permission() {
		try {
			log.info("************************* TC-08*************************");
			extentTest.setDescription(
					"TC_CreateOrgUser_8 To Verify org user login functionality with 'publish alerts & receive alerts' permission ");
			loginPage = new LoginPage(getDriver());
			createUserPage = new CreateUserPage(getDriver());
			alertsPage = new AlertsPage(getDriver());
			intelPage = new IntelPage(getDriver());
			((JavascriptExecutor) driver).executeScript("window.open()");
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			driver.get("http://www.yopmail.com/en/");
			driver.switchTo().window(tabs.get(0));
			loginPage.enterEmailAddess(config.getProperty("superAdminEmail"));
			loginPage.enterPassword(config.getProperty("superAdminPassword"));
			loginPage.clickOnLogInButton();
			createUserPage.clickOnUserManagementDropDownButton();
			createUserPage.clickOnCreateUser();
			createUserPage.clickOnTopHeaderOrg();
			createUserPage.selectORG();
			createUserPage.clickOnCreateUsertype();

			// User type Creation
			createUserPage.createUserNameForPublishAlerts();
			createUserPage.clickOnUserTypeCriteriaCheckBoxsWithPublishAlerts();
			createUserPage.clickOnUserTypeSaveButton();
			createUserPage.clickOnUserTypeConfirmButtonPopUp();

			// User creation
			createUserPage.clickOnCreateUser();
			createUserPage.clickOnSelectLocation();
			createUserPage.selectLocation();
			Assert.assertFalse(createUserPage.isExpiryDatePresent());
			createUserPage.clickOnUserAbilitiesSelect();
			createUserPage.selectUsertypeForPublishAlerts();
			Assert.assertTrue(createUserPage.isExpiryDatePresent());
			createUserPage.clickOnManuallyAddUserLink();

			// Enter data for new user
			createUserPage.EnterDataSaferWatchUsers();
			createUserPage.clickOnSendAccessEmailCheckBox();
			createUserPage.clickOnUserListLhs();
			Assert.assertTrue(createUserPage.isCreatedUserPresent());
			String strEmail = createUserPage.getCreatedUserText();

			// Get Password from yopmail
			driver.switchTo().window(tabs.get(1));
			driver.findElement(By.cssSelector("input.scpt")).sendKeys(strEmail);
			driver.findElement(By.cssSelector("input.scpt")).sendKeys(Keys.ENTER);
			driver.switchTo().frame("ifmail");
			String strPassword = createUserPage.getYopmailPasswordText();
			driver.manage().deleteAllCookies();
			driver.close();

			// Verify the Org user with Receive Intel rights
			driver.switchTo().window(tabs.get(0));
			loginPage.clickOnUserProfile();
			loginPage.clickOnLogOutButton();
			loginPage.enterEmailAddess(strEmail);
			loginPage.enterPassword(strPassword);
			loginPage.clickOnLogInButton();
			String currentUrl = loginPage.getCurrentWebpageURL();
			Assert.assertTrue(currentUrl.contains("alerts"));
			Assert.assertTrue(alertsPage.isAlertsLhsMenuPresent());
			Assert.assertTrue(alertsPage.isMakeAnAlertButtonPresent());
			loginPage.clickOnUserProfile();
        	loginPage.clickOnLogOutButton();
			

		} catch (Exception e) {
			e.printStackTrace();
			logError("Unable To Verify org user login functionality with 'publish alerts & receive alerts' permission "
					+ UtilityMethods.getStackTrace());
			Assert.fail("Verify org user login functionality with 'publish alerts & receive alerts' permission");
		}
	}

	@Test(priority = 9, description = "TC_CreateOrgUser_9 To Verify org user login functionality with 'receive alerts' permission ")
	public void  Verify_org_user_login_functionality_with_receive_alerts_permission() {
		try {
			log.info("************************* TC-09*************************");
			extentTest.setDescription(
					"TC_CreateOrgUser_8 To Verify org user login functionality with 'receive intel' permission");
			loginPage = new LoginPage(getDriver());
			createUserPage = new CreateUserPage(getDriver());
			alertsPage = new AlertsPage(getDriver());
			intelPage = new IntelPage(getDriver());
			((JavascriptExecutor) driver).executeScript("window.open()");
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			driver.get("http://www.yopmail.com/en/");
			driver.switchTo().window(tabs.get(0));
			loginPage.enterEmailAddess(config.getProperty("superAdminEmail"));
			loginPage.enterPassword(config.getProperty("superAdminPassword"));
			loginPage.clickOnLogInButton();
			createUserPage.clickOnUserManagementDropDownButton();
			createUserPage.clickOnCreateUser();
			createUserPage.clickOnTopHeaderOrg();
			createUserPage.selectORG();
			createUserPage.clickOnCreateUsertype();

			// User type Creation
			createUserPage.createUserNameForReceiveAlerts();
			createUserPage.clickOnUserTypeCriteriaCheckBoxsWithReceiveAlertsCheckBox();
			createUserPage.clickOnUserTypeSaveButton();
			createUserPage.clickOnUserTypeConfirmButtonPopUp();

			// User creation
			createUserPage.clickOnCreateUser();
			createUserPage.clickOnSelectLocation();
			createUserPage.selectLocation();
			Assert.assertFalse(createUserPage.isExpiryDatePresent());
			createUserPage.clickOnUserAbilitiesSelect();
			createUserPage.selectUsertypeForReceiveAlerts();
			Assert.assertTrue(createUserPage.isExpiryDatePresent());
			createUserPage.clickOnManuallyAddUserLink();

			// Enter data for new user
			createUserPage.EnterDataSaferWatchUsers();
			createUserPage.clickOnSendAccessEmailCheckBox();
			createUserPage.clickOnUserListLhs();
			Assert.assertTrue(createUserPage.isCreatedUserPresent());
			String strEmail = createUserPage.getCreatedUserText();

			// Get Password from yopmail
			driver.switchTo().window(tabs.get(1));
			driver.findElement(By.cssSelector("input.scpt")).sendKeys(strEmail);
			driver.findElement(By.cssSelector("input.scpt")).sendKeys(Keys.ENTER);
			driver.switchTo().frame("ifmail");
			String strPassword = createUserPage.getYopmailPasswordText();
			driver.manage().deleteAllCookies();
			driver.close();

			// Verify the Org user with Receive Intel rights
			driver.switchTo().window(tabs.get(0));
			loginPage.clickOnUserProfile();
			loginPage.clickOnLogOutButton();
			loginPage.enterEmailAddess(strEmail);
			loginPage.enterPassword(strPassword);
			loginPage.clickOnLogInButton();
			Assert.assertEquals(loginPage.isPublicUserErrorMessagePresent(),
					"You do not have have enough rights to access this part of Application.");

			

		} catch (Exception e) {
			e.printStackTrace();
			logError("Unable to Verify org user login functionality with 'receive alerts' permission "
					+ UtilityMethods.getStackTrace());
			Assert.fail("Verify org user login functionality with 'receive alerts' permission ");
		}
	}
		
}
