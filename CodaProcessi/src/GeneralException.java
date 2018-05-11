/**
 * Eccezione che serve per gestire particolari azioni eseguite dall'utente:
 * Per esempio ricerca di un processo inesistente.
 * @author Lazzarini Giuseppe
 *
 */
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
