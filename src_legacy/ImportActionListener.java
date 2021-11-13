import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

/*
 * Author: Foram Joshi and Shikhar Gupta
 * Mentor: Manish Gupta
 */
public class ImportActionListener implements ActionListener {

    static int bh;
    static int bw;
    static int cw;
    JFileChooser chooser;
    String choosertitle;
    static ArrayList <Integer> xC;
    static ArrayList <Integer> yC;
    static ArrayList <XYCoordinates> xy;
    @Override
    public void actionPerformed(ActionEvent e) {

    String currentFile = null;
    JFileChooser fc=new JFileChooser();
    fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fc = new JFileChooser();
            int value = fc.showOpenDialog(null);
        if(value == JFileChooser.APPROVE_OPTION) {
            File f = fc.getSelectedFile();
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(f));
            }
            catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            String st = "";
            try {

                st=br.readLine();
                cw=Integer.parseInt(st);
                st=br.readLine();
                MainFrame.ProjPath=String.valueOf(st);
                System.out.print("\n"+MainFrame.ProjPath);
                st=br.readLine();
                MainFrame.ProjName=String.valueOf(st);
                System.out.print("\n"+MainFrame.ProjName);


                if(cw==2){
                    FreeGridActionListener.isImported=true;
                    xC=new ArrayList<Integer>();
                    yC=new ArrayList<Integer>();
                    st=br.readLine();
                    bh=(Integer.parseInt(st));
                    st=br.readLine();
                    bw=(Integer.parseInt(st));
                    while((st = br.readLine()) != null){
                        xC.add(Integer.parseInt(st));
                        System.out.print("\n"+Integer.parseInt(st));
                        st=br.readLine();
                        yC.add(Integer.parseInt(st));
                        System.out.print("  "+Integer.parseInt(st));
                    }
                	System.out.println("The xC and yC lists are");
                    for(int i=0;i<xC.size();i++){
                    	System.out.print("X:"+xC.get(i)+" ");
                    	System.out.print("Y:"+yC.get(i));
                    	System.out.println();
                    }
                    
                    FreeGridActionListener.xCoordinateTileList.clear();
                    FreeGridActionListener.yCoordinateTileList.clear();
                                      
                    for(int i=0;i<xC.size();i++){
                    	FreeGridActionListener.xCoordinateTileList.add(xC.get(i));
                    	FreeGridActionListener.yCoordinateTileList.add(yC.get(i));
                    	
                    	
                    }
                    System.out.println("The xCordinate and yCordinate lists are");
                    for(int i=0;i<xC.size();i++){
                    	System.out.print("X:"+FreeGridActionListener.xCoordinateTileList.get(i)+" ");
                    	System.out.print("Y:"+FreeGridActionListener.yCoordinateTileList.get(i));
                    	System.out.println();
                    }
                    FreeGridActionListener.canvas.pressed=7;
                    FreeGridActionListener.canvas.repaint();
                   

                }

                else{
                    DigitalGridActionListener.isImported=true;
                    xy=new ArrayList<XYCoordinates>();
                    xy.clear();
                    DigitalGridActionListener.xyCoordinatesTileList.clear();
                    DigitalGridActionListener.digitizedDataStack.clearData();
                    MainFrame.digitizedTracker=0;
                    for(int k=0;k<15;k++)
                    	for(int j=0;j<23;j++){
                    		MainFrame.selectedDigitized[k][j]=-1;
                    	}
                    int x,y;
                    while((st = br.readLine()) != null){
                        x=Integer.parseInt(st);
                        st=br.readLine();
                        y=Integer.parseInt(st);
                        DigitalGridActionListener.xyCoordinatesTileList.add(new XYCoordinates(x,y));
                        MainFrame.selectedFree[x][y]=MainFrame.digitizedTracker;
                    	MainFrame.digitizedTracker++;
                        System.out.println("newly added x:"+x);
                        System.out.println("newly added y:"+y);
                    }
                    System.out.println("While loop is finished");
                    for(int i=0;i<DigitalGridActionListener.xyCoordinatesTileList.size();i++){
                        DigitalGridActionListener.gridValueAddedArray[DigitalGridActionListener.xyCoordinatesTileList.get(i).getxCoordinate()][DigitalGridActionListener.xyCoordinatesTileList.get(i).getyCoordinate()]=true;
                    }
                    System.out.println("The gridvalue arraylist has been populated");
                    DigitalGridActionListener.canvas.repaint();
                    System.out.print("\nDigi Grid clear has been called from edit dimension");
                }


            }
            catch (IOException ep) {
                ep.printStackTrace();
            }
        }
    }

}
