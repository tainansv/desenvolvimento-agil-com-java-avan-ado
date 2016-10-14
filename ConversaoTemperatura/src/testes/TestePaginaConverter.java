package testes;
import static org.junit.Assert.*;

import org.junit.Before;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TestePaginaConverter {

	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testConverterCelsius() throws Exception {
		driver.get(baseUrl + "/ConversaoTemperatura/converter-temp.html");
		driver.findElement(By.name("valor")).clear();
		driver.findElement(By.name("valor")).sendKeys("100");
		driver.findElement(By.name("converter")).click();
		assertEquals("100 Celsius = 212 Fahrenheit", driver.findElement(By.id("mensagem")).getText());
	}
	
	@Test
	public void testConverterFahrenheit() throws Exception {
		driver.get(baseUrl + "/ConversaoTemperatura/converter-temp.html");
		new Select(driver.findElement(By.name("opcao"))).selectByVisibleText("Fahrenheit para Celsius");
		driver.findElement(By.name("valor")).clear();
		driver.findElement(By.name("valor")).sendKeys("212");
		driver.findElement(By.name("converter")).click();
		assertEquals("212 Fahrenheit = 100 Celsius", driver.findElement(By.id("mensagem")).getText());
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
