package exercise.set.two;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

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
 * 03/13/2017		John Martin		Initial build out of SuccessfulLogin and UnsuccessfulLogin scenarios.
 */

public class Login extends TestCase {
	
	//Create instance of a local webdriver.
	private WebDriver driver = null;
	
	//Setup an instance of the webdriver.
	@BeforeClass(alwaysRun = true)
	@Parameters({"browser"})
	private void setupTestCase(String browser) {
		this.driver = setupWebDriver(browser);
	}
	
	//Test scenario for a successful login.
	@Test
	public void testSuccessfulLogin() {
		
		//Instantiate login page object.
		LoginPage loginPageObject = new LoginPage(driver);
		
		//Navigate to BlueSource.
		Reporter.log("Navigate to the BlueSource website.<br>");
		loginPageObject.navigatetoBlueSource();
		Assert.assertTrue(loginPageObject.verifySuccessfulNavigation(), "Unable to successfully verify navigation to BlueSource; please investigate.");
		
		//Login to the BlueSource website.
		Reporter.log("Verify a successful login to BlueSource.<br>");
		loginPageObject.login(Users.BASE.getUsername());
		Assert.assertTrue(loginPageObject.verifySuccessfulLogin(), "Unable to successfully verify a successful sign-in to the BlueSource website; please investigate.");
		
		//Instantiate homepage page object.
		HomePage homePageObject = new HomePage(driver);
				
		//Verify the employee page paid time off section is displayed.
		Reporter.log("Verify the 'Time Off Info' section is displayed on the home page.<br>");
		Assert.assertTrue(homePageObject.verifyTimeOffSectionDisplayed(), "Unable to successfully verify the 'Time Off Info' section on the home page; please investigate.");
		
		//Verify the general information section is displayed.
		Reporter.log("Verify the 'General Info' section is displayed on the home page.<br>");
		Assert.assertTrue(homePageObject.verifyTimeOffSectionDisplayed(), "Unable to successfully verify the 'General Info' section on the home page; please investigate.");
		
		//Logout of the BlueSource website.
		Reporter.log("Logout of BlueSource.<br>");
		loginPageObject.logout();
		Assert.assertTrue(loginPageObject.verifySuccessfulLogout(), "Unable to verify a successful logout; please investigate.");
	}
	
	//Test scenario for an unsuccessful login.
	@Test
	public void testUnsuccessfulLogin() {
		//Instantiate login page object.
		LoginPage loginPageObject = new LoginPage(driver);
		
		//Navigate to BlueSource.
		Reporter.log("Successfully navigated to the BlueSource website.<br>");
		loginPageObject.navigatetoBlueSource();
		Assert.assertTrue(loginPageObject.verifySuccessfulNavigation(), "Unable to successfully verify navigation to BlueSource; please investigate.");
		
		//Login to the BlueSource website.
		Reporter.log("Verify an unsuccessful login to BlueSource.<br>");
		loginPageObject.login(Users.FAKEUSER.getUsername());
		Assert.assertFalse(loginPageObject.verifySuccessfulLogin(), "Successful sign-in detected when expected an unsuccessful login to the BlueSource website; please investigate.");
	}

}
