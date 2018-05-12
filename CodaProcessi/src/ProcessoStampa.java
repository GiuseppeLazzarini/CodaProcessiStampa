import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * La classe rappresenta un processo che costiusce un coda di stampa. Gli attributi sono: il nome del
 * pc che ha inviato la richiesta di stampa, il nome dell'applicazione che ha inviato il processo di stampa(quindi
 * il formato del file), il nome del file da stampare e la data e l'ora di invio, il codiceID per identificare 
 * il processo. Inoltre per assegnare codici differenti è presente un contatore statico
 * @author Lazzarini Giuseppe
 * @version 2.0
 */
public class ProcessoStampa implements Serializable
{
	//Attributi
	private String pc;
	private String formatoFile;
	private String nomeProcesso;
	private LocalDateTime dataOra;
	private static int contatore;
	private int codiceID;
	
	/**
	 * Costruttore. Quando viene istanziata un nuovo processo il contatore statico si incrementa ed
	 * il suo valore momentaneo viene utilizzato per assegnare il codiceID.
	 * Per la data si una il metodo now(ora locale) della classe LocalDateTime.
	 * @param pc nome del computer che ha inviato la richiesta di stampa
	 * @param formatoFile nome dell'applicazione che ha inviato il processo di stampa
	 * @param nomeProcesso nome del file da stampare
	 */
	public ProcessoStampa(String pc,String formatoFile,String nomeProcesso)
	{
	
		contatore++;
		setCodiceID(contatore);
		setPc(pc);
		setFormatoFile(formatoFile);
		setNomeProcesso(nomeProcesso);
		dataOra=LocalDateTime.now();
		
	}
	/**
	 * Costruttore Vuoto.
	 */
	public ProcessoStampa()
	{
		contatore++;
		setCodiceID(contatore);
		this.pc="";
		this.formatoFile="";
		this.nomeProcesso="";
		dataOra=LocalDateTime.now();
	}
	
	/**
	 * Metodo  getter che restituisce il nome del computer che ha inviato la richiesta di stampa.
	 * @return Nome del pc
	 */
	public String getPc() {
		return pc;
	}
	/**
	 * Metodo setter che consente di settare il nome del computer che ha inviato la richiesta di stampa.
	 * @param pc  nome del computer che ha inviato la richiesta di stampa
	 */
	public void setPc(String pc) {
		this.pc = pc;
	}
	/**
	 * Metodo  getter che restituisce nome dell'applicazione che ha inviato il processo di stampa
	 * @return  Nome dell'applicazione
	 */
	public String getFormatoFile() {
		return formatoFile;
	}
	/**
	 * Metodo setter che consente di settare il nome dell'applicazione che ha inviato il processo di stampa
	 * @param formatoFile nome dell'applicazione che ha inviato il processo di stampa
	 */
	public void setFormatoFile(String formatoFile) {
		this.formatoFile = formatoFile;
	}
	/**
	 *  Metodo  getter che restituisce il nome del file da stampare
	 * @return Nome del file
	 */
	public String getNomeProcesso() {
		return nomeProcesso;
	}
	/**
	 * Metodo setter che consente di settare il nome del file da stampare
	 * @param nomeProcesso nome del file da stampare
	 */
	public void setNomeProcesso(String nomeProcesso) {
		this.nomeProcesso = nomeProcesso;
	}
	/**
	 * Metodo  getter che restituisce l'ora e la data di invio del processo
	 * @return l'ora e la data di invio del processo
	 */
	public LocalDateTime getDataOra() {
		return dataOra;
	}
	/**
	 *  Metodo setter che consente di settare l'ora e la data di invio del processo
	 * @param dataOra l'ora e la data di invio del processo
	 */
	public void setDataOra(LocalDateTime dataOra) {
		this.dataOra = dataOra;
	}
	/**
	 * Metodo  getter che restituisce il numero di processi creati
	 * @return Numero di processi creati
	 */
	public static int getContatore() {
		return contatore;
	}
	/**
	 * Metodo setter che consente di settare il contatore di processi
	 * @param c
	 */
	public static void setContatore(int c)
	{
		contatore=c;
	}
	/**
	 * Metodo  getter che restituisce il codiceID del processo
	 * @return CodiceID del processo
	 */
	public int getCodiceID() {
		return codiceID;
	}
	/**
	 * Metodo setter consente di settare il codice identificativo del processo
	 * @param codiceID
	 */
	public void setCodiceID(int codiceID)
	{
		this.codiceID=codiceID;
	}

	/**
	 * Metodo equals che verifica se 2 processi sono uguali
	 * @param p processo da confrontare
	 * @return true se sono uguali
	 * @return false se sono diversi
	 */
	public boolean equals(ProcessoStampa p)
	{
		if (getCodiceID()==p.getCodiceID())
			return true;
		else
			return false;
	}
	
	/**
	 * Restituisce una stringa contenente le informazioni sul processo
	 */
	public String toString()
	{
		String processo="";
		processo+=getCodiceID()+" "+getPc()+" "+getFormatoFile()+" "+getNomeProcesso()+" "+dataOra+" ";
		return processo;
	}


	


	


	
	
	
}
