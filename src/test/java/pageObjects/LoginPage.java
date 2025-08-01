package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	
public LoginPage(WebDriver driver) {
		
		super(driver);
		
		

}

@FindBy(xpath="//input[@id='input-email']")
WebElement txtEmailAddress;

@FindBy(xpath="//input[@id='input-password']")
WebElement txtpassword;

@FindBy(xpath="//input[@//input[@value='Login']")
WebElement btnLogin;

public void setEmail(String email) {
	txtEmailAddress.sendKeys(email);
}

public void setpassword(String pwd) {
	txtpassword.sendKeys(pwd);
}

public void clicklogin() {
	btnLogin.click();
}

}
