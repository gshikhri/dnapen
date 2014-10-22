/* Author: Arnav Goyal, Shikhar K Gupta, Foram Joshi and Akshita Sahai
 * Project: DNA Pen
 * Mentor: Prof. Manish K Gupta
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class SaveOptimizedSeq implements ActionListener {

    static boolean alreadySavedDetailedDNA=false;
    static ArrayList<Domains> ListDigital;
    static ArrayList<Domains> ListFree;

    static ModifyOptimizedSeq m;
    static OptionPane o;
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
    @Override
    public void actionPerformed(ActionEvent e) {

    	
    	
    	MainFrame.savedDataType='r';
    	System.out.println("Count of listeners: " + (e.getSource()));    	
        BufferedWriter bufferedWriter = null;
        if (MainFrame.newProjectCreated==true)
        {
            if (MainFrame.currentWindow == 2) {
                if (false) {
                    JOptionPane.showMessageDialog(null, "You've already saved the File !",
                            "Success!", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                try {
                	
                	System.out.println("modify opt seq called for free grid");
                	m= new ModifyOptimizedSeq();
                	 ListFree = ModifyOptimizedSeq.xyDrawdataListFree;
                	 System.out.println("size of xydrawdatalistfree:"+ModifyOptimizedSeq.xyDrawdataListFree.size());
                 	
                    MainFrame.isFreeSaved = true;
                    if(MainFrame.currentWindow==2) {
                    	ListDigital = ModifyOptimizedSeq.xyDrawdataListDigital;
                        bufferedWriter = new BufferedWriter(new FileWriter(MainFrame.ProjPath+"/"+"FreeGrid_Detailed_DNAData_"+MainFrame.ProjName+".csv"));
                        writeHeadersCanvasFree(bufferedWriter);
                    }
                   
                    bufferedWriter.write("\n\n Generated using DNA Pen.");
                    bufferedWriter.write(",");
                    bufferedWriter.write("(http://www.guptalab.org/dnapen/)");
                    bufferedWriter.close();
                    JOptionPane.showMessageDialog(null, "File Saved Successfully !",
                            "Success!", JOptionPane.INFORMATION_MESSAGE);


                }
                catch (IOException e1)
                {
                    JOptionPane.showMessageDialog(null, "The Freehand Grid file could not be Saved!",
                            "Error!", JOptionPane.INFORMATION_MESSAGE);
                }
            } else if (MainFrame.currentWindow == 3) {
                if (false) {
                    JOptionPane.showMessageDialog(null, "You've already saved the File !",
                            "Success!", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                try {
                	o=new OptionPane();
                	ModifyOptimizedSeq mf=new ModifyOptimizedSeq();
                	System.out.println("modify opt seq called for digitized grid");
                   
                   if(MainFrame.currentWindow==3) {
                        bufferedWriter = new BufferedWriter(new FileWriter(MainFrame.ProjPath+"/"+"DigitalGrid_Detailed_DNAData_"+MainFrame.ProjName+".csv"));
                        writeHeadersCanvasDigital(bufferedWriter);
                    }


                    bufferedWriter.write("\n\n Generated using DNA Pen.");
                    bufferedWriter.write(",");
                    bufferedWriter.write("(http://www.guptalab.org/dnapen/)");
                    bufferedWriter.close();
                    JOptionPane.showMessageDialog(null, "File Saved Successfully !",
                            "Success!", JOptionPane.INFORMATION_MESSAGE);

                    MainFrame.isDigitizedSaved = true;
                }
                catch (IOException e1)
                {
                    JOptionPane.showMessageDialog(null, "The File could not be Saved!",
                            "Error!", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No Sequence drawn. Choose a Grid to draw !",
                        "Error!", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public void writeHeadersCanvasDigital(BufferedWriter bufferedWriter) {
        try {
            double TileHeight = MainFrame.dnaTile.getTileHeight();
            double TileWidth = MainFrame.dnaTile.getTileWidth();
            int x,y;
            
            if(MainFrame.GCrangeFrom!=0&&MainFrame.GCRangeTo!=0){
	            bufferedWriter.write("GC Range:");
	            bufferedWriter.write(MainFrame.GCrangeFrom);
	            bufferedWriter.write("-");
	            bufferedWriter.write(MainFrame.GCRangeTo);
	            bufferedWriter.write("\n");
            }
            
            if(MainFrame.FreeEnergyFrom!=0&&MainFrame.FreeEnergyTo!=0){
	            bufferedWriter.write("FreeEnergyRange:");
	            bufferedWriter.write(MainFrame.FreeEnergyFrom);
	            bufferedWriter.write("-");
	            bufferedWriter.write(MainFrame.FreeEnergyTo);
	            bufferedWriter.write("\n");
            }
           
            bufferedWriter.write("X Cordinate");
        	bufferedWriter.write(",");
        	bufferedWriter.write("Y Cordinate");
        	bufferedWriter.write(",");
        	bufferedWriter.write("Domain1");
        	bufferedWriter.write(",");
        	bufferedWriter.write("Domain2");
        	bufferedWriter.write(",");
        	bufferedWriter.write("Domain3");
        	bufferedWriter.write(",");
        	bufferedWriter.write("Domain4");
        	bufferedWriter.write("\n");
            for(i=0;i<ListDigital.size();i++){
            	bufferedWriter.write(ListDigital.get(i).getxCoordinate()+ "");
                bufferedWriter.write(",");
                bufferedWriter.write(ListDigital.get(i).getyCoordinate() + "");
                bufferedWriter.write(",");
                bufferedWriter.write(ListDigital.get(i).getDomainOne() + "");
                bufferedWriter.write(",");
                bufferedWriter.write(ListDigital.get(i).getDomainTwo() + "");
                bufferedWriter.write(",");
                bufferedWriter.write(ListDigital.get(i).getDomainThree() + "");
                bufferedWriter.write(",");
                bufferedWriter.write(ListDigital.get(i).getDomainFour() + "");
                bufferedWriter.write("\n");

            }
        }
        catch (Exception e1) {
            JOptionPane.showMessageDialog(null, "Exception Occurred !",
                    "Error!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void writeHeadersCanvasFree(BufferedWriter bufferedWriter) {
    	try{
    		 if(MainFrame.GCrangeFrom!=0&&MainFrame.GCRangeTo!=0){
 	            bufferedWriter.write("GC Range:");
 	            bufferedWriter.write(MainFrame.GCrangeFrom);
 	            bufferedWriter.write("-");
 	            bufferedWriter.write(MainFrame.GCRangeTo);
 	            bufferedWriter.write("\n");
             }
             
             if(MainFrame.FreeEnergyFrom!=0&&MainFrame.FreeEnergyTo!=0){
 	            bufferedWriter.write("FreeEnergyRange:");
 	            bufferedWriter.write(MainFrame.FreeEnergyFrom);
 	            bufferedWriter.write("-");
 	            bufferedWriter.write(MainFrame.FreeEnergyTo);
 	            bufferedWriter.write("\n");
             }
            
            bufferedWriter.write("X Cordinate");
        	bufferedWriter.write(",");
        	bufferedWriter.write("Y Cordinate");
        	bufferedWriter.write(",");
        	bufferedWriter.write("Domain1");
        	bufferedWriter.write(",");
        	bufferedWriter.write("Domain2");
        	bufferedWriter.write(",");
        	bufferedWriter.write("Domain3");
        	bufferedWriter.write(",");
        	bufferedWriter.write("Domain4");
        	bufferedWriter.write("\n");
        	 System.out.println("size of frrelist:"+ListFree.size());
            for(i=0;i<ListFree.size();i++){
                bufferedWriter.write(ListFree.get(i).getxCoordinate()+ "");
                bufferedWriter.write(",");
                bufferedWriter.write(ListFree.get(i).getyCoordinate() + "");
                bufferedWriter.write(",");
                bufferedWriter.write(ListFree.get(i).getDomainOne() + "");
                bufferedWriter.write(",");
                bufferedWriter.write(ListFree.get(i).getDomainTwo() + "");
                bufferedWriter.write(",");
                bufferedWriter.write(ListFree.get(i).getDomainThree() + "");
                bufferedWriter.write(",");
                bufferedWriter.write(ListFree.get(i).getDomainFour() + "");
                bufferedWriter.write("\n");

            }
        }
        catch (Exception e1) {
            JOptionPane.showMessageDialog(null, "Exception Occurred !",
                    "Error!", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    
}
