/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netpro1;

import java.io.*;
import java.net.*;
import java.security.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.*;

/**
 *
 * @author Software Engineer: Muhaimen Khatib
 */
class AllIPsByName implements Callable<InetAddress[]> {

    String link;

    public AllIPsByName(String link) {
        this.link = link;
    }

    public synchronized InetAddress[] call() throws Exception {
        return InetAddress.getAllByName(link);
    }
}

class Grinder implements Callable<ArrayList<AllIPsByName>> {

    String filePath;

    public Grinder(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<AllIPsByName> call() throws Exception {
        ArrayList<AllIPsByName> links = new ArrayList<AllIPsByName>();

        return links;
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

//          //Initialize an objects of callable inerfacing Grinder class
//          int numOfthreads = 3;
//          Grinder g1 = new Grinder ("src/TestingText/file1.txt");
//          Grinder g2 = new Grinder ("src/TestingText/file2.txt");
//          Grinder g3 = new Grinder ("src/TestingText/file3.txt");
//          
//          //Reserve a number of pool executors
//          ExecutorService service = Executors.newFixedThreadPool(numOfthreads);
//          
//          //Making threading processes using Future class to submit grinder objects togother
//          
//          Future <Integer> x1 = service.submit(g1);
//          Future <Integer> x2 = service.submit(g2);
//          Future <Integer> x3 = service.submit(g3);
//          
//          //ready to get the data from processes
//          
//          System.out.println(x1.get());
//          System.out.println(x2.get());
//          System.out.println(x3.get());
//          
//          
//          //Finishing all processes -Done
//          service.shutdown();
        // //IP = 192.168.1.1
//            InetAddress ina[] = InetAddress.getAllByName("www.netflix.com");
//            
//            for (InetAddress ip:ina)
//                System.out.println(ip.getHostAddress());
//        ArrayList<AllIPsByName> links = new ArrayList<AllIPsByName>();
//        int sum = 0;
//        BufferedReader bufin = new BufferedReader(new FileReader("src/TestingText/readingData.txt"));
//        String line = "";
//        while ((line = bufin.readLine()) != null) {
//            links.add(new AllIPsByName(line));
//        }
//
//        //1- objects<> 2- pool() 3- futures<>
//        ExecutorService svc = Executors.newFixedThreadPool(links.size());
//
//        ArrayList<Future> tasks = new ArrayList<Future>();
//
//        for (AllIPsByName ipsOflink : links) {
//            tasks.add(svc.submit(ipsOflink));
//        }
//        int numObjects = 0;
//        for (Future task : tasks) {
//            InetAddress[] iness = (InetAddress[]) task.get();
//            System.out.println('\n' + links.get(numObjects).link + '\n' + "#___________________________#");
//            System.out.println();
//            for (InetAddress ip : iness) {
//                System.out.println(ip.getHostAddress());
//            }
//            numObjects++;
//        }
//        
//
//        svc.shutdown();
        byte addr[] = new byte[]{(byte) 192, (byte) 168, 1, 103};
        NetworkInterface ni = NetworkInterface.getByInetAddress(InetAddress.getByAddress(addr));
        System.out.println(ni.getDisplayName());

//        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
//        NetworkInterface inter;
//        while (interfaces.hasMoreElements()) {
//            inter = interfaces.nextElement();
//            System.out.println(inter.getInterfaceAddresses() );
//        }
        
        System.out.println(NetworkInterface.getByIndex(12).getDisplayName());
    }

    public static void readFile(String fileName) throws Exception {

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

        for (int i = start; i < start + numberOfCharactersPerLine; i++) {
            out.write(s1);
            out.write('\r');
            out.write('\n');
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
