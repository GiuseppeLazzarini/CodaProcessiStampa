import static org.junit.Assert.*;

import org.junit.Test;

public class ProcessoStampaTest 
{
	@Test
	public void testCostruttoreProcessoStampa() 
	{
		ProcessoStampa p=new ProcessoStampa("pc1","word","ciao");
		assertTrue("Costruttore principale ProcessoStampa",p.getPc().compareTo("pc1")==0 && p.getFormatoFile().compareTo("word")==0 && p.getNomeProcesso().compareTo("ciao")==0);
	}
	
	@Test
	public void testCostruttoreProcessoStampaDefault() 
	{
		ProcessoStampa p=new ProcessoStampa();
		assertTrue("Costruttore di default ProcessoStampa",p.getPc().compareTo("")==0 && p.getFormatoFile().compareTo("")==0 && p.getNomeProcesso().compareTo("")==0);
	}

	@Test
	public void testSetPc() 
	{
		ProcessoStampa p=new ProcessoStampa();
		p.setPc("pc1");
		assertTrue("setPc",p.getPc().compareTo("pc1")==0);
	}
	
	@Test
	public void testSetFormatoFile() 
	{
		ProcessoStampa p=new ProcessoStampa();
		p.setFormatoFile("word");
		assertTrue("setFormatoFile",p.getFormatoFile().compareTo("word")==0);
	}
	
	@Test
	public void testSetNomeProcesso() 
	{
		ProcessoStampa p=new ProcessoStampa();
		p.setNomeProcesso("ciao");
		assertTrue("setNomeProcesso",p.getNomeProcesso().compareTo("ciao")==0);
	}
	


}
