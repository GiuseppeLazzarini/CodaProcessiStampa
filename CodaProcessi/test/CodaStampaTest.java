import static org.junit.Assert.*;

import org.junit.Test;

public class CodaStampaTest {

	@Test
	public void testCostruttoreCodaStampa() 
	{
		CodaStampa s=new CodaStampa();
		assertTrue("Costruttore CodaStampa",s.getHead()==null && s.getElementi()==0);
	}
	/*test commentato in quanto il metodo in questione è privato
	@Test
	public void testCreaNodo() 
	{
		CodaStampa s=new CodaStampa();
		ProcessoStampa p1=new ProcessoStampa();
		ProcessoStampa p2=new ProcessoStampa();
		Nodo n1=new Nodo(p1);
		Nodo n2=s.creaNodo(p2, n1);
		assertTrue("Crea Nodo",n2.getInfo().equals(p2) && n2.getLink()==n1);
	}*/
	
	@Test
	public void testAggiungiProcesso() 
	{
		CodaStampa s=new CodaStampa();
		ProcessoStampa p1=new ProcessoStampa();
		s.aggiungiProcesso(p1);
		assertTrue("Aggiungi processo alla stampa",s.getHead().getInfo()==p1 && s.getElementi()==1);
	}
	
	@Test
	public void testStampaProcesso() throws StampaException 
	{
		CodaStampa s=new CodaStampa();
		ProcessoStampa p1=new ProcessoStampa();
		ProcessoStampa p2=new ProcessoStampa();
		s.aggiungiProcesso(p1);
		s.aggiungiProcesso(p2);
		assertTrue("Stampa processo",s.stampaProcesso().equals(p1));
	}
	
	
	@Test (expected=StampaException .class)
	public void testStampaProcessoEccezione() throws StampaException 
	{
		CodaStampa s=new CodaStampa();
		ProcessoStampa p1=new ProcessoStampa();
		ProcessoStampa p2=new ProcessoStampa();
		s.stampaProcesso();
	}

	@Test 
	public void testProcessiPcAlfabetico() throws StampaException, GeneralException 
	{
		CodaStampa s=new CodaStampa();
		ProcessoStampa p1=new ProcessoStampa("pc1","word","test");
		ProcessoStampa p2=new ProcessoStampa("pc2","excel","ciao");
		ProcessoStampa p3=new ProcessoStampa("pc1","word","koala");
		ProcessoStampa p4=new ProcessoStampa("pc2","excel","ciao");
		s.aggiungiProcesso(p1);
		s.aggiungiProcesso(p2);
		s.aggiungiProcesso(p3);
		s.aggiungiProcesso(p4);
		
		ProcessoStampa[] elenco=s.processiPcAlfabetico("pc1");
		ProcessoStampa[] elencoVerifica= {p3,p1};
		assertArrayEquals("ProcessiPcAlfabetico",elencoVerifica,elenco);
	}
}
