import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import org.imgscalr.Scalr;


public class RenderedImageActionListener implements ActionListener {

	static ArrayList <Integer> xC;
    static ArrayList <Integer> yC;
    static ArrayList <XYCoordinates> xy;
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(MainFrame.currentWindow==2){
			
			JFileChooser renderChooser=new JFileChooser();
			renderChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			
			int returnValue=renderChooser.showOpenDialog(null);
			if(returnValue==JFileChooser.APPROVE_OPTION){
				File renderImage=renderChooser.getSelectedFile();
				BufferedImage img = null;
				try {
				    img = ImageIO.read(renderImage);
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
				int height;
				int width;
				int x,y,rgb,r,g,b;
				 xC=new ArrayList<Integer>();
                 yC=new ArrayList<Integer>();
				height=img.getHeight();
				width=img.getWidth();	
				System.out.println(height+"\t"+width);
				xy=new ArrayList<XYCoordinates>();
                xy.clear();
				for (y = 0; y < height;y++){
				    for (x= 0; x < width;x++){
				    	rgb = img.getRGB(x, y);
				        r = (rgb >> 16) & 0xFF;
				        g = (rgb >> 8) & 0xFF;
				        b = (rgb & 0xFF);
				        if (r==0&&g==0&&b==0){
				        	xC.add(x);
				        	yC.add(y);
				        }
				    }
				}
                FreeGridActionListener.isToBeSaved = true;
                FreeGridActionListener.savedFunctionCalled = false;
                MainFrame.isFreeSaved = false;
                FreeGridActionListener.isTileDataSaved=false;
                FreeGridActionListener.isPDFSaved=false;
                FreeGridActionListener.isLatexSaved=false;
                FreeGridActionListener.isImageSaved=false;
				FreeGridActionListener.canvas.pressed=11;
                FreeGridActionListener.canvas.repaint();

			}
			
		}
		else if (MainFrame.currentWindow==3){
			
			JFileChooser renderChooser=new JFileChooser();
			renderChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			
			int returnValue=renderChooser.showOpenDialog(null);
			if(returnValue==JFileChooser.APPROVE_OPTION){
				File renderImage=renderChooser.getSelectedFile();
				BufferedImage img = null;
				try {
				    img = ImageIO.read(renderImage);
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
				BufferedImage resizedImg =
						  Scalr.resize(img, Scalr.Method.SPEED, Scalr.Mode.FIT_TO_WIDTH,
						               1000, 5000, Scalr.OP_ANTIALIAS);
				int height;
				int width;
				int x,y,rgb,r,g,b;
				ArrayList<Integer> black= new ArrayList<Integer>(); 
				ArrayList<Integer> white= new ArrayList<Integer>();
				height=resizedImg.getHeight();
				width=resizedImg.getWidth();	
				System.out.println(height+"\t"+width);
				xy=new ArrayList<XYCoordinates>();
                xy.clear();
				for (y = 0; y < height;){
				    for (x= 0; x < width;){
				        for(int i=x;i<x+90;i++){
				        	rgb = resizedImg.getRGB(x, y);
					        r = (rgb >> 16) & 0xFF;
					        g = (rgb >> 8) & 0xFF;
					        b = (rgb & 0xFF);
					        black.clear();
					        white.clear();
					        if (r==0&&g==0&&b==0){
					        	black.add(1);
					        }
					        else if (r==255&&g==255&&b==255){
					        	white.add(1);
					        }
				        }
				        if(black.size()>=white.size()){
				        	DigitalGridActionListener.xyCoordinatesTileList.add(new XYCoordinates(x/90,y/30));
				        }
				        x=x+90;
				    }
				    y=y+30;
				}
                System.out.println("For loop is finished");
                for(int i=0;i<DigitalGridActionListener.xyCoordinatesTileList.size();i++){
                    DigitalGridActionListener.gridValueAddedArray[DigitalGridActionListener.xyCoordinatesTileList.get(i).getxCoordinate()][DigitalGridActionListener.xyCoordinatesTileList.get(i).getyCoordinate()]=true;
                }
                System.out.println("The gridvalue arraylist has been populated");
                DigitalGridActionListener.canvas.repaint();
                System.out.print("\nDigi Grid clear has been called from edit dimension");
			}
		}
		
	}
}