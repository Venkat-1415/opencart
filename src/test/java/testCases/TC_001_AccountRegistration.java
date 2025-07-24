package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistration extends BaseClass{
	
	@Test(groups={"sanity" , "Regression"})
	public void verify_account_registration() {
		
		logger.info("****Starting TC_001_AccountRegistrationTest****");
		logger.debug("This is a debug log message");
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("clicked on my account link");
			
			hp.clickRegister();
			logger.info("clicked on register");
			
			AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
			
			logger.info("providing customer details");
			regpage.setFirstName(randomeString().toUpperCase());
			regpage.setlastname(randomeString().toUpperCase());
			regpage.setEmail(randomeString()+"@gmail.com");
			regpage.setTelephone(randomeNumber());
			
			//String password = randomAlphaNumeric();
			
			//regpage.setPassword(password);
			//regpage.setConfirmPassword(password);
			regpage.setPassword("test@123");
			logger.info("Provided Password ");

			regpage.setConfirmPassword("test@123");
			logger.info("Provided Confrmed Password ");
			
			regpage.setPrivacyPolicy();
			regpage.clickContinue();
			
			logger.info("Validating the expected messsage");
			
			String confmsg = regpage.getConfirmationMsg();
			
			Assert.assertEquals(confmsg, "Your Account Has Been Created!");
			
			logger.info("Test Passed");
		} catch (Exception e) {
			logger.error("Test failed:" + e.getMessage());
			Assert.fail("Test failed:" + e.getMessage());
			e.printStackTrace();
		}finally {
			logger.info("**** TestCase TC_001_AccountRegistration ******");
		}
		
		
		
	}

}
