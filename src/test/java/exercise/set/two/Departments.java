package exercise.set.two;

import homepage.LoginPage;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import admin.DepartmentsPage;
import common.TestCase;
import common.Users;

public class Departments extends TestCase {
	
	//Create instance of a local webdriver.
	private WebDriver driver = null;
	
	//Create variable for department name used in the test methods.
	private String departmentName;
	private String updatedDepartmentName;
	
	//Setup an instance of the webdriver.
	@BeforeClass(alwaysRun = true)
	@Parameters({"browser"})
	private void setupTestCase(String browser) {
		this.driver = setupWebDriver(browser);
	}

	//Test scenario to validate the inability to add a department if required fields are left blank.
	@Test
	public void testAddDepartmentRequiredFields() {
		
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
		
		//Instantiate departments page object.
		DepartmentsPage departmentsPageObject = new DepartmentsPage(driver);
		
		//Navigate to the 'Departments' page.
		Reporter.log("Navigate to the 'Departments' page.<br>");
		departmentsPageObject.clickDepartmentsLink();
		
		//Verify a successful navigation to the 'Departments' page.
		Assert.assertTrue(departmentsPageObject.verifyDepartmentsPageDisplayed(), "Unable to successfully verify the 'Departments' page is displayed; please investigate.");
		
		//Click the 'Add Department' link.
		Reporter.log("Click the 'Add Department' link.<br>");
		departmentsPageObject.clickAddDepartmentLink();
		
		//Click the 'Create Department' button.
		Reporter.log("Click the 'Create Department' button.<br>");
		departmentsPageObject.clickCreateDepartmentButton();
		
		//Verify the error message is displayed to indicate an unsuccessful department creation.
		Reporter.log("Verify an error message is displayed to indicate an unsuccessful department creation due to missing required fields.<br>");
		Assert.assertTrue(departmentsPageObject.verifyDepartmentsPageError(), "Unable to verify the department was not allowed to be successfully created without a name value; please investigate.");
	
	}
	
	//Test scenario to successfully add a department if the required fields contain valid values.
	@Test(dependsOnMethods = "testAddDepartmentRequiredFields")
	public void testAddDepartment() {
		
		//Instantiate departments page object.
		DepartmentsPage departmentsPageObject = new DepartmentsPage(driver);
		
		//Set the local department name variable to value created in departments page object.
		departmentName = departmentsPageObject.getDepartmentName();
		
		//Enter in the name value into the 'Name' field.
		Reporter.log("Enter in a name value into the 'Name' field.<br>");
		departmentsPageObject.enterDepartmentName();
		
		//Click the 'Create Department' button.
		Reporter.log("Click the 'Create Department' button.<br>");
		departmentsPageObject.clickCreateDepartmentButton();
		
		//Verify the success message is displayed to indicate a successful department creation.
		Reporter.log("Verify an success message is displayed to indicate a successful department creation.<br>");
		Assert.assertTrue(departmentsPageObject.verifyDepartmentsPageSuccessMessage(), "Unable to verify the department was successfully created; please investigate.");
		
		//Verify the newly created department exists in the list of departments.
		Reporter.log("Verify the newly created department value of [ "+ departmentName +" ] is displayed in the list of departments on the page.<br>");
		Assert.assertTrue(departmentsPageObject.verifyDepartmentInList(departmentName, true), "Unable to verify the newly created department was found in the list of departments; please investigate.");
		
	}
	
	//Test scenario to validate the inability to update a department if required fields are cleared/left blank.
	@Test(dependsOnMethods = "testAddDepartment")
	public void testUpdateDepartmentRequiredFields() {
		
		//Instantiate departments page object.
		DepartmentsPage departmentsPageObject = new DepartmentsPage(driver);
		
		//Locate the newly created department in the list of departments and select 'Edit' icon.
		Reporter.log("Click the edit icon for the department in the list.<br>");
		departmentsPageObject.clickEditDepartmentIcon(departmentName);
		
		//Clear the department name field.
		Reporter.log("Clear the 'Department Name' field.<br>");
		departmentsPageObject.clearDepartmentNameField();
		
		//Click the 'Update Department' button.
		Reporter.log("Click the 'Update Department' button.<br>");
		departmentsPageObject.clickUpdateDepartmentButton();
		
		//Verify the error message is displayed to indicate an unsuccessful department creation.
		Reporter.log("Verify an error message is displayed to indicate an unsuccessful department update due to missing required fields.<br>");
		Assert.assertTrue(departmentsPageObject.verifyDepartmentsPageError(), "Unable to verify the department was not allowed to be successfully updated without a name value; please investigate.");
	}
	
	//Test scenario to successfully update a department if the required fields contain valid values.
	@Test(dependsOnMethods = "testUpdateDepartmentRequiredFields")
	public void testUpdateDepartment() {
		
		//Instantiate departments page object.
		DepartmentsPage departmentsPageObject = new DepartmentsPage(driver);
		
		//Set the local updated department name variable to value created in departments page object.
		updatedDepartmentName = departmentsPageObject.getDepartmentName();
		
		//Enter in the name value into the 'Name' field.
		Reporter.log("Enter in a name value into the 'Name' field.<br>");
		departmentsPageObject.clearDepartmentNameField();
		departmentsPageObject.enterDepartmentName();
		
		//Click the 'Update Department' button.
		Reporter.log("Click the 'Create Department' button.<br>");
		departmentsPageObject.clickUpdateDepartmentButton();
		
		//Verify the success message is displayed to indicate an successful department update.
		Reporter.log("Verify an success message is displayed to indicate a successful department creation.<br>");
		Assert.assertTrue(departmentsPageObject.verifyDepartmentsPageSuccessMessage(), "Unable to verify the department was successfully created; please investigate.");
		
		//Verify the newly created department exists in the list of departments.
		Reporter.log("Verify the newly updated department value of [ "+ updatedDepartmentName +" ] is displayed in the list of departments on the page.<br>");
		Assert.assertTrue(departmentsPageObject.verifyDepartmentInList(updatedDepartmentName, true), "Unable to verify the newly created department was found in the list of departments; please investigate.");
		
	}
	
	//Test scenario to successfully delete a department and verify it no longer exists in the departments list.
	@Test(dependsOnMethods = "testUpdateDepartment")
	public void testDeleteDepartment() {
		
		//Instantiate departments page object.
		DepartmentsPage departmentsPageObject = new DepartmentsPage(driver);
				
		//Click the 'delete' icon for the department in the list.
		Reporter.log("Click the delete icon for the department in the list.<br>");
		departmentsPageObject.clickDeleteDepartmentIcon(updatedDepartmentName);
		
		//Confirm the pop-up to delete the department.
		Reporter.log("Confirm the deletion by accepting the javascript alert.<br>");
		departmentsPageObject.confirmDeleteDepartment();
		
		//Verify the success message is displayed to indicate an successful department deletion.
		Reporter.log("Verify an success message is displayed to indicate a successful department deletion.<br>");
		Assert.assertTrue(departmentsPageObject.verifyDepartmentsPageSuccessMessage(), "Unable to verify the department was successfully deleted; please investigate.");
		
		//Verify the department is no longer in the list of departments.
		Reporter.log("Verify the deleted department value of [ "+ updatedDepartmentName +" ] is not displayed in the list of departments on the page.<br>");
		Assert.assertTrue(departmentsPageObject.verifyDepartmentInList(updatedDepartmentName, false), "Unable to verify the newly deleted department is absent in the list of departments; please investigate.");
		
	}
}
