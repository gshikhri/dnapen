import java.util.ArrayList;
import java.util.Random;

import org.imgscalr.Main;


public class AssignConstrainedSeq {
	static GenerateConstrainedSeq g;
	static ArrayList<DNASequence> SeqList;
	static ArrayList<Domains> xyDomainList=new ArrayList<Domains>();
	static ArrayList<Domains> xyDomainListFree = new ArrayList<Domains>();
	 public String domainSeqOne;
	 public String domainSeqTwo;
	 public String domainSeqThree;
     public String domainSeqFour;
     public String CompleteDomainSeq; 
     
//     int h=FreeGridActionListener.tileHeight;
//     int w=FreeGridActionListener.tileWidth;
     
     int w=1050/7;
     int h=700/3;
     
    static int i1;
	static int i2;
	static int i3;
	static int i4;
     
     public AssignConstrainedSeq(){
    	 g=new GenerateConstrainedSeq();
    	 SeqList= GenerateConstrainedSeq.SeqList;
    	 System.out.println("AssiginconstrainedSeq called");
    	 int i,j,counter=0;
    	 xyDomainList.clear();
    	 xyDomainListFree.clear();
    	 
    	 if(MainFrame.currentWindow==3){
	         for(j=0;j<23;j++)
	             for(i=0;i<15;i++){
	                 if(j%2==1){
	                     if(i<14)  {
	                    	 generateDigitizedSeq(i,j,counter);
	                         counter++;
	                     }
	                 }
	                 else if(j%2==0) {
	                	 generateDigitizedSeq(i,j,counter);
	                     counter++;
	                 }
	             }
    	 }
    	 
    	 else if(MainFrame.currentWindow==2){
    		 	counter=0;
    		 	w=1050/EditDimensionActionListener.TilewallWidth;
                h=699/EditDimensionActionListener.TileWallHeight;
    	        for(j=0;j<h;j++)
    	            for(i=0;i<w;i++){
    	            	
    	            	System.out.println("assigning for:"+i+","+j);
    	                if(j%2==1){
    	                    if(i<w-1)  {
    	                    	generateFreeGridSeq(i,j,counter);
    	                        counter++;
    	                    }
    	                }
    	                else if(j%2==0) {
    	                	generateFreeGridSeq(i,j,counter);
    	                    counter++;
    	                }
    	            }
    	 }

     }
	
