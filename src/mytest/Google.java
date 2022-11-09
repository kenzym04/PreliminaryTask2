package mytest;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Google {

	WebDriver driver;

	@BeforeMethod
	public void setup() {
		System.setProperty("Webdriver.chrome.driver", "C:\\Selenium\\selenium-server-4.6.0.exe");
		driver = new ChromeDriver();
		// Navigate to amazon.com
		driver.get("https://www.google.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	public void searchForTestAutomationLearning() {
		// find element
		WebElement element = driver.findElement(By.name("q"));
		// send search string "Test Automation Learning" using sendKeys() and then
		// execute submit()
		element.sendKeys("Test Automation Learning");
		// Explicit wait for the search results
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul")));
		element.submit();
	}

	public void selectUdemyCourseLink() {
		// Select the link with Udemy course
		driver.findElement(By.xpath("//h3[contains(text(),'Top Automation Testing Courses Online - Udemy')]")).click();
		// Verify the Udemy site is opened
		String actualTitle = driver.getTitle();
		String expectedTitle = "    Top Automation Testing Courses Online - Updated [November 2022]  | Udemy";
		// Verify the Udemy site is opened
		Assert.assertEquals(actualTitle, expectedTitle);
	}

	public void SearchForBDDwithCucumber() throws InterruptedException {
		// Enter string in Search textbox then press enter
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement SearchTextbox = wait.until(ExpectedConditions
				.presenceOfElementLocated((By.xpath("//input[@id='u302-search-form-autocomplete--3']"))));
		SearchTextbox.click();
		SearchTextbox.sendKeys("BDD with Cucumber");
		SearchTextbox.sendKeys(Keys.ENTER);

		// Get all BDD with cucumber courses
		List<WebElement> CourseReviews = wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath("//span[contains(text(),'(7,674)')]")));

		// List to get all courses' number.
		ArrayList<Integer> mylist = new ArrayList<>();

		// Loop through all the WebElement and Extract number from String
		for (WebElement CourseInfo : CourseReviews) {
			try {
				// converting String to StringBuilder 
				StringBuilder builder = new StringBuilder(CourseInfo.getText()); 
				// removing first character 
				String text = builder.deleteCharAt(0).toString();
				mylist.add(Integer.parseInt(text.split(")")[0].trim()));
				
			} catch (Exception ignored) {
			}
		}

		// Print to verify list and get highest number to use in xpath to find the distinct element and click on it
		System.out.println(mylist);
		Integer maximum_number = Collections.max(mylist);

		driver.findElement(By.xpath(String.format("//span[text()=\"%s )\"]", maximum_number))).click();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
