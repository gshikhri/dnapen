/* Author: Arnav Goyal, Shikhar K Gupta, Foram Joshi and Akshita Sahai
 * Project: DNA Pen
 * Mentor: Prof. Manish K Gupta
 */

import javax.swing.*;
import java.util.ArrayList;


public class ModifySequences{

    static boolean alreadySavedDetailedDNA=false;
    static AssignConstrainedSeq a;
    static ArrayList<Domains> xyDomainListDigital= AssignConstrainedSeq.xyDomainList;
    static ArrayList<XYCoordinates> xyCoordinateList = DigitalGridActionListener.xyCoordinatesTileList;
    static ArrayList<Domains> xyDrawdataListDigital = new ArrayList<Domains>();
    static ArrayList<Domains> xyTempListDigital = new ArrayList<Domains>();

    static ArrayList<Integer> xCoordinateList = FreeGridActionListener.xCoordinateTileList;
    static ArrayList<Integer> yCoordinateList = FreeGridActionListener.yCoordinateTileList;
    static ArrayList<Domains> xyDrawdataListFree = new ArrayList<Domains>();
    static ArrayList<Domains> xyTempListFree = new ArrayList<Domains>();

    int i;
    static String sequence1;
    static String sequence2;
    static String sequence3;
    static String sequence4;

    static String d1;
    static String d2;
    static String d3;
    static String d4;

    BrickData Tiledata=new BrickData();
   
    public ModifySequences() {
    	a=new AssignConstrainedSeq();
    	xyDrawdataListFree.clear();
    	MainFrame.savedDataType='r';
                   
                    ArrayList<XYCoordinates> xyCoordinatesList = DigitalGridActionListener.xyCoordinatesTileList;
                    if(MainFrame.currentWindow==2){
                    	xyDrawdataListFree.clear();
                        ModifyFreeGrid();
                    }
                    else if(MainFrame.currentWindow==3) {
                    	ModifyDigitized();
                    }
    }            
        
