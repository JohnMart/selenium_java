package exercise.set.one;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class Exercise6 {
	
	// Set the path for the local drivers.
	final static public String LOCAL_DRIVERS_PATH = "/drivers/";
	
	@Test
	public void runExercise6() {
		// Set the path for the local Chrome driver.
		System.setProperty("webdriver.chrome.driver", this.getClass().getResource(LOCAL_DRIVERS_PATH + "chromedriver.exe").getPath());
		
		// Create an instance of the chrome driver.
		WebDriver driver = new ChromeDriver();
		
		// Navigate to the-internet website.
		driver.get("https://the-internet.herokuapp.com/");
		
		// Click the "Dropdown" link.
		driver.findElement(By.linkText("Dropdown")).click();
		
		// Create the dropdown listbox element.
		Select dropdown = new Select(driver.findElement(By.id("dropdown")));
		
		// Select an option by Index value.
		dropdown.selectByIndex(1);
		
		// Select an option by Value.
		dropdown.selectByValue("2");
		
		// Select an  option by Visible Text.
		dropdown.selectByVisibleText("Option 1");
		
		// Close the webdriver.
		driver.quit();
	}
	
}
