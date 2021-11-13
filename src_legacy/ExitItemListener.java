/* Author: Arnav Goyal, Shikhar K Gupta, Foram Meghal Joshi, Sushant Pritmani and Akshita Sahai
 * Project: DNA Pen
 * Mentor: Prof. Manish K Gupta
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;


public class ExitItemListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if (MainFrame.currentWindow == 0) {
            System.exit(0);
        }
        if (MainFrame.currentWindow == 1) {
            exitSaveTileData();
        } else if (MainFrame.currentWindow == 2) {
            if (MainFrame.isFreeSaved==false) {
                Object[] options = {"Yes",
                        "No",
                        "Cancel"};

                int userChoice = JOptionPane.showOptionDialog(null,
                        "Would you like to save the DNA Seq before leaving?",
                        "Save Resource",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[2]);

                if (userChoice == 0) {

                    BufferedWriter bufferedWriter = null;
                    if (true) {
                        if(MainFrame.currentWindow == 2) {




                        }
                    }
                }
                else if (userChoice == 2) {
                    return;
                }
            }
            FreeGridActionListener.canvas.setVisible(false);
            MainFrame.mainFrame.setTitle("DNA Pen - Welcome !");
            exitSaveTileData();
        } else if (MainFrame.currentWindow == 3) {
            if (MainFrame.isDigitizedSaved==false) {
                Object[] options = {"Yes",
                        "No",
                        "Cancel"};

                int userChoice = JOptionPane.showOptionDialog(null,
                        "Would you like to save the DNA Seq before leaving?",
                        "Save Resource",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[2]);

                if (userChoice == 0) {

                    BufferedWriter bufferedWriter = null;
                    if (true) {
                        if(MainFrame.currentWindow == 3) {

                        }
                    }
                } else if (userChoice == 2) {
                    return;
                }
            }
            DigitalGridActionListener.canvas.setVisible(false);
            MainFrame.mainFrame.setTitle("DNA Pen - Welcome !");
            exitSaveTileData();
        }
    }

    public void exitSaveTileData() {
        if (MainFrame.newTileCreated) {
            if (MainFrame.saveTileFunctionCalled) {
                System.exit(0);
            } else {
                Object[] options = {"Yes",
                        "No",
                        "Cancel"};

                int userChoice = JOptionPane.showOptionDialog(null,
                        "Would you like to save the Tile Data before leaving?",
                        "Save Resource",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[2]);

                if (userChoice == 0) {
                    System.out.println(userChoice);

                    BufferedWriter bufferedWriter = null;
                    if (true) {
                        if(MainFrame.currentWindow == 1) {

                        }
                    }
                    System.exit(0);
                } else if (userChoice == 2) {
                    return;
                } else if (userChoice == 1) {
                    System.exit(0);
                }
            }
        }
        System.exit(0);
    }

    public void writeHeadersCanvas(BufferedWriter bufferedWriter, int listSize) {

    }

    public void writeHeaders(BufferedWriter bufferedWriter) {

    }

    public int[] calcHalfTiles(double TileHeight, double TileWidth) {
        int[] halfTiles = new int[2];

        for (int i = 0; i < halfTiles.length; i++) {
            halfTiles[i] = 0;
        }

        halfTiles[0] += 2 * (TileWidth / 1.75);
        halfTiles[1] += 2 * Math.ceil(TileHeight / 1.2);

        return halfTiles;
    }

    public int calcFullTiles(double TileHeight, double TileWidth) {
        int fullTiles = 0;

        int oddRows = (int) Math.ceil(TileHeight / 1.2);
        int evenRows = (int)(TileHeight / 0.6) - oddRows;

        fullTiles += oddRows * ((TileWidth / 1.75) - 1);
        fullTiles += evenRows * (TileWidth /1.75);

        return fullTiles;
    }

    public int calcStickyEnds(double TileHeight, double TileWidth) {
        int stickyEnds = 0;

        stickyEnds += 2 * Math.ceil(TileHeight / 1.2);

        return stickyEnds;
    }

    public int calcTotalNumberSeq(int[] halfTiles, int fullTiles, int stickyEnds) {
        int totalNumberSeq = 0;

        for (int i = 0; i < halfTiles.length; i++) {
            totalNumberSeq += halfTiles[i];
        }

        totalNumberSeq = totalNumberSeq / 2;
        totalNumberSeq += fullTiles;
        totalNumberSeq += stickyEnds / 2;

        return totalNumberSeq;
    }
}


