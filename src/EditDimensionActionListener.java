import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: Foram Joshi
 */
public class EditDimensionActionListener implements ActionListener {

    static int TilewallWidth=35;
    static int TileWallHeight=15;
    Object selection1;
    boolean show=false;

    @Override
    public void actionPerformed(ActionEvent e) {
        if(MainFrame.currentWindow==3){
            JOptionPane.showMessageDialog(null, "In order to change dimensions, please select Free Grid Molecular Canvas",
                    "Success!", JOptionPane.INFORMATION_MESSAGE);
        }

        else if(MainFrame.currentWindow==2){
            if (MainFrame.isFreeSaved==false) {

                Object[] options = {"Yes",
                        "No",
                        "Cancel"};

                int userChoice = JOptionPane.showOptionDialog(null,
                        "All previous data will be lost \n" +
                                "Change Dimension anyway?",
                        "Save Resource",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[2]);

                if (userChoice == 0) {
                   show=true;
                }

                else if (userChoice == 1) {
                    return;
                }
            }
            
            else
            	show=true;
            
            if(show==true){
                show=false;
                String[] selectionValues1 = new String[]{  "210nm x 138nm", "175nm x 114nm", "147nm x 99nm", "126nm x 87nm","112nm x 75nm" };
                String initialSelection1 = "210nm x 138nm";
                selection1 = (JOptionPane.showInputDialog(null, "Select a dimension for the Tile Wall",
                        "Dimensions (nmxnm)", JOptionPane.QUESTION_MESSAGE, null, selectionValues1, initialSelection1));

                System.out.print("value selected: " + selection1);

                if(selection1.equals("210nm x 138nm")){
                    TileWallHeight=15;
                    TilewallWidth=35;
                    System.out.print("\nFree Grid clear has been called 5");
                }
                else if(selection1.equals("175nm x 114nm")){
                    TileWallHeight=18;
                    TilewallWidth=42;
                    System.out.print("\nFree Grid clear has been called 6");
                }
                else if(selection1.equals("147nm x 99nm")){
                    TileWallHeight=21;
                    TilewallWidth=49;
                    System.out.print("\nFree Grid clear has been called 7");
                }
                else if(selection1.equals( "126nm x 87nm")){
                    TileWallHeight=24;
                    TilewallWidth=56;
                    System.out.print("\nFree Grid clear has been called 8");
                }
                else if(selection1.equals("112nm x 75nm")){
                    TileWallHeight=27;
                    TilewallWidth=63;
                    System.out.print("\nFree Grid clear has been called 9");
                }
                else{
                    TileWallHeight=3;
                    TilewallWidth=7;
                    System.out.print("\nFree Grid clear has been called 10");

                }
                FreeGridActionListener.xCoordinateTileList.clear();
                FreeGridActionListener.yCoordinateTileList.clear();
                System.out.print("\nFree Grid clear has been called from edit dimension");
                FreeGridActionListener.canvas.pressed = 2;
                FreeGridActionListener.canvas.repaint();
                FreeGridActionListener.canvas.setBackground(Color.white);


            }

        }


    }
}
