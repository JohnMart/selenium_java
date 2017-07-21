package exercise.set.two;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import admin.EmployeesPage;
import common.TestCase;
import common.Users;
import homepage.HomePage;
import homepage.LoginPage;

/*
 * @Author John Martin
 * @Summary Login Test Scenarios.
 * @Date 03/13/2017
 * Change Log:
 * <Date>			<Author>		<Change Made>
 * 03/13/2017		John Martin		Initial build out of Employees related methods for User Story 1.7.
 */

public class Employees extends TestCase {
	
	//Create instance of a local webdriver.
	private WebDriver driver = null;
	
	//Setup an instance of the webdriver.
	@BeforeClass(alwaysRun = true)
	@Parameters({"browser"})
	private void setupTestCase(String browser) {
		this.driver = setupWebDriver(browser);
	}
	
	//Test scenario to validate the inability to add an employee if
	//required fields are left blank on the new employee form.
	@Test
	public void testEmployeeFormRequiredFields() {
		
		//Instantiate login page object.
		LoginPage loginPageObject = new LoginPage(driver);
		
		//Navigate to BlueSource.
		Reporter.log("Navigate to the BlueSource website.<br>");
		loginPageObject.navigatetoBlueSource();
		Assert.assertTrue(loginPageObject.verifySuccessfulNavigation(), "Unable to successfully verify navigation to BlueSource; please investigate.");
		
		//Login to the BlueSource website.
		Reporter.log("Verify a successful login to BlueSource.<br>");
		loginPageObject.login(Users.COMPANYADMIN.getUsername());
		Assert.assertTrue(loginPageObject.verifySuccessfulLogin(), "Unable to successfully verify a successful sign-in to the BlueSource website; please investigate.");
		
		//Instantiate homepage page object.
		HomePage homePageObject = new HomePage(driver);
		
		//Navigate to the 'Employees' page.
		Reporter.log("Navigate to the 'Employees' page.<br>");
		homePageObject.clickEmployeesLink();
		
		//Verify a successful navigation to the 'Employees' page.
		EmployeesPage employeesPage = new EmployeesPage(driver);
		Assert.assertTrue(employeesPage.verifyEmployeesPageDisplayed(), "Unable to successfully verify the 'Employees' page is displayed; please investigate.");
		
		//Click the 'Add' button to add a new employee.
		Reporter.log("Click the 'Add' button.<br>");
		employeesPage.clickAddEmployeeButton();
		
		//Enter data into the first name and last name fields.
		Reporter.log("Enter values into the 'first name' and 'last name' fields.<br>");
		employeesPage.enterEmployeeFirstName();
		employeesPage.enterEmployeeLastName();
		
		//Click the 'Create Employee' button.
		Reporter.log("Click the 'Create Employee' button.<br>");
		employeesPage.clickCreateEmployeeButton();
		
		//Verify the username field is focused to indicate a required field prompt.
		Reporter.log("Verify the 'username' field is focused to indicate a required field prompt.<br>");
		Assert.assertTrue(employeesPage.verifyUsernameFieldFocus(), "Unable to successfully verify the 'username' field is has focus to indicated a required field; please investigate.");
	}
	
	//Test scenario to validate the inability to add an employee if
	//invalid data is populated into the new employee form.
	@Test(dependsOnMethods = "testEmployeeFormRequiredFields")
	public void testEmployeeFormInvalidData() {
		
		//Instantiate the Employees page object.
		EmployeesPage employeesPage = new EmployeesPage(driver);
		
		//Set the 'username' field to an valid value.
		employeesPage.enterEmployeeUsername();
		
		//Set the 'Cell Phone' field to an invalid value.
		Reporter.log("Enter invalid value into the 'Cell Phone' field.<br>");
		employeesPage.enterEmployeeCellPhone();
		
		//Click the 'Create Employee' button.
		Reporter.log("Click the 'Create Employee' button.<br>");
		employeesPage.clickCreateEmployeeButton();
		
		//Verify the 'Cell Phone' field has an input format error.
		Reporter.log("Verify the 'Cell Phone' field has a format error associated with the field due to bad data.<br>");
		Assert.assertTrue(employeesPage.verifyCellPhoneFormatError(), "Unable to successfully verify the 'Cell Phone' field has a format error associated with the field; please investigate.");
	}
	
	//Test scenario for successfully adding an employee.
	@Test(dependsOnMethods = { "testEmployeeFormRequiredFields", "testEmployeeFormInvalidData" })
	public void testAddEmployee() {
		
		//Instantiate the Employees page object.
		EmployeesPage employeesPage = new EmployeesPage(driver);
				
		//Clear the 'Cell Phone' text field.
		Reporter.log("Clear the 'Cell Phone' field.<br>");
		employeesPage.clearCellPhoneField();
		
		//Select a manager from the dropdown list.
		Reporter.log("Select the employee's manager from the 'Manager' field.<br>");
		employeesPage.selectEmployeeManager();
		
		//Click the 'Create Employee' button.
		Reporter.log("Click the 'Create Employee' button.<br>");
		employeesPage.clickCreateEmployeeButton();
	
		//Verify the employee was successfully created.
		Reporter.log("Verify the employee was successfully created.<br>");
		employeesPage.verifyEmployeeCreated();
		
		//Instantiate homepage page object.
		HomePage homePageObject = new HomePage(driver);
		
		//Navigate to the 'Employees' page.
		Reporter.log("Logout of BlueSource.<br>");
		Assert.assertTrue(homePageObject.clickLogoutLink(), "Unable to successfully verify a successful sign-out to the BlueSource website; please investigate.");
	}
	
	//Test scenario to verify a successfully added employee displays in their manager's list.
	@Test(dependsOnMethods = "testAddEmployee")
	public void testEmployeeReportsToManager() {
		
		//Instantiate login page object.
		LoginPage loginPageObject = new LoginPage(driver);
		
		//Login to the BlueSource website.
		Reporter.log("Verify a successful login to BlueSource.<br>");
		loginPageObject.login(Users.MANAGER.getUsername());
		Assert.assertTrue(loginPageObject.verifySuccessfulLogin(), "Unable to successfully verify a successful sign-in to the BlueSource website; please investigate.");
		
		//Instantiate homepage page object.
		HomePage homePageObject = new HomePage(driver);
		
		//Navigate to the 'Employees' page.
		Reporter.log("Navigate to the 'Employees' page.<br>");
		homePageObject.clickEmployeesLink();
		
		//Verify a successful navigation to the 'Employee's page.
		EmployeesPage employeesPage = new EmployeesPage(driver);
		Assert.assertTrue(employeesPage.verifyEmployeesPageDisplayed(), "Unable to successfully verify the Employees page is displayed; please investigate.");
	
		//Filter the employees table by for direct reports.
		Reporter.log("Filter the 'Employees' table for direct reports.<br>");
		employeesPage.filterEmployeesTable("Direct");
		
		//Verify the newly created employee displays in their manager's direct reports.
		Reporter.log("Verify the employee is in the 'Employees' table as a direct reports.<br>");
		employeesPage.verifyEmployeeReportsToManager();
	}

}
