package admin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.Utilities;

/*
 * @Author John Martin
 * @Summary Employees Page Object Class - all objects and methods.
 * @Date 03/15/2017
 * Change Log:
 * <Date>			<Author>		<Change Made>
 * 03/27/2017		John M.			Build out of methods for AC 1.7.
 */

public class EmployeesPage {
	
	//----------------------------------------------------
	//		Variable Declaration/Object Repository
	//----------------------------------------------------
	
	//Instantiate a private web driver instance.
	private WebDriver driver;
	
	//Set the employee username, firt name and last name values with an appended timestamp value.
	private final String newEmployeeUsername = Utilities.getPropertiesValue("Employee", "username") + Utilities.getTimeStamp();
	private final String newEmployeeFirstName = Utilities.getPropertiesValue("Employee", "firstname") + Utilities.getTimeStamp();
	private final String newEmployeeLastName = Utilities.getPropertiesValue("Employee", "lastname") + Utilities.getTimeStamp();
	
	//Declare a wait for the webdriver.
	WebDriverWait wait = null;
	
	//Declaration of objects.
	@FindBy(xpath = "//*[@id='all-content']/div[2]/div/div[2]/button") WebElement btnAdd;
	@FindBy(id = "employee_username") WebElement txtUsername;
	@FindBy(id = "employee_first_name") WebElement txtFirstName;
	@FindBy(id = "employee_last_name") WebElement txtLastName;
	@FindBy(id = "employee_cell_phone") WebElement txtCellPhone;
	@FindBy(css = "#new_employee > div.form-group.modal-footer > input") WebElement btnCreateEmployee;
	@FindBy(css = "#resource-content > div.table-responsive > table") WebElement tblEmployees;
	@FindBy(css = "#all-content > div.alert.alert-success.alert-dismissable") WebElement eleSuccessMessage;
	@FindBy(id = "filter_btn") WebElement btnTableFilter;
	
	private By ddlManagerID = By.id("employee_manager_id");
	
	//----------------------------------------------------
	//				Class Constructor
	//----------------------------------------------------
	public EmployeesPage(WebDriver driver) {
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
	
	//Verify the 'Add' button to add an employee exists.
	public boolean verifyEmployeesPageDisplayed() {
		wait.until(ExpectedConditions.visibilityOf(tblEmployees));
		if (tblEmployees.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}
	
	//Click the 'Add' button to add an employee.
	public void clickAddEmployeeButton() {
		wait.until(ExpectedConditions.elementToBeClickable(btnAdd));
		btnAdd.click();
	}
	
	//Click the 'Create Employee' button to add an employee.
	public void clickCreateEmployeeButton() {
		btnCreateEmployee.click();
	}
	
	//Enter the username value on the 'Add Employee' form.
	public void enterEmployeeUsername() {
		txtUsername.sendKeys(newEmployeeUsername);
	}
	
	//Enter the first name value on the 'Add Employee' form.
	public void enterEmployeeFirstName() {
		wait.until(ExpectedConditions.visibilityOf(txtFirstName));
		txtFirstName.sendKeys(newEmployeeFirstName);
	}
	
	//Enter the last name value on the 'Add Employee' form.
	public void enterEmployeeLastName() {
		txtLastName.sendKeys(newEmployeeLastName);
	}
	
	//Enter the cell phone value on the 'Add Employee' form.
	public void enterEmployeeCellPhone() {
		txtCellPhone.sendKeys(Utilities.getPropertiesValue("Employee", "cellphone"));
	}
	
	//Clears the 'Cell Phone' field on the 'Add Employee' form.
	public void clearCellPhoneField() {
		txtCellPhone.clear();
	}
	
	//Select the manager value on the 'Add Employee' form.
	public void selectEmployeeManager() {
		Select ddlManager = new Select(driver.findElement(ddlManagerID));
		ddlManager.selectByValue(Utilities.getPropertiesValue("Employee", "managervalue"));
	}
	
	//Select the 'Direct' filter option for the 'Employees' table.
	public void filterEmployeesTable(String filterOption) {
		//Click the filter button.
		btnTableFilter.click();
		
		//Build a list of all the filter options to select one.
		List<WebElement> filterOptions = driver.findElements(By.className("filter_list_item"));
		
		for (WebElement option : filterOptions) {
			if (option.getText().trim().equals(filterOption)) {
				option.click();
			}
		}
	}
	
	//Verifies if the 'username' field on the 'Add Employee' form has focus.
	public boolean verifyUsernameFieldFocus() {
		if (txtUsername.equals(driver.switchTo().activeElement())) {
			return true;
		} else {
			return false;
		}
	}

	//Verifies if the 'cell phone' field on the 'Add Employee' form contains formatting issues.
	public boolean verifyCellPhoneFormatError() {
		if (txtCellPhone.getAttribute("class").contains("has-error")) {
			return true;
		} else {
			return false;
		}
	}
	
	//Verifies if the employee was created successfully.
	public boolean verifyEmployeeCreated() {
		if (eleSuccessMessage.getText().trim().equals("Employee successfully created.")) {
			return true;
		} else {
			return false;
		}
	}
	
	//Verify an employee displays in a manager's direct reports.
	public boolean verifyEmployeeReportsToManager() {
		Boolean isPresent = false;
		
		//Pull all the rows in the 'Employees' table.
		List<WebElement> rows = tblEmployees.findElements(By.tagName("tr"));
		
		//Loop through all the rows, pull the cell values, verify if the newly created
		//employee exists in one of the rows.
		for (WebElement row : rows) {
			//Pull all the cells in the row.
			List<WebElement> cells = row.findElements(By.tagName("td"));
			
			//Loop through all the cells in the selected row.
			for (WebElement cell : cells) {
				//Check to see if the name value matches the first name.
				if (cell.getText().trim().equals(newEmployeeFirstName)) {
					isPresent = true;
					break;
				}
			}
			
			//Exit the rows loop if we found the first name value.
			if (isPresent) {
				break;
			}
		}
		
		//Pass out a value based on whether the record was found.
		if (isPresent) {
			return true;
		} else {
			return false;
		}
	}
	
	//Wrapper method for filling out required fields on 'Add Employee' form.
	public void populateAddEmployeeForm() {
		enterEmployeeUsername();
		enterEmployeeFirstName();
		enterEmployeeLastName();
	}

}
