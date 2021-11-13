import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

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
				FreeGridActionListener.canvas.pressed=11;
                FreeGridActionListener.canvas.repaint();
			}
		}
	}
}
