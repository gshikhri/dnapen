/* Author: Arnav Goyal and Akshita Sahai
 * Project: DNA Pen
 * Mentor: Prof. Manish K Gupta
 */

/* This class creates dnaTiles DNA Tile as described in the referred Paper.
 * It takes the Height and Width as inputted by the user and creates dnaTiles DNA Tile
 * A couple of mathematical assumptions have been taken.
 * These assumptions have been drawn from the Tile Structure given in the paper
 * for the default 3nm * 7nm Tile and have been used to create Tiles of any other dimension
 *
 * Note: 3nm * 7nm is the smallest possible dimension.
 */

public class DNABrick {
    public double TileWidth;
    public double TileHeight;
    public int minWidth;
    public int minHeight;
    public DNADomains dnaDomains;

    public Object[][] middleU;
    public StickyEnd leftStickyEnd;
    public StickyEnd rightStickyEnd;

    public DNABrick(double TileHeight,double TileWidth) {
        this.TileHeight = TileHeight;
        this.TileWidth = TileWidth;

        minWidth = (int)(TileWidth / 1.75);
        minHeight = (int)(TileHeight / .6);


        middleU = new Object[minHeight][minWidth + 1];

        GenerateDNAFile gen = new GenerateDNAFile();

    }


    public double getTileWidth() {
        return TileWidth;
    }

    public double getTileHeight() {
        return TileHeight;
    }

}
