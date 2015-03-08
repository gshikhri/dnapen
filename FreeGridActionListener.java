/* Author: Arnav Goyal, Shikhar K Gupta, Foram Joshi, Sushant Pritmani and Akshita Sahai
 * Project: DNA Pen
 * Mentor: Prof. Manish K Gupta
 */

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class FreeGridActionListener implements ActionListener{

    static DrawCanvas canvas;
    static int tileHeight = 15; // Set default tile Height
    static int tileWidth = 35; // Set default tile Width
    static boolean Dimchanged = false;
    static boolean isToBeSaved = false;
    static boolean savedFunctionCalled = false;
    static ArrayList<Integer> xCoordinateTileList = new ArrayList<Integer>();
    static ArrayList<Integer> yCoordinateTileList = new ArrayList<Integer>();
    boolean dosave=false;
    static boolean isImported=false;
    static boolean isTileDataSaved=true;
    static boolean isPDFSaved=true;
    static boolean isLatexSaved=true;
    static boolean isImageSaved=true;
    
    public static Graphics graphicsForDrawing;  // A graphics context for the applet.

    public static Graphics offscreenGraphics;   // A graphics context for the off-screen canvas.


    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.TrueEnableContent();
        MainFrame.mainFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
        MainFrame.isFreeSaved=true;


        if (!MainFrame.newTileCreated) {
            MainFrame.dnaTile = new DNABrick(EditDimensionActionListener.TileWallHeight, EditDimensionActionListener.TilewallWidth);
            MainFrame.newTileCreated = true;
        }

        if (MainFrame.currentWindow == 2) {

            JOptionPane.showMessageDialog(null, "You are currently working on the Free Grid Molecular Canvas",
                    "Success!", JOptionPane.INFORMATION_MESSAGE);
            return;

        }

        if ((MainFrame.currentWindow == 3)&&(MainFrame.framecounter==0)) {
            if (MainFrame.isDigitizedSaved==false) {

                Object[] options = {"Yes",
                        "No",
                        "Cancel"};

                int userChoice = JOptionPane.showOptionDialog(null,
                        "Are you sure you want to switch to Free Grid Molecular Canvas before saving your data?",
                        "Save Resource",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[2]);

                if (userChoice == 0) {
                    dosave=false;
                }
                else if (userChoice == 1) {
                    return;
                }

                else if (userChoice == 2) {
                    return;
                }
            }

            if(dosave==false){
                DigitalGridActionListener.canvas.setVisible(false);
                DigitalGridActionListener.xyCoordinatesTileList.clear();
                MainFrame.isDigitizedSaved = true;
                MainFrame.panelLeft.remove(0);
            }
        }

        // Set value of currentWindow to 2 to indicate that Free Hand Molecular Canvas is active.
        MainFrame.currentWindow = 2;
        MainFrame.mainFrame.setTitle("DNA Pen - Free Hand Molecular Canvas");
        MainFrame.mainFrame.setExtendedState(Frame.MAXIMIZED_BOTH);

        //initialize all components
        MainFrame.GCrangeFrom=0;
        MainFrame.GCRangeTo=0;
        MainFrame.HighFreeEnergy=false;
        MainFrame.FreeEnergyFrom=0;
        MainFrame.FreeEnergyTo=0;
        
       
        JPanel jPanel = new JPanel();
        MainFrame.panelLeft.add(jPanel);
        jPanel.setSize(1050, 700);
        jPanel.setLayout(new GridLayout(1,1));
        canvas = new DrawCanvas();
        jPanel.add(canvas);
        canvas.setLocation(150,5);
        

    }


    public class DrawCanvas extends Canvas implements MouseListener, MouseMotionListener {


        int ysize= (int) (700/tileHeight);
        int xsize= (int) (1050/tileWidth);
        
        boolean[][] gridValueAddedArray = new boolean[xsize][ysize];

        int X, Y, pressed = 0;


        Image OSC;
        int widthOfOSC;
        int heightOfOSC;
        Image undoBuffer;
        private int prevX, prevY;     // The previous location of the mouse.

        private int startX, startY;   // The starting position of the mouse.

        private boolean dragging;     // This is set to true when the user is drawing.

        private Graphics graphicsForDrawing;  // A graphics context for the applet.

        private Graphics offscreenGraphics;   // A graphics context for the off-screen canvas.

        int xRightBound = -1;
        int xLeftBound = -1;
        int yUpBound = -1;
        int yDownBound = -1;


        public DrawCanvas() {
            setBackground(Color.white);
            addMouseListener(this);
            addMouseMotionListener(this);
            setBackground(Color.white);
            setSize(1050, 700);
           

            for (int i = 0; i < xsize; i++) {
                for (int j = 0; j < ysize; j++) {
                    gridValueAddedArray[i][j] = false;
                }
            }
            xCoordinateTileList.clear();
            yCoordinateTileList.clear();

        }


        private void setupOSC() {
            if (OSC == null || widthOfOSC != getSize().width || heightOfOSC != getSize().height) {
                // Create the OSC, or make a new one if canvas size has changed.

                OSC = null;  // (If OSC & undoBuffer already exist, this frees up the memory.)
                undoBuffer = null;

                OSC = createImage(getSize().width, getSize().height);
                widthOfOSC = getSize().width;
                heightOfOSC = getSize().height;
                Graphics OSG = OSC.getGraphics();  // Graphics context for drawing to OSC.
                OSG.setColor(getBackground());
                OSG.fillRect(0, 0, widthOfOSC, heightOfOSC);
                OSG.dispose();

                undoBuffer = createImage(widthOfOSC, heightOfOSC);
                OSG = undoBuffer.getGraphics();  // Graphics context for drawing to the undoBuffer.
                OSG.setColor(getBackground());
                OSG.fillRect(0, 0, widthOfOSC, heightOfOSC);
                OSG.dispose();

            }
        }

        private Graphics getOSG() {
            // Return a graphics context for drawing to the off-screen canvas.
            // A new canvas is created if necessary.  Note that the graphics
            // context should not be kept for any length of time, in case the
            // size of the canvas changes.
            setupOSC();
            return OSC.getGraphics();
        }
        

        public void clearOSC() {
            // Fill the off-screen canvas with the background color.
            // (First, save the current image in the undo buffer.)
            System.out.print("Free Grid clear has been called to clear");
            Graphics undoGr = undoBuffer.getGraphics();
            undoGr.drawImage(OSC, 0, 0, null);
            undoGr.dispose();
            Graphics OSG = OSC.getGraphics();
            OSG.setColor(Color.white);
            OSG.fillRect(0, 0, widthOfOSC, heightOfOSC);
            OSG.dispose();
            System.out.print("Free Grid clear has been called disposed");
        }


        @Override
        public void update(Graphics g) {
            paint(g);
        }


        @Override
        public void paint(Graphics g) {
            setupOSC();
            g.drawImage(OSC, 0, 0, this);
            if (pressed==5){
            	
            	 graphicsForDrawing = getGraphics();  // For drawing on the screen.
	             graphicsForDrawing.setColor(getCurrentColor());
	
	             offscreenGraphics = getOSG();  // For drawing on the canvas.
	             offscreenGraphics.setColor(getCurrentColor());
	             Graphics graphics;
	             //BufferedImage bufImage=new BufferedImage(widthOfOSC, heightOfOSC, BufferedImage.TYPE_INT_BGR);
	             ArrayList <Integer> xC;
	             ArrayList <Integer> yC;
	            if(ShapePanelListener.shapeType==1){
	            	graphicsForDrawing.drawRect(50, 50, 300, 300);
					offscreenGraphics.drawRect(50, 50, 300, 300);
				/*	graphics=bufImage.getGraphics();
					graphics.drawRect(50, 50, 300, 300);
					graphics.drawImage(bufImage, 0, 0, null);
					int height;
					int width;
					int x,y,rgb,red,green,blue;
					 xC=new ArrayList<Integer>();
	                 yC=new ArrayList<Integer>();
					height=bufImage.getHeight();
					width=bufImage.getWidth();	
					System.out.println(height+"\t"+width);
					for (y = 0; y < height;y++){
					    for (x= 0; x < width;x++){
					    	rgb = bufImage.getRGB(x, y);
					        red = (rgb >> 16) & 0xFF;
					        green = (rgb >> 8) & 0xFF;
					        blue = (rgb & 0xFF);
					        if (red==255&&green==255&&blue==255){
					        	xC.add(x);
					        	yC.add(y);
					        }
					    }
					}*/
					pressed=0;
					repaint();
					System.out.println("Repaint done");
					
	            }
	            if(ShapePanelListener.shapeType==2){
	            	graphicsForDrawing.drawLine(50, 50, 300, 300);
					offscreenGraphics.drawLine(50, 50, 300, 300);
	            }
	            if(ShapePanelListener.shapeType==3){
	            	graphicsForDrawing.drawRect(50, 50, 300, 300);
					graphicsForDrawing.fillRect(50, 50, 300, 300);
					offscreenGraphics.drawRect(50, 50, 300, 300);
					offscreenGraphics.fillRect(50, 50, 300, 300);
	            }
	            if(ShapePanelListener.shapeType==4){
	            	graphicsForDrawing.drawOval(50, 50, 300, 300);
					offscreenGraphics.drawOval(50, 50, 300, 300);
	            }
	            pressed=0;
				canvas.repaint();
				System.out.println("Square was called");
            }
            if (pressed==7){
            	 System.out.println("Import was called");
            	 graphicsForDrawing = getGraphics();  // For drawing on the screen.
                 graphicsForDrawing.setColor(getCurrentColor());

                 offscreenGraphics = getOSG();  // For drawing on the canvas.
                 offscreenGraphics.setColor(getCurrentColor());
                
                for(int i=0;i<FreeGridActionListener.xCoordinateTileList.size()-1;i++){
                	
                	putMultiFigure(graphicsForDrawing,FreeGridActionListener.xCoordinateTileList.get(i)*ImportActionListener.bw,
                			FreeGridActionListener.yCoordinateTileList.get(i)*ImportActionListener.bh,
                			FreeGridActionListener.xCoordinateTileList.get(i+1)*ImportActionListener.bw,
                			FreeGridActionListener.yCoordinateTileList.get(i+1)*ImportActionListener.bh);
                	putMultiFigure(offscreenGraphics,FreeGridActionListener.xCoordinateTileList.get(i)*ImportActionListener.bw,
                			FreeGridActionListener.yCoordinateTileList.get(i)*ImportActionListener.bh,
                			FreeGridActionListener.xCoordinateTileList.get(i+1)*ImportActionListener.bw,
                			FreeGridActionListener.yCoordinateTileList.get(i+1)*ImportActionListener.bh);
                }
                
                pressed=0;
                canvas.repaint();
                
                System.out.println("\nimport was called");
                
            }
            if (pressed==11){

	           	graphicsForDrawing = getGraphics();  // For drawing on the screen.
	            graphicsForDrawing.setColor(getCurrentColor());
	
	            offscreenGraphics = getOSG();  // For drawing on the canvas.
	            offscreenGraphics.setColor(getCurrentColor());

            	
            	System.out.println("Render was called");
            	Graphics2D g2=(Graphics2D) g;
                g2.setStroke(new BasicStroke(ImportActionListener.bh));
                
                //problem 1: merging the two array lists
                int xSize=RenderedImageActionListener.xC.size();
                
                ArrayList<XYCoordinates> xyTempList = new ArrayList<XYCoordinates>();
                FreeGridActionListener.xCoordinateTileList.clear();
                FreeGridActionListener.yCoordinateTileList.clear();
                int s=0;
                int xcoord;
                int ycoord;
                while(s<xSize){

                	xcoord=RenderedImageActionListener.xC.get(s);
                	ycoord=RenderedImageActionListener.yC.get(s);
                	FreeGridActionListener.xCoordinateTileList.add(xcoord/tileWidth);
                	FreeGridActionListener.yCoordinateTileList.add(ycoord/tileHeight);
                	s++;
                }
                System.out.println("printing the FreGrid CordinateList");
                //printing contents of the tile list before removing duplicates
                for(int i=0;i<FreeGridActionListener.xCoordinateTileList.size();i++){
                	
                }

                
                //problem 2: removing the duplicates
                for(int i=0;i<FreeGridActionListener.xCoordinateTileList.size();i++){
                	xcoord=FreeGridActionListener.xCoordinateTileList.get(i);
                	ycoord=FreeGridActionListener.yCoordinateTileList.get(i);
                	
                	for(int k=i+1;k<FreeGridActionListener.xCoordinateTileList.size();k++){
                		if(FreeGridActionListener.xCoordinateTileList.get(k)==xcoord&&FreeGridActionListener.yCoordinateTileList.get(k)==ycoord){
                			FreeGridActionListener.xCoordinateTileList.remove(k);
                			FreeGridActionListener.yCoordinateTileList.remove(k);
                			
                			k--;
                		}
                	}
                	
                }
                System.out.println(FreeGridActionListener.xCoordinateTileList.size());
                
       
                for(int i=0;i<RenderedImageActionListener.xC.size();i++){                	
                	graphicsForDrawing.drawOval(RenderedImageActionListener.xC.get(i),RenderedImageActionListener.yC.get(i),3,3);
                	offscreenGraphics.drawOval(RenderedImageActionListener.xC.get(i),RenderedImageActionListener.yC.get(i),3,3);
         
                }
                FreeGridActionListener.isImageSaved=false;
                FreeGridActionListener.isTileDataSaved=false;
                FreeGridActionListener.isLatexSaved=false;
                FreeGridActionListener.isTileDataSaved=false;
                FreeGridActionListener.isPDFSaved=false;
                MainFrame.isFreeSaved=false;
                pressed=0;
                System.out.println("\nrender is finished");
            }
            //pressed=18 signifies canvas clear
            if (pressed==18){
                System.out.print("Free Grid clear has been called 3");
                clearOSC();
                repaint();
                setBackground(Color.white);
                pressed=0;
                System.out.print("Free Grid has been cleared");
            }
            //manually clear
            if (pressed==2){
                System.out.println("Freegrid clear for "+pressed+" has been called");
                tileHeight=EditDimensionActionListener.TileWallHeight;
                tileWidth=EditDimensionActionListener.TilewallWidth;
                System.out.print("New dimensions: "+ tileHeight+tileWidth);
                pressed=18;
                repaint();
                System.out.println("clear was called again");
            }

        }


        private Color getCurrentColor() {
            // Check the colorChoice menu to find the currently
            // selected color, and return the appropriate color
            // object.
            if(pressed==4)   {

                return Color.white;
            }
            else
                return Color.black;
        }

        private void putFigure(Graphics g,
                               int x1, int y1, int x2, int y2) {
            Graphics2D g2=(Graphics2D) g;
                if(pressed==7){
	            	g2.setStroke(new BasicStroke(ImportActionListener.bh));
	            }
	            else{
	            g2.setStroke(new BasicStroke(EditDimensionActionListener.TileWallHeight));
	            }
	            g2.drawLine(x1, y1, x2, y2);
            
        }

        private void putMultiFigure(Graphics g, int x1, int y1, int x2, int y2) {
            putFigure(g, x1, y1, x2, y2);
        } // end putMultiFigure

        @Override
        public void mouseClicked(MouseEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void mousePressed(MouseEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
            isToBeSaved = true;
            savedFunctionCalled = false;
            MainFrame.isFreeSaved = false;
            isTileDataSaved=false;
            isPDFSaved=false;
            isLatexSaved=false;
            isImageSaved=false;
            if (dragging == true)  // Ignore mouse presses that occur
                return;            //    when user is already drawing a curve.
            prevX = startX = e.getX();  // Save mouse coordinates.
            prevY = startY = e.getY();

            graphicsForDrawing = getGraphics();  // For drawing on the screen.
            graphicsForDrawing.setColor(getCurrentColor());

            offscreenGraphics = getOSG();  // For drawing on the canvas.
            offscreenGraphics.setColor(getCurrentColor());
            dragging = true;  // Start drawing.
            calcDom(prevX, prevY);

        }

        public void calcDom(int xCoordinate, int yCoordinate) {
            int i = xCoordinate;
            int j = yCoordinate;
            xRightBound = -1;
            xLeftBound = -1;
            yUpBound = -1;
            yDownBound = -1;

            while (true) {
                if (i % tileWidth == 0) {
                    xRightBound = i;
                    break;
                } else {
                    i++;
                }
            }

            i = xCoordinate;

            while (true) {
                if (i % tileWidth == 0) {
                    xLeftBound = i;
                    break;
                } else {
                    i--;
                }
            }

            i = yCoordinate;

            while (true) {
                if (i % tileHeight == 0) {
                    yUpBound = i;
                    break;
                } else {
                    i--;
                }
            }

            i = yCoordinate;

            while (true) {
                if (i % tileHeight == 0) {
                    yDownBound = i;
                    break;
                } else {
                    i++;
                }
            }
        }

        public void domChange(int xCoordinate, int yCoordinate) {
            int remindexx,remindexy;
            if (pressed==4){
                boolean change = false;
                if (xCoordinate > xRightBound || xCoordinate < xLeftBound) {
                    change = true;
                    if (gridValueAddedArray[xCoordinate / tileWidth][yCoordinate / tileHeight]==true) {
                        remindexx=xCoordinateTileList.lastIndexOf(xCoordinate / tileWidth);
                        if(remindexx!=-1)
                            xCoordinateTileList.remove(remindexx);

                        remindexy=yCoordinateTileList.lastIndexOf(yCoordinate / tileHeight) ;
                        if(remindexy!=-1)
                            yCoordinateTileList.remove(remindexy);
                        gridValueAddedArray[xCoordinate / tileWidth][yCoordinate / tileHeight] = false;
                        
                    }

                    //System.out.println("x: " + xCoordinate + " removed\n");

                    calcDom(xCoordinate, yCoordinate);
                } else if (yCoordinate < yUpBound || yCoordinate > yDownBound) {
                    change = true;
                    if (gridValueAddedArray[xCoordinate / tileWidth][yCoordinate / tileHeight]==true) {
                        remindexx=xCoordinateTileList.lastIndexOf(xCoordinate / tileWidth);
                        if(remindexx!=-1)
                            xCoordinateTileList.remove(remindexx);

                        remindexy=yCoordinateTileList.lastIndexOf(yCoordinate / tileHeight) ;
                        if(remindexy!=-1)
                            yCoordinateTileList.remove(remindexy);
                        gridValueAddedArray[xCoordinate / tileWidth][yCoordinate / tileHeight] = false;
                        
                    }

                    //System.out.println("y: " + yCoordinate + " removed\n");

                    calcDom(xCoordinate, yCoordinate);
                }
            }

            else if(pressed==0){
                boolean change = false;
                if (xCoordinate > xRightBound || xCoordinate < xLeftBound) {
                    change = true;
                    if (!gridValueAddedArray[xCoordinate / tileWidth][yCoordinate / tileHeight]) {
                        xCoordinateTileList.add(xCoordinate / tileWidth);
                        yCoordinateTileList.add(yCoordinate / tileHeight);
                        
                        gridValueAddedArray[xCoordinate / tileWidth][yCoordinate / tileHeight] = true;
                    }

                    System.out.println("x: " + xCoordinate + " added w:"+ tileWidth+"\n");

                    calcDom(xCoordinate, yCoordinate);
                } else if (yCoordinate < yUpBound || yCoordinate > yDownBound) {
                    change = true;
                    if (!gridValueAddedArray[xCoordinate / tileWidth][yCoordinate / tileHeight]) {
                        xCoordinateTileList.add(xCoordinate / tileWidth);
                        yCoordinateTileList.add(yCoordinate / tileHeight);
                        
                        gridValueAddedArray[xCoordinate / tileWidth][yCoordinate / tileHeight] = true;
                    }

                    System.out.println("y: " + yCoordinate + " added w: "+ tileWidth+"\n");

                    calcDom(xCoordinate, yCoordinate);
                }
            }
        }


        @Override
        public void mouseReleased(MouseEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
            if (dragging == false)
                return;  // Nothing to do because the user isn't drawing.
            dragging = false;

            graphicsForDrawing.dispose();
            offscreenGraphics.dispose();
            graphicsForDrawing = null;
            offscreenGraphics = null;
            xRightBound = -1;
            xLeftBound = -1;
            yUpBound = -1;
            yDownBound = -1;

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void mouseExited(MouseEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
            if (dragging == false)
                return;  // Nothing to do because the user isn't drawing.

            int x = e.getX();   // x-coordinate of mouse.
            int y = e.getY();   // y=coordinate of mouse.


            // Draw the line on the applet and on the off-screen canvas.
            putMultiFigure(graphicsForDrawing,prevX, prevY, x, y);
            putMultiFigure(offscreenGraphics,prevX, prevY, x, y);
            domChange(x, y);
            prevX = x;  // Save coords for the next call to mouseDragged or mouseReleased.
            prevY = y;


        }

        @Override
        public void mouseMoved(MouseEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
        }


    }

}
