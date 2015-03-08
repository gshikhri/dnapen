/* Author:Shikhar K Gupta, Foram Joshi
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


public class SaveDrawDataActionListener implements ActionListener {

    static boolean alreadySavedDetailedDNA=false;
    static ArrayList<Domains> ListDigital = ModifySequences.xyDrawdataListDigital;
    static ArrayList<Domains> ListFree;

    static ModifySequences m;
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
        BufferedWriter bufferedWriter = null;
        if (MainFrame.newProjectCreated==true)
        {
            if (MainFrame.currentWindow == 2) {
                if (MainFrame.isFreeSaved==true) {
                    JOptionPane.showMessageDialog(null, "You've already saved the File !",
                            "Success!", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                else{
	                try {
	                	if(!FreeGridActionListener.isImageSaved&&!FreeGridActionListener.isLatexSaved&&
	                			!FreeGridActionListener.isTileDataSaved&&!FreeGridActionListener.isPDFSaved){
	                		m=new ModifySequences();
	                	}
	                	 ListFree = ModifySequences.xyDrawdataListFree;
	                	 System.out.println("size of xydrawdatalistfree:"+ModifySequences.xyDrawdataListFree.size());
	                 	
	                    bufferedWriter = new BufferedWriter(new FileWriter(MainFrame.ProjPath+"/"+"FreeGrid_Detailed_DNAData_"+MainFrame.ProjName+".csv"));
	                    writeHeadersCanvasFree(bufferedWriter);
	                   
	                   
	                    bufferedWriter.write("\n\n Generated using DNA Pen.");
	                    bufferedWriter.write(",");
	                    bufferedWriter.write("(http://www.guptalab.org/dnapen/)");
	                    bufferedWriter.close();
	                    JOptionPane.showMessageDialog(null, "File Saved Successfully !",
	                            "Success!", JOptionPane.INFORMATION_MESSAGE);
	
	                    MainFrame.isFreeSaved = true;
	
	                }
	                catch (IOException e1)
	                {
	                    JOptionPane.showMessageDialog(null, "The Freehand Grid file could not be Saved!",
	                            "Error!", JOptionPane.INFORMATION_MESSAGE);
	                }
                }
            } else if (MainFrame.currentWindow == 3) {
                if (MainFrame.isDigitizedSaved==true) {
                    JOptionPane.showMessageDialog(null, "You've already saved the File !",
                            "Success!", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                else{
	                try {
	                	if(!DigitalGridActionListener.isImageSaved&&!DigitalGridActionListener.isLatexSaved&&
	                			!DigitalGridActionListener.isPDFSaved&&!DigitalGridActionListener.isTileDataSaved){
	                		m=new ModifySequences();
	                	}
	                	System.out.println("modify opt seq called for digitized grid");
	                   
	                	bufferedWriter = new BufferedWriter(new FileWriter(MainFrame.ProjPath+"/"+"DigitalGrid_Detailed_DNAData_"+MainFrame.ProjName+".csv"));
	                    writeHeadersCanvasDigital(bufferedWriter);
	                    	
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
