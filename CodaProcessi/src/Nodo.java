import java.io.Serializable;
/**
 * La classe rappresenta i nodi che servono a costruire la coda di stampa. Gli attributi rappresentano: 
 * la componente informativa ossia il processo di stampa con i relativi parametri e la componente link
 * che contiene il collegamento il nodo successivo.
 * @author Lazzarini Giuseppe
 * @version 2.0
 *
 */
public class Nodo implements Serializable
{
	//Attributi
	private ProcessoStampa info;
	private Nodo link;
	
	/**
	 * Costruttore. Quando viene istanziato un nuovo nodo la componente link viene settata a null.
	 * @param processo oggetto di tipo ProcessoStampa che va a costituire la parte informativa del nodo
	 */
	public Nodo (ProcessoStampa processo)
	{
		setInfo(processo);
		setLink(null);
	}

	/**
	 * Metodo getter che restituisce la componente informativa del nodo
	 * @return info oggetto di tipo ProcessoStampa
	 */
	public ProcessoStampa getInfo() {
		return info;
	}

	/**
	 * Metodo setter che consente di settare la componente informativa del nodo
	 * @param info oggetto di tipo ProcessoStampa
	 */
	public void setInfo(ProcessoStampa info) {
		this.info = info;
	}

	/**
	 *  Metodo getter che restituisce la componente link del nodo
	 * @return link reference del prossimo oggetto nodo
	 */
	public Nodo getLink() {
		return link;
	}

	/**
	 * Metodo setter che consente di settare la componente link del nodo 
	 * @param link reference del prossimo oggetto nodo
	 */
	public void setLink(Nodo link) {
		this.link = link;
	}
	
	
	
	
}
