package selenium;

import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import dev.failsafe.internal.util.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;

public class loginTest {
	WebDriver driver;
	public String URL;
	//SoftAssert softassert = new SoftAssert();
	Assertion hardAssert = new Assertion();
  @BeforeTest
  public void setUp() {
	  	//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\resources\\chromedriver.exe");
		//driver = new ChromeDriver();
		
		ChromeOptions chromeOptions = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(chromeOptions);
		
		driver.manage().window().maximize();
		URL = "https://practicetestautomation.com/practice-test-login/";
		driver.get(URL);
		System.out.println("1. Open Chrome & Application");
  }
  
  @Test(priority = 1)
  public void signIn() {
	  WebElement textUsername = driver.findElement(By.name("username"));
		Highlighter(driver, textUsername);
		textUsername.sendKeys("student");

		WebElement textPassword = driver.findElement(By.name("password"));
		Highlighter(driver, textPassword);
		textPassword.sendKeys("Password123");

		WebElement buttonLogin = driver.findElement(By.xpath("//button[text()='Submit']"));
		Highlighter(driver, buttonLogin);
		buttonLogin.click();

		System.out.println("2. Sign In");
  }
  
  @Test(priority = 2)
	public void successfulLoginVerification() {
	  
	  	WebElement successTextElement = driver.findElement(By.xpath("//h1[text()='Logged In Successfully']"));
		String actualSuccessText = "Logged In Successfully";
	  	String getSuccessText = successTextElement.getText();

	  	hardAssert.assertEquals(getSuccessText,actualSuccessText);
		System.out.println("3. Verify Successful Login Text!");
		//softassert.assertAll();
	}
  
  @Test(priority = 3)
	public void userSignOut() {
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement linkLogout = driver.findElement(By.xpath("//a[contains(text(),'Log out')]"));
		Highlighter(driver, linkLogout);
		linkLogout.click();

		System.out.println("4. Sign Out");
	}
  
	private void Highlighter(WebDriver driver2, WebElement linkWelcome) {
		// TODO Auto-generated method stub

	}
  @AfterTest
  public void tearDown() {
	  System.out.println("5. Close Chrome & Application");
	 driver.quit();
  }

}
