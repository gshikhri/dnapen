
public class DNASequence {

	public String DNASequence;
	public boolean isUsed;
	
	public DNASequence(String seq){
		this.DNASequence=seq;
		this.isUsed=false;
	}
	public String getSequence(){
		this.isUsed=true;
		return(DNASequence);
		
	}
}