    public void ModifyDigitized(){
    	System.out.println("now modifying opt dig seq");
        int k;
        xyDrawdataListDigital.clear();
        xyTempListDigital.clear();

            int x1,y1,x,y;
            for (k = 0; k < xyCoordinateList.size(); k++) {
                x1=xyCoordinateList.get(k).getxCoordinate();
                y1=xyCoordinateList.get(k).getyCoordinate();
                for(int j=0;j< xyDomainListDigital.size();j++){
                    if((xyDomainListDigital.get(j).getxCoordinate()==x1)&&(xyDomainListDigital.get(j).getyCoordinate()==y1)){
                        xyTempListDigital.add(new Domains(x1,y1,xyDomainListDigital.get(j).getDomainOne(),
                        		xyDomainListDigital.get(j).getDomainTwo(),xyDomainListDigital.get(j).getDomainThree(),
                        		xyDomainListDigital.get(j).getDomainFour(),false));
                    
                    System.out.println("added"+x1+""+y1);
                    }
                }

            }    
            int size=xyTempListDigital.size();
            for(int p=0;p<xyTempListDigital.size();p++) {
                x=xyTempListDigital.get(p).getxCoordinate();
                y=xyTempListDigital.get(p).getyCoordinate();

                d1=new String(xyTempListDigital.get(p).getDomainOne());
                d2=new String(xyTempListDigital.get(p).getDomainTwo());
                d3=new String(xyTempListDigital.get(p).getDomainThree());
                d4=new String(xyTempListDigital.get(p).getDomainFour());
                int foundright=0;
                int foundleft=0;
                int foundbottomleft=0;
                int foundbottom=0;
                int foundtop=0;
                int foundtopleft=0;
                int foundbottomright=0;
                int foundtopright=0;

                int changeT=0;
                if (y%2==0){
                	for(int q=0;q<xyTempListDigital.size();q++) {

                            if((xyTempListDigital.get(q).getxCoordinate()==x+1)&&
                            		(xyTempListDigital.get(q).getyCoordinate()==y)){
                                foundright=1;
                                sequence1=new String(xyTempListDigital.get(q).getDomainOne());
                                sequence4=new String(xyTempListDigital.get(q).getDomainFour());
                                System.out.println("found right for "+x+","+y);

                            }

                            if((xyTempListDigital.get(q).getxCoordinate()==x-1)&&
                            		(xyTempListDigital.get(q).getyCoordinate()==y)){
                                foundleft=1;
                                sequence2=new String(xyTempListDigital.get(q).getDomainTwo());
                                sequence3=new String(xyTempListDigital.get(q).getDomainThree());
                                System.out.println("found left for "+x+","+y);
                            }

                            if((xyTempListDigital.get(q).getxCoordinate()==x-1)&&
                            		(xyTempListDigital.get(q).getyCoordinate()==y+1)){
                                foundbottomleft=1;
                                System.out.println("found bottomleft for "+x+","+y);
                            }

                            if((xyTempListDigital.get(q).getxCoordinate()==x)&&
                            		(xyTempListDigital.get(q).getyCoordinate()==y+1))
                            		{
                                foundbottom=1;
                                System.out.println("found bottom for "+x+","+y);
                            }

                            if((xyTempListDigital.get(q).getxCoordinate()==x)&&
                            		(xyTempListDigital.get(q).getyCoordinate()==y-1))
                            		{
                                foundtop=1;
                                System.out.println("found top for "+x+","+y);
                            }

                            if((xyTempListDigital.get(q).getxCoordinate()==x-1)&&
                            		(xyTempListDigital.get(q).getyCoordinate()==y-1))
                            		{
                                foundtopleft=1;
                                System.out.println("found topleft for "+x+","+y);
                            }
                        }


                        if(foundleft==0&&foundtop==0&&foundright==0&&foundbottom==0&&foundtopleft==0&&foundbottomleft==0){
                            //do nothing
                        }

                        else{
                            if(foundleft==0&&foundbottomleft==0&&y>0) {
                                d1=new String("TTTTTTTTTT");
                                changeT=1;
                            }
                            if(foundleft==0&&foundtopleft==0&&y>0)  {
                                d4=new String("TTTTTTTTTT");
                                changeT=1;
                            }
                            if(foundright==0&&foundbottom==0)  {
                                d2=new String("TTTTTTTTTTT");
                                changeT=1;
                            }
                            if(foundright==0&&foundtop==0){
                                d3=new String("TTTTTTTTTTT");
                                changeT=1;
                            }
                            if(changeT==1){
                                xyDrawdataListDigital.add(new Domains(x,y,d1,d2,d3,d4,false));
                                xyTempListDigital.get(p).isChanged=true;

                            }
                        }
                        if(foundtop==0&&foundright==1){
                            xyDrawdataListDigital.add(new Domains(x,y-1,negateSeqRev(d3),negateSeqRev(sequence4),"","",true));
                        }

                        if(foundbottom==0&&foundright==1){
                            xyDrawdataListDigital.add(new Domains(x,y+1,"","",negateSeqRev(sequence1),negateSeqRev(d2),true));
                        }
  //                      if(foundtopleft==0&&foundleft==1){
  //                          xyDrawdataListDigital.add(new Domains(x-1,y-1,negateSeqRev(sequence3),negateSeqRev(d4),"","",true));
  //                      }

 //                       if(foundbottomleft==0&&foundleft==1){
 //                           xyDrawdataListDigital.add(new Domains(x-1,y+1,"","",negateSeqRev(d1),negateSeqRev(sequence2),true));
 //                       }

               }
                

                else if (y%2==1){
                	for(int q=0;q<xyTempListDigital.size();q++) {

                            if((xyTempListDigital.get(q).getxCoordinate()==x+1)&&
                            		(xyTempListDigital.get(q).getyCoordinate()==y))
                            		{
                                foundright=1;
                                System.out.println("found right for "+x+","+y);
                                sequence1=new String(xyTempListDigital.get(q).getDomainOne());
                                sequence4=new String(xyTempListDigital.get(q).getDomainFour());

                            }

                            if((xyTempListDigital.get(q).getxCoordinate()==x-1)&&
                            		(xyTempListDigital.get(q).getyCoordinate()==y))
                            		{
                                foundleft=1;
                                System.out.println("found left for "+x+","+y);
                                sequence2=new String(xyTempListDigital.get(q).getDomainTwo());
                                sequence3=new String(xyTempListDigital.get(q).getDomainThree());
                            }

                            if((xyTempListDigital.get(q).getxCoordinate()==x+1)&&
                            		(xyTempListDigital.get(q).getyCoordinate()==y+1))
                            		{
                                foundbottomright=1;
                                System.out.println("found bottomright for "+x+","+y);
                            }

                            if((xyTempListDigital.get(q).getxCoordinate()==x)&&
                            		(xyTempListDigital.get(q).getyCoordinate()==y+1))
                            		{
                                foundbottom=1;
                                System.out.println("found bottom for "+x+","+y);
                            }

                            if((xyTempListDigital.get(q).getxCoordinate()==x)&&
                            		(xyTempListDigital.get(q).getyCoordinate()==y-1))
                            		{
                                foundtop=1;
                                System.out.println("found top for "+x+","+y);
                            }

                            if((xyTempListDigital.get(q).getxCoordinate()==x+1)&&
                            		(xyTempListDigital.get(q).getyCoordinate()==y-1))
                            		{
                                foundtopright=1;
                                System.out.println("found topright for "+x+","+y);
                            }
                        }

                        if(foundleft==0&&foundtop==0&&foundright==0&&foundbottom==0&&foundtopright==0&&foundbottomright==0){
                            //do nothing
                        }

                        else{
                            if(foundleft==0&&foundbottom==0) {
                                d1=new String("TTTTTTTTTTT");
                                changeT=1;
                            }
                            if(foundbottomright==0&&foundright==0)  {
                                d2=new String("TTTTTTTTTT");
                                changeT=1;
                            }
                            if(foundright==0&&foundtopright==0)  {
                                d3=new String("TTTTTTTTTT");
                                changeT=1;
                            }
                            if(foundleft==0&&foundtop==0){
                                d4=new String("TTTTTTTTTTT");
                                changeT=1;
                            }
                        }
                        if(changeT==1){
                            xyDrawdataListDigital.add(new Domains(x,y,d1,d2,d3,d4,false));
                            xyTempListDigital.get(p).isChanged=true;

                        }

                        if(foundtopright==0&&foundright==1){
                            xyDrawdataListDigital.add(new Domains(x+1,y-1,negateSeqRev(d3),negateSeqRev(sequence4),"","",true));
                        }

                        if(foundbottomright==0&&foundright==1){
                            xyDrawdataListDigital.add(new Domains(x+1,y+1,"","",negateSeqRev(sequence1),negateSeqRev(d2),true));
                        }
 //                       if(foundtop==0&&foundleft==1){
 //                           xyDrawdataListDigital.add(new Domains(x,y-1,negateSeqRev(sequence3),negateSeqRev(d4),"","",true));
 //                       }

 //                       if(foundbottom==0&&foundleft==1){
 //                           xyDrawdataListDigital.add(new Domains(x,y+1,"","",negateSeqRev(d1),negateSeqRev(sequence2),true));
 //                       }
                    }
                }
            
        
            for(int z=0;z<xyTempListDigital.size();z++)
            	if(xyTempListDigital.get(z).isChanged==false)
            		xyDrawdataListDigital.add(new Domains(xyTempListDigital.get(z).x,
            				xyTempListDigital.get(z).y,xyTempListDigital.get(z).getDomainOne(),
                    		xyTempListDigital.get(z).getDomainTwo(),xyTempListDigital.get(z).getDomainThree(),
                    		xyTempListDigital.get(z).getDomainFour(),xyTempListDigital.get(z).isHalf));
    }
    

