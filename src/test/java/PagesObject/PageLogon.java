//Page Object de la Página que se muestra cuando se ingresan mal los datos de logueo

package PagesObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class PageLogon {
	
	private WebDriver driver;
	private By titleText;
	
	public PageLogon(WebDriver driver)
	{
		this.driver = driver;
		this.titleText = By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[3]/td/p/font/b");
	}
	
	public void assertPageLogon()
	{
		Assert.assertTrue(driver.findElement(titleText).getText().contains("Welcome back")); //Es preferible que los asserts vayan en el Page Object
	}


}
