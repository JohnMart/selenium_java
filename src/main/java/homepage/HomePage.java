package homepage;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import common.Utilities;

/*
 * @Author John Martin
 * @Summary Login Page Object Class - all objects and methods.
 * @Date 02/28/2017
 * Change Log:
 * <Date>			<Author>		<Change Made>
 */

public class HomePage {
	
	//----------------------------------------------------
	//		Variable Declaration/Object Repository
	//----------------------------------------------------
	
	//Instantiate a private web driver instance.
	private WebDriver driver;
	
	//Declaration of objects.
	@FindBy(linkText = "Departments") WebElement lnkDepartments;
	@FindBy(linkText = "Titles") WebElement lnkTitles;
	@FindBy(linkText = "Employees") WebElement lnkEmployees;
	@FindBy(xpath = "//*[@id='accordion']/div/div[3]/h4") WebElement eleTimeOffHeader;
	@FindBy(xpath ="//*[@id='accordion']/div/div[5]/h4") WebElement eleGeneralInfoHeader;
	@FindBy(linkText = "Logout") WebElement lnkLogout;
	
	//----------------------------------------------------
	//				Class Constructor
	//----------------------------------------------------
	public HomePage(WebDriver driver) {
		//Declares the driver passed in via argument
		//as the local driver to initialize all the page objects.
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}

	//----------------------------------------------------
	//			Methods, Actions, & Calculations
	//----------------------------------------------------
	
	//Click the 'Departments' link.
	public void clickDepartmentsLink() {
		lnkDepartments.click();
	}
	
	//Click the 'Titles' link.
	public void clickTitlesLink() {
		lnkTitles.click();
	}
	
	//Click the 'Employees' link.
	public void clickEmployeesLink() {
		lnkEmployees.click();
	}
	
	//Click the 'Logout' link.
	public boolean clickLogoutLink() {
		lnkLogout.click();
		try {
			if (!eleGeneralInfoHeader.isDisplayed()) {
				return true;
			} else {
				return false;
			}
		} catch (NoSuchElementException e) {
			return true;
		}
	}
	
	//Verify the 'Time Off Info' section is displayed.
	public boolean verifyTimeOffSectionDisplayed() {
		boolean isPresent = false;
		
		try {
			isPresent = Utilities.verifyObjectIsDisplayed(driver, eleTimeOffHeader);
		} catch(NoSuchElementException e) {
			Reporter.log("Unable to verify the 'Time Off Info' section is displayed on the Homepage.<br>");
		}
		
		return isPresent;
	}
	
	//Verify the 'General Information' section is displayed.
	public boolean verifyGeneralInfoSectionDisplayed() {
		boolean isPresent = false;
		
		try {
			isPresent = Utilities.verifyObjectIsDisplayed(driver, eleGeneralInfoHeader);
		} catch(NoSuchElementException e) {
			Reporter.log("Unable to verify the 'General Info' section is displayed on the Homepage.<br>");
		}
		
		return isPresent;
	}
}
