

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kyle
 * This class contains the methods used by ThreadsDriver to get the prime factors. 
 * Using system time we get the time taken for 5 worker threads to execute a getPrimes 
 * method which requires a large number of calculations and processor power.
 * 
 */

public class ThreadsPrimes implements Runnable {

    private Thread t;//Necessary variable for thread creation.
    private String threadName;
    private int firstNumber;//Threads initial number to find prime factors of.
    private int lastNumber;//Threads last number to find prime factors of.
    private double threadStartTime;
    private double threadEndTime;
    public double threadTimeTaken;
    
    /**
     * Following variables are all vital for writing to log file
     */
    private static String logFile;
    public static BufferedWriter logFileWriter; 
    private static File logFileObj; 
    private static FileWriter fileWriter;
    
    //Based on which worker is being initialized, assign the appropriate numbers.
    ThreadsPrimes(String name) {
        
        threadName = name;
        
        switch (threadName) {
            case "Worker1":
                firstNumber = 1000000000;
                lastNumber = 1000000004;
                break;

            case "Worker2":
                firstNumber = 1000000005;
                lastNumber = 1000000009;
                break;

            case "Worker3":
                firstNumber = 1000000010;
                lastNumber = 1000000014;
                break;

            case "Worker4":
                firstNumber = 1000000015;
                lastNumber = 1000000019;
                break;

            case "Worker5":
                firstNumber = 1000000020;
                lastNumber = 1000000024;
                break;

        }
        logFile = "ThreadsResults.txt";//Create our logFile
        logFileObj = new File(logFile);
        try {
            fileWriter = new FileWriter(logFileObj);//Create object which writes to logFile
            logFileWriter = new BufferedWriter(fileWriter);
        } catch (IOException e) {
        }
        System.out.println("Creating " + threadName + " Range is: " + firstNumber + " " + lastNumber);
    }

    @Override
    /**
     * This run method will get the system time and compare it the the system time after 
     * it's set of numbers have been factored. The time will then be converted to seconds 
     * and we will have the worker's time.
     * 
     */
    public void run() {
        
        
        threadStartTime = System.currentTimeMillis();//Initialize our timer
        
        //Each number in the set is passed to findPrimes()
        for (int i = firstNumber; i <= lastNumber; i++) {
            String message = "" + i + " contains the prime factors " + findPrimes(i);
            System.out.println(threadName + " " + message);
        
            try {
                //Write to log file results of findPrimes()
                logFileWriter.write(message);
                logFileWriter.newLine();
                logFileWriter.flush();
                
                
            } catch (IOException ex) {
                Logger.getLogger(ThreadsPrimes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //Stop timer after each number in thread has been calculated 
        threadEndTime = System.currentTimeMillis();
        threadTimeTaken = ((threadEndTime - threadStartTime) / 1000);//Convert to seconds time taken for thread
        System.out.println("Time taken: for " + threadName + " was: " + threadTimeTaken);
        
        
        
            try {
                logFileWriter.write(""+threadTimeTaken+ " seconds.");//Write to logFile time taken
                logFileWriter.newLine();
            } catch (IOException ex) {
                Logger.getLogger(ThreadsPrimes.class.getName()).log(Level.SEVERE, null, ex);
            }

    }
    /**
     * This method creates a new thread object, then runs it.
     */
    public void start() {
        System.out.println("Starting " + threadName);
        
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();

        }

    }

    /**
     * This method gets the prime factors for a single number.
     * It incrementally checks for factors starting from 2, and when a factor is found,
     * it cuts the number by that factor. Each factor found is added to a hashSet which 
     * is similar to an array except it doesn't support duplicate values as intended.
     * 
     * @param number the number being calculated
     * @return primeSet the HashSet containing the primeFactors for number.
     */
    public static HashSet findPrimes(int number) {

        HashSet primeSet = new HashSet();

        for (int i = 2; i <= number; i++) {
            if (number % i == 0) {
                primeSet.add(i);
                number /= i;
                i--;
            }
        }

        return primeSet;

    }
    
}
