
/**
 *
 * @author Kyle
 * 
 * This class acts as a driver initializing the threads
 * and then using ThreadsPrimes methods to get the prime factors and 
 * coming up with an average time for the threads. The average time as well as 
 * the results of the prime factors are all written to a log file.
 * 
 */
public class ThreadsDriver {
   
    
    public static void main(String args[]) throws Exception {
        
        //Initialize our worker threads
        ThreadsPrimes Worker1 = new ThreadsPrimes("Worker1");
        ThreadsPrimes Worker2 = new ThreadsPrimes("Worker2");
        ThreadsPrimes Worker3 = new ThreadsPrimes("Worker3");
        ThreadsPrimes Worker4 = new ThreadsPrimes("Worker4");
        ThreadsPrimes Worker5 = new ThreadsPrimes("Worker5");
        
        //Start threads
        Worker1.start();
        Worker2.start();
        Worker3.start();
        Worker4.start();
        Worker5.start();
        
        Thread.sleep(30000);//Wait until the threads are finished
        
        //Get the average time for the workers
        double averageTime = ((Worker1.threadTimeTaken + Worker2.threadTimeTaken + Worker3.threadTimeTaken + Worker4.threadTimeTaken + Worker5.threadTimeTaken)/5);
        
        //Print average time
        String message = "Average Time Taken was: " + averageTime;
        System.out.println(message);
        
        //Write average time to logFile
        ThreadsPrimes.logFileWriter.write(message);
        ThreadsPrimes.logFileWriter.close();
        
    }
    
    
}
