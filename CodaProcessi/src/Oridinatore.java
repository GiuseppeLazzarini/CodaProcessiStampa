
public class Oridinatore 
{
	
	public static int scambia(ProcessoStampa[]array,int pos1,int pos2)
	{
		ProcessoStampa s;
		if(pos1<0 || pos2<0 || pos1>=array.length || pos2>=array.length)
			return -1;
		else
		{
			s=array[pos1];
			array[pos1]=array[pos2];
			array[pos2]=s;
			return 0;
		}
	}
	private static ProcessoStampa[] copiaArray(ProcessoStampa[]array)
	{
		ProcessoStampa[]arrayCopia=new ProcessoStampa[array.length];
		for (int i = 0; i < arrayCopia.length; i++) 
			arrayCopia[i]=array[i];
		return arrayCopia;
	}
	public static ProcessoStampa[]OrdinaProcessiAlfabetico(ProcessoStampa[] array)
	{
		ProcessoStampa[] arrayCopia=copiaArray(array);
		for (int i = 0; i < arrayCopia.length-1; i++) 
		{
			for (int j = i+1; j < arrayCopia.length; j++) 
			{
				if(arrayCopia[i].getNomeProcesso().compareToIgnoreCase(arrayCopia[j].getNomeProcesso())>0)
					scambia(arrayCopia,i,j);
			}
		}
		return arrayCopia;
	}
}
