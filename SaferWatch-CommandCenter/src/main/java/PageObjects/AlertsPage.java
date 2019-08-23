package PageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import Common.ConfigManager;
import Common.ActionMethods;

public class AlertsPage extends ActionMethods {

	
	private String alertsLhsMenu = "css:li#alertsTab";
	
	private String makeAnAlertButton ="css:.btn.btn-make-alert";
	
	
	
	
	
	

	static WebDriver driver;
	public Logger log = Logger.getLogger(AlertsPage.class);
	ConfigManager config;

	public AlertsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		config = new ConfigManager();
	}

	public boolean isAlertsLhsMenuPresent() {
		return isElementVisible(alertsLhsMenu, SHORTWAIT);
		
	}
	
	
	public boolean isMakeAnAlertButtonPresent(){
		return isElementVisible(makeAnAlertButton,SHORTWAIT);
	}
	

}
