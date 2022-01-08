/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netpro1;

import java.io.*;

/**
 *
 * @author Admin
 */
public class NetPro1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        generateCharacters(new FileOutputStream("src/TestingText/t1.txt"));

    }
    
    public static void generateCharacters(OutputStream out) throws Exception {
        int firstPrintableCharacter = 33;
        int numberOfPrintableCharacters = 94;
        int numberOfCharactersPerLine = 72;
        int start = firstPrintableCharacter;
        byte s1[] = {97,98,99};
        //the same as (char)97
        /* infinite loop */
        for (int i = start; i < start + numberOfCharactersPerLine; i++) {
            out.write(s1);
            //out.write('\r'); // carriage return
            out.write('\n'); // linefeed
            
        }
    }
}
