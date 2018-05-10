import java.io.Serializable;
import java.time.LocalDateTime;

public class ProcessoStampa implements Serializable
{
	
	private String pc;
	private String formatoFile;
	private String nomeProcesso;
	private LocalDateTime dataOra;
	private static int contatore;
	private int codiceID;
	
	
	public ProcessoStampa(String pc,String formatoFile,String nomeProcesso)
	{
	
		contatore++;
		setCodiceID(contatore);
		setPc(pc);
		setFormatoFile(formatoFile);
		setNomeProcesso(nomeProcesso);
		dataOra=LocalDateTime.now();
		
	}
	public ProcessoStampa()
	{
		contatore++;
		setCodiceID(contatore);
		this.pc="";
		this.formatoFile="";
		this.nomeProcesso="";
		dataOra=LocalDateTime.now();
	}
	
	
	public String getPc() {
		return pc;
	}
	public void setPc(String pc) {
		this.pc = pc;
	}
	public String getFormatoFile() {
		return formatoFile;
	}
	public void setFormatoFile(String formatoFile) {
		this.formatoFile = formatoFile;
	}
	public String getNomeProcesso() {
		return nomeProcesso;
	}
	public void setNomeProcesso(String nomeProcesso) {
		this.nomeProcesso = nomeProcesso;
	}
	public LocalDateTime getDataOra() {
		return dataOra;
	}
	public void setDataOra(LocalDateTime dataOra) {
		this.dataOra = dataOra;
	}
	public static int getContatore() {
		return contatore;
	}
	public int getCodiceID() {
		return codiceID;
	}


	public void setCodiceID(int codiceID) {
		this.codiceID = codiceID;
	}
	
	public boolean equals(ProcessoStampa p)
	{
		if (getCodiceID()==p.getCodiceID())
			return true;
		else
			return false;
	}
	
	public String toString()
	{
		String processo="";
		processo+=getCodiceID()+" "+getPc()+" "+getFormatoFile()+" "+getNomeProcesso()+" "+dataOra+" ";
		return processo;
	}


	


	


	
	
	
}
