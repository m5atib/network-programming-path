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
import java.util.regex.*;

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

class WebsiteToHTML implements Callable<Void> {

    URL link;

    public WebsiteToHTML(URL link) {
        this.link = link;
    }

    public Void call() throws Exception {

        BufferedInputStream br = new BufferedInputStream(link.openStream());

        //BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\Admin\\Desktop\\FALL 2021-2022\\NETWORKS PROGRAMMING\\linksdown\\" + getWebsitename() + ".html"));
        byte data[] = br.readAllBytes();
        //bw.write(new String(data), 0, data.length);
        System.out.println(new String(data));
        //bw.flush();
        //bw.close();
        br.close();

        return null;
    }

    String getWebsitename() {
        
        Pattern pattern = Pattern.compile("((\\.).+?(\\.))");
        
        Matcher matcher = pattern.matcher(link.getHost());
        
        boolean found = false;
        //if (!found) return "notFound"+Math.random(); 
        matcher.find();
        String str = matcher.group();
        return str.substring(1,str.length()-1);
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
        ArrayList<URL> links = readFile("src\\TestingText\\readingData.txt");

        ExecutorService svc = Executors.newFixedThreadPool(links.size());

        ArrayList<Future> tasks = new ArrayList<Future>();

        for (URL url : links) {
            tasks.add(svc.submit(new WebsiteToHTML(url)));
        }

        svc.shutdown();
    }

    public static ArrayList<URL> readFile(String fileName) throws Exception {

        ArrayList<URL> links = new ArrayList<URL>();
        BufferedReader bufin = new BufferedReader(new FileReader(fileName));
        String line = "";
        while ((line = bufin.readLine()) != null) {
            links.add(new URL(line));
        }
        return links;

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

class CC {

    // Reset
    public static final String RESET = "\033[0m";  // Text Reset

    // Regular Colors
    public static final String BLACK = "\033[0;30m";   // BLACK
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String PURPLE = "\033[0;35m";  // PURPLE
    public static final String CYAN = "\033[0;36m";    // CYAN
    public static final String WHITE = "\033[0;37m";   // WHITE

    // Bold
    public static final String BLACK_BOLD = "\033[1;30m";  // BLACK
    public static final String RED_BOLD = "\033[1;31m";    // RED
    public static final String GREEN_BOLD = "\033[1;32m";  // GREEN
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
    public static final String BLUE_BOLD = "\033[1;34m";   // BLUE
    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
    public static final String CYAN_BOLD = "\033[1;36m";   // CYAN
    public static final String WHITE_BOLD = "\033[1;37m";  // WHITE

    // Underline
    public static final String BLACK_UNDERLINED = "\033[4;30m";  // BLACK
    public static final String RED_UNDERLINED = "\033[4;31m";    // RED
    public static final String GREEN_UNDERLINED = "\033[4;32m";  // GREEN
    public static final String YELLOW_UNDERLINED = "\033[4;33m"; // YELLOW
    public static final String BLUE_UNDERLINED = "\033[4;34m";   // BLUE
    public static final String PURPLE_UNDERLINED = "\033[4;35m"; // PURPLE
    public static final String CYAN_UNDERLINED = "\033[4;36m";   // CYAN
    public static final String WHITE_UNDERLINED = "\033[4;37m";  // WHITE

    // Background
    public static final String BLACK_BACKGROUND = "\033[40m";  // BLACK
    public static final String RED_BACKGROUND = "\033[41m";    // RED
    public static final String GREEN_BACKGROUND = "\033[42m";  // GREEN
    public static final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW
    public static final String BLUE_BACKGROUND = "\033[44m";   // BLUE
    public static final String PURPLE_BACKGROUND = "\033[45m"; // PURPLE
    public static final String CYAN_BACKGROUND = "\033[46m";   // CYAN
    public static final String WHITE_BACKGROUND = "\033[47m";  // WHITE

    // High Intensity
    public static final String BLACK_BRIGHT = "\033[0;90m";  // BLACK
    public static final String RED_BRIGHT = "\033[0;91m";    // RED
    public static final String GREEN_BRIGHT = "\033[0;92m";  // GREEN
    public static final String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW
    public static final String BLUE_BRIGHT = "\033[0;94m";   // BLUE
    public static final String PURPLE_BRIGHT = "\033[0;95m"; // PURPLE
    public static final String CYAN_BRIGHT = "\033[0;96m";   // CYAN
    public static final String WHITE_BRIGHT = "\033[0;97m";  // WHITE

    // Bold High Intensity
    public static final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
    public static final String RED_BOLD_BRIGHT = "\033[1;91m";   // RED
    public static final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
    public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
    public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";  // BLUE
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
    public static final String CYAN_BOLD_BRIGHT = "\033[1;96m";  // CYAN
    public static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE

    // High Intensity backgrounds
    public static final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";// BLACK
    public static final String RED_BACKGROUND_BRIGHT = "\033[0;101m";// RED
    public static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";// GREEN
    public static final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";// YELLOW
    public static final String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";// BLUE
    public static final String PURPLE_BACKGROUND_BRIGHT = "\033[0;105m"; // PURPLE
    public static final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m";  // CYAN
    public static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m";   // WHITE
}
