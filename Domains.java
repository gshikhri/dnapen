/*
 * User: Foram Joshi and Shikhar K Gupta
 * Mentor: Manish K Gupta
 */
public class Domains {
    public int x;
    public int y;
    public String domainSeqOne;
    public String domainSeqTwo;
    public String domainSeqThree;
    public String domainSeqFour;
    public String CompleteDomainSeq;
    public boolean isHalf=false;
    public boolean isChanged=false;
    public boolean isCentralTile=false;

    public Domains(int x, int y, String domainSeqOne,String domainSeqTwo, String domainSeqThree,String domainSeqFour, boolean halfTile) {
        this.x = x;
        this.y = y;
        this.domainSeqOne = domainSeqOne;
        this.domainSeqTwo = domainSeqTwo;
        this.domainSeqThree = domainSeqThree;
        this.domainSeqFour = domainSeqFour;
        this.isHalf=halfTile;
    }

    public int getxCoordinate() {
        return x;
    }

    public int getyCoordinate() {
        return y;
    }

    public String getDomainOne() {
        return domainSeqOne;
    }

    public String getDomainTwo() {
        return domainSeqTwo;
    }
    public String getDomainThree() {
        return domainSeqThree;
    }

    public String getDomainFour() {
        return domainSeqFour;
    }

}
