
public class MainClass {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		ProcessoStampa p1=new ProcessoStampa("pc1","word","v");
		ProcessoStampa p2=new ProcessoStampa("pc1","excel","i");
		ProcessoStampa p3=new ProcessoStampa("pc2","excel","t");
		ProcessoStampa p4=new ProcessoStampa("pc1","powerpoint","b");
		
		CodaStampa s1=new CodaStampa();
		
		s1.enqueue(p1);
		s1.enqueue(p2);
		s1.enqueue(p3);
		s1.enqueue(p4);
		ProcessoStampa[] prova;
		try 
		{
			prova=s1.processiPcAlfabetico("pc1");
			for (int i = 0; i < prova.length; i++) 
			{
				System.out.println(prova[i].toString());
			}
		} catch (StampaException e) 
		{
			System.out.println(e.toString());
		} 
		catch (GeneralException e) 
		{
			System.out.println(e.toString());
		}
		
		
	}

}