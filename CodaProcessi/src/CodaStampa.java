import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
/**
 * La classe rappresenta una stampante condivisa da più computer. Viene gestita secondo una coda, quindi 
 * come una struttura dati FIFO(First in, First out). Gli attributi che costituiscono la stampante sono 2:
 * il puntatore head di tipo Nodo che punta all'ultimo elemento della coda e l'attributo elementi che tiene
 * conto dei nodi inseriti nella stampante.
 *@author Lazzarini Giuseppe
 * @version 2.0
 *
 */
public class CodaStampa  implements Serializable
{
	//Attributi
	private Nodo head;
	private int elementi;
	
	/**
	 * Costruttore.Istanzia una coda di stampa vuota.
	 */
	public CodaStampa()
	{
		head=null;
		elementi=0;
	}
	
	/**
	 * Metodo getter che restituisce il numero di elementi inseriti
	 * @return elementi
	 */
	public int getElementi()
	{
		return elementi;
	}
	/**
	 * Metodo getter che restituisce il nodo puntato da head.	 
	 * @return head
	 */
	public Nodo getHead()
	{
		return head;
	}
	/**
	 * Metodo privato utilizzato all'interno della classe per aggiungere elementi alla coda.
	 * @param processo rappresenta la parte informativa del nodo 
	 * @param link	contiene il reference del prossimo nodo, quindi il collegamento
	 * @return nodo restituisce il nodo creato
	 */
	private Nodo creaNodo(ProcessoStampa processo,Nodo link)
	{
		Nodo nodo=new Nodo(processo);
		nodo.setLink(link);
		return nodo;
	}
	/**
	 * Metodo privato utilizzato all'interno della classe per ottenere il nodo in una certa posizione.
	 * @param posizione posizione da cui ricavare il nodo
	 * @return p il collegamento ottenuto nella posizione richiesta
	 * @throws StampaException viene sollevata se viene inserita un posizione non valida oppure se la coda è vuota
	 */
	private Nodo getLinkPosizione(int posizione) throws StampaException
	{
		Nodo p;
		int n;
		p=head;
		n=1;
		if (posizione<1|| posizione>getElementi())
			throw new StampaException("Posizione non valida");
		if(elementi==0)
			throw new  StampaException("Nessun processo in fase di stampa");
		while(p.getLink()!=null && n<posizione)
		{
			p=p.getLink(); //p va a puntare al nodo successivo
			n++;
		}
		return p;
	}
	
	/**
	 * Consente di aggiungere un processo alla fase di stampa.
	 * @param processo processo da inviare alla stampante 
	 */
	public void aggiungiProcesso(ProcessoStampa processo)
	{
		Nodo p=creaNodo(processo,head);
		head=p;
		elementi++;
	}
	
	/**
	 * Consente di stampare il primo processo in testa alla coda .
	 * @return p processo stampato 
	 * @throws StampaException viene sollevata se la coda è vuota
	 */
	public ProcessoStampa stampaProcesso() throws StampaException
	{
		if(elementi==0)
			throw new StampaException("Nessun processo in fase di stampa");
		Nodo p;
		if(elementi==1)
		{
			p=head;
			head=null;
			elementi--;
			return p.getInfo();
		}
		p=getLinkPosizione(elementi);
		Nodo penultimo=getLinkPosizione(elementi-1);
		penultimo.setLink(null);
		elementi--;
		return p.getInfo();
	}
	/**
	 * Consente di visualizzare i processi di stampa di un certo PC in ordine alfabetico del nome del file.
	 * @param nomePc nome del pc da cercare
	 * @return processiPC array contenente i processi relativi ad un PC in ordine alfabetico
	 * @throws StampaException viene sollevata se la coda è vuota
	 * @throws GeneralException viene sollevata quando nessun processo corrisponde al pc inserito
	 */
	public ProcessoStampa[]	 processiPcAlfabetico(String nomePc) throws StampaException, GeneralException
	{
		if(elementi==0)
			throw new StampaException("Nessun processo in fase di stampa");
		ProcessoStampa[] processiPC;
		int contatore=0;
		Nodo p=head;
		while(p!=null)
		{
			if(p.getInfo().getPc().compareToIgnoreCase(nomePc)==0)
						contatore++;
			p=p.getLink();
		}
		if(contatore==0)
			throw new GeneralException("Nessun processo per il pc richiesto");
		processiPC=new ProcessoStampa[contatore];
		contatore=0;
		p=head;
		while(p!=null)
		{
			if(p.getInfo().getPc().compareToIgnoreCase(nomePc)==0)
			{
				processiPC[contatore]=p.getInfo();
						contatore++;
			}		
			p=p.getLink();
		}
		processiPC=Oridinatore.OrdinaProcessiAlfabetico(processiPC);
		
		return processiPC;
	}
	/**
	 * Consente di ricavare un processo dalla coda di stampa in base all'ID inserito.
	 * @param codice codice del pc da cercare 
	 * @return p processo di stampa 
	 * @throws StampaException  viene sollevata se la coda è vuota
	 * @throws GeneralException viene sollevata quando nessun processo corrisponde al ID inserito
	 */
	public ProcessoStampa getProcesso (int codice) throws StampaException, GeneralException
	{
		if(elementi==0)
			throw new StampaException("Nessun processo in stampa");
		Nodo p=head;
		while(p!=null)
		{
			if(p.getInfo().getCodiceID()==codice)
				return p.getInfo();
			p=p.getLink();
		}
		throw new GeneralException("Nessun processo corriponde all'ID indicato");
		
	}

