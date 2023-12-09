package almosaferWeb;

import java.time.LocalDate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class myTestCases {
	WebDriver driver = new ChromeDriver();
	String alomosaferWebPage = "https://www.almosafer.com/en";
	SoftAssert softassert = new SoftAssert();

	@BeforeTest
	public void setUp() {
		driver.manage().window().maximize();
		driver.get(alomosaferWebPage);
		WebElement welcomeScreen = driver
				.findElement(By.xpath("//button[normalize-space()='Kingdom of Saudi Arabia, SAR']"));
		welcomeScreen.click();

	}

	@Test
	public void defaultLanguage() {
		WebElement languageElement = driver.findElement(By.tagName("html"));
		String actualDefaultLanguage = languageElement.getAttribute("lang");
		String expectedDefaultLanguage = "en";
		softassert.assertEquals(actualDefaultLanguage, expectedDefaultLanguage);

	}

	@Test()
	public void currencyTest() {
		WebElement currencyElement = driver.findElement(By.cssSelector(".sc-dRFtgE.fPnvOO"));
		String actualCurrency = currencyElement.getText();
		String expectedCurrency = "[SAR]";
		softassert.assertNotEquals(actualCurrency, expectedCurrency);

	}

	@Test()
	public void numberTest() {
		String actualNumber = driver.findElement(By.xpath("//strong[normalize-space()='+966554400000']")).getText();
		String expectedNumber = "+966554400000";
		softassert.assertEquals(actualNumber, expectedNumber);

	}

	@Test()
	public void qitafLogoTest() {
		WebElement acualLogo = driver.findElement(By.xpath("//div[@class='sc-dznXNo iZejAw']"));
		boolean qitafLogoIsDisplayed = acualLogo.isDisplayed();
		boolean expectedQitafLogo = true;
		softassert.assertEquals(qitafLogoIsDisplayed, expectedQitafLogo);

	}

	@Test()
	public void hotelsTabIsNotSelected() {
		WebElement hotelsElement = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		String theActualHotelsState = hotelsElement.getAttribute("aria-selected");
		String theExpectedHotelsState = "false";
		Assert.assertEquals(theActualHotelsState, theExpectedHotelsState);

	}

	@Test()
	public void testTheFlightDepartureAndReturnDate() {
		WebElement flightDepartureDate = driver
				.findElement(By.cssSelector("div[class='sc-OxbzP sc-lnrBVv gKbptE'] span[class='sc-fvLVrH hNjEjT']"));
		int acualFlightDepartureDate = Integer.parseInt(flightDepartureDate.getText());
		WebElement flightReturnDate = driver
				.findElement(By.cssSelector("div[class='sc-OxbzP sc-bYnzgO bojUIa'] span[class='sc-fvLVrH hNjEjT']"));
		WebElement AcualDayVlaue = driver
				.findElement(By.cssSelector("div[class='sc-OxbzP sc-lnrBVv gKbptE'] span[class='sc-eSePXt ljMnJa']"));
		WebElement AcualMonth = driver
				.findElement(By.cssSelector("div[class='sc-OxbzP sc-bYnzgO bojUIa'] span[class='sc-hvvHee cuAEQj']"));
		int acualFlightReturnDate = Integer.parseInt(flightReturnDate.getText());
		LocalDate today = LocalDate.now();
		int ExpectedFlightDepartureDate = today.plusDays(1).getDayOfMonth();
		int ExpectedFlightReturnDate = today.plusDays(2).getDayOfMonth();

		String AcualDay = AcualDayVlaue.getText().toUpperCase();
		String ExpectedDay = today.plusDays(1).getDayOfWeek().toString().toUpperCase();

		String AcualMonthElement = AcualMonth.getText().toUpperCase().toUpperCase();
		String ExpectedMonthElement = today.plusDays(1).getMonth().toString().toUpperCase();
		Assert.assertEquals(acualFlightDepartureDate, ExpectedFlightDepartureDate);
		Assert.assertEquals(acualFlightReturnDate, ExpectedFlightReturnDate);
		Assert.assertEquals(AcualDay, ExpectedDay);
		Assert.assertEquals(AcualMonthElement, ExpectedMonthElement);

	}

	@AfterTest
	public void postTest() {
		softassert.assertAll();

	}

}
