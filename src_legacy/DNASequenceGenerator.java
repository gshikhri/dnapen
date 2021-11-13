/* Author: Arnav Goyal, Foram Joshi, Shikhar K Gupta and Akshita Sahai
 * Project: DNA Pen
 * Mentor: Prof. Manish K Gupta
 */

import java.util.Random;

public class DNASequenceGenerator {

    String before;
    String now1;
    String now2;
    String now3;
    String randomDNASeq;
    String dnaAlphabet = "ACGT";

    public DNASequenceGenerator (int seqLength,String x) {
        this.randomDNASeq = "";
        Random randomSeq = new Random();
        if(x.equals("P")){
            if(seqLength==10)
                randomDNASeq = new String("TTTTTTTTTT");
            else
                randomDNASeq = new String("TTTTTTTTTTT");
        }
        else{
            
                if(x.equals("A")==true)
                    this.randomDNASeq += "A";
                
                else if(x.equals("T")==true)
                    this.randomDNASeq += "T";
                
                else if(x.equals("C")==true)
                    this.randomDNASeq += "C";
                
                else if(x.equals("G")==true)
                    this.randomDNASeq += "G";
             
           
            for (int i = 1; i < seqLength-1; i++) {

                now1=new String(String.valueOf(this.dnaAlphabet.charAt(randomSeq.nextInt(this.dnaAlphabet.length())))) ;
                this.randomDNASeq += now1;
                i++;
                before=new String(String.valueOf(now1));
                now2=new String(String.valueOf(this.dnaAlphabet.charAt(randomSeq.nextInt(this.dnaAlphabet.length())))) ;
                while((now1.equals(before))&&(now2.equals(before))){
                    now2=new String(String.valueOf(this.dnaAlphabet.charAt(randomSeq.nextInt(this.dnaAlphabet.length())))) ;
                }
                if(i==10){
                	if(x.equals("A")==true)
                        this.randomDNASeq += "A";
                    
                    else if(x.equals("T")==true)
                        this.randomDNASeq += "T";
                    
                    else if(x.equals("C")==true)
                        this.randomDNASeq += "C";
                    
                    else if(x.equals("G")==true)
                        this.randomDNASeq += "G";
                }
                else
                    this.randomDNASeq += now2;
            }
            if(seqLength==10){
            	if(x.equals("A")==true)
                    this.randomDNASeq += "A";
                
                else if(x.equals("T")==true)
                    this.randomDNASeq += "T";
                
                else if(x.equals("C")==true)
                    this.randomDNASeq += "C";
                
                else if(x.equals("G")==true)
                    this.randomDNASeq += "G";
            }
        }
    }


    public  String getT10(){
        return randomDNASeq;
    }

    public String getRandomDNASeq() {
        return randomDNASeq;
    }
}
