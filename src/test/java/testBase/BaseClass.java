package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Parameters;

public class BaseClass {
	
	public static WebDriver driver ;
	public Logger logger;
	public Properties p;
	
	@BeforeClass(groups= {"sanity","Regression","master"})
	@Parameters({"os" , "browser"})
	public void setup(String os ,  String br) throws Exception {
		
		// loading properties file
		FileInputStream fis = new FileInputStream("G:\\opencart\\src\\test\\resources\\config.properties");
		p= new Properties();
		p.load(fis);
		
		logger = LogManager.getLogger(this.getClass());
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			//os
			if(os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN10);
			}else if (os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			}else {
				System.out.println("no browser matching");
				return ;
			}
			
			//bowser
			switch(br.toLowerCase()) {
			case "chrome" : capabilities.setBrowserName("chrome");break;
			case "edge" : capabilities.setBrowserName("MicrosoftEdge"); break;
			default : System.out.println("No Matching browser..."); return;
			}
			
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
		}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
			
		
		
		switch(br.toLowerCase()) {
		case "chrome" : driver= new ChromeDriver();break;
		case "edge" : driver = new EdgeDriver();break;
		default : System.out.println("No Matching browser..."); return;
		}
		
		/*driver= new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURl"));
		driver.manage().window().maximize();*/
	}
	}
	
	@AfterClass(groups= {"sanity","Regression","master"})
	public void tearDown() {
		driver.quit();
	}
	
	public String randomeString()
	{
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	public String randomeNumber()
	{
		String generatedString=RandomStringUtils.randomNumeric(10);
		return generatedString;
	}
	
	public String randomAlphaNumeric()
	{
		String str=RandomStringUtils.randomAlphabetic(3);
		String num=RandomStringUtils.randomNumeric(3);
		
		return (str+"@"+num);
	}
	
	public String captureScreen(String tname) throws Exception {
		
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver ;
		File sourceFile= takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp+ ".png";
		File targetFile = new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath ;
	}
	
	public void captureScrrenshot(ITestResult result) throws Exception {
		if(ITestResult.FAILURE== result.getStatus()) {
			
			String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			TakesScreenshot ts = (TakesScreenshot) driver ;
			File src = ts.getScreenshotAs(OutputType.FILE);
			
			FileUtils.copyFile(src, new File("./screenshot/" + result.getName()+ "_"+ timeStamp +  ".png"));
			System.out.println(result.getName() + " method() Screenshot captured");
		}
	}


}
