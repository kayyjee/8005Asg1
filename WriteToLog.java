
/**
 *
 * @author Kyle
 * This program will read from the 5 worker log files and write the contents into a neat master log file. 
 * It stores the contents of the worker log files in arrays. It then reads the time taken for each worker
 * to execute and compares the values to come up with an average time. All of this is then written to the master
 * log file.
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.ProcessBuilder;

public class WriteToLog {

    static int numbersPerWorker = 10;//Change this variable depending on the amount of numbers per worker
    
    //Storing the contents of the workers log files into an array
    static String[] file1Contents = new String[numbersPerWorker + 1];
    static String[] file2Contents = new String[numbersPerWorker + 1];
    static String[] file3Contents = new String[numbersPerWorker + 1];
    static String[] file4Contents = new String[numbersPerWorker + 1];
    static String[] file5Contents = new String[numbersPerWorker + 1];
    
    //BufferedReader objects will read the log file contents
    static BufferedReader in;
    static BufferedReader in2;
    static BufferedReader in3;
    static BufferedReader in4;
    static BufferedReader in5;
    
    private static String logFile = "ProcessesMaster.txt";//Master log file will contain all worker logs
    private static BufferedWriter logFileWriter;//LogWriter object will write to log file

    public static void main(String[] args) throws IOException, InterruptedException {
        
        //The locations of the 5 worker log files
        String file1 = "C:\\Asg1\\src\\worker1Results.txt";
        String file2 = "C:\\Asg1\\src\\worker2Results.txt";
        String file3 = "C:\\Asg1\\src\\worker3Results.txt";
        String file4 = "C:\\Asg1\\src\\worker4Results.txt";
        String file5 = "C:\\Asg1\\src\\worker5Results.txt";
        
        //Initialize Reader objects which will read log file contents
        in = new BufferedReader(new FileReader(file1));
        in2 = new BufferedReader(new FileReader(file2));
        in3 = new BufferedReader(new FileReader(file3));
        in4 = new BufferedReader(new FileReader(file4));
        in5 = new BufferedReader(new FileReader(file5));
        
        //Read line by line, contents of log files and store in array
        for (int i = 0; i < (numbersPerWorker + 1); i++) {
            file1Contents[i] = in.readLine();
            file2Contents[i] = in2.readLine();
            file3Contents[i] = in3.readLine();
            file4Contents[i] = in4.readLine();
            file5Contents[i] = in5.readLine();
        }
        
        //Last line of worker log files contains the time taken. Store that value as a seperate variable.
        Double worker1Time = Double.parseDouble(file1Contents[numbersPerWorker].toString().substring(0, 5));
        Double worker2Time = Double.parseDouble(file2Contents[numbersPerWorker].toString().substring(0, 5));
        Double worker3Time = Double.parseDouble(file3Contents[numbersPerWorker].toString().substring(0, 5));
        Double worker4Time = Double.parseDouble(file4Contents[numbersPerWorker].toString().substring(0, 5));
        Double worker5Time = Double.parseDouble(file5Contents[numbersPerWorker].toString().substring(0, 5));
        
        //Get the average of the workes
        Double averageTime = ((worker1Time + worker2Time + worker3Time + worker4Time + worker5Time) / 5);
        
        //Pass to our writing method
        WriteToLogFile(averageTime);
        
        //Output the average time
        System.out.println("The average time taken for " + numbersPerWorker + " workers to execute was: " + averageTime + " seconds.");

    }
    
    /**
     * This method will write the contents of the worker log files neatly into a master log file.
     * It will also write the time taken for each worker as well as the average time for each.
     * 
     * @param averageTime
     * @throws IOException 
     */
    public static void WriteToLogFile(Double averageTime) throws IOException {

        File logFileObj = new File(logFile);//Create the log file
        try {
            //Initialize fileWriter object
            FileWriter fileWriter = new FileWriter(logFileObj);
            logFileWriter = new BufferedWriter(fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        logFileWriter.write("Results for Worker 1: ");//Writing contents of worker1
        logFileWriter.newLine();
        for (int i = 0; i < file1Contents.length; i++) {
            logFileWriter.write(file1Contents[i]);
            logFileWriter.newLine();
        }
        logFileWriter.newLine();
        logFileWriter.write("Results for Worker 2: ");//Writing contents of worker2
        logFileWriter.newLine();
        for (int i = 0; i < file1Contents.length; i++) {
            logFileWriter.write(file2Contents[i]);
            logFileWriter.newLine();
        }
        logFileWriter.newLine();
        logFileWriter.write("Results for Worker 3: ");//Writing contents of worker1
        logFileWriter.newLine();
        for (int i = 0; i < file1Contents.length; i++) {
            logFileWriter.write(file3Contents[i]);
            logFileWriter.newLine();
        }
        logFileWriter.newLine();
        logFileWriter.write("Results for Worker 4: ");//Writing contents of worker1
        logFileWriter.newLine();
        for (int i = 0; i < file1Contents.length; i++) {
            logFileWriter.write(file4Contents[i]);
            logFileWriter.newLine();
        }
        logFileWriter.newLine();
        logFileWriter.write("Results for Worker 5: ");//Writing contents of worker1
        logFileWriter.newLine();
        for (int i = 0; i < file1Contents.length; i++) {
            logFileWriter.write(file5Contents[i]);
            logFileWriter.newLine();
        }
        logFileWriter.newLine();
        logFileWriter.write("Average time taken was: " + averageTime + " seconds.");//Writing average time
        
        //Flush and close
        logFileWriter.flush();
        logFileWriter.close();
    }
}

