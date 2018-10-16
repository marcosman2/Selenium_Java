package PagesObject;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Helpers.Helpers;

public class PageLogin {
	
	private WebDriver driver;                                                    //Se declara el driver, porque es el que ejecuta las acciones en el navegador
	private By userField;                                                        //Se declara el campo User
	private By passwordField;                                                    //Se declara el campo Password
	private By loginButton;                                                     //Se declara el botón
	private By inputFields;
	
	public PageLogin(WebDriver driver)                                           //Constructor de la clase
	{
		this.driver = driver;                                                    //Se inicializa el driver
		this.userField = By.name("userName");                                    //Se inicializa el campo User
		this.passwordField = By.name("password");                                //Se inicializa el campo Password
		this.loginButton = By.name("login");                                     //Se inicializa el botón
		this.inputFields = By.tagName("input");                                  //Se incializa el campo que contiene los inputs
	}
	
	public void login (String user, String pass)                                 //Método para hacer el login
	{
		driver.findElement(userField).sendKeys(user);
		driver.findElement(passwordField).sendKeys(pass);
		driver.findElement(loginButton).click();
		File myScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);                        //Código para tomar un pantallazo
		try
		{
			FileUtils.copyFile(myScreenshot, new File("Screenshots/LOGIN"+System.currentTimeMillis()+".png"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}                                                                                                      //Aquí termina el código para tomar el pantallazo
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public void findInputFields()                                                 //Método para contar los elementos de tipo input
	{
		List<WebElement> loginFields = driver.findElements(inputFields);          //Se crea una lista, donde se guardan los campos tipo input
		System.out.println("This is a test: "+loginFields.size());
		Assert.assertTrue(loginFields.size() == 5);
	}
	
	
}
