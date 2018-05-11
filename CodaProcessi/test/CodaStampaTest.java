import static org.junit.Assert.*;

import java.io.IOException;

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
	

	@Test (expected=GeneralException .class)
	public void testProcessiPcAlfabeticoGeneralException() throws StampaException, GeneralException 
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
		
		ProcessoStampa[] elenco=s.processiPcAlfabetico("pc3");
	}
	
	@Test (expected=StampaException .class)
	public void testProcessiPcAlfabeticoStampaException() throws StampaException, GeneralException 
	{
		CodaStampa s=new CodaStampa();
		ProcessoStampa[] elenco=s.processiPcAlfabetico("pc3");
	}
	
	@Test 
	public void testGetProcesso() throws StampaException, GeneralException 
	{
		CodaStampa s=new CodaStampa();
		ProcessoStampa p1=new ProcessoStampa("pc1","word","test");
		ProcessoStampa p2=new ProcessoStampa("pc2","excel","ciao");
		s.aggiungiProcesso(p1);
		s.aggiungiProcesso(p2);
		assertTrue("getProcesso",s.getProcesso(p1.getCodiceID())==p1);
	}
	
	@Test (expected=StampaException .class)
	public void testGetProcessoStampaException() throws StampaException, GeneralException 
	{
		CodaStampa s=new CodaStampa();
		s.getProcesso(2);
	}
	
	@Test (expected=GeneralException .class)
	public void testGetProcessoGeneralException() throws StampaException, GeneralException 
	{
		CodaStampa s=new CodaStampa();
		ProcessoStampa p1=new ProcessoStampa("pc1","word","test");
		ProcessoStampa p2=new ProcessoStampa("pc2","excel","ciao");
		s.aggiungiProcesso(p1);
		s.aggiungiProcesso(p2);
		s.getProcesso(44);
	}
	
	@Test 
	public void testAnnullaStampa1() throws StampaException, GeneralException, IOException, FileException 
	{
		CodaStampa s=new CodaStampa();
		ProcessoStampa p1=new ProcessoStampa("pc1","word","test");
		s.aggiungiProcesso(p1);
		s.annullaStampa(p1.getCodiceID(), "logTest.txt");
		assertTrue("Annulla un processo",s.getHead()==null);
	}
	
	@Test 
	public void testAnnullaStampa2() throws StampaException, GeneralException, IOException, FileException 
	{
		CodaStampa s=new CodaStampa();
		ProcessoStampa p1=new ProcessoStampa("pc1","word","test");
		ProcessoStampa p2=new ProcessoStampa("pc2","excel","ciao");
		s.aggiungiProcesso(p1);
		s.aggiungiProcesso(p2);
		s.annullaStampa(p2.getCodiceID(), "logTest.txt");
		assertTrue("Annulla un processo con un solo processo",s.getElementi()==1 && s.getProcesso(p1.getCodiceID())==p1);
	}
	
	@Test 
	public void testAnnullaStampa3() throws StampaException, GeneralException, IOException, FileException 
	{
		CodaStampa s=new CodaStampa();
		ProcessoStampa p1=new ProcessoStampa("pc1","word","test");
		ProcessoStampa p2=new ProcessoStampa("pc2","excel","ciao");
		s.aggiungiProcesso(p1);
		s.aggiungiProcesso(p2);
		s.annullaStampa(p2.getCodiceID(), "logTest.txt");
		assertTrue("Annulla un processo il posizione 1",s.getElementi()==1 && s.getProcesso(p1.getCodiceID())==p1);
	}
	
	@Test 
	public void testAnnullaStampa4() throws StampaException, GeneralException, IOException, FileException 
	{
		CodaStampa s=new CodaStampa();
		ProcessoStampa p1=new ProcessoStampa("pc1","word","test");
		ProcessoStampa p2=new ProcessoStampa("pc2","excel","ciao");
		s.aggiungiProcesso(p1);
		s.aggiungiProcesso(p2);
		s.annullaStampa(p1.getCodiceID(), "logTest.txt");
		assertTrue("Annulla un processo ",s.getElementi()==1 && s.getProcesso(p2.getCodiceID())==p2);
	}
	
	@Test (expected=StampaException .class)
	public void testAnnullaStampaStampaException() throws StampaException, GeneralException, IOException, FileException 
	{
		CodaStampa s=new CodaStampa();
		s.annullaStampa(1, "logTest.txt");
		
	}
	
	@Test (expected=GeneralException .class)
	public void testAnnullaStampaGeneralException() throws StampaException, GeneralException, IOException, FileException 
	{
		CodaStampa s=new CodaStampa();
		ProcessoStampa p1=new ProcessoStampa("pc1","word","test");
		ProcessoStampa p2=new ProcessoStampa("pc2","excel","ciao");
		s.aggiungiProcesso(p1);
		s.aggiungiProcesso(p2);
		s.annullaStampa(200, "log.txt");
	}
	
	@Test (expected=IOException .class)
	public void testAnnullaStampaIOException() throws StampaException, GeneralException, IOException, FileException 
	{
		CodaStampa s=new CodaStampa();
		ProcessoStampa p1=new ProcessoStampa("pc1","word","test");
		ProcessoStampa p2=new ProcessoStampa("pc2","excel","ciao");
		s.aggiungiProcesso(p1);
		s.aggiungiProcesso(p2);
		s.annullaStampa(p1.getCodiceID(), "Z:\\stampeProgetti\\p1_completate.txt");
	}
	
	@Test 
	public void testPortaInTesta() throws StampaException, GeneralException 
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
		s.portaInTesta(p2.getCodiceID());
		assertTrue("Porta in testa processo ",s.stampaProcesso().equals(p2));
	}
	
	@Test (expected=StampaException .class)
	public void testPortaInTestaStampaException() throws StampaException, GeneralException 
	{
		CodaStampa s=new CodaStampa();
		s.portaInTesta(2);
		
	}
	
	@Test (expected=GeneralException .class)
	public void testPortaInTestaGeneralException() throws StampaException, GeneralException 
	{
		CodaStampa s=new CodaStampa();
		ProcessoStampa p1=new ProcessoStampa("pc1","word","test");
		ProcessoStampa p2=new ProcessoStampa("pc2","excel","ciao");
		s.aggiungiProcesso(p1);
		s.aggiungiProcesso(p2);
		s.portaInTesta(333);
	}
	
	@Test 
	public void testToStringVuoto() 
	{
		CodaStampa s=new CodaStampa();
		assertTrue("toString ",s.toString().equals("Head-->Vuota"));
	}
	
	@Test 
	public void testToString() 
	{
		CodaStampa s=new CodaStampa();
		ProcessoStampa p1=new ProcessoStampa("pc1","word","test");
		s.aggiungiProcesso(p1);
		String risultato="Head-->"+p1.toString();
		assertTrue("toString ",s.toString().equals(risultato));
	}
	
	@Test (expected=IOException .class)
	public void testSerializzazioneEccezione() throws IOException 
	{
		CodaStampa s=new CodaStampa();
		ProcessoStampa p1=new ProcessoStampa("pc1","word","test");
		s.aggiungiProcesso(p1);
		s.salvaStampa("Z:\\stampeProgetti\\p1_completate.txt");
	}
	
	@Test
	public void testSerializzazioneDeserializzazione() throws IOException, ClassNotFoundException 
	{
		CodaStampa s=new CodaStampa();
		ProcessoStampa p1=new ProcessoStampa("pc1","word","test");
		s.aggiungiProcesso(p1);
		s.salvaStampa("codaStampaTest.bin");
		CodaStampa stampanteCopia=s.caricaStampa("codaStampaTest.bin");
		assertTrue("Serializzazione e Deserializzzazione",s.toString().equals(stampanteCopia.toString()));
	}
	
	
}
