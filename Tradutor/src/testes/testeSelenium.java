package testes;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class testeSelenium {

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
	  public void testTradutorJanela() throws Exception {
	    driver.get(baseUrl + "/Tradutor/");
	    driver.findElement(By.name("palavra")).clear();
	    driver.findElement(By.name("palavra")).sendKeys("janela");
	    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
	    assertEquals("A tradução de janela é: window.", driver.findElement(By.id("traducao")).getText());
	  }
	  
	  @Test
	  public void testTradutorLivro() throws Exception {
	    driver.get(baseUrl + "/Tradutor/");
	    driver.findElement(By.name("palavra")).clear();
	    driver.findElement(By.name("palavra")).sendKeys("livro");
	    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
	    assertEquals("A tradução de livro é: book.", driver.findElement(By.id("traducao")).getText());
	  }
	  
	  @Test
	  public void testTradutorTeste() throws Exception {
	    driver.get(baseUrl + "/Tradutor/");
	    driver.findElement(By.name("palavra")).clear();
	    driver.findElement(By.name("palavra")).sendKeys("teste");
	    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
	    assertEquals("A tradução de teste é: teste.", driver.findElement(By.id("traducao")).getText());
	  }

	  @After
	  public void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }

	  

}
