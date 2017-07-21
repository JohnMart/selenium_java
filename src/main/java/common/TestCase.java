package common;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;

public class TestCase {
	
	// Location for the local driver path.
	final static public String LOCAL_DRIVERS_PATH = "/drivers/";
	
	// Instantiate the webdriver.
	protected WebDriver driver = null;
	
	//Setup the webdriver instance based on browser selection.
	protected WebDriver setupWebDriver(String browser) {
		
		// Set the path to use for the local drivers.
		switch (browser) {
		default:
		case "chrome":
			// Set the path for the local Chrome driver.
			System.setProperty("webdriver.chrome.driver", this.getClass().getResource(LOCAL_DRIVERS_PATH + "chromedriver.exe").getPath());
			driver = new ChromeDriver();
			break;
		
		case "internet explorer":
		case "ie":
			// Set the path for the local Internet Explorer driver.
			System.setProperty("webdriver.ie.driver", this.getClass().getResource(LOCAL_DRIVERS_PATH + "IEDriverServer.exe").getPath());
			driver = new InternetExplorerDriver();
			break;
		
		case "microsoft edge":
		case "edge":
			// Set the path for the local Microsoft Edge driver.
			System.setProperty("webdriver.edge.driver", this.getClass().getResource(LOCAL_DRIVERS_PATH + "MicrosoftWebDriver.exe").getPath());
			driver = new EdgeDriver();
			break;
			
		}
		
		// Pass out the established driver instance.
		if (driver != null) {
			return driver;
		} else {
			throw new IllegalStateException("Driver is null.");
		}
	}
	
	@AfterClass
	public void tearDown(){
		// Quit the driver.
		if (driver != null) { driver.quit(); }
	}
}
