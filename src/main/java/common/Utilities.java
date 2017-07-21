package common;

import java.util.Base64;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Utilities {
	
	//Verify an object loaded/is displayed.
	public static Boolean verifyObjectIsDisplayed(WebDriver driver, WebElement object) {

		// Variable declaration to be used in the for loop.
		boolean isLoaded = false;
		int waitCount;
		int waitCountCap = 10;
		
		// Loop statement to wait for an object to display.
		for (waitCount = 0; waitCountCap > waitCount; waitCount++) {

			// Check to see if the object is displayed.
			if (object.isDisplayed()) {
				isLoaded = true;
				break;
			} else {
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			}
		}
		
		return isLoaded;
	}
	
	//Decode and return an incoming Base64 string.
	public static String decodeString(String stringToDecode) {
		// Decode an incoming string to bytes.
		byte[] decodedBytes = Base64.getDecoder().decode(stringToDecode);
		
		// Convert the decoded bytes back into a string.
		String decodedString = new String(decodedBytes);
		
		return decodedString;
	}
	
	//Set a field value to a decoded Base64 string.
	public static void setSecure(WebElement field, String value) {
		field.sendKeys(decodeString(value));
	}
	
	//Retrieve a property value from the Environment properties file.
	public static String getEnvironmentValue(String key) {
		// Retrieve the values in the Environment properties file.
		ResourceBundle envFile = ResourceBundle.getBundle("Environment");
		
		// Return the key value.
		return envFile.getString(key);
	}
	
	//Retrieve a property value from a specified properties file.
	public static String getPropertiesValue(String filename, String key) {
		// Retrieve the values in the Environment properties file.
		ResourceBundle propFile = ResourceBundle.getBundle(filename);
		
		// Return the key value.
		return propFile.getString(key);
	}

	//Select a value from a dropdown list or listbox element.
	public static void selectListBoxOption(WebElement listbox, String option) {
		Select listBoxObject = new Select(listbox);
		try {
			listBoxObject.selectByVisibleText(option);
		} catch(Exception e) {
			System.out.println("An exception has occurred in: 'selectListBoxOption' method.");
		}
	}
	
	//Create time stamp method.
    public static String getTimeStamp() {
        java.util.Date timeStamp = new java.util.Date();
        Long timeStampPrint = timeStamp.getTime();
        return timeStampPrint.toString();
    }
    
	//Hard wait function.
	public static void waiting(long milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			System.out.println("Unable to wait for [ " + milliseconds + " ] milliseconds; an exception occured.");
		}
	}
	
}