	/**
	 * Consente di annullare  la stampa un processo immettendo il codiceID.
	 * Inoltre i file cancellati verranno salvati in un file di testo log.txt in formato CSV.
	 * @param codice codice del processo da annullare 
	 * @param nameFile nome del file su cui salvare il processo
	 * @throws StampaException   viene sollevata se la coda è vuota
	 * @throws GeneralException viene sollevata quando nessun processo corrisponde al ID inserito
	 * @throws IOException viene sollevata quando si verificano durante la scrittura su file 
	 * @throws FileException viene sollevata quando vi sono errori relativi al file 
	 */
	public void annullaStampa(int codice,String nameFile) throws StampaException, GeneralException, IOException, FileException
	{
		if(elementi==0)
			throw new StampaException("Nessun processo in fase di stampa");
		Nodo p=head;
		int posizione=0;
		
		if(elementi==1)
		{
			TextFile file=new TextFile(nameFile,'W');
			String processoCSV;
			ProcessoStampa processo;
			processo=getProcesso(codice);
			processoCSV=processo.getNomeProcesso()+";"+processo.getPc()+";"+processo.getFormatoFile()+";";
			file.toFile(processoCSV);
			file.closeFile();
			p=head;
			head=null;
			elementi--;
			return;
		}
		
		while(p!=null)
		{
			posizione++;
			
			if(p.getInfo().getCodiceID()==codice)
			{
				TextFile file=new TextFile(nameFile,'W');
				String processoCSV;
				ProcessoStampa processo;
				processo=getProcesso(codice);
				processoCSV=processo.getNomeProcesso()+";"+processo.getPc()+";"+processo.getFormatoFile()+";";
				file.toFile(processoCSV);
				file.closeFile();
				if (posizione==1) 
				{
					head=getLinkPosizione(posizione+1);
					elementi--;
					return;
				}
				p=getLinkPosizione(posizione);
				Nodo precedente=getLinkPosizione(posizione-1);
				precedente.setLink(p.getLink());
				elementi--;
				return;
			}		
			p=p.getLink();
		}
		throw new GeneralException("Nessun processo corriponde all'ID indicato");
	}
	
	/**
	 * Consente di portare in testa alla coda di stampa un processo, e di conseguenza il primo ad essere stampato.
	 * @param codice  codice del processo da portare in testa 
	 * @throws StampaException  viene sollevata se la coda è vuota
	 * @throws GeneralException viene sollevata quando nessun processo corrisponde al ID inserito
	 */
	public void portaInTesta (int codice) throws StampaException, GeneralException
	{
		if(elementi==0)
			throw new StampaException("Nessun processo in fase di stampa");
		if(elementi==1)
			throw new StampaException("In fase di stampa è presente un solo processo. Impossibile cambiare priorità");
		Nodo p=head;
		int contatore=0;
		while(p!=null)
		{
			contatore++;
			if(p.getInfo().getCodiceID()==codice)
			{
				p=getLinkPosizione(contatore);
				Nodo precedente=getLinkPosizione(contatore-1);
				precedente.setLink(p.getLink());
				Nodo nodoInTesta=getLinkPosizione(elementi);
				nodoInTesta.setLink(p);
				p.setLink(null);
				return;
			}		
			p=p.getLink();
		}
		throw new GeneralException("Nessun processo corriponde all'ID indicato");
	}
	
	public String toString()
	{
		String risultato="Head";
		if(elementi==0)
			return risultato+="-->Vuota";
		Nodo p=head;
		while(p!=null)
		{
			risultato+="-->"+p.getInfo().toString();
			p=p.getLink();
		}
		return risultato;
	}
	
	public void salvaStampa(String nomeFile) throws IOException
	{
		FileOutputStream file =new FileOutputStream(nomeFile);
		ObjectOutputStream writer=new ObjectOutputStream(file);
		writer.writeObject(this);
		writer.flush();
		file.close();
	}
	public CodaStampa caricaStampa (String nomeFile) throws IOException, ClassNotFoundException
	{
		FileInputStream file=new FileInputStream(nomeFile);
		ObjectInputStream reader= new ObjectInputStream(file);
		
		CodaStampa s;
		
		s=(CodaStampa)(reader.readObject());
		file.close();
		return s;
	}
}
