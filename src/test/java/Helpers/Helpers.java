package Helpers;

public class Helpers {
	
	public void waitingTime(int seconds) //M�todo para esperar
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
