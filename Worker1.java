

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.HashSet;
import java.util.Set;


/**
 *
 * @author Kyle
 * This class will be converted into a runnable Jar file by the command line that will act as an individual process. 
 * When it is run, it will take the system time before calculating the prime factors for its set of numbers. 
 * When it is finished, it will take the system time again and with both, find the time it took in seconds. All of
 * the calculations will be written to a log file.
 * 
 */
public class Worker1 {
    
    //Variables for calculating worker time
    public static double startTime;
    public static double endTime;
    
    //Set of numbers that the worker contains
    public static int firstNumber;
    public static int lastNumber;

    private static String logFile = "worker1Results.txt";//Worker will log to here
    private static BufferedWriter logFileWriter;//Log Writing object

    

    public static void main(String[] args) throws Exception {
        
        //Initialize our logFile Writing object
        File logFileObj = new File(logFile);
        try {
            FileWriter fileWriter = new FileWriter(logFileObj);
            logFileWriter = new BufferedWriter(fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //The numbers that our worker is going to factor
        firstNumber = 1000000000;
        lastNumber =   1000000009;

        startTime = System.currentTimeMillis();//Get current system time as a start time
        
        //For each number in our set, get the prime factors
        for (int i = firstNumber; i <= lastNumber; i++) {

            String message = "" + i + " contains the prime factors " + findPrimes(i);//Passing number to findPrimes()
            System.out.println(message);//Output the message
            logFileWriter.write(message);//Log message
            logFileWriter.newLine();
        }

        endTime = System.currentTimeMillis();//After all numbers have been factored, get system time

        double timeTaken = ((endTime - startTime) / 1000);//Calculate the timeTaken, converting to seconds
        logFileWriter.write("" + timeTaken + " seconds.");//Log time taken
        
        //Flush and close
        logFileWriter.flush();
        logFileWriter.close();

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

        HashSet hs = new HashSet();

        for (int i = 2; i <= number; i++) {
            if (number % i == 0) {
                hs.add(i);
                number /= i;
                i--;

            }
        }
        return hs;

    }

}
