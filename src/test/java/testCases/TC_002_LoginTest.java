package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass {
	@Test(groups= {"sanity","Regression"})
	public void verify_login() {
		logger.info("*****Starting TC_001_LoginTest");
		logger.debug("capturing application debug logs");
		
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLoginbtn();
			
			LoginPage lp = new LoginPage(driver);
			lp.clicklogin();
			lp.setEmail(p.getProperty("email"));
			lp.setpassword(p.getProperty("password"));
			
			// MyAccountPage
			MyAccountPage macc = new MyAccountPage(driver);
			boolean targetpage=macc.isMyAccountPageExists();
			Assert.assertTrue(targetpage);
		} catch (Exception e) {
			Assert.fail();
			e.printStackTrace();
		}
		
		logger.info("**** Finished TC_002_LoginTest  ****");
		
	}

}
