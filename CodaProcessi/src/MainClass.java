import java.io.IOException;

public class MainClass {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		ProcessoStampa p1=new ProcessoStampa("pc1","word","primo");
		ProcessoStampa p2=new ProcessoStampa("pc1","excel","muori");
		ProcessoStampa p3=new ProcessoStampa("pc2","excel","t");
		ProcessoStampa p4=new ProcessoStampa("pc1","powerpoint","va");
		
		CodaStampa s1=new CodaStampa();
		
		s1.aggiungiProcesso(p1);
		s1.aggiungiProcesso(p2);
		s1.aggiungiProcesso(p3);
		s1.aggiungiProcesso(p4);
		System.out.println(s1.toString());
		
		
		try 
		{
			s1.annullaStampa(4, "log.txt");
			System.out.println(s1.toString());
		} catch (StampaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeneralException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		
	}

}
