package week5.day1.assignments;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseIncident {

	public ChromeDriver driver;
	public static String incidentNumber;

	@BeforeMethod
	public void preCondition() throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		// 1. Launch ServiceNow application
		driver.get("https://dev118812.service-now.com");
		System.out.println(driver.getTitle());
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.switchTo().frame("gsft_main");

		// 2. Login with valid credentials
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("user_password")).sendKeys("OBiwDjpIr84I");
		driver.findElement(By.id("sysverb_login")).click();

		// 3. Enter Incident in filter navigator and press enter"
		WebElement filter = driver.findElement(By.id("filter"));
		filter.sendKeys("Incident");
		Thread.sleep(2000);
		filter.sendKeys(Keys.ENTER);
		Thread.sleep(4000);
	}

	@AfterMethod
	public void postCondition() {
		driver.quit();
	}
}
