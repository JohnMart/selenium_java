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

public class TitlesPage {
	
	//----------------------------------------------------
	//		Variable Declaration/Object Repository
	//----------------------------------------------------
	
	//Instantiate a private web driver instance.
	private WebDriver driver;
	
	//Set the department name value with an appended timestamp value.
	private final String newTitleName = Utilities.getPropertiesValue("Title", "name") + Utilities.getTimeStamp();
	
	//Declare a wait for the webdriver.
	WebDriverWait wait = null;

	//Declaration of objects.
	@FindBy(className = "caret") WebElement ddlAdmin;
	@FindBy(linkText = "Titles") WebElement lnkTitles;
	@FindBy(css = "#content > h1") WebElement icoNewTitle;
	@FindBy(linkText = "New Title") WebElement lnkNewTitle;
	@FindBy(id = "title_name") WebElement txtTitleName;
	@FindBy(xpath = "//*[@id='new_title']/div[3]/input") WebElement btnCreateTitle;
	@FindBy(css = "#content > div.alert.alert-success.alert-dismissable") WebElement eleSuccessMessage;
	@FindBy(css = "#content > div.alert.alert-danger.alert-dismissable") WebElement eleFailMessage;
	@FindBy(name = "commit") WebElement btnUpdateTitle;
	
	//----------------------------------------------------
	//				Class Constructor
	//----------------------------------------------------
	public TitlesPage(WebDriver driver) {
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
	
	//Getter method for the title name value.
	public String getTitleName() {
		return newTitleName;
	}
	
	//Click the 'Titles' link.
	public void clickTitlesLink() {
		//Click the 'Admin' dropdown button.
		ddlAdmin.click();
		
		//Select the 'Titles' link from the dropdown.
		lnkTitles.click();
	}
	
	//Clear the title name field.
	public void clearTitleNameField() {
		txtTitleName.clear();
	}
	
	//Click the 'New Title' link.
	public void clickNewTitleLink() {
		lnkNewTitle.click();
	}
	
	//Click the 'Update Title' button.
	public void clickUpdateTitleButton() {
		btnUpdateTitle.click();
	}
	
	//Click the 'Create Title' button.
	public void clickCreateTitleButton() {
		btnCreateTitle.click();
	}
	
	//Verify the 'Titles' header exists.
	public boolean verifyTitlesPageDisplayed() {
		wait.until(ExpectedConditions.visibilityOf(icoNewTitle));
		if (icoNewTitle.getText().equals("Listing titles")) {
			return true;
		} else {
			return false;
		}
	}
	
	//Verify an error existing on the 'Titles' page.
	public boolean verifyTitlesPageSuccessMessage() {
		if (eleSuccessMessage.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}
	
	//Verify an error existing on the 'Titles' page.
	public boolean verifyTitlesPageError() {
		if (eleFailMessage.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}
	
	//Confirm the deletion on the javascript pop-up window.
	public void confirmDeleteTitle() {
		driver.switchTo().alert().accept();
	}
	
	//Enter the title name value on the 'New Department' page.
	public void enterTitleName() {
		wait.until(ExpectedConditions.visibilityOf(txtTitleName));
		txtTitleName.sendKeys(newTitleName);
	}
	
	//Verify a title is or is not listed in the title list on the page.
	public boolean verifyTitleInList(String titleName, Boolean exist) {
		Boolean isPresent = false;
		
		//Pull all the title values into a list.
		List<WebElement> titles = driver.findElements(By.tagName("td"));
		
		//Verify if the title name exists in the titles list on the page.
		for (WebElement title : titles) {
			if (title.getText().trim().equals(titleName)) {
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
	
	//Click the edit icon for a title in the list of titles.
	public void clickEditTitleIcon(String titleName) {
		Boolean isFound = false;
		
		//Pull all the title values into a list.
		List<WebElement> titles = driver.findElements(By.tagName("td"));
		
		//Verify if the title name exists in the titles list on the page.
		for (WebElement title : titles) {
			if (title.getText().trim().equals(titleName)) {

				//Pull all of the links inside of the title container.
				List<WebElement> links = title.findElements(By.tagName("a"));
				
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
	
	//Click the delete icon for a title in the list of titles.
	public void clickDeleteTitleIcon(String titleName) {
		Boolean isFound = false;
		
		//Pull all the title values into a list.
		List<WebElement> titles = driver.findElements(By.tagName("td"));
		
		//Verify if the title name exists in the titles list on the page.
		for (WebElement title : titles) {
			if (title.getText().trim().equals(titleName)) {
			
				//Pull all of the links inside of the title container.
				List<WebElement> icons = title.findElements(By.tagName("span"));
				
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
}