     public void generateDigitizedSeq(int i, int j, int entry) {
     	//P=protector bricks, R=random
         if(j==0){
             if(i==0){
                 domainSeqOne = "TTTTTTTTTT";
                 domainSeqTwo = AssignRandom(2,11,"T");
                 domainSeqThree = AssignRandom(3,11,"G");
                 domainSeqFour = "TTTTTTTTTT";
             }

             else if(i==14){
                 domainSeqOne = AssignRandom(1,10,"C");
                 domainSeqTwo = "TTTTTTTTTTT";
                 domainSeqThree = "TTTTTTTTTTT";
                 domainSeqFour = AssignRandom(4,10,"A");
             }

             else {
            	 do{
	                 domainSeqOne = AssignRandom(1,10,"C");
	                 domainSeqTwo = AssignRandom(2,11,"T");
	                 domainSeqThree = AssignRandom(3,11,"G");
	                 domainSeqFour = AssignRandom(4,10,"A");
            	 }while(!checkGCContent(domainSeqOne, domainSeqTwo, domainSeqThree, domainSeqFour));
             }

             //xyDomainList.add(new Domains(i,j,domainSeqOne,domainSeqTwo,domainSeqThree,domainSeqFour));
             //entry++;

             //System.out.print("\n one "+i+" "+j+" "+entry);
         }

         else if(j%2==1){
             int temp;

             temp=entry-15;

             domainSeqFour = negateSeqRev(xyDomainList.get(temp).domainSeqTwo) ;
             temp++;
             domainSeqThree = negateSeqRev(xyDomainList.get(temp).domainSeqOne) ;
             do{
	             domainSeqOne = AssignRandom(1,11,"C");
	             domainSeqTwo = AssignRandom(2,10,"T");
             }while(!checkGCContent(domainSeqOne, domainSeqTwo, domainSeqThree, domainSeqFour));
             
             //System.out.print("\n two "+i+" "+j+ " "+entry);

         }
         else if((j>0)&&(j%2==0)){
             int temp;
             temp=entry-14;
             if(i==0){

                 domainSeqOne = "TTTTTTTTTT";
                 domainSeqFour = "TTTTTTTTTT";
                 domainSeqTwo = AssignRandom(2,11,"T");
                 domainSeqThree = negateSeqRev(xyDomainList.get(temp).domainSeqOne) ;

 //                System.out.print("\n three "+i+" "+j+" "+entry);
             }
             else if(i==14){
                 temp--;
                 domainSeqFour = negateSeqRev(xyDomainList.get(temp).domainSeqTwo) ;
                 domainSeqOne = AssignRandom(1,10,"C");
                 domainSeqTwo = "TTTTTTTTTTT";
                 domainSeqThree = "TTTTTTTTTTT";

  //               System.out.print("\n four "+i+" "+j+" "+entry);

             }

             else{
                 temp--;
                 domainSeqFour = negateSeqRev(xyDomainList.get(temp).domainSeqTwo) ;
                 temp++;
                 domainSeqThree = negateSeqRev(xyDomainList.get(temp).domainSeqOne) ;
                 do{
                	 domainSeqOne = AssignRandom(1,10,"C");
                	 domainSeqTwo = AssignRandom(2,11,"T");
                 }while(!checkGCContent(domainSeqOne, domainSeqTwo, domainSeqThree, domainSeqFour));
 //                System.out.print("\n five "+i+" "+j+" "+entry);
             }
         }
         xyDomainList.add(new Domains(i,j,domainSeqOne,domainSeqTwo,domainSeqThree,domainSeqFour,false));
     }

	
	
	
	
	
     public void generateFreeGridSeq(int i, int j, int entry){
    	 if(j==0){
             if(i==0){
                 domainSeqOne ="TTTTTTTTTT";
                 domainSeqTwo =AssignRandom(2,11,"T");
                 domainSeqThree =AssignRandom(3,11,"G");
                 domainSeqFour ="TTTTTTTTTT";
             }

             else if(i==w-1){
                 domainSeqOne =AssignRandom(1,10,"C");
                 domainSeqTwo ="TTTTTTTTTTT";
                 domainSeqThree ="TTTTTTTTTTT";
                 domainSeqFour =AssignRandom(4,10,"A");
             }

             else {
            	 do{
	                 domainSeqOne =AssignRandom(1,10,"C");
	                 domainSeqTwo =AssignRandom(2,11,"T");
	                 domainSeqThree =AssignRandom(3,11,"G");
	                 domainSeqFour =AssignRandom(4,10,"A");
            	 }while(!checkGCContent(domainSeqOne, domainSeqTwo, domainSeqThree, domainSeqFour));
             }

             //xyDomainListFree.add(new Domains(i,j,domainSeqOne,domainSeqTwo,domainSeqThree,domainSeqFour));
             //entry++;

             //System.out.print("\n one "+i+" "+j+" "+entry);
         }

         else if(j%2==1){
             int temp;

             temp=entry-w;

             domainSeqFour = negateSeqRev(xyDomainListFree.get(temp).domainSeqTwo) ;
             temp++;
             domainSeqThree = negateSeqRev(xyDomainListFree.get(temp).domainSeqOne) ;
             do{
	             domainSeqOne =AssignRandom(1,11,"C");
	             domainSeqTwo =AssignRandom(2,10,"T");
             }while(!checkGCContent(domainSeqOne, domainSeqTwo, domainSeqThree, domainSeqFour));
             //System.out.print("\n two "+i+" "+j+ " "+entry);

         }
         else if((j>0)&&(j%2==0)){
             int temp;
             temp=entry-w-1;
             if(i==0){

                 domainSeqOne ="TTTTTTTTTT";
                 domainSeqFour ="TTTTTTTTTT";
                 domainSeqTwo =AssignRandom(2,11,"T");

                 domainSeqThree = negateSeqRev(xyDomainListFree.get(temp).domainSeqOne) ;
                 //System.out.print("\n three "+i+" "+j+" "+entry);
             }
             else if(i==w-1){
                 temp--;
                 domainSeqFour = negateSeqRev(xyDomainListFree.get(temp).domainSeqTwo) ;
                 domainSeqOne =AssignRandom(1,10,"C");
                 domainSeqTwo ="TTTTTTTTTTT";
                 domainSeqThree ="TTTTTTTTTTT";

                 //System.out.print("\n four "+i+" "+j+" "+entry);
             }

             else{

                 temp--;
                 domainSeqFour = negateSeqRev(xyDomainListFree.get(temp).domainSeqTwo) ;
                 temp++;
                 domainSeqThree = negateSeqRev(xyDomainListFree.get(temp).domainSeqOne) ;
                 do{
	                 domainSeqOne =AssignRandom(1,10,"C");
	                 domainSeqTwo =AssignRandom(2,11,"T");
                 }while(!checkGCContent(domainSeqOne, domainSeqTwo, domainSeqThree, domainSeqFour));

                 //System.out.print("\n five "+i+" "+j+" "+entry);
             }
         }
    	 xyDomainListFree.add(new Domains(i,j,domainSeqOne,domainSeqTwo,domainSeqThree,domainSeqFour,false));
     }
     public static String AssignRandom(int index, int len, String c){
			
			String Strand=new String("");
			Random rand = new Random(); 
			int randomIndex;
			randomIndex= rand.nextInt(SeqList.size()); 

			i1=-1;
			i2=-1;
			i3=-1;
			i4=-1;
			
			if(len==10){
				if(c.equals("A")){
				    while(SeqList.get(randomIndex).isUsed==true||
				    		SeqList.get(randomIndex).DNASequence.charAt(0)!='A'||
				    		SeqList.get(randomIndex).DNASequence.charAt(9)!='A'){
				    	randomIndex= rand.nextInt(SeqList.size()); 
				    }
				}
				else if(c.equals("T")){
				    while(SeqList.get(randomIndex).isUsed==true||
				    		SeqList.get(randomIndex).DNASequence.charAt(0)!='T'||
				    		SeqList.get(randomIndex).DNASequence.charAt(9)!='T'){
				    	randomIndex= rand.nextInt(SeqList.size()); 
				    }
				}
				else if(c.equals("G")){
				    while(SeqList.get(randomIndex).isUsed==true||
				    		SeqList.get(randomIndex).DNASequence.charAt(0)!='G'||
				    		SeqList.get(randomIndex).DNASequence.charAt(9)!='G'){
				    	randomIndex= rand.nextInt(SeqList.size()); 
				    }
				}
				else if(c.equals("C")){
				    while(SeqList.get(randomIndex).isUsed==true||
				    		SeqList.get(randomIndex).DNASequence.charAt(0)!='C'||
				    		SeqList.get(randomIndex).DNASequence.charAt(9)!='C'){
				    	randomIndex= rand.nextInt(SeqList.size()); 
				    }
				}
				Strand=SeqList.get(randomIndex).getSequence();
				SeqList.get(randomIndex).isUsed=true;
			}
			else if(len==11){
				if(c.equals("A")){
					Strand="A";
				    while(SeqList.get(randomIndex).isUsed==true||
				    		SeqList.get(randomIndex).DNASequence.charAt(9)!='A'){
				    	randomIndex= rand.nextInt(SeqList.size()); 
				    }
				}
				else if(c.equals("T")){
					Strand="T";
				    while(SeqList.get(randomIndex).isUsed==true||
				    		SeqList.get(randomIndex).DNASequence.charAt(9)!='T'){
				    	randomIndex= rand.nextInt(SeqList.size()); 
				    }
				}
				else if(c.equals("G")){
					Strand="G";
				    while(SeqList.get(randomIndex).isUsed==true||
				    		SeqList.get(randomIndex).DNASequence.charAt(9)!='G'){
				    	randomIndex= rand.nextInt(SeqList.size()); 
				    }
				}
				else if(c.equals("C")){
					Strand="C";
				    while(SeqList.get(randomIndex).isUsed==true||
				    		SeqList.get(randomIndex).DNASequence.charAt(9)!='C'){
				    	randomIndex= rand.nextInt(SeqList.size()); 
				    }
				}
				Strand=Strand.concat(SeqList.get(randomIndex).getSequence());
				SeqList.get(randomIndex).isUsed=true;
			}
			
			if(index==1)
				i1=index;
			else if(index==2)
				i2=index;
			else if(index==3)
				i3=index;
			else 
				i4=index;
     	//System.out.println("String generated:"+Strand);
			return(Strand);
		    
		}
	
