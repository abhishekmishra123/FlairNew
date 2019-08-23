package PageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import Common.ConfigManager;
import Common.ActionMethods;

public class IntelPage extends ActionMethods {

	
	private String intelLhsMenu = "css:a.has-arrow.waves-effect.waves-dark.active";
	
	
	
	

	static WebDriver driver;
	public Logger log = Logger.getLogger(IntelPage.class);
	ConfigManager config;

	public IntelPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		config = new ConfigManager();
	}

	public boolean isIntelLhsMenuPresent() {
		return isElementVisible(intelLhsMenu, SHORTWAIT);
		
	}
	
	

}
