package exercise.set.one;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Exercise7 {
	
	// Set the path for the local drivers.
	final static public String LOCAL_DRIVERS_PATH = "/drivers/";

	@Test
	public void runExercise7() {
		// Set the path for the local Chrome driver.
		System.setProperty("webdriver.chrome.driver", this.getClass().getResource(LOCAL_DRIVERS_PATH + "chromedriver.exe").getPath());
		
		// Create an instance of the chrome driver.
		WebDriver driver = new ChromeDriver();
		
		// Navigate to the-internet website.
		driver.get("http://www.w3schools.com/html/html_forms.asp");
		
		// Select an option from the radio button.
		driver.findElement(By.xpath("//*[@id='main']/input[4]")).click();
		
		// Verify the 'Female' radio button is successfully selected.
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='main']/input[4]")).isSelected(), "Unable to verify the 'Female' radio button was checked.");
		
		// Close the webdriver.
		driver.quit();
	}
}
