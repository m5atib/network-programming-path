/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netpro1;

import java.io.*;
import java.security.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.*;

/**
 *
 * @author Software Engineer: Muhaimen Khatib
 */
class Grinder implements Callable<Void> {

    String filePath;

    public Grinder(String filePath) {
        this.filePath = filePath;
    }

    public Void call() throws Exception {
        int sum = 0;
        BufferedReader bufin = new BufferedReader(new FileReader(filePath));
        String line = "";
        while ((line = bufin.readLine()) != null) {
            System.out.println(filePath + " : " + line );
            sum += Integer.parseInt(line);
        }
        //return sum;
        return null;
    }

}

class Digester implements Runnable {

    String filePath;
    int arr[];
    int runno, sum;
    volatile public boolean isFinished = false;
    NetPro1 obj;

    public Digester(int runno, int arr[], String filePath, NetPro1 obj) {
        this.filePath = filePath;
        this.arr = arr;
        this.runno = runno;
        this.obj = obj;
    }

    @Override
    public void run() {

        BufferedReader bufin;

        try {
            bufin = new BufferedReader(new FileReader(filePath));
            String line = "";
            while ((line = bufin.readLine()) != null) {
                sum += Integer.parseInt(line);
            }
            //isFinished = true;
            NetPro1.maxSum(sum);
            //obj.getInfoRef(sum, filePath);
            bufin.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

        }

    }

    public String getFilePath() {
        return filePath;
    }

    public int[] getArr() {
        return arr;
    }

    public int getRunno() {
        return runno;
    }

    public int getSum() {
        return sum;
    }

    public boolean isIsFinished() {
        return isFinished;
    }

}

public class NetPro1 {

    /**
     * @param args the command line arguments
     */
    static int c = 0;
    static int max;
    int counter = 0;

    public static void main(String[] args) throws Exception {
        // TODO code application logic here
//        String outFile = "src/TestingText/writingData.txt";
//        String inFile = "src/TestingText/readingData.txt";
//
//        writeFile("src/TestingText/file1.txt");
//        writeFile("src/TestingText/file2.txt");
//        writeFile("src/TestingText/file3.txt");
//
//        NetPro1 obj = new NetPro1();
//
//        //System.out.println(Arrays.toString(array1));
//        Digester obj1 = new Digester(1, null, "src/TestingText/file1.txt", obj);
//        Digester obj2 = new Digester(2, null, "src/TestingText/file2.txt", obj);
//        Digester obj3 = new Digester(3, null, "src/TestingText/file3.txt", obj);
//
//        Thread dig1 = new Thread(obj1);
//        Thread dig2 = new Thread(obj2);
//        Thread dig3 = new Thread(obj3);
//
//        dig1.start();
//        dig2.start();
//        dig3.start();
//
//        while (!obj1.isFinished || !obj2.isFinished || !obj3.isFinished);
//        System.out.println("sum ob1 = " + obj1.sum);
//        System.out.println("sum ob2 = " + obj2.sum);
//        System.out.println("sum ob3 = " + obj3.sum);

          //Initialize an objects of callable inerfacing Grinder class
          int numOfthreads = 3;
          Grinder g1 = new Grinder ("src/TestingText/file1.txt");
          Grinder g2 = new Grinder ("src/TestingText/file2.txt");
          Grinder g3 = new Grinder ("src/TestingText/file3.txt");
          
          //Reserve a number of pool executors
          ExecutorService service = Executors.newFixedThreadPool(numOfthreads);
          
          //Making threading processes using Future class to submit grinder objects togother
          
          Future <Void> x1 = service.submit(g1);
          Future <Void> x2 = service.submit(g2);
          Future <Void> x3 = service.submit(g3);
          
          //ready to get the data from processes
          
//          System.out.println(x1.get());
//          System.out.println(x2.get());
//          System.out.println(x3.get());
//          
          
          //Finishing all processes -Done
          service.shutdown();
          
    }

    public static void readFile(String fileName) throws IOException {
        BufferedReader bufin = new BufferedReader(new FileReader(fileName));
        String line = "";
        while ((line = bufin.readLine()) != null) {
            System.out.println(line);
        }
    }

    public static void writeFile(String fileName) throws IOException {
        Random random = new Random();
        int randomlyNums[] = random.ints(10000, 10, 500).toArray();
        //System.out.println("sum of " + fileName + " = " + sumOfarray(randomlyNums));
        BufferedWriter bufout = new BufferedWriter(new FileWriter(fileName));

        for (int i = 0; i < 10000; i++) {
            bufout.write(randomlyNums[i] + "\n");
        }
        bufout.flush();
        bufout.close();

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

    public static void maxSum(int sum) {
        if (c++ == 0) {
            max = sum;
        } else if (sum > max) {
            max = sum;
        } else if (c == 3) {
            System.out.println("the max = " + max);
        }
    }

    public static int sumOfarray(int arr[]) {

        int sum = 0;
        for (int x : arr) {
            sum += x;
        }
        return sum;
    }

    public void getInfoRef(int s, String name) {
        counter++;
        System.out.println(name + "\t" + s);

        if (counter == 3) {
            System.out.println("done ref method");
        }

        System.out.println("counter : " + counter);
    }
}
