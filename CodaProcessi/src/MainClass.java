import java.io.IOException;

public class MainClass {
	static ConsoleInput tastiera=new ConsoleInput();
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		String[] elencoFunzioni=new String[8];
		elencoFunzioni[0]="0-->Esci";
		elencoFunzioni[1]="1-->Aggiungi un processo di stampa";
		elencoFunzioni[2]="2-->Visualizza i processi in fase di stampa";
		elencoFunzioni[3]="3-->Stampa processo";
		elencoFunzioni[4]="4-->Elimina processo di stampa(inserisci codiceID del processo)";
		elencoFunzioni[5]="5-->Porta processo in testa alla code(inserisci codiceID del processo)";
		elencoFunzioni[6]="6-->Visualizza i dati relativi a un processo(inserisci codiceID del processo)";
		elencoFunzioni[7]="7-->Visualizza i processi in ordine alfabetico relativi ad un pc";
		
		CodaStampa stampante=new CodaStampa();
		try 
		{
			stampante=stampante.caricaStampa("codaStampa.bin");
			System.out.println("file caricati");
			if(stampante.getElementi()!=0)
				ProcessoStampa.setContatore(stampante.getHead().getInfo().getCodiceID());
		} catch (ClassNotFoundException e1) 
		{
			System.out.println("Impossibile caricare oggetti di tipo ProcessoStampa");
		}
		catch (IOException e1) 
		{
			System.out.println("Impossibile leggere da file");
		}
		
		Menu menu=new Menu(elencoFunzioni);
		int scelta=0;
		
		do 
		{
			scelta=menu.scelta();
			switch (scelta) 
			{
			case 0:
			{

				try 
				{
					stampante.salvaStampa("codaStampa.bin");
					System.out.println("salvataggio stampante avvenuta con successo");
				} 
				catch (IOException e) 
				{
					System.out.println("Errore sorgente informativa. Operazione annullata.");
				}
				
			}
			break;
			case 1:
			{
				ProcessoStampa p=new ProcessoStampa();
				try 
				{
					System.out.println("Inserisci nome del pc di stampa: ");
					p.setPc(tastiera.readString());
					System.out.println("Inserisci formato del file(es.word,excel..): ");
					p.setFormatoFile(tastiera.readString());
					System.out.println("Inserisci nome del file da stampare: ");
					p.setNomeProcesso(tastiera.readString());
					stampante.aggiungiProcesso(p);
					System.out.println("Processo aggiunto");
					stampante.salvaStampa("codaStampa.bin");
					System.out.println("salvataggio modifica avvenuta con successo");
				} catch (NumberFormatException e) 
				{
					System.out.println("Formato dati errato. Operazione annullata.");
					
				} catch (IOException e) 
				{
					System.out.println("Errore sorgente informativa. Operazione annullata.");
				}
			}
			break;
			case 2:
			{
				System.out.println(stampante.toString());
			}
			break;
			case 3:
			{
				try 
				{
					ProcessoStampa p=stampante.stampaProcesso();
					System.out.println("Il porcesso stampato è:\n"+p.toString());
					stampante.salvaStampa("codaStampa.bin");
					System.out.println("salvataggio modifica avvenuta con successo");
				} catch (StampaException e) 
				{
					System.out.println(e.toString());
				} 
				catch (IOException e) 
				{
					System.out.println("Errore sorgente informativa. Operazione annullata.");
				}
				
				
			}
			break;
			case 4:
			{
				
				try 
				{
					
					System.out.println(stampante.toString());
					System.out.println("Inserisci codiceID realativo al processo da eliminare");
					stampante.annullaStampa(tastiera.readInt(), "log.txt");
					System.out.println("Stampa processo annullata correttamente");
					stampante.salvaStampa("codaStampa.bin");
					System.out.println("salvataggio modifica avvenuta con successo");
				} catch (NumberFormatException e) 
				{
					System.out.println("Formato dati errato. Operazione annullata.");
				} 
				catch (StampaException e) 
				{
					System.out.println(e.toString());
				}
				catch (GeneralException e) 
				{
					System.out.println(e.toString());
				} catch (IOException e) 
				{
					System.out.println("Errore sorgente informativa. Operazione annullata.");
				} 
				catch (FileException e) 
				{
					System.out.println(e.toString());
				}
	
			}
			break;
			case 5:
			{
				try 
				{
					System.out.println(stampante.toString());
					System.out.println("Inserisci codiceID realativo al processo da portare in testa: ");
					stampante.portaInTesta(tastiera.readInt());
					System.out.println("Ecco la coda di stampa aggiornata: ");
					System.out.println(stampante.toString());
					stampante.salvaStampa("codaStampa.bin");
					System.out.println("salvataggio modifica avvenuta con successo");
				} 
				catch (NumberFormatException e)
				{
					System.out.println("Formato dati input errato. Operazione annullata.");
				}
				catch (StampaException e) 
				{
					System.out.println(e.toString());
				} 
				catch (IOException e) 
				{
					System.out.println("Errore sorgente informativa. Operazione annullata.");
				} 
				catch (GeneralException e) 
				{
					System.out.println(e.toString());
				}
			}
			break;
			case 6:
			{
				try 
				{
					System.out.println(stampante.toString());
					System.out.println("Inserisci codiceID realativo al processo da visualizzare: ");
					System.out.println(stampante.getProcesso(tastiera.readInt()).toString());
				} 
				catch (NumberFormatException e) 
				{
					System.out.println("Formato dati errato. Operazione annullata.");
					
				} 
				catch (StampaException e) 
				{
					System.out.println(e.toString());
				} 
				catch (GeneralException e) 
				{
					System.out.println(e.toString());
				}
				catch (IOException e) 
				{
					System.out.println("Errore sorgente informativa. Operazione annullata.");
				}
			}
			break;
			case 7:
			{
				
				try 
				{
					ProcessoStampa[] p;
					System.out.println("Inserisci il nome del pc a cui appartengono i pc da visualizzare: ");
					p=stampante.processiPcAlfabetico(tastiera.readString());
					for (int i = 0; i < p.length; i++) 
					{
						System.out.println(p[i].toString());
					}
				} 
				catch (NumberFormatException e) 
				{
					System.out.println("Formato dati errato. Operazione annullata.");
				}
				catch (StampaException e)
				{
					System.out.println(e.toString());
				} 
				catch (GeneralException e) 
				{
					System.out.println(e.toString());
				} 
				catch (IOException e) 
				{
					System.out.println("Errore sorgente informativa. Operazione annullata.");
				}
			}
			break;
			default:
				System.out.println("Scelta non consentita");
				break;
			}
			
			System.out.println("Premi un tasto per continuare...");
			try {
				tastiera.readString();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		} while (scelta!=0);
	}
		

}
