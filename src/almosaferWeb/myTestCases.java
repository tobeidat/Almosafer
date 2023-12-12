package almosaferWeb;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class myTestCases {
	WebDriver driver = new ChromeDriver();
	String alomosaferWebPage = "https://www.almosafer.com/en";
	SoftAssert softassert = new SoftAssert();
	String[] arabicCites = { "دبي", "جدة" };
	String[] engilshCites = { "Riyadh", "Jeddah", "Dubai" };
	Random rand = new Random();
	int randomIndexValue = rand.nextInt(arabicCites.length);
	int randomIndexVal = rand.nextInt(engilshCites.length);
	int randomNumbers = rand.nextInt(2);

	@BeforeTest
	public void setup() {
		driver.manage().window().maximize();
		driver.get(alomosaferWebPage);
		WebElement welcomeScreenButton = driver
				.findElement(By.cssSelector("button[class='sc-jTzLTM hQpNle cta__button cta__saudi btn btn-primary']"));
		welcomeScreenButton.click();

	}

	@Test(enabled = false)
	public void defaultLanguage() {
		WebElement languageElement = driver.findElement(By.tagName("html"));
		String DefaultLanguageOfWebsite = languageElement.getAttribute("lang");
		String DefaultLanguageAsExpected = "en";
		softassert.assertEquals(DefaultLanguageOfWebsite, DefaultLanguageAsExpected, "check the language");

	}

	@Test(enabled = false)
	public void currencyTest() {
		WebElement currencyElement = driver.findElement(By.cssSelector(".sc-dRFtgE.fPnvOO"));
		String actualCurrency = currencyElement.getText();
		String expectedCurrency = "[SAR]";
		softassert.assertNotEquals(actualCurrency, expectedCurrency);

	}

	@Test(enabled = false)
	public void numberTest() {
		String numberInTheWebsite = driver.findElement(By.xpath("//strong[normalize-space()='+966554400000']"))
				.getText();
		String numberAsExpected = "+966554400000";
		softassert.assertEquals(numberInTheWebsite, numberAsExpected);

	}

	@Test(enabled = false)
	public void qitafLogoTest() {
		WebElement acualLogo = driver.findElement(By.xpath("//div[@class='sc-dznXNo iZejAw']"));
		boolean qitafLogoIsDisplayed = acualLogo.isDisplayed();
		boolean expectedQitafLogo = true;
		softassert.assertEquals(qitafLogoIsDisplayed, expectedQitafLogo);

	}

	@Test(enabled = false)
	public void hotelsTabIsNotSelected() {
		WebElement hotelsElement = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		String hotelsStateInWebsite = hotelsElement.getAttribute("aria-selected");
		String hotelsState = "false";
		Assert.assertEquals(hotelsStateInWebsite, hotelsState);

	}

	@Test(enabled = false)
	public void testTheFlightDepartureAndReturnDate() {
		WebElement flightDepartureDate = driver
				.findElement(By.cssSelector("div[class='sc-OxbzP sc-lnrBVv gKbptE'] span[class='sc-fvLVrH hNjEjT']"));
		int flightDepartureDateAsInteger = Integer.parseInt(flightDepartureDate.getText());
		WebElement flightReturnDate = driver
				.findElement(By.cssSelector("div[class='sc-OxbzP sc-bYnzgO bojUIa'] span[class='sc-fvLVrH hNjEjT']"));
		WebElement dayVlaue = driver
				.findElement(By.cssSelector("div[class='sc-OxbzP sc-lnrBVv gKbptE'] span[class='sc-eSePXt ljMnJa']"));
		WebElement monthValueInWebsite = driver
				.findElement(By.cssSelector("div[class='sc-OxbzP sc-bYnzgO bojUIa'] span[class='sc-hvvHee cuAEQj']"));
		int flightReturnDateInWebsite = Integer.parseInt(flightReturnDate.getText());
		LocalDate today = LocalDate.now();
		int flightDepartureDateAsExpected = today.plusDays(1).getDayOfMonth();
		int flightReturnDateAsExpected = today.plusDays(2).getDayOfMonth();

		String AcualDay = dayVlaue.getText().toUpperCase();
		String ExpectedDay = today.plusDays(1).getDayOfWeek().toString().toUpperCase();

		String AcualMonthElement = monthValueInWebsite.getText().toUpperCase().toUpperCase();
		String ExpectedMonthElement = today.plusDays(1).getMonth().toString().toUpperCase();
		Assert.assertEquals(flightDepartureDateAsInteger, flightDepartureDateAsExpected);
		Assert.assertEquals(flightReturnDateInWebsite, flightReturnDateAsExpected);
		Assert.assertEquals(AcualDay, ExpectedDay);
		Assert.assertEquals(AcualMonthElement, ExpectedMonthElement);

	}

	@Test(invocationCount = 1)
	public void changeLanguageTest() throws InterruptedException {
		String[] urls = { "https://global.almosafer.com/en", "https://global.almosafer.com/ar" };
		int index = rand.nextInt(urls.length);
		driver.get(urls[index]);
		String websiteLan = driver.findElement(By.tagName("html")).getAttribute("lang");

		WebElement hotelTab = driver.findElement(By.cssSelector("#uncontrolled-tab-example-tab-hotels"));
		hotelTab.click();
		Thread.sleep(2000);
		WebElement searchForHotlesElement = driver.findElement(By.className("phbroq-2"));
		if (driver.getCurrentUrl().contains("en")) {
			Assert.assertEquals(websiteLan, "en");
			searchForHotlesElement.sendKeys(engilshCites[randomIndexVal]);

		} else {

			Assert.assertEquals(websiteLan, "ar");
			searchForHotlesElement.sendKeys(arabicCites[randomIndexValue]);
		}
		Thread.sleep(2000);
		WebElement cityListElement = driver.findElement(By.className("phbroq-4"));
		List<WebElement> citesList = cityListElement.findElements(By.tagName("li"));
		citesList.get(1).click();
		WebElement webelement = driver.findElement(By.className("tln3e3-1"));

		Select select = new Select(webelement);
		select.selectByIndex(randomNumbers);

	}

	@AfterTest
	public void postTest() {
		softassert.assertAll();

	}

}
