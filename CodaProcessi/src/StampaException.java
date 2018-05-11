/**
 * Eccezione che serve per gestire particolari codizioni della stampante.
 * Per esempio stampante vuota.
 * @author Lazzarini Giuseppe
 *
 */
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
