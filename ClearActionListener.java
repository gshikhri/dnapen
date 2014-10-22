/* Author: Arnav Goyal, Shikhar K Gupta, Foram Joshi and Akshita Sahai
 * Project: DNA Pen
 * Mentor: Prof. Manish K Gupta
 */

/* ClearActionListener.java
 * This class defines the Action Listener for the 'Clear' option under the 'Edit' menu.
 * Whenever the user clicks on 'Clear', this class is initiated.
 * The entire Molecular Canvas is cleared and all the save flags are reset.
 * The ArrayLists used to store the Draw Information are also cleared.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ClearActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
        if (MainFrame.currentWindow == 1 || MainFrame.currentWindow == 0) {
            JOptionPane.showMessageDialog(null, "Nothing to clear. Select a canvas first.",
                    "Error!", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

		if(MainFrame.currentWindow == 2) { // Free Style Molecular Canvas
            // Clear Draw Data

            if(true){
               FreeGridActionListener.canvas.setBackground(Color.red);
                System.out.println("Clear called in Save Tile Data");
            }
            FreeGridActionListener.xCoordinateTileList.clear();
            FreeGridActionListener.yCoordinateTileList.clear();
            System.out.print("Free Grid clear has been called 1");
			FreeGridActionListener.canvas.setBackground(Color.white);
			FreeGridActionListener.canvas.pressed = 18;
            System.out.print("Free Grid clear has been called 2");

            // Reset Flags
			MainFrame.isFreeSaved=true;

		}
		
		if(MainFrame.currentWindow == 3) { // Digitized Molecular Canvas
		    // Clear Draw Data
            DigitalGridActionListener.xyCoordinatesTileList.clear();
            DigitalGridActionListener.digitizedDataStack.clearData();

			DigitalGridActionListener.canvas.setBackground(Color.white);
			DigitalGridActionListener.canvas.pressed = 2;
			DigitalGridActionListener.canvas.repaint();

			// Reset Flags
			MainFrame.isDigitizedSaved=true;
		}
    }
}
