import java.util.ArrayList;
import java.util.Random;

import org.imgscalr.Main;


public class AssignOptimizedSeq {
	static GenerateConstrainedSeq g;
	static ArrayList<DNASequence> SeqList;
	static ArrayList<Domains> xyDomainList=new ArrayList<Domains>();
	static ArrayList<Domains> xyDomainListFree = new ArrayList<Domains>();
	static ArrayList<XYCoordinates> xyCoordinatesListFree =new ArrayList<XYCoordinates>();
	 public String domainSeqOne;
	 public String domainSeqTwo;
	 public String domainSeqThree;
     public String domainSeqFour;
     public String CompleteDomainSeq; 
     
//     int h=FreeGridActionListener.tileHeight;
//     int w=FreeGridActionListener.tileWidth;
     
    int w=1050/7;
    int h=700/3;
     
    static int i1=-1;
	static int i2=-1;
	static int i3=-1;
	static int i4=-1;
     
     public AssignOptimizedSeq(){
    	 g=new GenerateConstrainedSeq();
    	 SeqList= GenerateConstrainedSeq.SeqList;
    	 System.out.println("AssiginconstrainedSeq called");
    	 int i,j,counter=0;
    	 xyDomainList.clear();
    	 xyDomainListFree.clear();
    	 
    	 if(MainFrame.currentWindow==3){
    		 if(MainFrame.HighFreeEnergy==true)
    			 findCentralDigitized();
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
    		 if(MainFrame.HighFreeEnergyDigitized==true)
    		 findCentralFree();
    		 	counter=0;
    		 	w=1050/7;
                h=700/3;
    	        for(j=0;j<h;j++)
    	            for(i=0;i<w;i++){
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
     
     public void findCentralDigitized(){
    	 
    	 int indexval;
    	 int minx, miny, maxy, maxx;
    	 int size=DigitalGridActionListener.xyCoordinatesTileList.size();
    	 for(int i=0;i<size;i++)
    		 DigitalGridActionListener.xyCoordinatesTileList.get(i).isCentralTile=true;
    	 
    	 //finding min y-coordinate for each x-coordinate
    	 for(int i=0;i<15;i++){
    		 int j=0;
    		 while(MainFrame.selectedDigitized[i][j]==-1&&j<23){
    			 j++;
    		 }
    		miny=j;
    		indexval=MainFrame.selectedDigitized[i][miny];
    		j=0;
    		while(j<4){
    			if(MainFrame.selectedDigitized[i][miny]!=-1)
    				DigitalGridActionListener.xyCoordinatesTileList.get(indexval).isCentralTile=false;
    			miny++;
    			j++;    				
    		}
    	}
    	 
    	//finding max y-coordinate for each x-coordinate
    	 for(int i=0;i<15;i++){
    		 int j=22;
    		 while(MainFrame.selectedDigitized[i][j]==-1&&j>=0){
    			 j--;
    		 }
    		maxy=j;
    		indexval=MainFrame.selectedDigitized[i][maxy];
    		j=0;
    		while(j<4){
    			if(MainFrame.selectedDigitized[i][maxy]!=-1)
    				DigitalGridActionListener.xyCoordinatesTileList.get(indexval).isCentralTile=false;
    			maxy--;
    			j++;    				
    		}
    	}
    	 
    	 //finding min x-coordinate for each y-coordinate
    	 for(int i=0;i<23;i++){
    		 int j=0;
    		 while(MainFrame.selectedDigitized[j][i]==-1&&j<15){
    			 j++;
    		 }
    		minx=j;
    		indexval=MainFrame.selectedDigitized[minx][i];
    		j=0;
    		while(j<4){
    			if(MainFrame.selectedDigitized[minx][i]!=-1)
    				DigitalGridActionListener.xyCoordinatesTileList.get(indexval).isCentralTile=false;
    			minx++;
    			j++;    				
    		}
    	}
    	 
    	//finding max x-coordinate for each y-coordinate
    	 for(int i=0;i<23;i++){
    		 int j=14;
    		 while(MainFrame.selectedDigitized[j][i]==-1&&j>=0){
    			 j--;
    		 }
    		maxx=j;
    		indexval=MainFrame.selectedDigitized[maxx][i];
    		j=0;
    		while(j<4){
    			if(MainFrame.selectedDigitized[maxx][i]!=-1)
    				DigitalGridActionListener.xyCoordinatesTileList.get(indexval).isCentralTile=false;
    			maxx--;
    			j++;    				
    		}
    	}
     }
     
     public void findCentralFree(){
    	 
    	 int indexval;
    	 int xCoordinate, yCoordinate;
    	 int minx, miny, maxy, maxx;
    	 int size=FreeGridActionListener.xCoordinateTileList.size();
    	 MainFrame.freeTracker=0;
    	 
    	//initialize selection to null
         for(int i=0;i<100;i++)
         	for(int j=0;j<350;j++)
         MainFrame.selectedFree[i][j]=-1;
         
         //marking all tiles as central
    	 for(int i=0;i<size;i++){
    		 xCoordinate=FreeGridActionListener.xCoordinateTileList.get(i);
    		 yCoordinate=FreeGridActionListener.yCoordinateTileList.get(i);
    		 MainFrame.selectedFree[xCoordinate][yCoordinate]=MainFrame.freeTracker;
    		 xyCoordinatesListFree.add(new XYCoordinates(xCoordinate, yCoordinate));
    		 xyCoordinatesListFree.get(i).isCentralTile=true;
    		 MainFrame.freeTracker++;
    	 }
    	 
    	 //finding min y-coordinate for each x-coordinate
    	 for(int i=0;i<100;i++){
    		 int j=0;
    		 while(MainFrame.selectedFree[i][j]==-1&&j<350){
    			 j++;
    		 }
    		miny=j;
    		indexval=MainFrame.selectedFree[i][miny];
    		j=0;
    		while(j<4){
    			if(MainFrame.selectedFree[i][miny]!=-1)
    				xyCoordinatesListFree.get(indexval).isCentralTile=false;
    			miny++;
    			j++;    				
    		}
    	}
    	 
    	//finding max y-coordinate for each x-coordinate
    	 for(int i=0;i<100;i++){
    		 int j=349;
    		 while(MainFrame.selectedFree[i][j]==-1&&j>=0){
    			 j--;
    		 }
    		maxy=j;
    		indexval=MainFrame.selectedFree[i][maxy];
    		j=0;
    		while(j<4){
    			if(MainFrame.selectedFree[i][maxy]!=-1)
    				xyCoordinatesListFree.get(indexval).isCentralTile=false;
    			maxy--;
    			j++;    				
    		}
    	}
    	 
    	 //finding min x-coordinate for each y-coordinate
    	 for(int i=0;i<350;i++){
    		 int j=0;
    		 while(MainFrame.selectedFree[j][i]==-1&&j<100){
    			 j++;
    		 }
    		minx=j;
    		indexval=MainFrame.selectedFree[minx][i];
    		j=0;
    		while(j<4){
    			if(MainFrame.selectedFree[minx][i]!=-1)
    				xyCoordinatesListFree.get(indexval).isCentralTile=false;
    			minx++;
    			j++;    				
    		}
    	}
    	 
    	//finding max x-coordinate for each y-coordinate
    	 for(int i=0;i<350;i++){
    		 int j=99;
    		 while(MainFrame.selectedFree[j][i]==-1&&j>=0){
    			 j--;
    		 }
    		maxx=j;
    		indexval=MainFrame.selectedFree[maxx][i];
    		j=0;
    		while(j<4){
    			if(MainFrame.selectedFree[maxx][i]!=-1)
    				xyCoordinatesListFree.get(indexval).isCentralTile=false;
    			maxx--;
    			j++;    				
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
            	 }while(checkGCContent(domainSeqOne, domainSeqTwo, domainSeqThree, domainSeqFour)==false);
             }

             //xyDomainList.add(new Domains(i,j,domainSeqOne,domainSeqTwo,domainSeqThree,domainSeqFour));
             //entry++;

             System.out.print("\n one "+i+" "+j+" "+entry);
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
             }while(checkGCContent(domainSeqOne, domainSeqTwo, domainSeqThree, domainSeqFour)==false);
         

             System.out.print("\n two "+i+" "+j+ " "+entry);

         }
         else if((j>0)&&(j%2==0)){
             int temp;
             temp=entry-14;
             if(i==0){

                 domainSeqOne = "TTTTTTTTTT";
                 domainSeqFour = "TTTTTTTTTT";
                 domainSeqTwo = AssignRandom(2,11,"T");
                 domainSeqThree = negateSeqRev(xyDomainList.get(temp).domainSeqOne) ;

                 System.out.print("\n three "+i+" "+j+" "+entry);
             }
             else if(i==14){
                 temp--;
                 domainSeqFour = negateSeqRev(xyDomainList.get(temp).domainSeqTwo) ;
                 domainSeqOne = AssignRandom(1,10,"C");
                 domainSeqTwo = "TTTTTTTTTTT";
                 domainSeqThree = "TTTTTTTTTTT";

              //   System.out.print("\n four "+i+" "+j+" "+entry);

             }

             else{
                 temp--;
                 domainSeqFour = negateSeqRev(xyDomainList.get(temp).domainSeqTwo) ;
                 temp++;
                 domainSeqThree = negateSeqRev(xyDomainList.get(temp).domainSeqOne) ;
                 do{
                	 domainSeqOne = AssignRandom(1,10,"C");
                	 domainSeqTwo = AssignRandom(2,11,"T");
                 }while(checkGCContent(domainSeqOne, domainSeqTwo, domainSeqThree, domainSeqFour)==false);
                 
                 //System.out.print("\n five "+i+" "+j+" "+entry);
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
                 }while(checkGCContent(domainSeqOne, domainSeqTwo, domainSeqThree, domainSeqFour)==false);
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
             }while(checkGCContent(domainSeqOne, domainSeqTwo, domainSeqThree, domainSeqFour)==false);
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
                 }while(checkGCContent(domainSeqOne, domainSeqTwo, domainSeqThree, domainSeqFour)==false);

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
        	System.out.println("String generated:"+Strand);
			return(Strand);
		    
		}
	
		public boolean checkHighFreeEnergy(String d1, String d2, String d3, String d4){
			
			int l1=d1.length();
			int l2=d2.length();
			int l3=d3.length();
			int l4=d4.length();
			
			boolean isHigh=true;
			if(MainFrame.HighFreeEnergy==true){
				
			}
			return isHigh;
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
			    
			    if(!(GCcount<=MainFrame.GCRangeTo&&GCcount>=MainFrame.GCrangeFrom)){
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
			i1=-1;
			i2=-1;
			i3=-1;
			i4=-1;
			
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
