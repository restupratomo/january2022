package qaautomation.january2022;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import qaautomation.january2022.pages.LoginPage;
import qaautomation.january2022.pages.ProfilePage;

/**
 * Unit test for simple App.
 */
public class AppTest extends BaseWebTestHeadless {
	LoginPage loginPage = new LoginPage(driver, explicitWait);
	ProfilePage profilePage = new ProfilePage(driver, explicitWait);

	@Test
	public void test1() {
		// loginpage
		String username = "tomsmith";
		String password = "SuperSecretPassword!";

		loginPage.login(username, password);

		String actualText = profilePage.getProfileText();

		String expectedText = "You logged into a secure area";
		System.out.println(actualText);

		// assertion
		Assert.assertTrue(actualText.contains(expectedText));
	}

	@Test
	public void test2() {
		String username = "tomsmith";
		String password = "SuperWrongPassword";

		driver.get().findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(username);
		driver.get().findElement(By.id("password")).sendKeys(password);
		driver.get().findElement(By.xpath("//button[@type='submit']")).click();
		String actualText = driver.get().findElement(By.xpath("//div[@id='flash']")).getText();
		String expectedText = "Your password is invalid";
		System.out.println(actualText);
		Assert.assertTrue(actualText.contains(expectedText));
	}

	@Test
	public void test3() {
		String username = "tomsmith";
		String password = "SuperWrongPassword";

		loginPage.inputUsername(username);
		loginPage.inputPassword(password);
		loginPage.clickLoginButton();
		String actualText = driver.get().findElement(By.xpath("//div[@id='flash']")).getText();
		String expectedText = "Your password is invalid";
		System.out.println(actualText);
		Assert.assertTrue(actualText.contains(expectedText));
	}

}
