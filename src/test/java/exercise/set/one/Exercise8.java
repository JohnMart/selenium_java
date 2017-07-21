package exercise.set.one;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class Exercise8 {
	
	// Set the path for the local drivers.
	final static public String LOCAL_DRIVERS_PATH = "/drivers/";
	
	@Test
	public void runExercise8() {
		// Set the path for the local Chrome driver.
		System.setProperty("webdriver.chrome.driver", this.getClass().getResource(LOCAL_DRIVERS_PATH + "chromedriver.exe").getPath());
		
		// Create an instance of the chrome driver.
		WebDriver driver = new ChromeDriver();
		
		// Navigate to the-internet website.
		driver.get("https://the-internet.herokuapp.com/");
		
		// Click the "Sortable Data Tables" link.
		driver.findElement(By.linkText("Sortable Data Tables")).click();
		
		//Exercise 8: c
		// Print out the text of the first cell in the first row.
		String firstColumnValue = driver.findElement(By.xpath("//*[@id='table1']/thead/tr/th[1]/span")).getText();
		
		// Report out the first column header value.
		System.out.println("The first column header value of the first table is: [ " + firstColumnValue + " ].");
		Reporter.log("The first column header value of the first table is: [ " + firstColumnValue + " ].<br>");
		
		//Exercise 8: d
		// Store the first row of cells in the firstTable.
		List<WebElement> firstRow = driver.findElement(By.xpath("//*[@id='table1']/thead/tr")).findElements(By.tagName("th"));
		
		// Loop through the first row of cells in the table, print out their text.
		for (int x = 0; firstRow.size() > x; x++) {
			String cellText = firstRow.get(x).getText();
			System.out.println("The cell value of [ "+ (x + 1) +" ] contains the following text: [ " + cellText + " ].");
			Reporter.log("The cell value of [ "+ (x + 1) +" ] contains the following text: [ " + cellText + " ].<br>");
		}
		
		//Exercise 8: e
		// Store the table for interaction.
		WebElement firstTable = driver.findElement(By.id("table1"));
		
		// Store all the rows in firstTable.
		List<WebElement> firstTableRows = firstTable.findElements(By.tagName("tr"));
		
		// Declare row counter & found flag.
		int rowNumber = 1;
		boolean isFound = false;
					
		// Loop through the first table to find the row number of row that contains the cell with text ‘jdoe@hotmail.com’.
		for (WebElement row : firstTableRows) {
			// Store all the cells in the row.
			List<WebElement> cells = row.findElements(By.tagName("td"));
			
			// Loop through the cells to find the cell that contains ‘jdoe@hotmail.com’.
			for (WebElement cell : cells) {
				if (cell.getText().trim().equals("jdoe@hotmail.com")) {
					isFound = true;
					System.out.println("The row value of [ "+ rowNumber +" ] contains the following text: [ " + cell.getText() + " ].");
					Reporter.log("The cell value of [ "+ rowNumber +" ] contains the following text: [ " + cell.getText() + " ].<br>");
					
					//Exercise 8: f
					// Click the delete/trash link for the row that contains the text ‘jdoe@hotmail.com’.
					// If found, loop through the already stored row to find the delete link.
					if (isFound) {
						for (WebElement othercell : cells) {
							// If the cell value contains "delete" in it, find the 'delete' link.
							if (othercell.getText().trim().contains("delete")) {
								// Set and click the 'delete' link.
								WebElement deleteLink = othercell.findElement(By.linkText("delete"));
								deleteLink.click();
								
								// Verify the URL contains the '#delete' phrase to
								// indicate the link was successfully clicked.
								String url = driver.getCurrentUrl();
								Assert.assertTrue(url.trim().contains("#delete"), "Unable to successfully verify the 'delete' link was clicked.");
								System.out.println("Successfully clicked the 'delete' link on row [ "+ rowNumber +" ].");
								Reporter.log("Successfully clicked the 'delete' link on row [ "+ rowNumber +" ].<br>");
								break;
							}
						}
					}
				}
			}
			
			// Increment the row number counter.
			rowNumber++;
		}
		
		// Verify the cell value of ‘jdoe@hotmail.com’ was found.
		Assert.assertTrue(isFound, "Unable to successfully find the table entry of ‘jdoe@hotmail.com’ in [ "+ rowNumber +" ] rows; please verify.");
		
		// Close the webdriver.
		driver.quit();
	}
}
