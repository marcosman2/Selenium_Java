//Page Object de la Página que se muestra cuando se ingresan correctamente los datos de logueo

package PagesObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import Helpers.Helpers;

public class PageReservation {
	
	private WebDriver driver;
	private By titleText;
	private By passengers;
	private By departing;
	private By arriving;
	
	public PageReservation(WebDriver driver)
	{
		this.driver = driver;
		this.titleText = By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[3]/td/font");
		this.passengers = By.name("passCount");
		this.departing = By.name("fromPort");
		this.arriving = By.name("toPort");
	}
	
	public void assertPageReservation()
	{
		Assert.assertTrue(driver.findElement(titleText).getText().contains("Use our1"));                  //Es preferible que los asserts vayan en el Page Object
	}	
	
	public void selectPassengers(int passengersNumber)
	{
		Select selectPassengersNumber = new Select(driver.findElement(passengers));                      //Se inicializa el dropdown
		selectPassengersNumber.selectByVisibleText(Integer.toString(passengersNumber));                  //Se selecciona por texto visible
	}
	
	public void selectDeparting(int fromCity)
	{
		Select selectDepartingCity = new Select(driver.findElement(departing));
		selectDepartingCity.selectByIndex(fromCity);                                                     //Se selecciona por el Index
	}
	
	public void selectArriving(String toCity)
	{
		Select selectArrivingCity = new Select(driver.findElement(arriving));
		selectArrivingCity.selectByValue(toCity);                                                                 //Se selecciona por el Value
	}
	
	public void differentFromTo(int fromCity, String toCity)
	{
		Select selectDepartingCity = new Select(driver.findElement(departing));
		Select selectArrivingCity = new Select(driver.findElement(arriving));
		selectDepartingCity.selectByIndex(fromCity);
		selectArrivingCity.selectByValue(toCity);
		Helpers helper = new Helpers();
		helper.waitingTime(5);
		WebElement departing = selectDepartingCity.getFirstSelectedOption();                                       //Se obtiene el texto de la opción seleccionada
		WebElement arriving = selectArrivingCity.getFirstSelectedOption();
		helper.waitingTime(5);		
		Assert.assertNotEquals(departing.getText(), arriving.getText(), "Los destinos seleccionados son iguales"); //Se comparan los textos de From y el To
	}
	
	public void londonToNewYork(int fromCity)
	{
		Select selectDepartingCity = new Select(driver.findElement(departing));
		Select selectArrivingCity = new Select(driver.findElement(arriving));
		selectDepartingCity.selectByIndex(fromCity);
		WebElement departing = selectDepartingCity.getFirstSelectedOption();
		if(departing.getText().contains("London"))
		{
			selectArrivingCity.selectByValue("New York");
		}
		else
		{
			selectArrivingCity.selectByValue("London");
		}	
		Helpers helper = new Helpers();
		helper.waitingTime(5);
	}
}
