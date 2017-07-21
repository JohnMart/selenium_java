package exercise.set.one;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Exercise10 {
	
	// Set the path for the local drivers.
	final static public String LOCAL_DRIVERS_PATH = "/drivers/";
	
	@Test
	public void testWaitUntilVisible() {
		// Set the path for the local Chrome driver.
		System.setProperty("webdriver.chrome.driver", this.getClass().getResource(LOCAL_DRIVERS_PATH + "chromedriver.exe").getPath());
		
		// Create an instance of the chrome driver.
		WebDriver driver = new ChromeDriver();
		
		// Navigate to the-internet website.
		driver.get("https://the-internet.herokuapp.com/");
		
		// Click the "Dynamic Loading" link.
		driver.findElement(By.linkText("Dynamic Loading")).click();
		
		// Click the "Example 1: Element on page that is hidden" link.
		driver.findElement(By.linkText("Example 1: Element on page that is hidden")).click();
		
		// Click the 'Start' button.
		driver.findElement(By.cssSelector("#start > button")).click();
		
		// Wait for the 'Hello World!' message to appear.
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#finish > h4")));
		
		// Close the webdriver.
		driver.quit();
	}
	
	@Test
	public void testWaitUntilInvisible() {
		// Set the path for the local Chrome driver.
		System.setProperty("webdriver.chrome.driver", this.getClass().getResource(LOCAL_DRIVERS_PATH + "chromedriver.exe").getPath());
		
		// Create an instance of the chrome driver.
		WebDriver driver = new ChromeDriver();
		
		// Navigate to the-internet website.
		driver.get("https://the-internet.herokuapp.com/");
		
		// Click the "Dynamic Loading" link.
		driver.findElement(By.linkText("Dynamic Loading")).click();
		
		// Click the "Example 1: Element on page that is hidden" link.
		driver.findElement(By.linkText("Example 1: Element on page that is hidden")).click();
		
		// Click the 'Start' button.
		driver.findElement(By.cssSelector("#start > button")).click();
		
		// Wait for the 'Hello World!' message to appear.
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#loading > img")));
		
		// Close the webdriver.
		driver.quit();
	}
	
	@Test
	public void testWaitUntilPresent() {
		// Set the path for the local Chrome driver.
		System.setProperty("webdriver.chrome.driver", this.getClass().getResource(LOCAL_DRIVERS_PATH + "chromedriver.exe").getPath());
		
		// Create an instance of the chrome driver.
		WebDriver driver = new ChromeDriver();
		
		// Navigate to the-internet website.
		driver.get("https://the-internet.herokuapp.com/");
		
		// Click the "Dynamic Loading" link.
		driver.findElement(By.linkText("Dynamic Loading")).click();
		
		// Click the "Example 2: Element rendered after the fact" link.
		driver.findElement(By.linkText("Example 2: Element rendered after the fact")).click();
		
		// Click the 'Start' button.
		driver.findElement(By.cssSelector("#start > button")).click();
		
		// Wait for the 'Hello World!' message to appear.
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#finish > h4")));
		
		// Close the webdriver.
		driver.quit();
	}

}
