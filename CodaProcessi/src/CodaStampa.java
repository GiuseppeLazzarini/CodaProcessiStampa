import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class CodaStampa  implements Serializable
{
	private Nodo head;
	private int elementi;
	
	public CodaStampa()
	{
		head=null;
		elementi=0;
	}
	
	public int getElementi()
	{
		return elementi;
	}
	public Nodo getHead()
	{
		return head;
	}
	
	private Nodo creaNodo(ProcessoStampa processo,Nodo link)
	{
		Nodo nodo=new Nodo(processo);
		nodo.setLink(link);
		return nodo;
	}
	
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
	
	public void aggiungiProcesso(ProcessoStampa processo)
	{
		Nodo p=creaNodo(processo,head);
		head=p;
		elementi++;
	}
	
	
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
	
	public void portaInTesta (int codice) throws StampaException
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
