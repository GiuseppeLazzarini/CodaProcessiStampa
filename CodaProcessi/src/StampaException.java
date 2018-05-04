
public class StampaException extends Exception 
{
	private String messaggio;
	
	public StampaException(String message)
	{
		this.messaggio=message;
	}
	
	public String toString()
	{
		return messaggio;
	}
}
