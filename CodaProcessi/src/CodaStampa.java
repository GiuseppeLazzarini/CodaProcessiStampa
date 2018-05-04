
public class CodaStampa 
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
	
	public void enqueue(ProcessoStampa processo)
	{
		Nodo p=creaNodo(processo,head);
		head=p;
		elementi++;
	}
	
	
	public ProcessoStampa dequeue() throws StampaException
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
	
	/*public void annullaStampa(int codice) throws StampaException, GeneralException
	{
		if(elementi==0)
			throw new StampaException("Nessun processo in fase di stampa");
		Nodo p;
		if(elementi==1)
		{
			p=head;
			head=null;
			elementi--;
		}
		
		p=getLinkPosizione(posizione);
		Nodo precedente=getLinkPosizione(posizione-1);
		precedente.setLink(p.getLink());
		elementi--;
	}*/
	public String toString()
	{
		String risultato="Head";
		if(elementi==0)
			return risultato+="-->";
		Nodo p=head;
		while(p!=null)
		{
			risultato+="-->"+p.getInfo().toString();
			p=p.getLink();
		}
		return risultato;
	}
}