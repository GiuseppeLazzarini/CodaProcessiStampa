import java.io.Serializable;

public class Nodo implements Serializable
{
	private ProcessoStampa info;
	private Nodo link;
	
	public Nodo (ProcessoStampa processo)
	{
		setInfo(processo);
		setLink(null);
	}

	public ProcessoStampa getInfo() {
		return info;
	}

	public void setInfo(ProcessoStampa info) {
		this.info = info;
	}

	public Nodo getLink() {
		return link;
	}

	public void setLink(Nodo link) {
		this.link = link;
	}
}
