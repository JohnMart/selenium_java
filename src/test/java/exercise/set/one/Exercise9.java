package exercise.set.one;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Exercise9 {
	
	// Set the path for the local drivers.
	final static public String LOCAL_DRIVERS_PATH = "/drivers/";
	
	@Test
	public void successfulLogin() {
		// Set the path for the local Chrome driver.
		System.setProperty("webdriver.chrome.driver", this.getClass().getResource(LOCAL_DRIVERS_PATH + "chromedriver.exe").getPath());
		
		// Create an instance of the chrome driver.
		WebDriver driver = new ChromeDriver();
		
		// Navigate to the-internet website.
		driver.get("https://the-internet.herokuapp.com/");
		
		// Click the "Form Authentication" link.
		driver.findElement(By.linkText("Form Authentication")).click();
		
		// Set the 'username' textbox value.
		driver.findElement(By.id("username")).sendKeys("tomsmith");
		
		// Set the 'password' textbox value.
		driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
		
		// Click the 'Login' button.
		driver.findElement(By.className("radius")).click();
		
		// Verify if the success message is displayed.
		Assert.assertTrue(driver.findElement(By.cssSelector(".flash.success")).isDisplayed(), "Unable to verify the successful login message is displayed.");
		
		// Close the webdriver.
		driver.quit();
	}
	
	@Test
	public void unsuccessfulLogin() {
		// Set the path for the local Chrome driver.
		System.setProperty("webdriver.chrome.driver", this.getClass().getResource(LOCAL_DRIVERS_PATH + "chromedriver.exe").getPath());
		
		// Create an instance of the chrome driver.
		WebDriver driver = new ChromeDriver();
		
		// Navigate to the-internet website.
		driver.get("https://the-internet.herokuapp.com/");
		
		// Click the "Form Authentication" link.
		driver.findElement(By.linkText("Form Authentication")).click();
		
		// Set the 'username' textbox value.
		driver.findElement(By.id("username")).sendKeys("tomsmith");
		
		// Set the 'password' textbox value.
		driver.findElement(By.id("password")).sendKeys("WrongPassword!");
		
		// Click the 'Login' button.
		driver.findElement(By.className("radius")).click();
		
		// Verify if the success message is displayed.
		Assert.assertTrue(driver.findElement(By.cssSelector(".flash.error")).isDisplayed(), "Unable to verify the unsuccessful login message is displayed.");
		
		// Close the webdriver.
		driver.quit();
	}
}
