import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

/*
 * Created with IntelliJ IDEA.
 * User: Foram
 * Date: 8/5/13
 * Time: 6:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class SaveImageActionListener implements ActionListener {

    static ArrayList<Domains> xyDrawdataList;
    static ArrayList<Domains> xyDrawdataListFree; 
    static int bw=FreeGridActionListener.tileWidth;
    static int bh=FreeGridActionListener.tileHeight;
    static ModifySequences m;
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.

        if (MainFrame.newTileCreated) {
            JFrame gui = new JFrame("Output Value Generated");
            
            contentPane = gui.getContentPane();
            contentPane.setLayout(null);
            contentPane.setBackground(SystemColor.WHITE);
            JMenuBar menubar= new JMenuBar();
            JButton capture= new JButton("Capture Screenshot");
            menubar.add(capture);
            gui.setJMenuBar(menubar);
            capture.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					BufferedImage image=getScereenshot(contentPane);
					try{
						if (MainFrame.currentWindow==3){
						ImageIO.write(image,"png", new File(MainFrame.ProjPath+"/"+"DigitalGrid_Tile_Image_"+MainFrame.ProjName+".png"));
						}
						else 
						ImageIO.write(image,"png", new File(MainFrame.ProjPath+"/"+"FreeGrid_Tile_Image_"+MainFrame.ProjName+".png"));
		                JOptionPane.showMessageDialog(null, "File Saved Successfully !",
		                        "Success!", JOptionPane.INFORMATION_MESSAGE);

					}
					catch (Exception e){
						 JOptionPane.showMessageDialog(null, "No Tile has been created. Use 'New' to create a Tile first",
				                    "Error!", JOptionPane.INFORMATION_MESSAGE);
					}
					
				}
				});
            //----
            if(MainFrame.currentWindow==3){
            	DigitalGridActionListener.isImageSaved=true;
            	if(!DigitalGridActionListener.isTileDataSaved&&!DigitalGridActionListener.isLatexSaved&&
            			!DigitalGridActionListener.isPDFSaved&&!MainFrame.isFreeSaved){
            		m=new ModifySequences();
            	}
            	xyDrawdataList=m.xyDrawdataListDigital;
            	for(int i=0;i<xyDrawdataList.size();i++){
                    if(xyDrawdataList.get(i).getDomainOne()==""&&xyDrawdataList.get(i).getDomainTwo()==""){

                        addLowerHalf(xyDrawdataList.get(i).getxCoordinate(),xyDrawdataList.get(i).getyCoordinate());
                    }
                    else if(xyDrawdataList.get(i).getDomainThree()==""&&xyDrawdataList.get(i).getDomainFour()==""){

                        addUpperHalf(xyDrawdataList.get(i).getxCoordinate(),xyDrawdataList.get(i).getyCoordinate());
                    }
                	if(xyDrawdataList.get(i).getDomainOne()!=""&&xyDrawdataList.get(i).getDomainTwo()!=""&&
                            xyDrawdataList.get(i).getDomainThree()!=""&&xyDrawdataList.get(i).getDomainFour()!=""){
                		//start one domain T sequences
                        if(xyDrawdataList.get(i).getDomainOne().contains("TTTTT")&&((xyDrawdataList.get(i).getDomainTwo().contains("TTTTT")==false)
                        &&(xyDrawdataList.get(i).getDomainThree().contains("TTTTT")==false)&&(xyDrawdataList.get(i).getDomainFour().contains("TTTTT")==false))){
                        	addBottomLeft(xyDrawdataList.get(i).getxCoordinate(),xyDrawdataList.get(i).getyCoordinate());
                        	System.out.println("found bl ");
                        }
                        else if(xyDrawdataList.get(i).getDomainTwo().contains("TTTTT")&&((xyDrawdataList.get(i).getDomainOne().contains("TTTTT")==false)
                        &&(xyDrawdataList.get(i).getDomainThree().contains("TTTTT")==false)&&(xyDrawdataList.get(i).getDomainFour().contains("TTTTT")==false))){
                           	addBottomRight(xyDrawdataList.get(i).getxCoordinate(),xyDrawdataList.get(i).getyCoordinate());
                           	System.out.println("found br");
                        }
                        else if(xyDrawdataList.get(i).getDomainThree().contains("TTTTT")&&((xyDrawdataList.get(i).getDomainTwo().contains("TTTTT")==false)
                        &&(xyDrawdataList.get(i).getDomainOne().contains("TTTTT")==false)&&(xyDrawdataList.get(i).getDomainFour().contains("TTTTT")==false))){
                           	addTopRight(xyDrawdataList.get(i).getxCoordinate(),xyDrawdataList.get(i).getyCoordinate());
                           	System.out.println("found tr");
                        }
                        else if(xyDrawdataList.get(i).getDomainFour().contains("TTTTT")&&((xyDrawdataList.get(i).getDomainTwo().contains("TTTTT")==false)
                        &&(xyDrawdataList.get(i).getDomainThree().contains("TTTTT")==false)&&(xyDrawdataList.get(i).getDomainOne().contains("TTTTT")==false))){
                           	addTopLeft(xyDrawdataList.get(i).getxCoordinate(),xyDrawdataList.get(i).getyCoordinate());
                           	System.out.println("found tl");
                        }
                        //end one domain T Sequences
                        //start two T domain sequences
                        else if(xyDrawdataList.get(i).getDomainOne().contains("TTTTT")&&xyDrawdataList.get(i).getDomainTwo().contains("TTTTT")
                        &&((xyDrawdataList.get(i).getDomainThree().contains("TTTTT")==false)&&(xyDrawdataList.get(i).getDomainFour().contains("TTTTT")==false))){
                            addBottom(xyDrawdataList.get(i).getxCoordinate(),xyDrawdataList.get(i).getyCoordinate());
                            System.out.println("found b");
                        }
                        else if(xyDrawdataList.get(i).getDomainThree().contains("TTTTT")&&xyDrawdataList.get(i).getDomainFour().contains("TTTTT")
                        &&((xyDrawdataList.get(i).getDomainOne().contains("TTTTT")==false)&&(xyDrawdataList.get(i).getDomainTwo().contains("TTTTT")==false))){
                            addTop(xyDrawdataList.get(i).getxCoordinate(),xyDrawdataList.get(i).getyCoordinate());
                            System.out.println("found t");
                        }
                        else if(xyDrawdataList.get(i).getDomainOne().contains("TTTTT")&&xyDrawdataList.get(i).getDomainThree().contains("TTTTT")
                        &&((xyDrawdataList.get(i).getDomainTwo().contains("TTTTT")==false)&&(xyDrawdataList.get(i).getDomainFour().contains("TTTTT")==false))){
                            addBottomLeftTopRight(xyDrawdataList.get(i).getxCoordinate(),xyDrawdataList.get(i).getyCoordinate());
                            System.out.println("found trbl");
                        }
                        else if(xyDrawdataList.get(i).getDomainOne().contains("TTTTT")&&xyDrawdataList.get(i).getDomainFour().contains("TTTTT")
                        &&((xyDrawdataList.get(i).getDomainThree().contains("TTTTT")==false)&&(xyDrawdataList.get(i).getDomainTwo().contains("TTTTT")==false))){
                            addTopLeftBottomLeft(xyDrawdataList.get(i).getxCoordinate(),xyDrawdataList.get(i).getyCoordinate());
                            System.out.println("found tlbl");
                        }
                        else if(xyDrawdataList.get(i).getDomainTwo().contains("TTTTT")&&xyDrawdataList.get(i).getDomainThree().contains("TTTTT")
                        &&((xyDrawdataList.get(i).getDomainOne().contains("TTTTT")==false)&&(xyDrawdataList.get(i).getDomainFour().contains("TTTTT")==false))){
                            addTopRightBottomRight(xyDrawdataList.get(i).getxCoordinate(),xyDrawdataList.get(i).getyCoordinate());
                            System.out.println("found trbr");
                        }
                        else if(xyDrawdataList.get(i).getDomainTwo().contains("TTTTT")&&xyDrawdataList.get(i).getDomainFour().contains("TTTTT")
                        &&((xyDrawdataList.get(i).getDomainOne().contains("TTTTT")==false)&&(xyDrawdataList.get(i).getDomainThree().contains("TTTTT")==false))){
                           addTopLeftBottomRight(xyDrawdataList.get(i).getxCoordinate(),xyDrawdataList.get(i).getyCoordinate());
                           System.out.println("found tlbr");
                        }
                        //end two T domain sequences
                        //start three T domain sequences
                        else if(xyDrawdataList.get(i).getDomainTwo().contains("TTTTT")&&xyDrawdataList.get(i).getDomainFour().contains("TTTTT")&&xyDrawdataList.get(i).getDomainOne().contains("TTTTT")
                        &&(xyDrawdataList.get(i).getDomainThree().contains("TTTTT")==false)){
                           addTopLeftBottomLeftBottomRight(xyDrawdataList.get(i).getxCoordinate(),xyDrawdataList.get(i).getyCoordinate());
                           System.out.println("found tlblbr");
                        }
                        else if(xyDrawdataList.get(i).getDomainThree().contains("TTTTT")&&xyDrawdataList.get(i).getDomainFour().contains("TTTTT")&&xyDrawdataList.get(i).getDomainOne().contains("TTTTT")
                                &&(xyDrawdataList.get(i).getDomainTwo().contains("TTTTT")==false)){
                                   addTopLeftTopRighttBottomLeft(xyDrawdataList.get(i).getxCoordinate(),xyDrawdataList.get(i).getyCoordinate());
                                   System.out.println("found tltrbl");
                                }
                        else if(xyDrawdataList.get(i).getDomainTwo().contains("TTTTT")&&xyDrawdataList.get(i).getDomainFour().contains("TTTTT")&&xyDrawdataList.get(i).getDomainThree().contains("TTTTT")
                                &&(xyDrawdataList.get(i).getDomainOne().contains("TTTTT")==false)){
                                   addTopLeftTopRightBottomRight(xyDrawdataList.get(i).getxCoordinate(),xyDrawdataList.get(i).getyCoordinate());
                                   System.out.println("found tltrbr");
                                }
                        else if(xyDrawdataList.get(i).getDomainTwo().contains("TTTTT")&&xyDrawdataList.get(i).getDomainThree().contains("TTTTT")&&xyDrawdataList.get(i).getDomainOne().contains("TTTTT")
                                &&(xyDrawdataList.get(i).getDomainFour().contains("TTTTT")==false)){
                                   addTopRightBottomLeftBottomRight(xyDrawdataList.get(i).getxCoordinate(),xyDrawdataList.get(i).getyCoordinate());
                                   System.out.println("found trblbr");
                                }
                        
                        //end three T domain sequences
                        else{
                        	addFull(xyDrawdataList.get(i).getxCoordinate(),xyDrawdataList.get(i).getyCoordinate());
                        	System.out.println("found full");
                        }
                    }
                }
                gui.setVisible(true);
                gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                gui.setSize(700,450);
                gui.setResizable(false);
                ImageIcon imgd = new ImageIcon("images/templogo.png");
                gui.setIconImage(imgd.getImage());

                //jdialog starts
                final JDialog d = new JDialog((Frame)null,"DNA Sequences");
                d.setIconImage(imgd.getImage());
                JPanel topPanel = new JPanel(new GridLayout(1,2));
                d.setLocation(710,0);
                JPanel basic = new JPanel();
                basic.setLayout(new BoxLayout(basic, BoxLayout.Y_AXIS));
                d.add(basic);
                JPanel textPanel = new JPanel(new BorderLayout());
                textPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
                final JTextPane pane = new JTextPane();
                pane.setContentType("text/html");
                String text =getText();
                pane.setText(text);
                pane.setEditable(false);
                textPanel.add(new JScrollPane(pane));
                topPanel.setSize(700,400);
                basic.add(textPanel);
                basic.add(topPanel);
                d.setSize(new Dimension(450, 150));
                d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                d.setVisible(true);
            }
            
            else if(MainFrame.currentWindow==2){
            	FreeGridActionListener.isImageSaved=true;
            	if(!FreeGridActionListener.isTileDataSaved&&!FreeGridActionListener.isLatexSaved&&
            			!FreeGridActionListener.isPDFSaved&&!MainFrame.isDigitizedSaved){
            		m=new ModifySequences();
            	}
            	xyDrawdataListFree=m.xyDrawdataListFree;
            	System.out.println("1");
                    bw=FreeGridActionListener.tileWidth;
                    bh=FreeGridActionListener.tileHeight;
                for(int i=0;i<xyDrawdataListFree.size();i++){
                	System.out.println("2");
                    if(xyDrawdataListFree.get(i).getDomainOne()!=""&&xyDrawdataListFree.get(i).getDomainTwo()!=""&&
                            xyDrawdataListFree.get(i).getDomainThree()!=""&&xyDrawdataListFree.get(i).getDomainFour()!=""){
                    	//start one domain T sequences
                    	System.out.println("3");
                        if(xyDrawdataListFree.get(i).getDomainOne().contains("TTTTT")&&
                        		((xyDrawdataListFree.get(i).getDomainTwo().contains("TTTTT")==false)
                        &&(xyDrawdataListFree.get(i).getDomainThree().contains("TTTTT")==false)&&
                        (xyDrawdataListFree.get(i).getDomainFour().contains("TTTTT")==false))){
                        	System.out.println("found bl");
                        	addBottomLeftFree(xyDrawdataListFree.get(i).getxCoordinate(),xyDrawdataListFree.get(i).getyCoordinate());
                        }
                        else if(xyDrawdataListFree.get(i).getDomainTwo().contains("TTTTT")&&((xyDrawdataListFree.get(i).getDomainOne().contains("TTTTT")==false)
                        &&(xyDrawdataListFree.get(i).getDomainThree().contains("TTTTT")==false)&&(xyDrawdataListFree.get(i).getDomainFour().contains("TTTTT")==false))){
                           	addBottomRightFree(xyDrawdataListFree.get(i).getxCoordinate(),xyDrawdataListFree.get(i).getyCoordinate());
                           	System.out.println("found br");
                        }
                        else if(xyDrawdataListFree.get(i).getDomainThree().contains("TTTTT")&&((xyDrawdataListFree.get(i).getDomainTwo().contains("TTTTT")==false)
                        &&(xyDrawdataListFree.get(i).getDomainOne().contains("TTTTT")==false)&&(xyDrawdataListFree.get(i).getDomainFour().contains("TTTTT")==false))){
                           	addTopRightFree(xyDrawdataListFree.get(i).getxCoordinate(),xyDrawdataListFree.get(i).getyCoordinate());
                           	System.out.println("found tr");
                        }
                        else if(xyDrawdataListFree.get(i).getDomainFour().contains("TTTTT")&&((xyDrawdataListFree.get(i).getDomainTwo().contains("TTTTT")==false)
                        &&(xyDrawdataListFree.get(i).getDomainThree().contains("TTTTT")==false)&&(xyDrawdataListFree.get(i).getDomainOne().contains("TTTTT")==false))){
                           	addTopLeftFree(xyDrawdataListFree.get(i).getxCoordinate(),xyDrawdataListFree.get(i).getyCoordinate());
                           	System.out.println("found tl");
                        }
                        //end one domain T Sequences
                        //start two T domain sequences
                        else if(xyDrawdataListFree.get(i).getDomainOne().contains("TTTTT")&&xyDrawdataListFree.get(i).getDomainTwo().contains("TTTTT")
                        &&((xyDrawdataListFree.get(i).getDomainThree().contains("TTTTT")==false)&&(xyDrawdataListFree.get(i).getDomainFour().contains("TTTTT")==false))){
                            addBottomFree(xyDrawdataListFree.get(i).getxCoordinate(),xyDrawdataListFree.get(i).getyCoordinate());
                            System.out.println("found b");
                        }
                        else if(xyDrawdataListFree.get(i).getDomainThree().contains("TTTTT")&&xyDrawdataListFree.get(i).getDomainFour().contains("TTTTT")
                        &&((xyDrawdataListFree.get(i).getDomainOne().contains("TTTTT")==false)&&(xyDrawdataListFree.get(i).getDomainTwo().contains("TTTTT")==false))){
                            addTopFree(xyDrawdataListFree.get(i).getxCoordinate(),xyDrawdataListFree.get(i).getyCoordinate());
                            System.out.println("found t");
                        }
                        else if(xyDrawdataListFree.get(i).getDomainOne().contains("TTTTT")&&xyDrawdataListFree.get(i).getDomainThree().contains("TTTTT")
                        &&((xyDrawdataListFree.get(i).getDomainTwo().contains("TTTTT")==false)&&(xyDrawdataListFree.get(i).getDomainFour().contains("TTTTT")==false))){
                            addBottomLeftTopRightFree(xyDrawdataListFree.get(i).getxCoordinate(),xyDrawdataListFree.get(i).getyCoordinate());
                            System.out.println("found trbl");
                        }
                        else if(xyDrawdataListFree.get(i).getDomainOne().contains("TTTTT")&&xyDrawdataListFree.get(i).getDomainFour().contains("TTTTT")
                        &&((xyDrawdataListFree.get(i).getDomainThree().contains("TTTTT")==false)&&(xyDrawdataListFree.get(i).getDomainTwo().contains("TTTTT")==false))){
                            addTopLeftBottomLeftFree(xyDrawdataListFree.get(i).getxCoordinate(),xyDrawdataListFree.get(i).getyCoordinate());
                            System.out.println("found tlbl");
                        }
                        else if(xyDrawdataListFree.get(i).getDomainTwo().contains("TTTTT")&&xyDrawdataListFree.get(i).getDomainThree().contains("TTTTT")
                        &&((xyDrawdataListFree.get(i).getDomainOne().contains("TTTTT")==false)&&(xyDrawdataListFree.get(i).getDomainFour().contains("TTTTT")==false))){
                            addTopRightBottomRightFree(xyDrawdataListFree.get(i).getxCoordinate(),xyDrawdataListFree.get(i).getyCoordinate());
                            System.out.println("found trbr");
                        }
                        else if(xyDrawdataListFree.get(i).getDomainTwo().contains("TTTTT")&&xyDrawdataListFree.get(i).getDomainFour().contains("TTTTT")
                        &&((xyDrawdataListFree.get(i).getDomainOne().contains("TTTTT")==false)&&
                        		(xyDrawdataListFree.get(i).getDomainThree().contains("TTTTT")==false))){
                           addTopLeftBottomRightFree(xyDrawdataListFree.get(i).getxCoordinate(),xyDrawdataListFree.get(i).getyCoordinate());
                           System.out.println("found tlbr");
                        }
                        //end two T domain sequences
                        //start three T domain sequences
                        else if(xyDrawdataListFree.get(i).getDomainTwo().contains("TTTTT")&&xyDrawdataListFree.get(i).getDomainFour().contains("TTTTT")&&
                        		xyDrawdataListFree.get(i).getDomainOne().contains("TTTTT")
                        &&(xyDrawdataListFree.get(i).getDomainThree().contains("TTTTT")==false)){
                           addTopLeftBottomLeftBottomRightFree(xyDrawdataListFree.get(i).getxCoordinate(),xyDrawdataListFree.get(i).getyCoordinate());
                           System.out.println("found tlblbr");
                        }
                        else if(xyDrawdataListFree.get(i).getDomainThree().contains("TTTTT")&&xyDrawdataListFree.get(i).getDomainFour().contains("TTTTT")&&
                        		xyDrawdataListFree.get(i).getDomainOne().contains("TTTTT")
                                &&(xyDrawdataListFree.get(i).getDomainTwo().contains("TTTTT")==false)){
                        		System.out.println("found tltrbl");   
                        		addTopLeftTopRighttBottomLeftFree(xyDrawdataListFree.get(i).getxCoordinate(),xyDrawdataListFree.get(i).getyCoordinate());
                                }
                        else if(xyDrawdataListFree.get(i).getDomainTwo().contains("TTTTT")&&xyDrawdataListFree.get(i).getDomainFour().contains("TTTTT")&&
                        		xyDrawdataListFree.get(i).getDomainThree().contains("TTTTT")
                                &&(xyDrawdataListFree.get(i).getDomainOne().contains("TTTTT")==false)){
                                   addTopLeftTopRightBottomRightFree(xyDrawdataListFree.get(i).getxCoordinate(),xyDrawdataListFree.get(i).getyCoordinate());
                                   System.out.println("found tltrbr");
                                }
                        else if(xyDrawdataListFree.get(i).getDomainTwo().contains("TTTTT")&&xyDrawdataListFree.get(i).getDomainThree().contains("TTTTT")&&
                        		xyDrawdataListFree.get(i).getDomainOne().contains("TTTTT")
                                &&(xyDrawdataListFree.get(i).getDomainFour().contains("TTTTT")==false)){
                                   addTopRightBottomLeftBottomRightFree(xyDrawdataListFree.get(i).getxCoordinate(),xyDrawdataListFree.get(i).getyCoordinate());
                                   System.out.println("found trblbr");
                                }
                        //end three T domain sequences
                        else if(xyDrawdataListFree.get(i).getDomainTwo().contains("TTTTT")==false&&
                        		xyDrawdataListFree.get(i).getDomainThree().contains("TTTTT")==false&&
                        		xyDrawdataListFree.get(i).getDomainOne().contains("TTTTT")==false
                                &&(xyDrawdataListFree.get(i).getDomainFour().contains("TTTTT")==false)){
                        	addFullFree(xyDrawdataListFree.get(i).getxCoordinate(),xyDrawdataListFree.get(i).getyCoordinate());
                        	System.out.println("found full");
                        }
                        
                        
                    }
                    if(xyDrawdataListFree.get(i).getDomainOne()==""&&xyDrawdataListFree.get(i).getDomainTwo()==""){
                        addLowerHalfFree(xyDrawdataListFree.get(i).getxCoordinate(),xyDrawdataListFree.get(i).getyCoordinate());
                    }
                    if(xyDrawdataListFree.get(i).getDomainThree()==""&&xyDrawdataListFree.get(i).getDomainFour()==""){
                        addUpperHalfFree(xyDrawdataListFree.get(i).getxCoordinate(),xyDrawdataListFree.get(i).getyCoordinate());
                    }
                }
                gui.setVisible(true);
                gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                gui.setSize(1600,1000);
                gui.setResizable(false);
                ImageIcon imgd = new ImageIcon("images/templogo.png");
                gui.setIconImage(imgd.getImage());
                //jdialog starts
                
                final JDialog d = new JDialog((Frame)null,"DNA Sequences");
                d.setIconImage(imgd.getImage());
                JPanel topPanel = new JPanel(new GridLayout(1,2));
                d.setLocation(710,0);
                JPanel basic = new JPanel();
                basic.setLayout(new BoxLayout(basic, BoxLayout.Y_AXIS));
                d.add(basic);
                JPanel textPanel = new JPanel(new BorderLayout());
                textPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
                final JTextPane pane = new JTextPane();
                pane.setContentType("text/html");
                String text =getTextFree();
                pane.setText(text);
                pane.setEditable(false);
                textPanel.add(new JScrollPane(pane));
                topPanel.setSize(700,400);
                basic.add(textPanel);
                basic.add(topPanel);
                d.setSize(new Dimension(450, 150));
                d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                d.setVisible(true);
               }
            }
        }
    
    public static BufferedImage getScereenshot(Component component){
    	BufferedImage image = new BufferedImage(
    		      component.getWidth(),
    		      component.getHeight(),
    		      BufferedImage.TYPE_INT_RGB
    		      );
    		    component.paint( image.getGraphics() );
    		    return image;
    }
    
    
    
    
    public static void addUpperHalf(int a, int b){
        JLabel img = new JLabel(new ImageIcon("images/upperhalf.png"));
        if(b%2==0)
            img.setBounds(45*a,15*b+15+7, 45,8); // x, y, width, height
        else
            img.setBounds(45*a+22,15*b+15+7, 45,8); // x, y, width, height
        contentPane.add(img);
    }
    public static void addFull(int a, int b){
        JLabel img = new JLabel(new ImageIcon("images/full.png"));
        if(b%2==0){
            img.setBounds(45*a,15*b+15, 45,15); // x, y, width, height
        }
        else
            img.setBounds(45*a+22,15*b+15, 45,15); // x, y, width, height
        contentPane.add(img);
    }
    public static void addBottomLeft(int a, int b){
        JLabel img = new JLabel(new ImageIcon("images/bottomleft.png"));
        if(b%2==0){
            img.setBounds(45*a,15*b+15, 45,15); // x, y, width, height
        }
        else
            img.setBounds(45*a+22,15*b+15, 45,15); // x, y, width, height
        contentPane.add(img);
    }
    public static void addBottomRight(int a, int b){
        JLabel img = new JLabel(new ImageIcon("images/bottomright.png"));
        if(b%2==0){
            img.setBounds(45*a,15*b+15, 45,15); // x, y, width, height
        }
        else
            img.setBounds(45*a+22,15*b+15, 45,15); // x, y, width, height
        contentPane.add(img);
    }
    public static void addTopLeft(int a, int b){
        JLabel img = new JLabel(new ImageIcon("images/topleft.png"));
        if(b%2==0){
            img.setBounds(45*a,15*b+15, 45,15); // x, y, width, height
        }
        else
            img.setBounds(45*a+22,15*b+15, 45,15); // x, y, width, height
        contentPane.add(img);
    }
    public static void addTopRight(int a, int b){
        JLabel img = new JLabel(new ImageIcon("images/topright.png"));
        if(b%2==0){
            img.setBounds(45*a,15*b+15, 45,15); // x, y, width, height
        }
        else
            img.setBounds(45*a+22,15*b+15, 45,15); // x, y, width, height
        contentPane.add(img);
    }
    public static void addTop(int a, int b){
        JLabel img = new JLabel(new ImageIcon("images/top.png"));
        if(b%2==0){
            img.setBounds(45*a,15*b+15, 45,15); // x, y, width, height
        }
        else
            img.setBounds(45*a+22,15*b+15, 45,15); // x, y, width, height
        contentPane.add(img);
    }
    public static void addBottom(int a, int b){
        JLabel img = new JLabel(new ImageIcon("images/bottom.png"));
        if(b%2==0){
            img.setBounds(45*a,15*b+15, 45,15); // x, y, width, height
        }
        else
            img.setBounds(45*a+22,15*b+15, 45,15); // x, y, width, height
        contentPane.add(img);
    }
    public static void addBottomLeftTopRight(int a, int b){
        JLabel img = new JLabel(new ImageIcon("images/bottomlefttopright.png"));
        if(b%2==0){
            img.setBounds(45*a,15*b+15, 45,15); // x, y, width, height
        }
        else
            img.setBounds(45*a+22,15*b+15, 45,15); // x, y, width, height
        contentPane.add(img);
    }
    public static void addTopLeftBottomLeft(int a, int b){
        JLabel img = new JLabel(new ImageIcon("images/topleftbottomleft.png"));
        if(b%2==0){
            img.setBounds(45*a,15*b+15, 45,15); // x, y, width, height
        }
        else
            img.setBounds(45*a+22,15*b+15, 45,15); // x, y, width, height
        contentPane.add(img);
    }
    public static void addTopRightBottomRight(int a, int b){
        JLabel img = new JLabel(new ImageIcon("images/toprightbottomright.png"));
        if(b%2==0){
            img.setBounds(45*a,15*b+15, 45,15); // x, y, width, height
        }
        else
            img.setBounds(45*a+22,15*b+15, 45,15); // x, y, width, height
        contentPane.add(img);
    }
    public static void addTopLeftBottomRight(int a, int b){
        JLabel img = new JLabel(new ImageIcon("images/topleftbottomright.png"));
        if(b%2==0){
            img.setBounds(45*a,15*b+15, 45,15); // x, y, width, height
        }
        else
            img.setBounds(45*a+22,15*b+15, 45,15); // x, y, width, height
        contentPane.add(img);
    }
    public static void addTopLeftBottomLeftBottomRight(int a, int b){
        JLabel img = new JLabel(new ImageIcon("images/topleftbottomleftbottomright.png"));
        if(b%2==0){
            img.setBounds(45*a,15*b+15, 45,15); // x, y, width, height
        }
        else
            img.setBounds(45*a+22,15*b+15, 45,15); // x, y, width, height
        contentPane.add(img);
    }
    public static void addTopLeftTopRighttBottomLeft(int a, int b){
        JLabel img = new JLabel(new ImageIcon("images/toplefttoprightbottomleft.png"));
        if(b%2==0){
            img.setBounds(45*a,15*b+15, 45,15); // x, y, width, height
        }
        else
            img.setBounds(45*a+22,15*b+15, 45,15); // x, y, width, height
        contentPane.add(img);
    }
    public static void addTopLeftTopRightBottomRight(int a, int b){
        JLabel img = new JLabel(new ImageIcon("images/toplefttoprightbottomright.png"));
        if(b%2==0){
            img.setBounds(45*a,15*b+15, 45,15); // x, y, width, height
        }
        else
            img.setBounds(45*a+22,15*b+15, 45,15); // x, y, width, height
        contentPane.add(img);
    }
    public static void addTopRightBottomLeftBottomRight(int a, int b){
        JLabel img = new JLabel(new ImageIcon("images/toprightbottomleftbottomright.png"));
        if(b%2==0){
            img.setBounds(45*a,15*b+15, 45,15); // x, y, width, height
        }
        else
            img.setBounds(45*a+22,15*b+15, 45,15); // x, y, width, height
        contentPane.add(img);
    }   
    public static void addLowerHalf(int a, int b){
        JLabel img = new JLabel(new ImageIcon("images/lowerhalf.png"));
        System.out.println(" lh called");
        if(b%2==0)
            img.setBounds(45*a,15*b+15, 45,8); // x, y, width, height
        else
            img.setBounds(45*a+22,15*b+15, 45,8); // x, y, width, height
        contentPane.add(img);
    }
    public static void addUpperHalfFree(int a, int b){
        JLabel img = new JLabel(new ImageIcon("images/TileImages/"+bh+"x"+bw+"upperhalf.png"));
        System.out.println(" uh called");
            if(b%2==0)
                img.setBounds(a*bw,b*bh+bh,bw,bh/2);
            else
                img.setBounds(a*bw+(bw/2),b*bh+bh,bw,bh/2);
        contentPane.add(img);
    }
    public static void addBottomLeftFree(int a, int b){
        JLabel img = new JLabel(new ImageIcon("images/TileImages/"+bh+"x"+bw+"bl.png"));
        System.out.println(" bl called");
            if(b%2==0){
                img.setBounds(a*bw,b*bh+(bh/2),bw,bh);
            }
            else
                img.setBounds(a*bw+(bw/2),b*bh+(bh/2),bw,bh);

        contentPane.add(img);
    }
    public static void addBottomRightFree(int a, int b){
        JLabel img = new JLabel(new ImageIcon("images/TileImages/"+bh+"x"+bw+"br.png"));
        System.out.println(" br called");
            if(b%2==0){
                img.setBounds(a*bw,b*bh+(bh/2),bw,bh);
            }
            else
                img.setBounds(a*bw+(bw/2),b*bh+(bh/2),bw,bh);

        contentPane.add(img);
    }
    public static void addTopRightFree(int a, int b){
        JLabel img = new JLabel(new ImageIcon("images/TileImages/"+bh+"x"+bw+"tr.png"));
        System.out.println(" tr called");
            if(b%2==0){
                img.setBounds(a*bw,b*bh+(bh/2),bw,bh);
            }
            else
                img.setBounds(a*bw+(bw/2),b*bh+(bh/2),bw,bh);

        contentPane.add(img);
    }
    public static void addTopLeftFree(int a, int b){
        JLabel img = new JLabel(new ImageIcon("images/TileImages/"+bh+"x"+bw+"tl.png"));
        System.out.println(" tl called");
            if(b%2==0){
                img.setBounds(a*bw,b*bh+(bh/2),bw,bh);
            }
            else
                img.setBounds(a*bw+(bw/2),b*bh+(bh/2),bw,bh);

        contentPane.add(img);
    }
    public static void addBottomFree(int a, int b){
        JLabel img = new JLabel(new ImageIcon("images/TileImages/"+bh+"x"+bw+"b.png"));
        System.out.println(" b called");
            if(b%2==0){
                img.setBounds(a*bw,b*bh+(bh/2),bw,bh);
            }
            else
                img.setBounds(a*bw+(bw/2),b*bh+(bh/2),bw,bh);

        contentPane.add(img);
    }
    public static void addTopFree(int a, int b){
        JLabel img = new JLabel(new ImageIcon("images/TileImages/"+bh+"x"+bw+"t.png"));
        System.out.println(" t called");
            if(b%2==0){
                img.setBounds(a*bw,b*bh+(bh/2),bw,bh);
            }
            else
                img.setBounds(a*bw+(bw/2),b*bh+(bh/2),bw,bh);

        contentPane.add(img);
    }
    public static void addBottomLeftTopRightFree(int a, int b){
        JLabel img = new JLabel(new ImageIcon("images/TileImages/"+bh+"x"+bw+"trbl.png"));
        System.out.println(" bltr called");
            if(b%2==0){
                img.setBounds(a*bw,b*bh+(bh/2),bw,bh);
            }
            else
                img.setBounds(a*bw+(bw/2),b*bh+(bh/2),bw,bh);

        contentPane.add(img);
    }
    public static void addTopLeftBottomLeftFree(int a, int b){
        JLabel img = new JLabel(new ImageIcon("images/TileImages/"+bh+"x"+bw+"tlbl.png"));
        System.out.println(" tlbl called");
            if(b%2==0){
                img.setBounds(a*bw,b*bh+(bh/2),bw,bh);
            }
            else
                img.setBounds(a*bw+(bw/2),b*bh+(bh/2),bw,bh);

        contentPane.add(img);
    }
    public static void addTopRightBottomRightFree(int a, int b){
        JLabel img = new JLabel(new ImageIcon("images/TileImages/"+bh+"x"+bw+"trbr.png"));
        System.out.println(" trbr called");
            if(b%2==0){
                img.setBounds(a*bw,b*bh+(bh/2),bw,bh);
            }
            else
                img.setBounds(a*bw+(bw/2),b*bh+(bh/2),bw,bh);

        contentPane.add(img);
    }
    public static void addTopLeftBottomRightFree(int a, int b){
        JLabel img = new JLabel(new ImageIcon("images/TileImages/"+bh+"x"+bw+"tlbr.png"));
        System.out.println(" tlbr called");
            if(b%2==0){
                img.setBounds(a*bw,b*bh+(bh/2),bw,bh);
            }
            else
                img.setBounds(a*bw+(bw/2),b*bh+(bh/2),bw,bh);

        contentPane.add(img);
    }
    public static void addTopLeftBottomLeftBottomRightFree(int a, int b){
        JLabel img = new JLabel(new ImageIcon("images/TileImages/"+bh+"x"+bw+"tlblbr.png"));
        System.out.println(" tlblbr called");
            if(b%2==0){
                img.setBounds(a*bw,b*bh+(bh/2),bw,bh);
            }
            else
                img.setBounds(a*bw+(bw/2),b*bh+(bh/2),bw,bh);

        contentPane.add(img);
    }
    public static void addTopLeftTopRighttBottomLeftFree(int a, int b){
        JLabel img = new JLabel(new ImageIcon("images/TileImages/"+bh+"x"+bw+"tltrbl.png"));
        System.out.println(" tltrbl called");
            if(b%2==0){
                img.setBounds(a*bw,b*bh+(bh/2),bw,bh);
            }
            else
                img.setBounds(a*bw+(bw/2),b*bh+(bh/2),bw,bh);

        contentPane.add(img);
    }
    public static void addTopLeftTopRightBottomRightFree(int a, int b){
        JLabel img = new JLabel(new ImageIcon("images/TileImages/"+bh+"x"+bw+"tltrbr.png"));
        System.out.println(" tltrbr called");
            if(b%2==0){
                img.setBounds(a*bw,b*bh+(bh/2),bw,bh);
            }
            else
                img.setBounds(a*bw+(bw/2),b*bh+(bh/2),bw,bh);

        contentPane.add(img);
    }
    public static void addFullFree(int a, int b){
        JLabel img = new JLabel(new ImageIcon("images/TileImages/"+bh+"x"+bw+"full.png"));
        System.out.println(" full called");
            if(b%2==0){
                img.setBounds(a*bw,b*bh+(bh/2),bw,bh);
            }
            else
                img.setBounds(a*bw+(bw/2),b*bh+(bh/2),bw,bh);

        contentPane.add(img);
    }
    public static void addTopRightBottomLeftBottomRightFree(int a, int b){
        JLabel img = new JLabel(new ImageIcon("images/TileImages/"+bh+"x"+bw+"trblbr.png"));
        System.out.println(" trblbr called");
            if(b%2==0){
                img.setBounds(a*bw,b*bh+(bh/2),bw,bh);
            }
            else
                img.setBounds(a*bw+(bw/2),b*bh+(bh/2),bw,bh);

        contentPane.add(img);
    }
    
    public static void addLowerHalfFree(int a, int b){
        JLabel img = new JLabel(new ImageIcon("images/TileImages/"+bh+"x"+bw+"lowerhalf.png"));
            if(b%2==0)
                img.setBounds(a*bw,b*bh+bh/2,bw,bh/2);
            else
                img.setBounds(a*bw+(bw/2),b*bh+bh/2, bw,bh/2);

        contentPane.add(img);

    }
    public static String getText(){
        String dnaSequences="\n";
        for(int i=0;i<xyDrawdataList.size();i++){
            dnaSequences=dnaSequences.concat("Tile "+i+": X: "+xyDrawdataList.get(i).getxCoordinate()+" Y:"+
                    xyDrawdataList.get(i).getyCoordinate()+ " DNA Sequence: "+xyDrawdataList.get(i).getDomainOne()+
                    xyDrawdataList.get(i).getDomainTwo()+xyDrawdataList.get(i).getDomainThree()+
                    xyDrawdataList.get(i).getDomainFour()+"\n");
        }
        //System.out.println(dnaSequences);
        return dnaSequences;
    }
    public static String getTextFree(){
        String dnaSequences="\n";
        for(int i=0;i<xyDrawdataListFree.size();i++){
            dnaSequences=dnaSequences.concat("Tile "+i+": X: "+xyDrawdataListFree.get(i).getxCoordinate()+" Y:"+
                    xyDrawdataListFree.get(i).getyCoordinate()+ " DNA Sequence: "+xyDrawdataListFree.get(i).getDomainOne()+
                    xyDrawdataListFree.get(i).getDomainTwo()+xyDrawdataListFree.get(i).getDomainThree()+
                    xyDrawdataListFree.get(i).getDomainFour()+"\n");
        }
        //System.out.println(dnaSequences);
        return dnaSequences;
    }

    public static Container contentPane;

}
