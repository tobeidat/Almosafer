package almosaferWeb;

import java.time.LocalDate;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class myTestCases extends parameters {

	@BeforeTest
	public void setup() {
		theBeginningOfTheWebsite();
	}

	@Test(priority = 1)
	public void defaultLanguage() {
		checkLanguage("en");
		softassert.assertAll();

	}

	@Test(priority = 2)
	public void currencyTest() {
		checkCurrency("[SAR]");
		softassert.assertAll();
	}

	@Test(priority = 3)
	public void numberTest() {
		checkTheNumberFunction("+966554400000");
		softassert.assertAll();

	}

	@Test(priority = 4)
	public void qitafLogoTest() {
		checkLogFunction(driver.findElement(By.xpath("//div[@class='sc-fihHvN eYrDjb']//*[name()='svg']")));
		softassert.assertAll();

	}

	@Test(priority = 5)
	public void hotelsTabIsNotSelected() {
		tabIsSelected(driver.findElement(By.id("uncontrolled-tab-example-tab-hotels")));
	}

	@Test(priority = 6)
	public void testTheFlightDepartureAndReturnDate() {
		WebElement flightDepartureDate = driver
				.findElement(By.cssSelector("div[class='sc-iHhHRJ sc-kqlzXE blwiEW'] span[class='sc-cPuPxo LiroG']"));
		int flightDepartureDateAsInteger = Integer.parseInt(flightDepartureDate.getText());
		WebElement flightReturnDate = driver
				.findElement(By.cssSelector("div[class='sc-iHhHRJ sc-OxbzP edzUwL'] span[class='sc-cPuPxo LiroG']"));
		WebElement dayVlaue = driver
				.findElement(By.cssSelector("div[class='sc-iHhHRJ sc-kqlzXE blwiEW'] span[class='sc-hvvHee cuAEQj']"));
		WebElement monthValueInWebsite = driver
				.findElement(By.cssSelector("div[class='sc-iHhHRJ sc-OxbzP edzUwL'] span[class='sc-bYnzgO doqpRk']"));
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

	@Test(priority = 7)
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
		WebElement searchButtonElement = driver.findElement(By.className("sc-1vkdpp9-6"));
		searchButtonElement.click();
		Thread.sleep(50000);
		String loadingBarElement = driver.findElement(By.className("sc-kAKrxA")).getText();
		if (driver.getCurrentUrl().contains("ar")) {
			Assert.assertEquals(loadingBarElement.contains("وجدنا"), true);
			WebElement lowestPrice = driver.findElement(By.className("jyUtIz"));
			lowestPrice.click();

		} else {

			Assert.assertEquals(loadingBarElement.contains("found"), true);
			WebElement lowestPrice = driver.findElement(By.className("hcjHpm"));
			lowestPrice.click();
		}

		List<WebElement> prices = driver.findElements(By.className("Price__Value"));
		int firstPriceItem = Integer.parseInt(prices.get(0).getText());
		int lastPriceItem = Integer.parseInt(prices.get(prices.size() - 1).getText());
		softassert.assertEquals(firstPriceItem < lastPriceItem, true);

	}

	@AfterTest
	public void postTest() {

	}

}
