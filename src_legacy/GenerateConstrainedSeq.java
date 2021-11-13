import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class GenerateConstrainedSeq {
	static ArrayList<DNASequence> TempSeqList= new ArrayList<DNASequence>();
	static ArrayList<DNASequence> SeqList= new ArrayList<DNASequence>();
	
	public GenerateConstrainedSeq(){
		TempSeqList.clear();
		SeqList.clear();
		runRandomSequences();
		
	}

	 public static void runRandomSequences() {
		 
			String csvFile = "final.csv";
			BufferedReader br = null;
			String line = "";
			String cvsSplitBy = ",";
		 
			try {
		 
				br = new BufferedReader(new FileReader(csvFile));
				while ((line = br.readLine()) != null) {
		 
				        // use comma as separator
					TempSeqList.add(new DNASequence(line.toString()));
			
				}
				
				for(int i=0;i<TempSeqList.size();i++){
					  SeqList.add(new DNASequence(changesize(TempSeqList.get(i).getSequence())));
				  }
		 
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		  }

	 public static String changesize(String domainSeq) {
	        String Seq = "";
	        int stringLength = domainSeq.length();

	        for (int i = 0; i <10; i++) {
	            if (domainSeq.charAt(i) == 'A') {
	                Seq += "A";
	            } else if (domainSeq.charAt(i) == 'T') {
	                Seq += "T";
	            } else if (domainSeq.charAt(i) == 'G') {
	                Seq += "G";
	            } else if (domainSeq.charAt(i) == 'C') {
	                Seq += "C";
	            }
	        }
	  //      System.out.println(""+Seq);
	        return (Seq);
	    }


}
