/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netpro1;

import java.io.*;
import java.security.*;
import java.util.*;

/**
 *
 * @author Software Engineer: Muhaimen Khatib
 */
class Digester implements Runnable {

    String filePath;
    int arr[];
    int runno;

    public Digester(int runno, int arr[], String filePath) {
        this.filePath = filePath;
        this.arr = arr;
        this.runno = runno;
    }

    @Override
    public void run() {
       
        for (int x : arr) {
            System.out.println(runno + " : " + x);
        }

    }

}

public class NetPro1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        String outFile = "src/TestingText/writingData.txt";
        String inFile = "src/TestingText/readingData.txt";

        BufferedWriter bufout = new BufferedWriter(new FileWriter(outFile));

        Random random = new Random();

        int[] array1 = random.ints(15, 10, 51).toArray();
        int[] array2 = random.ints(15, 10, 51).toArray();
        int[] array3 = random.ints(15, 10, 51).toArray();
        
        Thread dig1 = new Thread(new Digester(1,array1,""));
        Thread dig2 = new Thread(new Digester(2,array2,""));
        Thread dig3 = new Thread(new Digester(3,array3,""));
        
        dig1.start(); 
        dig2.start();
        dig3.start();
        
    }

    public static void printArray(int array[]) {
        System.out.print("[ ");
        for (int x : array) {
            System.out.print(x + ", ");
        }
        System.out.println(" ]");
        //System.out.println(array.length);
    }

    public static void readFile(String fileName) throws IOException {
        BufferedReader bufin = new BufferedReader(new FileReader(fileName));
        String line = "";
        while ((line = bufin.readLine()) != null) {
            System.out.println(line);
        }
    }

    public static void generateCharacters(OutputStream out) throws Exception {
        int firstPrintableCharacter = 33;
        int numberOfPrintableCharacters = 94;
        int numberOfCharactersPerLine = 72;
        int start = firstPrintableCharacter;
        byte s1[] = {97, 98, 99};
        //the same as (char)97
        /* infinite loop */
        for (int i = start; i < start + numberOfCharactersPerLine; i++) {
            out.write(s1);
            out.write('\r'); // carriage return
            out.write('\n'); // linefeed

        }
    }
}
