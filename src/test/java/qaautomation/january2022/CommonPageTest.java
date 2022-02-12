package qaautomation.january2022;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import qaautomation.january2022.pages.CommonPage;
import qaautomation.january2022.utils.TestUtility;

public class CommonPageTest extends BaseWebTest {
	CommonPage commonPage = new CommonPage(driver, explicitWait);

	@Test
	public void testSwitching() {
		commonPage.openNewTab();
		commonPage.switchWindow(1);
		commonPage.openUrl("https://facebook.com");
		commonPage.openNewWindow();
		commonPage.switchWindow(2);
		commonPage.openUrl("https://twitter.com");
		commonPage.switchWindow(0);
		driver.get().findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("username");
	}

	@Test
	public void testJS() {
		String jsAlert = "alert('beware of alert')";
		commonPage.runJavaScriptCommand(jsAlert);
		TestUtility.hardWait(2);
		commonPage.acceptAlert();
		TestUtility.hardWait(2);
	}

}
