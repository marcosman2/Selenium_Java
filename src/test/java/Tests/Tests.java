package Tests;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Helpers.Helpers;
import PagesObject.PageLogin;
import PagesObject.PageLogon;
import PagesObject.PageReservation;

public class Tests
{
	private WebDriver driver;
	@BeforeMethod
	public void setUp()
	{
		                                                                           //DesiredCapabilities caps = new DesiredCapabilities();
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe"); //Se indica la ruta donde está el driver de Chrome
		driver = new ChromeDriver();                                               //Se crea el driver que va a ejecutar todas las acciones
		driver.manage().window().maximize();                                       //Se maximiza la ventana
		driver.navigate().to("http://newtours.demoaut.com/");                      //Abre el sitio indicado
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);           //Espera durante 10 segundos, hasta que la página cargue
	}
	/*@Test
	public void wrongLogin()
	{
		PageLogin pageLogin = new PageLogin(driver);                               //Se inicializa el constructor
		pageLogin.login("user", "user");                                           //Se llama al método login
		PageLogon pageLogon = new PageLogon(driver);
		pageLogon.assertPageLogon();		
	}*/
	
	@Test
	public void correctLogin()
	{
		PageLogin pageLogin = new PageLogin(driver);
		pageLogin.login("mercury", "mercury");
		PageReservation pageReservation = new PageReservation(driver);
		pageReservation.assertPageReservation();
		
	}
	
	/*@Test
	public void travelSelections()
	{
		PageLogin pageLogin = new PageLogin(driver);
		pageLogin.login("mercury", "mercury");
		PageReservation pageReservation = new PageReservation(driver);
		pageReservation.selectPassengers(3);
		pageReservation.selectDeparting(0);
		pageReservation.selectArriving("New York");
		Helpers helper = new Helpers();
		helper.waitingTime(5);		
	}
	
	@Test
	public void equalSelections()
	{
		PageLogin pageLogin = new PageLogin(driver);
		pageLogin.login("mercury", "mercury");
		PageReservation pageReservation = new PageReservation(driver);
		pageReservation.differentFromTo(2, "London");
		Helpers helper = new Helpers();
		helper.waitingTime(5);		
	}
	
	@Test
	public void londonToNY()
	{
		PageLogin pageLogin = new PageLogin(driver);
		pageLogin.login("mercury", "mercury");
		PageReservation pageReservation = new PageReservation(driver);
		int randomCity = (int) (Math.random()*8);                                              //Convertir a entero un Random (que es de tipo Double)
		pageReservation.londonToNewYork(randomCity);
	}
	
	@Test
	public void inputFields()
	{
		PageLogin pageLogin = new PageLogin(driver);
		pageLogin.findInputFields();
	}*/
	
	@AfterMethod
	public void tearDown(ITestResult result) //En el Tear Down se 
	{
		if(!result.isSuccess())
		{
			File errorScreenshot =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			try
			{
				FileUtils.copyFile(errorScreenshot, new File("Screenshots/ERROR"+System.currentTimeMillis()+".png"));
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		driver.close();
	}
}