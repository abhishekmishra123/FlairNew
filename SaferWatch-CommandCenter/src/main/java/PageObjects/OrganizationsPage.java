package PageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import Common.ConfigManager;
import Common.ActionMethods;

public class OrganizationsPage extends ActionMethods {

	private String configurationLHSMenu = "xpath://span[text()='Configuration']";
	private String organizationsLHSMenu = "xpath://a[text()='Organizations']";
	private String addOrganizationButton = "css:.pull-md-right.pt-3.pull-right.div-add-organization";
	
	
	
	

	static WebDriver driver;
	public Logger log = Logger.getLogger(OrganizationsPage.class);
	ConfigManager config;

	public OrganizationsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		config = new ConfigManager();
	}

	public void clickOnConfigurationLHSMenu() {
    waitForElementVisible(configurationLHSMenu, SHORTWAIT);
    waitForElementClickable(configurationLHSMenu, SHORTWAIT);
    safeJavaScriptClick(configurationLHSMenu);
    waitForElementClickable(organizationsLHSMenu, SHORTWAIT);
    safeJavaScriptClick(organizationsLHSMenu);  
	}
	
	
	public boolean isAddOrganizationButtonPresent() {
		return isElementVisible(addOrganizationButton, SHORTWAIT);
		
	}
	
	

}
