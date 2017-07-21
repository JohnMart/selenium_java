package exercise.set.two;

import homepage.LoginPage;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import admin.TitlesPage;
import common.TestCase;
import common.Users;

public class Titles extends TestCase {

	//Create instance of a local webdriver.
	private WebDriver driver = null;
	
	//Create variable for department name used in the test methods.
	private String title;
	private String updatedTitle;
	
	//Setup an instance of the webdriver.
	@BeforeClass(alwaysRun = true)
	@Parameters({"browser"})
	private void setupTestCase(String browser) {
		this.driver = setupWebDriver(browser);
	}
	
	//Test scenario to validate the inability to add a title if required fields are left blank.
	@Test
	public void testAddTitleRequiredFields() {
		
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
		
		//Instantiate titles page object.
		TitlesPage titlesPageObject = new TitlesPage(driver);
		
		//Navigate to the 'Titles' page.
		Reporter.log("Navigate to the 'Titles' page.<br>");
		titlesPageObject.clickTitlesLink();
		
		//Verify a successful navigation to the 'Titles' page.
		Assert.assertTrue(titlesPageObject.verifyTitlesPageDisplayed(), "Unable to successfully verify the 'Titles' page is displayed; please investigate.");
		
		//Click the 'New Title' button.
		Reporter.log("Click the 'New Title' link.<br>");
		titlesPageObject.clickNewTitleLink();
		
		//Click the 'Create Title' button.
		Reporter.log("Click the 'Create Title' button.<br>");
		titlesPageObject.clickCreateTitleButton();
		
		//Verify the error message is displayed to indicate an unsuccessful title creation.
		Reporter.log("Verify an error message is displayed to indicate an unsuccessful title creation due to missing required fields.<br>");
		Assert.assertTrue(titlesPageObject.verifyTitlesPageError(), "Unable to verify the title was not allowed to be successfully created without a name value; please investigate.");
	
	}
	
	//Test scenario to successfully add a title if the required fields contain valid values.
	@Test(dependsOnMethods = "testAddTitleRequiredFields")
	public void testAddTitle() {
		
		//Instantiate titles page object.
		TitlesPage titlesPageObject = new TitlesPage(driver);
		
		//Set the local title name variable to value created in titles page object.
		title = titlesPageObject.getTitleName();
		
		//Enter in the name value into the 'Name' field.
		Reporter.log("Enter in a name value into the 'Name' field.<br>");
		titlesPageObject.enterTitleName();
		
		//Click the 'Create Title' button.
		Reporter.log("Click the 'Create Title' button.<br>");
		titlesPageObject.clickCreateTitleButton();
		
		//Verify the success message is displayed to indicate a successful title creation.
		Reporter.log("Verify an success message is displayed to indicate a successful title creation.<br>");
		Assert.assertTrue(titlesPageObject.verifyTitlesPageSuccessMessage(), "Unable to verify the title was successfully created; please investigate.");
		
		//Verify the newly created title exists in the list of titles.
		Reporter.log("Verify the newly created title value of [ "+ title +" ] is displayed in the list of titles on the page.<br>");
		Assert.assertTrue(titlesPageObject.verifyTitleInList(title, true), "Unable to verify the newly created title  was found in the list of titles; please investigate.");
		
	}
	
	//Test scenario to validate the inability to update a title if required fields are cleared/left blank.
	@Test(dependsOnMethods = "testAddTitle")
	public void testUpdateTitleRequiredFields() {
		
		//Instantiate titles page object.
		TitlesPage titlesPageObject = new TitlesPage(driver);
		
		//Locate the newly created title in the list of titles and select 'Edit' icon.
		Reporter.log("Click the edit icon for the title in the list.<br>");
		titlesPageObject.clickEditTitleIcon(title);
		
		//Clear the title name field.
		Reporter.log("Clear the 'Title Name' field.<br>");
		titlesPageObject.clearTitleNameField();
		
		//Click the 'Update Title' button.
		Reporter.log("Click the 'Update Title' button.<br>");
		titlesPageObject.clickUpdateTitleButton();
		
		//Verify the error message is displayed to indicate an unsuccessful title update.
		Reporter.log("Verify an error message is displayed to indicate an unsuccessful title update due to missing required fields.<br>");
		Assert.assertTrue(titlesPageObject.verifyTitlesPageError(), "Unable to verify the title was not allowed to be successfully updated without a name value; please investigate.");
	}
	
	//Test scenario to successfully update a department if the required fields contain valid values.
	@Test(dependsOnMethods = "testUpdateTitleRequiredFields")
	public void testUpdateTitle() {
		
		//Instantiate titles page object.
		TitlesPage titlesPageObject = new TitlesPage(driver);
		
		//Set the local updated title name variable to value created in titles page object.
		updatedTitle = titlesPageObject.getTitleName();
		
		//Enter in the name value into the 'Name' field.
		Reporter.log("Enter in a name value into the 'Name' field.<br>");
		titlesPageObject.clearTitleNameField();
		titlesPageObject.enterTitleName();
		
		//Click the 'Update Title' button.
		Reporter.log("Click the 'Update Title' button.<br>");
		titlesPageObject.clickUpdateTitleButton();
		
		//Verify the success message is displayed to indicate an successful title update.
		Reporter.log("Verify an success message is displayed to indicate a successful title update.<br>");
		Assert.assertTrue(titlesPageObject.verifyTitlesPageSuccessMessage(), "Unable to verify the title was successfully updated; please investigate.");
		
		//Verify the newly created title exists in the list of titles.
		Reporter.log("Verify the newly updated title value of [ "+ updatedTitle +" ] is displayed in the list of titles on the page.<br>");
		Assert.assertTrue(titlesPageObject.verifyTitleInList(updatedTitle, true), "Unable to verify the newly updated title was found in the list of titles; please investigate.");
		
	}
	
	//Test scenario to successfully delete a title and verify it no longer exists in the titles list.
	@Test(dependsOnMethods = "testUpdateTitle")
	public void testDeleteTitle() {
		
		//Instantiate titles page object.
		TitlesPage titlesPageObject = new TitlesPage(driver);
				
		//Click the 'delete' icon for the title in the list.
		Reporter.log("Click the delete icon for the title in the list.<br>");
		titlesPageObject.clickDeleteTitleIcon(updatedTitle);
		
		//Confirm the pop-up to delete the department.
		Reporter.log("Confirm the deletion by accepting the javascript alert.<br>");
		titlesPageObject.confirmDeleteTitle();
		
		//Verify the success message is displayed to indicate an successful title deletion.
		Reporter.log("Verify an success message is displayed to indicate a successful title deletion.<br>");
		Assert.assertTrue(titlesPageObject.verifyTitlesPageSuccessMessage(), "Unable to verify the title was successfully deleted; please investigate.");
		
		//Verify the title is no longer in the list of titles.
		Reporter.log("Verify the deleted title value of [ "+ updatedTitle +" ] is not displayed in the list of titles on the page.<br>");
		Assert.assertTrue(titlesPageObject.verifyTitleInList(updatedTitle, false), "Unable to verify the newly deleted title is absent in the list of titles; please investigate.");
		
	}
	
}
