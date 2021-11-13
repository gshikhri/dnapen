import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/*
 * Author: Foram Joshi and Shikhar Gupta
 * Mentor: Manish Gupta
 */
public class ExportActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        try {


            if(MainFrame.currentWindow==2) {

                int TileHeight=FreeGridActionListener.tileHeight;
                int TileWidth=FreeGridActionListener.tileWidth;

                ArrayList<Integer> x=FreeGridActionListener.xCoordinateTileList;
                ArrayList<Integer> y=FreeGridActionListener.yCoordinateTileList;
                BufferedWriter bw = new BufferedWriter(new FileWriter(MainFrame.ProjPath+"/FreeGrid_SourceFile_"+MainFrame.ProjName+".dnap"));
                bw.write(String.valueOf(MainFrame.currentWindow));
                bw.write("\n");
                bw.write(MainFrame.ProjPath);
                bw.write("\n");
                bw.write(MainFrame.ProjName);
                bw.write("\n");
                bw.write(String.valueOf(TileHeight));
                bw.write("\n");
                bw.write(String.valueOf(TileWidth));
                System.out.print("\n"+ x.size());
                for(int i=0;i<x.size();i++){
                    bw.write("\n");
                    bw.write(String.valueOf(x.get(i)));
                    bw.write("\n");
                    bw.write(String.valueOf(y.get(i)));
                }
                bw.close();
                JOptionPane.showMessageDialog(null, "Source File Saved!",
                        "Success!", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            else if(MainFrame.currentWindow==3) {
                ArrayList<XYCoordinates> xy = DigitalGridActionListener.xyCoordinatesTileList;

                BufferedWriter bw = new BufferedWriter(new FileWriter(MainFrame.ProjPath+"/"+"DigitizedGrid_SourceFile_"+MainFrame.ProjName+".dnap"));
                bw.write(String.valueOf(MainFrame.currentWindow));
                bw.write("\n");
                bw.write(MainFrame.ProjPath);
                bw.write("\n");
                bw.write(MainFrame.ProjName);
                System.out.print("\n"+ xy.size());
                for(int i=0;i<xy.size();i++){
                    bw.write("\n");
                    bw.write(String.valueOf(xy.get(i).getxCoordinate()));
                    bw.write("\n");
                    bw.write(String.valueOf(xy.get(i).getyCoordinate()));
                }
                bw.close();
                JOptionPane.showMessageDialog(null, "Source File Saved!",
                        "Success!", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

        }
        catch (IOException exp) {
            exp.printStackTrace();
        }

    }
}
