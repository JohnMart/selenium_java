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

public class LoginPage {
	
	//----------------------------------------------------
	//		Variable Declaration/Object Repository
	//----------------------------------------------------
	
	//Instantiate a private web driver instance.
	private WebDriver driver;
	
	//Declaration of objects.
	@FindBy(id = "employee_username") WebElement txtUsername;
	@FindBy(id = "employee_password") WebElement txtPassword;
	@FindBy(name = "commit") WebElement btnLogin;
	@FindBy(id = "help-btn") WebElement btnHelp;
	@FindBy(linkText = "Logout") WebElement lnkLogout;
	
	//----------------------------------------------------
	//				Class Constructor
	//----------------------------------------------------
	public LoginPage(WebDriver driver) {
		//Declares the driver passed in via argument
		//as the local driver to initialize all the page objects.
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//----------------------------------------------------
	//			Methods, Actions, & Calculations
	//----------------------------------------------------
	
	//Navigate to BlueSource.
	public void navigatetoBlueSource() {
		driver.get(Utilities.getEnvironmentValue("URL"));
	}
	
	//Entering the username into the username field.
	public void enterUsername(String user) {
		txtUsername.sendKeys(user);
	}
	
	//Entering the password into the password field.
	public void enterPassword() {
		Utilities.setSecure(txtPassword, Utilities.getEnvironmentValue("password"));
	}
	
	//Click the 'Login' button.
	public void clickLogin() {
		btnLogin.click();
	}
	
	//Verify successful navigation to BlueSource.
	public boolean verifySuccessfulNavigation() {
		boolean isPresent = false;
		
		try {
			isPresent = Utilities.verifyObjectIsDisplayed(driver, btnLogin);
		} catch(NoSuchElementException e) {
			Reporter.log("Unable to verify a successful navigation to BlueSource.<br>");
		}
		
		return isPresent;
	}
	
	//Verify successful login to BlueSource environment.
	public boolean verifySuccessfulLogin() {
		boolean isPresent = false;
		
		try {
			isPresent = Utilities.verifyObjectIsDisplayed(driver, btnHelp);
		} catch(NoSuchElementException e) {
			Reporter.log("Unable to verify a successful login to BlueSource.<br>");
		}
		
		return isPresent;
	}
	
	//Logout of BlueSource.
	public void logout() {
		lnkLogout.click();
	}
	
	//Verify successful login to BlueSource environment.
	public boolean verifySuccessfulLogout() {
		boolean isPresent = false;
		
		try {
			isPresent = Utilities.verifyObjectIsDisplayed(driver, btnLogin);
		} catch(NoSuchElementException e) {
			Reporter.log("Unable to verify a successful logout from BlueSource.<br>");
		}
		
		return isPresent;
	}
	
	//Wrapper method for a successful login.
	public void login(String user) {
		enterUsername(user);
		enterPassword();
		clickLogin();
	}
}
