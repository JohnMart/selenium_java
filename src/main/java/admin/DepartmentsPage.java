package admin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.Utilities;

public class DepartmentsPage {

	//----------------------------------------------------
	//		Variable Declaration/Object Repository
	//----------------------------------------------------
	
	//Instantiate a private web driver instance.
	private WebDriver driver;
	
	//Set the department name value with an appended timestamp value.
	private final String newDepartmentName = Utilities.getPropertiesValue("Department", "name") + Utilities.getTimeStamp();
	
	//Declare a wait for the webdriver.
	WebDriverWait wait = null;
	
	//Declaration of objects.
	@FindBy(className = "caret") WebElement ddlAdmin;
	@FindBy(linkText = "Departments") WebElement lnkDepartments;
	@FindBy(css = "#content > h1") WebElement icoDepartments;
	@FindBy(linkText = "Add Department") WebElement lnkAddDepartment;
	@FindBy(id = "department_name") WebElement txtDepartmentName;
	@FindBy(xpath = "//*[@id='new_department']/div[6]/input") WebElement btnCreateDepartment;
	@FindBy(css = "#content > div.alert.alert-success.alert-dismissable") WebElement eleSuccessMessage;
	@FindBy(css = "#content > div.alert.alert-danger.alert-dismissable") WebElement eleFailMessage;
	@FindBy(name = "commit") WebElement btnUpdateDepartment;
	
	//----------------------------------------------------
	//				Class Constructor
	//----------------------------------------------------
	public DepartmentsPage(WebDriver driver) {
		//Declares the driver passed in via argument
		//as the local driver to initialize all the page objects.
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		//Setup the wait object for the entire class.
		wait = new WebDriverWait(driver, 20);
	}
	
	//----------------------------------------------------
	//			Methods, Actions, & Calculations
	//----------------------------------------------------
	
	//Getter method for the department name value.
	public String getDepartmentName() {
		return newDepartmentName;
	}
	
	//Click the 'Departments' link.
	public void clickDepartmentsLink() {
		//Click the 'Admin' dropdown button.
		ddlAdmin.click();
		
		//Select the 'Departments' link from the dropdown.
		lnkDepartments.click();
	}
	
	//Clear the department name field.
	public void clearDepartmentNameField() {
		txtDepartmentName.clear();
	}
	
	//Click the 'Add Department' link.
	public void clickAddDepartmentLink() {
		lnkAddDepartment.click();
	}
	
	//Click the 'Update Department' button.
	public void clickUpdateDepartmentButton() {
		btnUpdateDepartment.click();
	}
	
	//Click the 'Create Department' button.
	public void clickCreateDepartmentButton() {
		btnCreateDepartment.click();
	}
	
	//Verify the 'Departments' header exists.
	public boolean verifyDepartmentsPageDisplayed() {
		wait.until(ExpectedConditions.visibilityOf(icoDepartments));
		if (icoDepartments.getText().equals("Departments")) {
			return true;
		} else {
			return false;
		}
	}
	
	//Verify an error existing on the 'Departments' page.
	public boolean verifyDepartmentsPageSuccessMessage() {
		if (eleSuccessMessage.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}
	
	//Verify an error existing on the 'Departments' page.
	public boolean verifyDepartmentsPageError() {
		if (eleFailMessage.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}
	
	//Verify a department is or is not listed in the department list on the page.
	public boolean verifyDepartmentInList(String departmentName, Boolean exist) {
		Boolean isPresent = false;
		
		//Pull all the department values into a list.
		List<WebElement> departments = driver.findElements(By.className("list-group-item"));
		
		//Verify if the department name exists in the department list on the page.
		for (WebElement department : departments) {
			if (department.getText().trim().contains(departmentName)) {
				isPresent = true;
				break;
			}
		}
		
		if (exist) {
			if (isPresent) {
				return true;
			} else {
				return false;
			}
		} else {
			if (!isPresent) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	//Click the edit icon for a department in the list of departments.
	public void clickEditDepartmentIcon(String departmentName) {
		Boolean isFound = false;
		
		//Pull all the department values into a list.
		List<WebElement> departments = driver.findElements(By.className("list-group-item"));
		
		//Verify if the department name exists in the department list on the page.
		for (WebElement department : departments) {
			if (department.getText().trim().contains(departmentName)) {

				//Pull all of the links inside of the department container.
				List<WebElement> links = department.findElements(By.tagName("a"));
				
				//Loop through and find the link that contains 'edit' and click it.
				for (WebElement link : links) {
					if (link.getAttribute("href").contains("edit")) {
						link.click();
						isFound = true;
						break;
					}
				}
			}
			
			//Exit the outer loop if we found our edit link.
			if (isFound) {
				break;
			}
			
		}		
	}
	
	//Click the delete icon for a department in the list of departments.
	public void clickDeleteDepartmentIcon(String departmentName) {
		Boolean isFound = false;
		
		//Pull all the department values into a list.
		List<WebElement> departments = driver.findElements(By.className("list-group-item"));
		
		//Verify if the department name exists in the department list on the page.
		for (WebElement department : departments) {
			if (department.getText().trim().contains(departmentName)) {
			
				//Pull all of the links inside of the department container.
				List<WebElement> icons = department.findElements(By.tagName("span"));
				
				//Loop through and find the 'delete' icon and click it.
				for (WebElement icon : icons) {
					if (icon.getAttribute("class").contains("trash")) {
						icon.click();
						isFound = true;
						break;
					}
				}
			}
			
			//Exit the outer loop if we found our delete icon.
			if (isFound) {
				break;
			}
			
		}		
	}
	
	//Confirm the deletion on the javascript pop-up window.
	public void confirmDeleteDepartment() {
		driver.switchTo().alert().accept();
	}
	
	//Enter the department name value on the 'New Department' page.
	public void enterDepartmentName() {
		wait.until(ExpectedConditions.visibilityOf(txtDepartmentName));
		txtDepartmentName.sendKeys(newDepartmentName);
	}

	
}
