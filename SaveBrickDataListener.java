/* Author: Arnav Goyal and Akshita Sahai
 * Project: DNA Pen
 * Mentor: Prof. Manish K Gupta
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class SaveBrickDataListener implements ActionListener {

    static ModifySequences m;
    static ArrayList<Domains> xyDrawdataListDigital = ModifySequences.xyDrawdataListDigital;
    static ArrayList<Domains> xyDrawdataListFree = ModifySequences.xyDrawdataListFree;
    int i;

    BrickData Tiledata=new BrickData();
    @Override
    public void actionPerformed(ActionEvent e) {

        BufferedWriter bufferedWriter = null;
        if (MainFrame.newProjectCreated==true)
        {
            if (MainFrame.currentWindow == 2) {
                if (FreeGridActionListener.isTileDataSaved==true) {
                    JOptionPane.showMessageDialog(null, "You've already saved the File !",
                            "Success!", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                else{
	                try {
	                	if(!FreeGridActionListener.isImageSaved&&!FreeGridActionListener.isLatexSaved&&
	                			!FreeGridActionListener.isPDFSaved&&!MainFrame.isFreeSaved){
	                		m=new ModifySequences();
	                	}
	                	FreeGridActionListener.isTileDataSaved=true;
	                    if(MainFrame.currentWindow==2) {                   	   
	                            bufferedWriter = new BufferedWriter(new FileWriter(MainFrame.ProjPath+"/"+"FreeGrid_DNASequenceData_"+MainFrame.ProjName+".csv"));
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
	                    JOptionPane.showMessageDialog(null, "The File could not be Saved!",
	                            "Error!", JOptionPane.INFORMATION_MESSAGE);
	                }
                }
            } else if (MainFrame.currentWindow == 3) {
                if (DigitalGridActionListener.isTileDataSaved==true) {
                    JOptionPane.showMessageDialog(null, "You've already saved the File !",
                            "Success!", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                else{
	                try {
	                	if(!DigitalGridActionListener.isImageSaved&&!DigitalGridActionListener.isLatexSaved&&
	                			!DigitalGridActionListener.isPDFSaved&&!MainFrame.isDigitizedSaved){
	                		m=new ModifySequences();
	                	}
	                    ArrayList<XYCoordinates> xyCoordinatesList = DigitalGridActionListener.xyCoordinatesTileList;

	                    bufferedWriter = new BufferedWriter(new FileWriter(MainFrame.ProjPath+"/"+"DigitalGrid_DNASequenceData_"+MainFrame.ProjName+".csv"));
	                    writeHeadersCanvasDigital(bufferedWriter);

	                 
	                    bufferedWriter.write("\n\n Generated using DNA Pen.");
	                    bufferedWriter.write(",");
	                    bufferedWriter.write("(http://www.guptalab.org/dnapen/)");
	                    bufferedWriter.close();
	                    JOptionPane.showMessageDialog(null, "File Saved Successfully !",
	                            "Success!", JOptionPane.INFORMATION_MESSAGE);
	
	                    DigitalGridActionListener.isTileDataSaved=true;
	                }
	                catch (IOException e1)
	                {
	                    JOptionPane.showMessageDialog(null, "The File could not be Saved!",
	                            "Error!", JOptionPane.INFORMATION_MESSAGE);
	                }
	            }
            }
                else {
                JOptionPane.showMessageDialog(null, "No Sequence drawn. Choose a Grid to draw !",
                        "Error!", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public void writeHeadersCanvasDigital(BufferedWriter bufferedWriter) {

        try {
            bufferedWriter.write("Seq No.");
            bufferedWriter.write(",");
            bufferedWriter.write("DNA Sequence");


            for(i=0;i<xyDrawdataListDigital.size();i++){
                bufferedWriter.write("\n");

                bufferedWriter.write(String.valueOf(i+1));
                bufferedWriter.write(",");
                bufferedWriter.write(xyDrawdataListDigital.get(i).getDomainOne()+xyDrawdataListDigital.get(i).getDomainTwo()+
                        xyDrawdataListDigital.get(i).getDomainThree()+xyDrawdataListDigital.get(i).getDomainFour());

            }
        }
        catch (Exception e1) {
            JOptionPane.showMessageDialog(null, "Exception Occurred !",
                    "Error!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void writeHeadersCanvasFree(BufferedWriter bufferedWriter) {

        try {

            bufferedWriter.write("Seq No.");
            bufferedWriter.write(",");
            bufferedWriter.write("DNA Sequence");


            for(i=0;i<xyDrawdataListFree.size();i++){
                bufferedWriter.write("\n");

                bufferedWriter.write(String.valueOf(i+1));
                bufferedWriter.write(",");
                bufferedWriter.write(xyDrawdataListFree.get(i).getDomainOne()+xyDrawdataListFree.get(i).getDomainTwo()+
                        xyDrawdataListFree.get(i).getDomainThree()+xyDrawdataListFree.get(i).getDomainFour());

            }
        }
        catch (Exception e1) {
            JOptionPane.showMessageDialog(null, "Exception Occurred !",
                    "Error!", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
