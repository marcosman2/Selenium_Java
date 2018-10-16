package Helpers;

public class Helpers {
	
	public void waitingTime(int seconds) //Método para esperar
	{
		try
		{
			Thread.sleep(seconds*1000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

}
