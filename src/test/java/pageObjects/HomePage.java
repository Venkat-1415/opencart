package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	
	
	
	public HomePage(WebDriver driver) {
		
		super(driver);
		
	}
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement lnkMyAccount;
	
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement lnkRegister;
	
	@FindBy(xpath="//a[text()='Login']")    // xpath of login button is linktext = "login";
	WebElement linkLogin;
	
	public void clickMyAccount() {
		lnkMyAccount.click();
	}
	
	public void clickRegister() {
		
		lnkRegister.click();
	}
	
	public void clickLoginbtn() {
		linkLogin.click();
	}

}
