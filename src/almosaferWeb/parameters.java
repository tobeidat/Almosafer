package almosaferWeb;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class parameters {
	WebDriver driver = new ChromeDriver();
	String alomosaferWebPage = "https://www.almosafer.com/en";
	SoftAssert softassert = new SoftAssert();
	String[] arabicCites = { "دبي", "جدة" };
	String[] engilshCites = { "Riyadh", "Jeddah", "Dubai" };
	Random rand = new Random();
	int randomIndexValue = rand.nextInt(arabicCites.length);
	int randomIndexVal = rand.nextInt(engilshCites.length);
	int randomNumbers = rand.nextInt(2);

	public void theBeginningOfTheWebsite() {
		driver.manage().window().maximize();
		driver.get(alomosaferWebPage);
		WebElement welcomeScreenButton = driver
				.findElement(By.cssSelector("button[class='sc-jTzLTM hQpNle cta__button cta__saudi btn btn-primary']"));
		welcomeScreenButton.click();

	}

	public void checkLanguage(String theLanguageToCheck) {
		WebElement languageElement = driver.findElement(By.tagName("html"));
		String DefaultLanguageOfWebsite = languageElement.getAttribute("lang");
		String DefaultLanguageAsExpected = theLanguageToCheck;
		softassert.assertEquals(DefaultLanguageOfWebsite, DefaultLanguageAsExpected, "check the language");

	}

	public void checkCurrency(String theCurrencyToCheck) {
		WebElement currencyElement = driver.findElement(By.cssSelector(".sc-dRFtgE.fPnvOO"));
		String actualCurrency = currencyElement.getText();
		String expectedCurrency = theCurrencyToCheck;
		softassert.assertNotEquals(actualCurrency, expectedCurrency);

	}

	public void checkTheNumberFunction(String numberToCheck) {
		String numberInTheWebsite = driver.findElement(By.xpath("//strong[normalize-space()='+966554400000']"))
				.getText();
		String numberAsExpected = numberToCheck;
		softassert.assertEquals(numberInTheWebsite, numberAsExpected);

	}

	public void checkLogFunction(WebElement theLogo) {
		WebElement acualLogo = theLogo;
		boolean qitafLogoIsDisplayed = acualLogo.isDisplayed();
		boolean expectedQitafLogo = true;
		softassert.assertEquals(qitafLogoIsDisplayed, expectedQitafLogo);

	}

	public void tabIsSelected(WebElement theTab) {
		WebElement hotelsElement = theTab;
		String hotelsStateInWebsite = hotelsElement.getAttribute("aria-selected");
		String hotelsState = "false";
		Assert.assertEquals(hotelsStateInWebsite, hotelsState);

	}

}