	public boolean checkGCContent(String d1, String d2, String d3, String d4){
		
		int l1=d1.length();
		int l2=d2.length();
		int l3=d3.length();
		int l4=d4.length();
		int GCcount=0,i;
		boolean isGCsatisfied=true;
		
		if(MainFrame.GCrangeFrom!=0&&MainFrame.GCRangeTo!=0){
	
		    for (i=0; i<l1; i++) {
	            if ((d1.charAt(i) == 'G')||(d1.charAt(i) == 'C')) 
	                GCcount+=1;
		    }
		    for (i=0; i<l2; i++) {
	            if ((d2.charAt(i) == 'G')||(d2.charAt(i) == 'C')) 
	                GCcount+=1;
		    }
		    for (i=0; i<l3; i++) {
	            if ((d3.charAt(i) == 'G')||(d3.charAt(i) == 'C')) 
	                GCcount+=1;
		    }
		    for (i=0; i<l4; i++) {
	            if ((d4.charAt(i) == 'G')||(d4.charAt(i) == 'C')) 
	                GCcount+=1;
		    }
		    
		    if(!(GCcount<=25&&GCcount>=16)){
		    	if(i1!=-1)
		    		SeqList.get(i1).isUsed=false;
		    	if(i2!=-1)
		    		SeqList.get(i2).isUsed=false;
		    	if(i3!=-1)
		    		SeqList.get(i3).isUsed=false;
		    	if(i4!=-1)
		    		SeqList.get(i4).isUsed=false;
		    	
		    	isGCsatisfied=false;
		    		
		    }
		
		}
		return(isGCsatisfied);
		
	}
		 public static String negateSeqRev(String domainSeq) {
		        String Seq = "";
		        int stringLength = domainSeq.length();

		        for (int i = stringLength-1; i >=0; i--) {
		            if (domainSeq.charAt(i) == 'A') {
		                Seq += "T";
		            } else if (domainSeq.charAt(i) == 'T') {
		                Seq += "A";
		            } else if (domainSeq.charAt(i) == 'G') {
		                Seq += "C";
		            } else if (domainSeq.charAt(i) == 'C') {
		                Seq += "G";
		            }
		        }
		        return (Seq);
		    }
		 
		    public static String negateSeq(String domainSeq) {
		        String negateSeq = "";
		        int stringLength = domainSeq.length();
		        System.out.println("StringLength:"+stringLength);
		        for (int i = 0; i <stringLength; i++) {
		            if (domainSeq.charAt(i) == 'A') {
		                negateSeq += "T";
		            } else if (domainSeq.charAt(i) == 'T') {
		                negateSeq += "A";
		            } else if (domainSeq.charAt(i) == 'G') {
		                negateSeq += "C";
		            } else if (domainSeq.charAt(i) == 'C') {
		                negateSeq += "G";
		            }
		        }
		        return (negateSeq);
		    }
}