    public void ModifyFreeGrid() {
    	 System.out.println("modify freegrid invoked");
        ArrayList<Domains> xyDomainListFree= AssignConstrainedSeq.xyDomainListFree;
        int k;
        xyDrawdataListFree.clear();
        xyTempListFree.clear();
       
            double TileHeight = EditDimensionActionListener.TileWallHeight;
            double TileWidth = EditDimensionActionListener.TilewallWidth;
            int x1,y1,x,y;
            int size=xCoordinateList.size();

            for (k = 0; k < xCoordinateList.size(); k++) {
                x1=xCoordinateList.get(k);
                y1=yCoordinateList.get(k);
                for(int j=0;j< xyDomainListFree.size();j++){
                    if((xyDomainListFree.get(j).getxCoordinate()==x1)&&(xyDomainListFree.get(j).getyCoordinate()==y1))
                        xyTempListFree.add(new Domains(x1,y1,xyDomainListFree.get(j).getDomainOne(),
                        		xyDomainListFree.get(j).getDomainTwo(),xyDomainListFree.get(j).getDomainThree(),
                        		xyDomainListFree.get(j).getDomainFour(),false));
                }

            }
            
            System.out.print("size of xytemplist:"+xyTempListFree.size());
            for(int p=0;p<xyTempListFree.size();p++) {
                x=xyTempListFree.get(p).getxCoordinate();
                y=xyTempListFree.get(p).getyCoordinate();

                d1=new String(xyTempListFree.get(p).getDomainOne());
                d2=new String(xyTempListFree.get(p).getDomainTwo());
                d3=new String(xyTempListFree.get(p).getDomainThree());
                d4=new String(xyTempListFree.get(p).getDomainFour());
                int foundright=0;
                int foundleft=0;
                int foundbottomleft=0;
                int foundbottom=0;
                int foundtop=0;
                int foundtopleft=0;
                int foundbottomright=0;
                int foundtopright=0;

                int changeT=0;
                if (y%2==0){

                    if(true){


                        for(int q=0;q<xyTempListFree.size();q++) {

                            if((xyTempListFree.get(q).getxCoordinate()==x+1)&&
                            		(xyTempListFree.get(q).getyCoordinate()==y))
                            		{
                                foundright=1;
                                sequence1=new String(xyTempListFree.get(q).getDomainOne());
                                sequence4=new String(xyTempListFree.get(q).getDomainFour());

                            }

                            if((xyTempListFree.get(q).getxCoordinate()==x-1)&&
                            		(xyTempListFree.get(q).getyCoordinate()==y))
                            		{
                                foundleft=1;
                                sequence3=new String(xyTempListFree.get(q).getDomainThree());
                                sequence2=new String(xyTempListFree.get(q).getDomainTwo());
                            }

                            if((xyTempListFree.get(q).getxCoordinate()==x-1)&&
                            		(xyTempListFree.get(q).getyCoordinate()==y+1))
                            		{
                                foundbottomleft=1;
                            }

                            if((xyTempListFree.get(q).getxCoordinate()==x)&&
                            		(xyTempListFree.get(q).getyCoordinate()==y+1)&&
                            		(xyTempListFree.get(q).getDomainThree().equals("")==false))
                            		{
                                foundbottom=1;
                            }

                            if((xyTempListFree.get(q).getxCoordinate()==x)&&
                            		(xyTempListFree.get(q).getyCoordinate()==y-1)&&
                            		(xyTempListFree.get(q).getDomainOne().equals("")==false))
                            		{
                                foundtop=1;
                            }

                            if((xyTempListFree.get(q).getxCoordinate()==x-1)&&
                            		(xyTempListFree.get(q).getyCoordinate()==y-1))
                            		{
                                foundtopleft=1;
                            }
                        }


                        if(foundleft==0&&foundtop==0&&foundright==0&&foundbottom==0){
                            //do nothing
                        }

                        else{
                            if(foundleft==0&&foundbottomleft==0&&y>0) {
                                d1=new String("TTTTTTTTTT");
                                changeT=1;
                            }
                            if(foundleft==0&&foundtopleft==0&&y>0)  {
                                d4=new String("TTTTTTTTTT");
                                changeT=1;
                            }
                            if(foundright==0&&foundbottom==0&&y<TileWidth-1)  {
                                d2=new String("TTTTTTTTTTT");
                                changeT=1;
                            }
                            if(foundright==0&&foundtop==0&&y<TileWidth-1){
                                d3=new String("TTTTTTTTTTT");
                                changeT=1;
                            }

                            if(changeT==1){
                                xyDrawdataListFree.add(new Domains(x,y,d1,d2,d3,d4,false));
                                System.out.println("added to list:"+x+","+y);
                                xyTempListFree.get(p).isChanged=true;
                            }
                        }
                        if(foundtop==0&&foundright==1){
                            xyDrawdataListFree.add(new Domains(x,y-1,negateSeqRev(d3),negateSeqRev(sequence4),"","",true));
                            System.out.println("added to list:"+x+","+y+"-1");
                        }

                        if(foundbottom==0&&foundright==1){
                            xyDrawdataListFree.add(new Domains(x,y+1,"","",negateSeqRev(sequence1),negateSeqRev(d2),true));
                            System.out.println("added to list:"+x+","+y+"+1");
                        }
//                        if(foundtopleft==0&&foundleft==1){
//                            xyDrawdataListFree.add(new Domains(x-1,y-1,negateSeqRev(sequence3),negateSeqRev(d4),"","",true));
//                        }

//                        if(foundbottomleft==0&&foundleft==1){
//                            xyDrawdataListFree.add(new Domains(x-1,y+1,"","",negateSeqRev(d1),negateSeqRev(sequence2),true));
//                        }

                    }
                }

                else if (y%2==1){

                    if(true){


                        for(int q=0;q<xyTempListFree.size();q++) {

                            if((xyTempListFree.get(q).getxCoordinate()==x+1)&&
                            		(xyTempListFree.get(q).getyCoordinate()==y))
                            		{
                                foundright=1;
                                sequence1=new String(xyTempListFree.get(q).getDomainOne());
                                sequence4=new String(xyTempListFree.get(q).getDomainFour());

                            }

                            if((xyTempListFree.get(q).getxCoordinate()==x-1)&&
                            		(xyTempListFree.get(q).getyCoordinate()==y))
                            		{
                                foundleft=1;
                                sequence3=new String(xyTempListFree.get(q).getDomainThree());
                                sequence2=new String(xyTempListFree.get(q).getDomainTwo());
                            }

                            if((xyTempListFree.get(q).getxCoordinate()==x+1)&&
                            		(xyTempListFree.get(q).getyCoordinate()==y+1))
                            		{
                                foundbottomright=1;
                            }

                            if((xyTempListFree.get(q).getxCoordinate()==x)&&
                            		(xyTempListFree.get(q).getyCoordinate()==y+1)&&
                            		(xyTempListFree.get(q).getDomainThree().equals("")==false))                            		{
                                foundbottom=1;
                            }

                            if((xyTempListFree.get(q).getxCoordinate()==x)&&
                            		(xyTempListFree.get(q).getyCoordinate()==y-1)&&
                            		(xyTempListFree.get(q).getDomainOne().equals("")==false))
                            		{
                                foundtop=1;
                            }

                            if((xyTempListFree.get(q).getxCoordinate()==x+1)&&
                            		(xyTempListFree.get(q).getyCoordinate()==y-1))
                            		{
                                foundtopright=1;
                            }
                        }

                        if(foundleft==0&&foundtop==0&&foundright==0&&foundbottom==0){
                            //do nothing
                        }

                        else{
                            if(foundleft==0&&foundbottom==0) {
                                d1=new String("TTTTTTTTTTT");
                                changeT=1;
                            }
                            if(foundbottomright==0&&foundright==0)  {
                                d2=new String("TTTTTTTTTT");
                                changeT=1;
                            }
                            if(foundright==0&&foundtopright==0)  {
                                d3=new String("TTTTTTTTTT");
                                changeT=1;
                            }
                            if(foundleft==0&&foundtop==0){
                                d4=new String("TTTTTTTTTTT");
                                changeT=1;
                            }
                        }
                        if(changeT==1){
                            xyDrawdataListFree.add(new Domains(x,y,d1,d2,d3,d4,false));
                            System.out.println("added to list:"+x+","+y);
                            xyTempListFree.get(p).isChanged=true;
                        }

                        if(foundtopright==0&&foundright==1){
                            xyDrawdataListFree.add(new Domains(x+1,y-1,negateSeqRev(d3),negateSeqRev(sequence4),"","",true));
                            System.out.println("added to list:"+x+"+1,"+y+"-1");
                        }

                        if(foundbottomright==0&&foundright==1){
                            xyDrawdataListFree.add(new Domains(x+1,y+1,"","",negateSeqRev(sequence1),negateSeqRev(d2),true));
                            System.out.println("added to list:"+x+"+1,"+y+"+1");
                        }
//                        if(foundtop==0&&foundleft==1){
//                            xyDrawdataListFree.add(new Domains(x,y-1,negateSeqRev(sequence3),negateSeqRev(d4),"","",true));
//                        }

//                        if(foundbottom==0&&foundleft==1){
//                            xyDrawdataListFree.add(new Domains(x,y+1,"","",negateSeqRev(d1),negateSeqRev(sequence2),true));
//                        }

                    }
                }
          }
            for(int z=0;z<xyTempListFree.size();z++)
            	if(xyTempListFree.get(z).isChanged==false)
            		xyDrawdataListFree.add(new Domains(xyTempListFree.get(z).x,
            				xyTempListFree.get(z).y,xyTempListFree.get(z).getDomainOne(),
                    		xyTempListFree.get(z).getDomainTwo(),xyTempListFree.get(z).getDomainThree(),
                    		xyTempListFree.get(z).getDomainFour(),xyTempListFree.get(z).isHalf));

        System.out.println("size of xydrawdatalistfree after adding unchalged tiles:"+xyDrawdataListFree.size());
         
    }
    public String negateSeqRev(String domainSeq) {
        String negateSeq = "";
        int stringLength = domainSeq.length();

        for (int i = stringLength-1; i >=0; i--) {
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
    public String negateSeq(String domainSeq) {
        String negateSeq = "";
        int stringLength = domainSeq.length();

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

