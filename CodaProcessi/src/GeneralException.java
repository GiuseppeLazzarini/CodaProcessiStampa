
public class GeneralException extends Exception 
{
	private String messaggio;
	
	public GeneralException(String message)
	{
		this.messaggio=message;
	}
	
	public String toString()
	{
		return messaggio;
	}
}
